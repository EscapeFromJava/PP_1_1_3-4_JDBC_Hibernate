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
        String request = "CREATE TABLE `my_db`.`users` (" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT," +
                "  `name` VARCHAR(45) NULL," +
                "  `last_name` VARCHAR(45) NULL," +
                "  `age` TINYINT NULL," +
                "  PRIMARY KEY (`id`));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String request = "DROP TABLE `my_db`.`users`";
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String request = "INSERT INTO `my_db`.`users` (`name`, `last_name`, `age`) VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String request = "DELETE FROM `my_db`.`users` WHERE (`id` = '" + id + "');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String request = "SELECT * FROM `my_db`.`users`";
        List<User> users = new LinkedList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String last_name = resultSet.getString("last_name");
                Byte age = resultSet.getByte("age");
                User user = new User(name, last_name, age);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String request = "TRUNCATE `my_db`.`users`;";
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
        } catch (SQLException e) {
            System.out.println("Request error: " + e.getMessage());
        }
    }
}
