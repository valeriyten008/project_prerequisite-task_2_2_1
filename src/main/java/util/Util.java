package util;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/example_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "5854732sirenaA!";
    private static final Logger logger = Logger.getLogger(Util.class.getName());
    private static SessionFactory sessionFactory;

    private Util() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in SessionFactory", e);
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error getting database connection", e);
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

}
