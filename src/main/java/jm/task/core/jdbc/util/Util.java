package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String DB = "postgres";
    private static final String SCHEMA = "public";
    private static final String DB_URL = "jdbc:postgresql://31.130.192.23:5432/" + DB
                                        + "?currentSchema=" + SCHEMA;
    private static final String USER = "postgres";
    private static final String PASS  = "postgres";

    public static Connection openConnection() throws SQLException{
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            throw e;
        }
    }
}
