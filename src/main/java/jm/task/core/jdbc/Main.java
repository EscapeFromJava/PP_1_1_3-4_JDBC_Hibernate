package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
//        userService.saveUser("Evgeniy", "Smirnov", (byte) 41);
//        userService.saveUser("Artem", "Petuhov", (byte) 21);
//        userService.saveUser("Aleksey", "Petrov", (byte) 30);
//        userService.saveUser("Olga", "Orlova", (byte) 26);
//        userService.saveUser("Nikolay", "Eremin", (byte) 31);
//        userService.saveUser("Elena", "Ivanova", (byte) 23);
//        userService.saveUser("Marina", "Egorova", (byte) 33);
        userService.generateRandomUsers(20);
        System.out.println(userService.getAverageAgeValue());
//        userService.getAllUsers().forEach(System.out::println);
//        userService.removeUserById(3);
//        System.out.println("всего " + userService.getAllUsers().size());

//        String lastName = "Boroda";
//        List<User> users = userService.getUsersByLastName(lastName);
//        System.out.println("Количество пользователей с фамилией " + lastName + " = " + users.size());
//        users.forEach(System.out::println);


//        System.out.println("get user: " + userService.getUserById(4));

//        userService.getUsersByAgeInterval((byte) 26, (byte) 36).forEach(System.out::println);

//        System.out.println(userService.getUserById(33));
//        userService.updateUserName(33, "Anatoliy");
//        System.out.println(userService.getUserById(33));

//        userService.cleanUsersTable();
//        userService.dropUsersTable();
    }
}
