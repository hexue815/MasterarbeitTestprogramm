package com.xue.mapper;

import com.xue.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> getAllUsers();

    void insertUser(User user);

    List<User> selectUsersWithScope(Map map);

    List<String> selectUsersByID(Map map);

    List<String> selectUsersByName(Map map);

    List<String> selectUsersByGender(Map map);

    List<String> selectUsersByAge(Map map);

    List<String> selectUsersByEmail(Map map);

    List<String> selectUsersByPhone(Map map);

    List<String> selectUsersByAddress(Map map);

    User getUserByID(int id);
}
