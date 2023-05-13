package com.asteroid.voardapp.service;

import com.asteroid.voardapp.mapper.UserMapper;
import com.asteroid.voardapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper user;
    @Override
    public void insertUserInfo(User userData){
        user.insertUserInfo(userData);
    };

    @Override
    public void authUserInfo(User userData){
        user.authUserInfo(userData);
    };
}
