package com.asteroid.voardapp.mapper;

import com.asteroid.voardapp.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public void insertUserInfo(User userData);
    public void authUserInfo(User userData);
}
