package com.asteroid.voardapp.service;

import com.asteroid.voardapp.model.User;

public interface UserService {
    public void insertUserInfo(User userData);
    public void authUserInfo(User userData);
}
