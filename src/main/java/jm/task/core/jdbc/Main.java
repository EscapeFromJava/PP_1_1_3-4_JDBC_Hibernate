package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Aleksey", "Petrov", (byte) 30);
        userService.saveUser("Elena", "Ivanova", (byte) 23);
        userService.saveUser("Marina", "Egorova", (byte) 33);
        userService.saveUser("Evgeniy", "Smirnov", (byte) 41);
        userService.removeUserById(3);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
