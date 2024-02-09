package DAO.custom.impl;

import Entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class CustomerDaoimpl {
    public static List<Customer> getAll() {
        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM Customer");
        List<Customer> list=query.list();
        session.close();
        return list;
    }
    public static boolean save(Customer customer) {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        session.saveOrUpdate(customer);
        transaction.commit();
        session.close();
        return true;
    }
}
