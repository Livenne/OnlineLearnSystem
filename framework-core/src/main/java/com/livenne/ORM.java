package com.livenne;


import com.livenne.annotation.*;
import com.livenne.utils.*;
import lombok.SneakyThrows;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class ORM {

    private static final String URL = "jdbc:mysql://localhost:3306/dev";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final Integer BASE_CONNECTION_POOL_SIZE = 5;

    private static LinkedList<Connection> connectionPool;

    @SneakyThrows
    public static void initialization() {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connectionPool = new LinkedList<>();
        for (int i = 0; i < BASE_CONNECTION_POOL_SIZE; i++) {
            connectionPool.add(DriverManager.getConnection(URL, USERNAME, PASSWORD));
        }
    }

    public static ResultSet sqlExecutor(SqlFunction<Connection,ResultSet> operation){
        Connection connection = getConnection();
        ResultSet result = null;
        try {
            result = operation.apply(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnConnection(connection);
        return result;
    }

    @SneakyThrows
    private static Connection getConnection() {
        if (connectionPool.isEmpty()) {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        Connection conn = connectionPool.poll();
        if (conn == null){
            return getConnection();
        }
        return conn;
    }

    private static void returnConnection(Connection connection) {
        connectionPool.offer(connection);
    }

    @SneakyThrows
    public static void automationDDL(Set<Class<?>> entityList){
        for (Class<?> entity : entityList) {
            String tableName = entity.getAnnotation(Entity.class).value();
            ResultSet checkTable = sqlExecutor(conn -> {
                DatabaseMetaData metaData = conn.getMetaData();
                return metaData.getTables(null, null, tableName, new String[]{"TABLE"});
            });
            if (checkTable.next()){
                for (Field field : entity.getDeclaredFields()) {
                    String fieldName = field.getName();
                    ResultSet checkColumn = sqlExecutor(conn -> {
                        DatabaseMetaData metaData = conn.getMetaData();
                        return metaData.getColumns(null, null, tableName, fieldName);
                    });
                    if (!checkColumn.next()){
                        StringJoiner alterColumnSql = new StringJoiner(" ");
                        alterColumnSql
                                .add("ALTER TABLE")
                                .add(tableName)
                                .add("ADD COLUMN")
                                .add(fieldName)
                                .add(field.isAnnotationPresent(ColumnType.class) ?
                                        field.getAnnotation(ColumnType.class).value() :
                                        SQLUtils.typeMatch(field.getType()))
                                .add(";");
                        sqlExecutor(conn->{
                            System.out.println(alterColumnSql.toString());
                            PreparedStatement preparedStatement = conn.prepareStatement(alterColumnSql.toString());
                            preparedStatement.execute();
                            return null;
                        });
                    }
                }
            }else {
                StringJoiner createTableSql = new StringJoiner(",","CREATE TABLE IF NOT EXISTS " + tableName + " (",");");
                for (Field field : entity.getDeclaredFields()) {
                    StringJoiner columnJoiner = new StringJoiner(" ");
                    columnJoiner
                            .add(field.getName())
                            .add(field.isAnnotationPresent(ColumnType.class) ?
                                    field.getAnnotation(ColumnType.class).value() :
                                    SQLUtils.typeMatch(field.getType()))
                            .add(field.isAnnotationPresent(Id.class) ? field.getAnnotation(Id.class).value().getValue() + " PRIMARY KEY" : "");
                    createTableSql.add(columnJoiner.toString());
                }
                sqlExecutor(conn->{
                    System.out.println(createTableSql.toString());
                    PreparedStatement statement = conn.prepareStatement(createTableSql.toString());
                    statement.execute();
                    return null;
                });
            }

        }
    }

    public static Object sqlImplement(Class<?> repoClass) {
        return Proxy.newProxyInstance(
                repoClass.getClassLoader(),
                new Class[]{repoClass},
                (proxy, method, args) -> {
                    if (method.getDeclaringClass() == Object.class) {
                        return switch (method.getName()) {
                            case "hashCode" -> System.identityHashCode(proxy);
                            case "equals" -> {
                                if (args != null && args.length == 1) {
                                    yield proxy == args[0];
                                }
                                yield false;
                            }
                            case "toString" -> "SQLProxy for " + repoClass.getName();
                            default -> method.invoke(proxy, args);
                        };
                    }
                    Type retGenericType = method.getGenericReturnType();
                    Class<?> retType = method.getReturnType();
                    if (!(AnnotationUtils.isAnnotationPresent(repoClass, Repository.class) && method.isAnnotationPresent(SqlExecute.class))) {
                        return ObjectUtils.getDefaultValue(retType);
                    }
                    ResultSet resultSet = sqlExecutor((conn) -> {
                        PreparedStatement statement = conn.prepareStatement(method.getAnnotation(SqlExecute.class).value());
                        if (args != null) {
                            for (int i = 0; i < args.length; i++) {
                                if (ObjectUtils.isBasicType(args[i].getClass())) {
                                    statement.setObject(i+1,args[i]);
                                }
                            }
                        }
                        statement.execute();
                        return statement.getResultSet();
                    });

                    if (retType.equals(void.class)) {
                        return null;
                    }
                    if (Collection.class.isAssignableFrom(retType)) {
                        ArrayList<Object> list = new ArrayList<>();
                        Class<?> entityClass = ClassUtils.getSubType(retGenericType);
                        while (resultSet.next()) {
                            Object entity = entityClass.getConstructor().newInstance();
                            for (Field field : entityClass.getDeclaredFields()) {
                                field.setAccessible(true);
                                field.set(entity, resultSet.getObject(field.getName()));
                            }
                            list.add(entity);
                        }
                        return list;
                    }
                    if (ObjectUtils.isBasicType(retType)) {
                        if (!resultSet.next()) {
                            return ObjectUtils.getDefaultValue(retType);
                        }
                        return resultSet.getObject(1);
                    }
                    Object entity = retType.getConstructor().newInstance();
                    if (!resultSet.next()) return null;
                    for (Field field : retType.getDeclaredFields()) {
                        field.setAccessible(true);
                        field.set(entity, resultSet.getObject(field.getName()));
                    }
                    return entity;
                });
    }
}
