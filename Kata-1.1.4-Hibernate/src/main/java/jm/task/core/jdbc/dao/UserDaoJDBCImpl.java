package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connect = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
    String query = "create table if not exists users\n" +
            "(\n" +
            "    id       INT auto_increment not null,\n" +
            "    name     TEXT not null,\n" +
            "    lastName TEXT not null,\n" +
            "    age      INT  not null,\n" +
            "    constraint Users_pk\n" +
            "        primary key (Id)\n" +
            ");";
    try (PreparedStatement ps = connect.prepareStatement(query)) {
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println("unable to create a table");
        }
    }

    public void dropUsersTable() {
    String query = "drop table if exists users;";
    try (PreparedStatement ps = connect.prepareStatement(query)) {
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println(("unable to delete the table"));
        }
    }

    public void saveUser(String name, String lastName, byte age) {
    String query = "insert into users (name, lastName, age) values(?, ?, ?);";
    try (PreparedStatement ps = connect.prepareStatement(query)) {
        ps.setString(1, name);
        ps.setString(2, lastName);
        ps.setByte(3, age);
        ps.executeUpdate();
        System.out.println("User " + name + " добавлен в базу данных");
    } catch (SQLException e) {
        System.err.println("unable to save a user");
        }
    }


    public void removeUserById(long id) {
    String query = "delete from users where id = ?;";
    try (PreparedStatement ps = connect.prepareStatement(query)) {
        ps.setLong(1, id);
    } catch (SQLException e) {
        System.err.println("unable to delete a user");
        }
    }

    public List<User> getAllUsers() {
        String query = "select * from users;";
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = connect.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
               long id = resultSet.getLong("id");
//                String name = resultSet.getString("name");
//                String lastName = (resultSet.getString("lastName"));
//                byte age = resultSet.getByte("age");
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                user.setId(id);
                users.add(user);

            }
        } catch (SQLException e) {
            System.err.println("unable to get all users");
        }
        return users;
    }


    public void cleanUsersTable() {
    String query = "truncate table users";
    try (PreparedStatement ps = connect.prepareStatement(query)) {
        ps.executeUpdate();
    } catch (SQLException e) {
        System.err.println("unable to clean the table");
        }
    }
}
