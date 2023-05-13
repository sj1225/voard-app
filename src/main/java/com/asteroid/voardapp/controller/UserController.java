package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.mapper.UserMapper;
import com.asteroid.voardapp.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/api/v1/logon")
    public void insertUserInfo(@RequestBody User userData){
        userMapper.insertUserInfo(userData);
    };

    @GetMapping("/api/v1/login")
    public void authUserInfo(@RequestBody User userData){
        userMapper.authUserInfo(userData);
    };
}
