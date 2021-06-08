package com.project.epiboly.controller;


import com.project.epiboly.dao.UserDao;
import com.project.epiboly.entity.LoginUser;
import com.project.epiboly.entity.User;
import com.project.epiboly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public Map<String,Object> status(@RequestBody LoginUser user){
        Map<String,Object> status = new HashMap<>();
        if(user.getUserName() != null){
            if(user.getPassword() != null){
                if(userService.existByName(user.getUserName())) {
                    User loginUser = userDao.findUserByUserName(user.getUserName());
                    if (loginUser.getPassword().equals(user.getPassword())) {
                        status.put("status", "200");//登陆成功！
                        status.put("userId", loginUser.getUserId());
                        status.put("userName", loginUser.getUserName());
                        status.put("password", loginUser.getPassword());
                        status.put("phoneNum", loginUser.getPhoneNum());
                        status.put("roles", loginUser.getRoles());
                    } else {
                        status.put("status", "500");//密码错误！
                    }
                }
                else{
                    status.put("status","404");//用户名不存在！
                }
            }
            else {
                status.put("status","403");//密码不得为空！
            }
        }
        else {
            status.put("status","402");//用户名不得为空！
        }
        return status;
    }

}
