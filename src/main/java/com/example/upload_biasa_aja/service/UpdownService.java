package com.example.upload_biasa_aja.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UpdownService {
    @Value("${resources.location}")
    private String resourceLocation;

    @Autowired
    private EncryptDecryptService encryptDecryptService;

    public byte[] download(UUID storageFileName) throws IOException {
        File file = new File(resourceLocation + "/" + storageFileName.toString());
        InputStream in = new FileInputStream(file);
        byte[] result = IOUtils.toByteArray(in);
        in.close();
        byte[] finalResult = encryptDecryptService.doDecrypt(result);
        return finalResult;
    }

    public void upload(MultipartFile multipartFile,UUID storageFileName) throws IllegalStateException, IOException {
        File file = new File(resourceLocation + "/" + storageFileName.toString());
        byte[] data =  encryptDecryptService.doEncrypt(multipartFile.getBytes());
        InputStream inputStream = new ByteArrayInputStream(data);
        FileUtils.copyInputStreamToFile(inputStream, file);
        inputStream.close();
    } 

    public void deleteFile(UUID storageFileName) throws Exception {
        File file = new File(resourceLocation + "/" + storageFileName.toString());
        file.delete();
    }
}
