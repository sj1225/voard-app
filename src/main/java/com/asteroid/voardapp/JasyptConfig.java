package com.asteroid.voardapp;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* Jasypt(자바 암호화 라이브러리) 암호기 설정
* https://gksdudrb922.tistory.com/147
* https://fourjae.tistory.com/entry/Spring-boot-Jasypt%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%ED%94%84%EB%A1%9C%ED%8D%BC%ED%8B%B0-%EC%95%94%ED%98%B8%ED%99%94%ED%95%98%EA%B8%B0
* */
@Configuration
public class JasyptConfig {
    @Value("${jasypt.encryptor.password}")
    private String password; //암호화에 사용할 키

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm("PBEWithMD5AndDES"); //사용할 알고리즘
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }
}
