package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/database.properties");
            Properties dataBaseProperties = new Properties();
            dataBaseProperties.load(fileReader);

            Configuration configuration = new Configuration();
            Properties properties = new Properties();

            properties.put(Environment.DRIVER, dataBaseProperties.getProperty("driver"));
            properties.put(Environment.URL, dataBaseProperties.getProperty("url"));
            properties.put(Environment.USER, dataBaseProperties.getProperty("username"));
            properties.put(Environment.PASS, dataBaseProperties.getProperty("password"));
            properties.put(Environment.DIALECT, dataBaseProperties.getProperty("dialect"));
            properties.put(Environment.SHOW_SQL, true);
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            properties.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(properties);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException | IOException e) {
            throw new RuntimeException(e);
        }
        return sessionFactory;
    }
}
