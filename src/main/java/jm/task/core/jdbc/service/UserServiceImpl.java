package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl(Util.getSessionFactory());

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }

    @Override
    public User getUserById(long id) {
        return userDaoHibernate.getUserById(id);
    }

    @Override
    public List<User> getUsersByAgeInterval(Byte min, Byte max) {
        return userDaoHibernate.getUsersByAgeInterval(min, max);
    }

    @Override
    public void updateUserName(long id, String name) {
        userDaoHibernate.updateUserName(id, name);
    }

    @Override
    public void generateRandomUsers(int n) {
        userDaoHibernate.generateRandomUsers(n);
    }

    @Override
    public List<User> getUsersByLastName(String lastName) {
        return userDaoHibernate.getUsersByLastName(lastName);
    }
}
