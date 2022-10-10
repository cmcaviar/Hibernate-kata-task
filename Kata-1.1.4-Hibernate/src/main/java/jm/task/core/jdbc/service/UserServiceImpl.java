package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao usr = new UserDaoHibernateImpl();

    public void createUsersTable() {
        usr.createUsersTable();
    }

    public void dropUsersTable() {
        usr.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        usr.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        usr.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return usr.getAllUsers();
    }

    public void cleanUsersTable() {
        usr.cleanUsersTable();
    }

}
