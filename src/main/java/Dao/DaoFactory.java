package Dao;

import Dao.custom.impl.CategoryItemDaoImpl;
import Dao.custom.impl.CustomerDaoimpl;
import Dao.custom.impl.OrderDaoImpl;
import Dao.custom.impl.UserDaoImpl;

public class DaoFactory {

    private DaoFactory(){}

    private static DaoFactory daoFactory;

    public static DaoFactory getInstance(){
        return daoFactory!=null?daoFactory:(daoFactory=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch(type){
            case CATEGORY_ITEM_DAO:return (T) (new CategoryItemDaoImpl());
            case CUSTOMER_DAO:return (T) new CustomerDaoimpl();
            case ORDER_DAO:return (T) new OrderDaoImpl();
            case USER_DAO:return (T) new UserDaoImpl();
        }
        return null;
    }
}
