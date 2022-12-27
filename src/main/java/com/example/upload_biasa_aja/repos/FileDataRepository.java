package com.example.upload_biasa_aja.repos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.upload_biasa_aja.entity.FileData;

public interface FileDataRepository extends JpaRepository<FileData,UUID> {
    
}
