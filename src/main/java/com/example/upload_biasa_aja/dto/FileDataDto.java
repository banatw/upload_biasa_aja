package com.example.upload_biasa_aja.dto;

import java.util.UUID;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDataDto {
    
    public UUID fileId;

    public String fileName;

    public MultipartFile multipartFile;

}
