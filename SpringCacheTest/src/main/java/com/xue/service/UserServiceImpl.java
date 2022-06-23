package com.xue.service;

import com.xue.mapper.UserMapper;
import com.xue.pojo.User;
import com.xue.utils.RandomValue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
        return userMapper.getAllUsers();
    }

    @CachePut(value = "user")
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    @Override
    public List<User> selectUsersWithScope(Map map) {
        return userMapper.selectUsersWithScope(map);
    }

    @Override
    public List<String> selectUsersByID(Map map) {
        return userMapper.selectUsersByID(map);
    }

    @Override
    public List<String> selectUsersByName(Map map) {
        return userMapper.selectUsersByName(map);
    }

    @Override
    public List<String> selectUsersByGender(Map map) {
        return userMapper.selectUsersByGender(map);
    }

    @Override
    public List<String> selectUsersByEmail(Map map) {
        return userMapper.selectUsersByEmail(map);
    }

    @Override
    public List<String> selectUsersByPhone(Map map) {
        return userMapper.selectUsersByPhone(map);
    }

    @Override
    public List<String> selectUsersByAddress(Map map) {
        return userMapper.selectUsersByAddress(map);
    }

    @Override
    public List<String> selectUsersByAge(Map map) {
        return userMapper.selectUsersByAge(map);
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





































