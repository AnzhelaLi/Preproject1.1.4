package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoHibernateImpl udh = new UserDaoHibernateImpl();

    public void createUsersTable() {
        udh.createUsersTable();
    }

    public void dropUsersTable() {
        udh.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        udh.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        udh.removeUserById(id);
    }

    public List<User> getAllUsers() {
       return udh.getAllUsers();
    }

    public void cleanUsersTable() {
        udh.cleanUsersTable();
    }
}
