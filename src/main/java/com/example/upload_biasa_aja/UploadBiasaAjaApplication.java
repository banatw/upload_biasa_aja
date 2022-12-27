package com.example.upload_biasa_aja;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.upload_biasa_aja.entity.FileData;
import com.example.upload_biasa_aja.repos.FileDataRepository;

@SpringBootApplication
public class UploadBiasaAjaApplication  {//implements CommandLineRunner  {

	// @Autowired
	// private FileDataRepository fileDataRepository;

	public static void main(String[] args) {
		SpringApplication.run(UploadBiasaAjaApplication.class, args);
	}

	// @Override
	// public void run(String... args) throws Exception {
	// 	// TODO Auto-generated method stub
	// 	FileData fileData = new FileData();
	// 	fileData.setFileName("DRH_2130102120019118.pdf");
	// 	fileDataRepository.save(fileData);
	// }

	/**
	 * InnerUploadBiasaAjaApplication
	 */
	@Configuration
	public class InnerUploadBiasaAjaApplication {
		
		@Bean
		public ModelMapper modelMapper() {
			return new ModelMapper();
		}
		
	}
}
