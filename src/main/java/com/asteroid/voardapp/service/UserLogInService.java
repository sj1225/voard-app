package com.asteroid.voardapp.service;

import com.asteroid.voardapp.model.UserSysInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserLogInService {
    public Map<String, Object> login(UserSysInfo userSysInfo, HttpServletResponse response);
}
