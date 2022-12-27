package com.example.upload_biasa_aja.config;

import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Myconfig {

    @Value("${encryptor.password}")
    private String encryptorPassword;

    @Bean
    public BasicBinaryEncryptor basicBinaryEncryptor() {
       BasicBinaryEncryptor bEncryptor = new BasicBinaryEncryptor();
       bEncryptor.setPassword(encryptorPassword);
       return bEncryptor;
    }
}
