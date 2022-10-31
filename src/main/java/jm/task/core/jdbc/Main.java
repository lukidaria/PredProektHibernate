package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {



    public static void main(String[] args) {

        UserDao userDao = new UserDaoHibernateImpl();
//        User user = new User;
//        user.setName("Alex");
//        user.setLastName("Ivanov");
//        user.setId(15L);

        userDao.saveUser("Alex", "Ivanov", (byte) 15);
       // userDao.removeUserById(1);

//        List < Student > students = studentDao.getStudents();
//        students.forEach(s - > System.out.println(s.getFirstName()));



    }

}
