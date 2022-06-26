package com.xue.service;

import com.xue.mapper.UserMapper;
import com.xue.pojo.User;
import com.xue.utils.RandomValue;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Map;

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
    public List<User> selectUsersWithScope(Map map) {
        return userMapper.selectUsersWithScope(map);
    }

    /*测试一级缓存*/
//    @Override
//    public List<User> selectUsersWithScope(Map map) {
//        List<User> users = new ArrayList<>();
//        for (int i = 0; i < 2; i++) {
//            users = userMapper.selectUsersWithScope(map);
//            logger.info("----------->query database");
//            System.out.println(users);
//        }
//        return users;
//    }

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
    public User getUserByID(int id) {
        return userMapper.getUserByID(id);
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





































