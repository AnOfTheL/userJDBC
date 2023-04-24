package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.openConnection()) {
            String sqlCreate = "CREATE TABLE IF NOT EXISTS public.users"
                    + "  (id           SERIAL PRIMARY KEY,"
                    + "   name         VARCHAR(255),"
                    + "   lastName     VARCHAR(255),"
                    + "   age       INTEGER)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCreate);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.openConnection()) {
            String sqlDrop = "DROP TABLE IF EXISTS users";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDrop);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.openConnection()) {
            String sqlInsert = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.openConnection()) {
            String sqlDeleteById = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteById);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final String query = "SELECT id, name, lastname, age FROM users";
        List<User> userssList = new ArrayList<>();
        try (Connection connection = Util.openConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery(query))
        {
            while(resultSet.next()) {
                userssList.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("lastName"),
                        resultSet.getByte("age")));
            }
            for(User user : userssList) {
                System.out.println(user.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userssList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.openConnection()) {
            String sqlDeleteAllRows = "DELETE FROM userss";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlDeleteAllRows);
            preparedStatement.executeUpdate(sqlDeleteAllRows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
