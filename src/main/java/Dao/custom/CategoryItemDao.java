package Dao.custom;

import Dao.SuperDao;

public interface CategoryItemDao extends SuperDao {
    boolean delete(String code);
}
