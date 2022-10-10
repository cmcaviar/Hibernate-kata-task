package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl usr = new UserServiceImpl();
        usr.createUsersTable();

        User user1 = new User("Bob", "Dylan", (byte) 44);
        User user2 = new User("Rob", "Dyldo", (byte) 45);
        User user3 = new User("Nob", "Debil", (byte) 46);
        User user4 = new User("Hob", "Down", (byte) 47);

        usr.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        usr.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        usr.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        usr.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        for (int i = 0; i < usr.getAllUsers().size(); i++) {
            System.out.println(usr.getAllUsers().get(i));
        }

        usr.cleanUsersTable();

        usr.dropUsersTable();

    }
}
