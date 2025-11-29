package com.livenne;


import com.livenne.annotation.Entity;
import com.livenne.annotation.Id;
import com.livenne.utils.StringUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Set;

public class ORM {

    public static Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/dev";
        String user = "root";
        String password = "root";
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "select * from User";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        String json = StringUtils.toJson(StringUtils.resultSetToJsonArray(resultSet));
        System.out.println(json);
    }

    public static void initialization(Set<Class<?>> entityList) {
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
//            System.out.println(createTableSql);
        }
    }

    public static void sqlImplement (Set<Class<?>> entityList) {
    }
}
