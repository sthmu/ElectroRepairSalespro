package DAO;

public interface CrudDao<T> {
    public boolean save(T entity);
    public boolean delete(String text);
}
