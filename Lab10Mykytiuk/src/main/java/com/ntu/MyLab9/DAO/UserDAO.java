package com.ntu.MyLab9.DAO;

import com.ntu.MyLab9.Entity.User;

import java.util.List;

public interface UserDAO {

    User getUserById(long id);
    List<User> getAllUsers();

    boolean insertUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(long id);

}
