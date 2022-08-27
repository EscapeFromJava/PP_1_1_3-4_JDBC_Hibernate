package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Evgeniy", "Smirnov", (byte) 41);
        userService.saveUser("Artem", "Petuhov", (byte) 21);
        userService.saveUser("Aleksey", "Petrov", (byte) 30);
        userService.saveUser("Olga", "Orlova", (byte) 26);
        userService.saveUser("Nikolay", "Eremin", (byte) 31);
        userService.saveUser("Elena", "Ivanova", (byte) 23);
        userService.saveUser("Marina", "Egorova", (byte) 33);
        userService.removeUserById(3);
        userService.getAllUsers().forEach(System.out::println);

//        System.out.println("get user: " + userService.getUserById(1));

//        userService.getUsersByAgeInterval((byte) 26, (byte) 33).forEach(System.out::println);

//        System.out.println(userService.getUserById(3));
//        userService.updateUserName(3, "Konev");
//        System.out.println(userService.getUserById(3));

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
