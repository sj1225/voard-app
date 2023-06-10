package com.asteroid.voardapp.mapper;

import com.asteroid.voardapp.model.UserSysInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SecurityMapper {
    UserSysInfo selectByUserId(String user_id);
}

