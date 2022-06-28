package com.xue.controller;

import com.xue.pojo.User;
import com.xue.service.RedisOperate;
import com.xue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
        List<String> users = new ArrayList<>();
        for (int i = start; i <=end ; i++) {
            if (redisOperate.get(String.valueOf(i))==null){
                System.out.println("-------------------->query the database");
                String s = userService.getUserByID(i).toString();
                redisOperate.set(String.valueOf(i), s);
                users.add(s);
            }else {
                System.out.println("-----------get the data from cache---------------");
                String s = redisOperate.get(String.valueOf(i));
                users.add(s);
            }
        }
        return users.toString();
    }

    /*
    * query attribute "email" with scope
    * query other attributes by adding getXXXByID(i)
    * */
    @RequestMapping("/getemails/{start}/{end}")
    public String getEmailsByID(@PathVariable("start") int start, @PathVariable("end") int end){
        List<String> mails = new ArrayList<>();
        for (int i = start; i <=end; i++) {
            if (redisOperate.get(String.valueOf(i))==null){
                System.out.println("-------------------->query the database");
                String s = userService.getEmailByID(i);
                redisOperate.set(String.valueOf(i), s);
                mails.add(s);
            }else {
                System.out.println("-----------get the data from cache---------------");
                String s = redisOperate.get(String.valueOf(i));
                mails.add(s);
            }
        }
        return mails.toString();
    }

    @RequestMapping("/selectusersbyattribute/{attribute}/{start}/{end}")
    public String selectusersbyattribute(@PathVariable("attribute") String attribute, @PathVariable("start") int start, @PathVariable("end") int end) {
        HashMap map = new HashMap<>();
        map.put("start", start - 1);
        map.put("end", end + 1 - start);
        String key = map.toString();
        List<String> users;
        String usersString = null;

        if (attribute.equals("id")) {
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByID(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("name")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByName(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("gender")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByGender(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("email")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByEmail(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("phone")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByPhone(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else if (attribute.equals("address")){
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByAddress(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }else {
            if (redisOperate.get(key)==null){
                System.out.println("-------------------->query the database");
                users = userService.selectUsersByAge(map);
                redisOperate.set(key, users.toString());
                return users.toString();
            }else{
                System.out.println("-----------get the data from cache---------------");
                usersString = redisOperate.get(key);
            }
        }
        return usersString;
    }


//    @RequestMapping("/selectusersbyattribute/{attribute}/{start}/{end}")
//    public void testRedis(@PathVariable("start") int start, @PathVariable("end") int end){
//        HashMap map = new HashMap<>();
//        map.put("start", start - 1);
//        map.put("end", end + 1 - start);
//
//        String start1 = redisOperate.get(String.valueOf(start));
//        System.out.println("--------------------> start1 ist:" + start1);
//        if (start1==null){
//            String s = userService.selectUsersByID(map).toString();
//            redisOperate.set(String.valueOf(start), s);
//        }else {
//            System.out.println("-----------get the data from cache---------------");
//        }
//    }

    @RequestMapping("/test")
    public String test() {
        /*创建string key*/
        redisOperate.set("one","1111111111");
        return "hello redis";
    }
}






















