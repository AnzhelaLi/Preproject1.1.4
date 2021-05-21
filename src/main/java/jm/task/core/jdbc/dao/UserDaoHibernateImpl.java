package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    private final SessionFactory sessionFactory = Util.getSessionFactory();
    Util util = new Util();
    Connection conn = util.getConnection();

    @Override
    public void createUsersTable() {
        PreparedStatement ps = null;
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT not NULL AUTO_INCREMENT, " +
                "name VARCHAR(20), " +
                "lastName VARCHAR(20), " +
                "age INTEGER, " +
                "PRIMARY KEY (id))";
        try {
            ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        PreparedStatement ps = null;
        String sql = "DROP TABLE IF EXISTS users";
        try {
            ps = conn.prepareStatement(sql);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            //start the transaction
            session.beginTransaction();
            //save User object
            User user = new User(name, lastName, age);
            session.save(user);

            //commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            //start the transaction
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            list = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();

        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("delete FROM users").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
