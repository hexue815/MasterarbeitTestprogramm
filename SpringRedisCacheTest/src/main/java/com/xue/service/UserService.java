package com.xue.service;

import com.xue.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> getAllUsers();

    void initDatabase(User user);

    void insertUser(User user);

    String selectUsersWithScope(Map map);

    String selectUsersByID(Map map);

    String selectUsersByName(Map map);

    String selectUsersByGender(Map map);

    String selectUsersByEmail(Map map);

    String selectUsersByPhone(Map map);

    String selectUsersByAddress(Map map);

    String selectUsersByAge(Map map);

    User getUserByID(int id);

    String getEmailByID(int id);
}
