package com.project.epiboly.controller;


import com.project.epiboly.entity.User;
import com.project.epiboly.entity.UserForRequestBody;
import com.project.epiboly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //查找所有用户
    @GetMapping("/findAllUsers")
    public List<User> findAll(){
        return userService.findAllUsers();
    }

    //根据名称模糊查询用户
    @GetMapping("/findUsersByName")
    public List<User> findUsersByNameContaining(@RequestParam("userName")String userName){
        return userService.findUsersByNameContaining(userName);
    }

    //新建或修改用户
    @PostMapping("/save")
    public Map<String,Object> saveUser(@RequestBody UserForRequestBody user){
        return userService.saveUser(user);
    }

    //删除用户
    @GetMapping("/delete")
    public Map<String,Object> deleteUserById(@RequestParam("userId")Long userId){
        return userService.deleteById(userId);
    }

}
