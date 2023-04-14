package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Util {
    // реализуйте настройку соединения с БД
    private static final String DB = "postgres";
    private static final String SCHEMA = "public";
    private static final String DB_URL = "jdbc:postgresql://31.130.192.23:5432/" + DB
                                        + "?currentSchema=" + SCHEMA;
    private static final String USER = "postgres";
    private static final String PASS  = "postgres";
    private static final String DRIVER  = "org.postgresql.Driver";

    private static ServiceRegistry serviceRegistry;
    private static Connection connection;
    private static SessionFactory sessionFactory = null;

    public static Connection openConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } catch (Exception e) {
                System.out.println("Connection Failed\n" + e);
            }
        }
        return connection;
    }

    public static SessionFactory createSessionFactory() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return (sessionFactory == null) ? createSessionFactory() : sessionFactory;
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            try {
                sessionFactory.close();
            } catch (HibernateException e) {
                e.printStackTrace();
                sessionFactory = null;
            }
        }
    }
}
