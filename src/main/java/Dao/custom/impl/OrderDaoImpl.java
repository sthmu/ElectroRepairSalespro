package Dao.custom.impl;

import Dao.custom.OrderDao;
import Entity.Orders;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    public static List<Orders> getAll() {
        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM Orders");
        List<Orders> list=query.list();
        session.close();
        return list;

    }

    public static boolean save(Orders orders) {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        session.saveOrUpdate(orders);
        transaction.commit();
        session.close();
        return true;
    }
}
