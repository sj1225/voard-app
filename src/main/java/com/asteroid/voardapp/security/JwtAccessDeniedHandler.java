package com.asteroid.voardapp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/* SecurityConfig.java 의 .accessDeniedHandler 에서 넘어옴.
* 인가되지 않은 접속을 한 경우 예외처리 발생 (Exception)
* */
@Component
@Slf4j
public class JwtAccessDeniedHandler implements AccessDeniedHandler, Serializable {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
       log.error("인가되지 않은 접근입니다.");
    }
}