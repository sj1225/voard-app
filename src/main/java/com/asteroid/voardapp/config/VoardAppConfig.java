package com.asteroid.voardapp.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication	// 스프링부트 메인 설정함수임을 선언
@MapperScan(basePackages = "com.asteroid.voardapp.mapper")	// 해당 경로 아래의 Interface를 자동으로 Mapper로 등록
@ComponentScan(basePackages = "com.asteroid.voardapp.**")	// @Component, streotype 어노테이션 클래스를 자동으로 Bean으로 등록
@EnableGlobalMethodSecurity(securedEnabled = true)			// 보안 설정
@EnableEncryptableProperties								// 암호화 설정
@Slf4j			// 로그 설정
@EnableAsync	// 비동기 설정
public class VoardAppConfig {
	public static void main(String[] args) {
		new SpringApplicationBuilder(VoardAppConfig.class).logStartupInfo(false).run(args);
	}
}
