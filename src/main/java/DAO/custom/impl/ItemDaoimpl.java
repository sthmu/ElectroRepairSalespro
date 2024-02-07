package DAO.custom.impl;

import DAO.CrudDao;
import DAO.custom.ItemDao;
import Entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class ItemDaoimpl implements ItemDao {


    public static List<Item> getAll() {
        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM Item");
        List<Item> list=query.list();
        session.close();
        return list;

    }

    @Override
    public boolean save(Item entity) {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return true;
    }
@Override
    public boolean delete(String code) {
    Session session=HibernateUtil.getSession();
    Transaction transaction= session.beginTransaction();
    System.out.println(code);
    session.delete(session.find(Item.class,code));
    transaction.commit();
    session.close();
    return true;
    }
}
