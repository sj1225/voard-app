package com.asteroid.voardapp.controller;

import com.asteroid.voardapp.model.UserSysInfo;
import com.asteroid.voardapp.service.UserLogInServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1")
public class UserLogInController {
    @Autowired
    UserLogInServiceImpl userLogInService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        log.debug("---------- 1-1.start UserLogInController.login.user >>> " + user);

        String user_id = user.get("user_id");
        String password = user.get("password");

        UserSysInfo userSysInfo = new UserSysInfo();
        userSysInfo.setUser_id(user_id);
        userSysInfo.setPassword(password);

        log.debug("---------- 1-2.start UserLogInController.login.userSysInfo >>> " + userSysInfo);

        Map<String, Object> result = userLogInService.login(userSysInfo, response);

        log.debug("---------- 1-3.start UserLogInController.login.result >>> " + result);
        log.debug("---------- 1-4.end UserLogInController.login");

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}