package com.livenne;


import com.livenne.annotation.Entity;
import com.livenne.annotation.Id;
import com.livenne.annotation.Repository;
import com.livenne.annotation.SqlExecute;
import com.livenne.utils.AnnotationUtils;
import com.livenne.utils.ObjectUtils;
import com.livenne.utils.StringUtils;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.LinkedList;
import java.util.Set;

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

    public static void automationDDL(Set<Class<?>> entityList){
        for (Class<?> entity : entityList) {
            StringBuilder createTableSql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + entity.getAnnotation(Entity.class).value() + " ( ");
            for (Field field : entity.getDeclaredFields()) {
                createTableSql
                        .append("\n")
                        .append("    ")
                        .append(field.getName())
                        .append(" ")
                        .append(field.getType().getSimpleName())
                        .append(field.isAnnotationPresent(Id.class) ? " AUTO_INCREMENT PRIMARY KEY " : "")
                        .append(",");
            }
            createTableSql.append("\n").append(" );");
            System.out.println(createTableSql);
        }
    }

    public static Object sqlImplement(Class<?> repoClass) {
        return Proxy.newProxyInstance(
                repoClass.getClassLoader(),
                new Class[]{repoClass},
                (proxy, method, args) -> {
                    Class<?> returnType = method.getReturnType();
                    if (!(AnnotationUtils.isAnnotationPresent(repoClass, Repository.class) && method.isAnnotationPresent(SqlExecute.class))) {
                        return ObjectUtils.getDefaultValue(returnType);
                    }
                    Connection connection = getConnection();
                    Object returnValue = method.invoke(repoClass, args); //TODO arguments
                    returnConnection(connection);
                    return returnValue;
                });
    }
}
