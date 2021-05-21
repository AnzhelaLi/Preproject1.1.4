package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
private static final String DB_USERNAME = "root";
private static final String DB_PASSWORD = "Melody305??!";

private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                //Hibernate settings
                Properties dbSettings = new Properties();

                dbSettings.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb?useSSL=false");
                dbSettings.put(Environment.DRIVER, DB_DRIVER);
                dbSettings.put(Environment.USER, DB_USERNAME);
                dbSettings.put(Environment.PASS, DB_PASSWORD);
                dbSettings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                dbSettings.put(Environment.SHOW_SQL, "true");
                dbSettings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                dbSettings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(dbSettings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


        private static Connection connection = null;
        static
        {
            try {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    public static Connection getConnection() {
        return connection;
    }
}
