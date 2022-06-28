package com.xue.controller;

import com.xue.pojo.User;
import com.xue.service.RedisOperate;
import com.xue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    private User user = new User();
    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;
    @Autowired
    @Qualifier("redisOperateImpl")
    private RedisOperate redisOperate;

    @RequestMapping("/init")
    public void init(){
        for (int i = 1; i <=1000; i++) {
            userService.initDatabase(user);
        }
    }

    @RequestMapping("/getall")
    public String getAll(){
        List<User> users = userService.getAllUsers();
        return users.toString();
    }

    /*
    * query all attributes of users with scope
    * */
    @RequestMapping("/selectusers/{start}/{end}")
    public String selectUsers(@PathVariable("start") int start, @PathVariable("end") int end){
        HashMap map = new HashMap<>();
        map.put("start", start - 1);
        map.put("end", end + 1 - start);
        String key = map.toString();
        String usersString = null;
        if (redisOperate.get(key)==null){
            System.out.println("-------------------->query the database------------");
            usersString = userService.selectUsersWithScope(map).toString();
            redisOperate.set(key, usersString);
        }else{
            System.out.println("-----------get the data from cache---------------");
            usersString = redisOperate.get(key);
        }
        return usersString;
    }

    /*
     * query attributes with scope
     * */
    @RequestMapping("/selectusersbyattribute/{attribute}/{start}/{end}")
    public String selectusersbyattribute(@PathVariable("attribute") String attribute, @PathVariable("start") int start, @PathVariable("end") int end) {
        HashMap map = new HashMap<>();
        map.put("start", start - 1);
        map.put("end", end + 1 - start);
        String key = map.toString();

        String usersString = null;

        if (attribute.equals("id")) {
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByID(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("name")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByName(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("gender")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByGender(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("email")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByEmail(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("phone")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByPhone(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("address")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByAddress(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else {
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                usersString = userService.selectUsersByAge(map).toString();
                redisOperate.set(key, usersString);
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }
        return usersString;
    }

    @RequestMapping("/test")
    public String test() {
        /*创建string key*/
        redisOperate.set("one","1111111111");
        return "hello redis";
    }
}






















