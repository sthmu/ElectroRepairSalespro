package Dao.custom;

import Dao.SuperDao;
import Entity.User;

import java.util.List;

public interface UserDao extends SuperDao {
    User login(String un, String pwd);

    boolean save(User user);

    List<User> getAllUsers();
}
