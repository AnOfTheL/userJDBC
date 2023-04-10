package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.*;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction;
        try (Session session = Util.getSessionFactory().openSession())
        {
            String sqlCreate = "create table if not exists public.userss"
                    + "  (id           serial primary key,"
                    + "   name         varchar(255),"
                    + "   lastName     varchar(255),"
                    + "   age       integer)";

            transaction = session.beginTransaction();
            Query query = session.createSQLQuery(sqlCreate).addEntity(User.class);
            //query.setParameter();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession())
        {
            String sqlDrop = "drop table if exists userss";
            session.beginTransaction();
            session.createSQLQuery(sqlDrop);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession())
        {
            session.beginTransaction();
            session.saveOrUpdate(new User(1L, name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
