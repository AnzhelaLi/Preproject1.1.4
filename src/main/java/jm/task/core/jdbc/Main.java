package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl u = new UserServiceImpl();

        u.createUsersTable();
        u.saveUser("Tim", "John", (byte)27);
        u.saveUser("Tom", "Smith", (byte)70);
        u.saveUser("Yelena", "John", (byte)24);
        u.saveUser("Kate", "Joly", (byte)9);

        System.out.println(u.getAllUsers().toString());

        u.cleanUsersTable();
        u.dropUsersTable();

        // реализуйте алгоритм здесь
    }
}
