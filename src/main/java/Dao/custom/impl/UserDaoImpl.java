package Dao.custom.impl;

import Entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class UserDaoImpl implements Dao.custom.UserDao {
    @Override
    public User login(String un, String pwd) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE userName='" + un + "'  and  Password='" + pwd + "'");
        List<User> user = query.list();
        session.close();
        return user.get(0);
    }
    @Override
    public boolean save(User user) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.beginTransaction();

        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Query query=session.createQuery("FROM User");
        List<User> userList=query.list();
        transaction.commit();
        session.close();
        return  userList;
    }
}
