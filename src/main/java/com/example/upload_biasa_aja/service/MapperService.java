package com.example.upload_biasa_aja.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.upload_biasa_aja.dto.FileDataDto;
import com.example.upload_biasa_aja.entity.FileData;

@Service
public class MapperService {

    @Autowired
    private ModelMapper modelMapper;

    public FileData dtoToEntity(FileDataDto fileDataDto) {
        FileData fileData = modelMapper.map(fileDataDto, FileData.class);
        fileData.setFileName(fileDataDto.getMultipartFile().getOriginalFilename());
        return fileData;
    }

    public FileDataDto entityToDto(FileData fileData) {
        FileDataDto fileDataDto = modelMapper.map(fileData, FileDataDto.class);
        return fileDataDto;
    }
}
