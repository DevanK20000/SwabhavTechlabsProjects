package com.aurionpro.bankmvc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {
    private static DbUtils dbUtils = null;

    private DbUtils() {
    }

    public static DbUtils getDbUtils(){
        if(dbUtils==null)
            dbUtils = new DbUtils();
        return  dbUtils;
    }

    public Connection connectToDb(){
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        String dbName = "banking";
        String dbUsername = "root";
        String dbPassword = "admin";
        try {
            Class.forName(dbDriver);
            Connection conn = DriverManager.getConnection(dbURL + dbName,
                    dbUsername,
                    dbPassword);
            System.out.println("DB connected");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
