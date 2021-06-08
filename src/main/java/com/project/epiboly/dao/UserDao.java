package com.project.epiboly.dao;

import com.project.epiboly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {

    //通过Id查询用户
    User findUserByUserId(Long userId);

    //通过用户名获取用户信息
    User findUserByUserName(String userName);

    //通过用户名模糊查询用户信息
    List<User> findUsersByUserNameContaining(String userName);


}
