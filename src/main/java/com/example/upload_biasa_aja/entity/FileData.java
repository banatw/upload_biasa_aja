package com.example.upload_biasa_aja.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FileData {
    @Id
    @Column(columnDefinition = "uuid",unique = true)
    @GeneratedValue(generator = "uuid")
    public UUID fileId;

    public String fileName;

}
