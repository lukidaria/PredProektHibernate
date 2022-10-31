package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private String sql;

    private Transaction transaction;


    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        sql = "CREATE TABLE IF NOT EXISTS user (id int PRIMARY KEY AUTO_INCREMENT,\n" +
                "  name varchar(15),\n" +
                "  lastname varchar(20),\n" +
                "  age int)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();


    }

    @Override
    public void dropUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        sql = "DROP TABLE IF EXISTS user";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        try (Session session = Util.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();


            // save the user object
            session.save(user);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }

    @Override
    public void removeUserById(long id) {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(session.get(User.class, id));

        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {

        List<User> userList = new ArrayList<>();

        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User").list();
        }

    }

    @Override
    public void cleanUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        sql = "DROP TABLE IF EXISTS user";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();

    }
}
