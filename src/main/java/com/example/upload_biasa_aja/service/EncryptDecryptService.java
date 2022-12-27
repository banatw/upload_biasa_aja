package com.example.upload_biasa_aja.service;

import org.jasypt.util.binary.BasicBinaryEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncryptDecryptService {
    @Autowired
    private BasicBinaryEncryptor basicBinaryEncryptor;

    // public EncryptDecryptService() {
    //     basicBinaryEncryptor.setPassword("123");
    // }

    public byte[] doEncrypt(byte[] source) {
        return basicBinaryEncryptor.encrypt(source);
    }

    public byte[] doDecrypt(byte[] source) {
        return basicBinaryEncryptor.decrypt(source);
    }
}

