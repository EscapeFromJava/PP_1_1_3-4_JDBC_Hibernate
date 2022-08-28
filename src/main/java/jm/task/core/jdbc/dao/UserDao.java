package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void saveUser(String name, String lastName, byte age);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    User getUserById(long id);

    List<User> getUsersByAgeInterval(Byte min, Byte max);

    void updateUserName(long id, String name);

    void generateRandomUsers(int n);

    List<User> getUsersByLastName(String lastName);

    double getAverageAgeValue();

}
