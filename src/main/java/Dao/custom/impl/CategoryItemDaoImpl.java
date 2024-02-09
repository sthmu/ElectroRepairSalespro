package Dao.custom.impl;

import Entity.CategoryItem;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

public class CategoryItemDaoImpl {
    public static List<CategoryItem> getAll() {
        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM CategoryItem");
        List<CategoryItem> list=query.list();
        session.close();
        return list;

    }


    public static boolean save(CategoryItem entity) {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return true;
    }

    public boolean delete(String code) {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        System.out.println(code);
        session.delete(session.find(CategoryItem.class,code));
        transaction.commit();
        session.close();
        return true;
    }



}
