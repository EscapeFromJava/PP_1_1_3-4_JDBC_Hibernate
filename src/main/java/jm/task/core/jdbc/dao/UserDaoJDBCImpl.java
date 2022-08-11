package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `users` (" +
                    "`id` BIGINT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NULL," +
                    "`last_name` VARCHAR(45) NULL," +
                    "`age` TINYINT NULL," +
                    "PRIMARY KEY (`id`)" +
                    ");");
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `users`");
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO `users` (`name`, `last_name`, `age`) VALUES ('" + name + "', '" + lastName + "', '" + age + "');");
            connection.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM `users` WHERE (`id` = '" + id + "');");
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM `users`")) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String last_name = resultSet.getString("last_name");
                Byte age = resultSet.getByte("age");
                User user = new User(name, last_name, age);
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE `users`;");
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }
}
