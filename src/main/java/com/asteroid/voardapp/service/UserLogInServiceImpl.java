package com.asteroid.voardapp.service;

import com.asteroid.voardapp.mapper.SecurityMapper;
import com.asteroid.voardapp.model.UserSysInfo;
import com.asteroid.voardapp.security.JwtTokenProvider;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLogInServiceImpl implements UserLogInService{
    //private final PasswordEncoder passwordEncoder;
    @Autowired
    private SecurityMapper securityMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public Map<String, Object> login(UserSysInfo user, HttpServletResponse response) {
        log.debug("---------- 2-1.start UserLogInServiceImpl.login.user >>> " + user);

        UserSysInfo userSysInfo = securityMapper.selectByUserId(user.getUser_id());
        log.debug("---------- 2-2.start UserLogInServiceImpl.login.userSysInfo >>> " + userSysInfo);

        // 사용자 정보가 유효한지 점검
        Map<String, Object> result = new HashMap<String, Object>();

        int idNotFound = 1;     // ID 존재 여부
        int pwNotCorrect = 0;   // 비밀번호 일치 여부

        String token = null;

        String user_id = null;
        String user_nm = null;

        if(userSysInfo != null){
            idNotFound = 0;
            token = jwtTokenProvider.createJwtAccessToken(userSysInfo);
            log.debug("---------- 2-3.start UserLogInServiceImpl.login.token >>> " + token);

            user_id = userSysInfo.getUser_id();
            user_nm = userSysInfo.getUser_nm();
        }
//        if ( !passwordEncoder.matches(userSysInfo.getPassword(), user.getPassword()) ){
//            pwNotCorrect = 1;
//        }

        result.put("user_id", user_id);
        result.put("user_nm", user_nm);
        result.put("idNotFound", idNotFound);
        result.put("pwNotCorrect", pwNotCorrect);
        result.put("token", token);

        log.debug("---------- 2-4.UserLogInServiceImpl.login.result >>> " + result);
        log.debug("---------- 2-5.end UserLogInServiceImpl.login");

        return result;
    }
}
