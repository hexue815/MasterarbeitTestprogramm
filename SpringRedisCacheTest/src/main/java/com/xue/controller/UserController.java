package com.xue.controller;

import com.xue.pojo.User;
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
        map.put("start", start-1);
        map.put("end", end+1-start);
        return userService.selectUsersWithScope(map);
    }

    /*
    * query attributes with scope
    * */
    @RequestMapping("/selectusersbyattribute/{attribute}/{start}/{end}")
    public String selectusersbyattribute(@PathVariable("attribute") String attribute, @PathVariable("start") int start, @PathVariable("end") int end) {
        HashMap map = new HashMap<>();
        map.put("start", start - 1);
        map.put("end", end + 1 - start);

        String usersString;

        if (attribute.equals("id")) {
            usersString = userService.selectUsersByID(map);
        }else if (attribute.equals("name")){
            usersString = userService.selectUsersByName(map);
        }else if (attribute.equals("gender")){
            usersString = userService.selectUsersByGender(map);
        }else if (attribute.equals("email")){
            usersString = userService.selectUsersByEmail(map);
        }else if (attribute.equals("phone")){
            usersString = userService.selectUsersByPhone(map);
        }else if (attribute.equals("address")){
            usersString = userService.selectUsersByAddress(map);
        }else {
            usersString = userService.selectUsersByAge(map);
        }
        return usersString;
    }
}






















