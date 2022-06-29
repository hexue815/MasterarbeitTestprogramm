package com.xue.service;

import com.xue.mapper.UserMapper;
import com.xue.pojo.User;
import com.xue.utils.RandomValue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

@Cacheable(value = "user")
public class UserServiceImpl implements UserService {
    @Autowired
    @Qualifier("userMapper")
    private UserMapper userMapper;
    private User user = new User();
    static Logger logger = Logger.getLogger(UserMapper.class);

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUsers() {
        logger.info("-------check the database");
        return userMapper.getAllUsers();
    }

    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public String selectUsersWithScope(Map map) {
        return userMapper.selectUsersWithScope(map).toString();
    }

    @Override
    public String selectUsersByID(Map map) {
        return userMapper.selectUsersByID(map).toString();
    }

    @Override
    public String selectUsersByName(Map map) {
        return userMapper.selectUsersByName(map).toString();
    }

    @Override
    public String selectUsersByGender(Map map) {
        return userMapper.selectUsersByGender(map).toString();
    }

    @Override
    public String selectUsersByEmail(Map map) {
        return userMapper.selectUsersByEmail(map).toString();
    }

    @Override
    public String selectUsersByPhone(Map map) {
        return userMapper.selectUsersByPhone(map).toString();
    }

    @Override
    public String selectUsersByAddress(Map map) {
        return userMapper.selectUsersByAddress(map).toString();
    }

    @Override
    public String selectUsersByAge(Map map) {
        return userMapper.selectUsersByAge(map).toString();
    }

    @Override
    public User getUserByID(int id) {
        return userMapper.getUserByID(id);
    }

    @Override
    public String getEmailByID(int id) {
        return userMapper.getEmailByID(id);
    }


    @Override
    public void initDatabase(User user) {
        user.setName(RandomValue.getRandomName());
        user.setGender(RandomValue.getRandomGender());
        user.setAge(RandomValue.getRandomAge());
        user.setEmail(RandomValue.getRandomEmail());
        user.setAddress(RandomValue.getRandomAddress());
        user.setPhone(RandomValue.getRandomPhone());
        userMapper.insertUser(user);
    }
}





































