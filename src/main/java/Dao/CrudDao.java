package Dao;

public interface CrudDao<T> {
    boolean save(T entity);
    boolean delete(String text);
}
