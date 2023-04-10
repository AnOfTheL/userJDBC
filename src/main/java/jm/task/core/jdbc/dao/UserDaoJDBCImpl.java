package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement())
        {
            String sqlCreate = "CREATE TABLE IF NOT EXISTS public.userss"
                    + "  (id           SERIAL PRIMARY KEY,"
                    + "   name         VARCHAR(255),"
                    + "   lastName     VARCHAR(255),"
                    + "   age       INTEGER)";

            statement.executeUpdate(sqlCreate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement())
        {
            String sqlDrop = "DROP TABLE IF EXISTS userss";
            statement.executeUpdate(sqlDrop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement())
        {
            String sqlInsert = "INSERT INTO userss (name, lastName, age)" +
                    " VALUES ('" + name
                    + "', '" + lastName
                    + "', " + age + ")";
            statement.executeUpdate(sqlInsert);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement())
        {
            String sqlDeleteById = "DELETE FROM userss WHERE id = " + id;
            statement.executeUpdate(sqlDeleteById);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        final String query = "SELECT id, name, lastname, age FROM userss";
        List<User> userssList = new ArrayList<>();
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
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
        try (Connection connection = Util.openConnection();
             Statement statement = connection.createStatement())
        {
            String sqlDeleteAllRows = "DELETE FROM userss";
            statement.executeUpdate(sqlDeleteAllRows);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
