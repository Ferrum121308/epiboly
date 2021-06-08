package com.project.epiboly.service;


import com.project.epiboly.dao.UserDao;
import com.project.epiboly.entity.Role;
import com.project.epiboly.entity.User;
import com.project.epiboly.entity.UserForRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleService roleService;

    //根据名称判断
    public boolean existByName(String userName){
        User uncheck = userDao.findUserByUserName(userName);
        return uncheck != null;
    }

    //查询全部用户
    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    //根据用户名查询用户
    public User findUserByName(String userName){
        return userDao.findUserByUserName(userName);
    }

    //根据用户名模糊查询用户
    public List<User> findUsersByNameContaining(String userName){
        return userDao.findUsersByUserNameContaining(userName);
    }

    //新建或修改用户
    public Map<String,Object> saveUser(UserForRequestBody incomeUser){
        Map<String,Object> condition = new HashMap<>();
        List<Role> incomeRoles = new ArrayList<>();
        System.out.println(incomeUser.getRole());
        if(incomeUser.getRole() != null){
            for(String roleName : incomeUser.getRole().split("[,|，]")){
                Role role = roleService.findRoleByName(roleName);
                if(role != null){
                    incomeRoles.add(role);
                    System.out.println(role);
                }
            }
        }
        if (incomeUser.getUserId() == null) {
            if(!existByName(incomeUser.getUserName())){
                User user = new User();
                user.setUserName(incomeUser.getUserName());
                user.setPassword(incomeUser.getPassword());
                user.setPhoneNum(incomeUser.getPhoneNum());
                user.setRoles(incomeRoles);
                userDao.save(user);
                condition.put("condition",0);
            }
            else {
                condition.put("condition",-1);
            }
        }
        else{
            if(!userDao.existsById(incomeUser.getUserId())){
                if(!existByName(incomeUser.getUserName())){
                    User user = new User();
                    user.setUserId(null);
                    user.setUserName(incomeUser.getUserName());
                    user.setPassword(incomeUser.getPassword());
                    user.setPhoneNum(incomeUser.getPhoneNum());
                    user.setRoles(incomeRoles);
                    userDao.save(user);
                    condition.put("condition",0);
                }
                else{
                    condition.put("condition",-1);
                }
            }
            else {
                User existUser = userDao.findUserByUserId(incomeUser.getUserId());
                if(incomeUser.getUserName() != null){
                    existUser.setUserName(incomeUser.getUserName());
                }
                if(incomeUser.getPassword() != null){
                    existUser.setPassword(incomeUser.getPassword());
                }
                if(incomeUser.getPhoneNum() != null){
                    existUser.setPhoneNum(incomeUser.getPhoneNum());
                }
                if(incomeRoles.size() != 0){
                    existUser.setRoles(incomeRoles);
                }
                userDao.save(existUser);
                condition.put("condition",0);
            }
        }
        return condition;
    }

    //删除用户
    public Map<String,Object> deleteById(Long userId){
        Map<String,Object> condition = new HashMap<>();
        if (userDao.existsById(userId)) {
            userDao.deleteById(userId);
            condition.put("condition",0);
        }
        else{
            condition.put("condition",-2);
        }
        return condition;
    }

}
