package com.example.upload_biasa_aja.controller;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
// import org.apache.tomcat.util.http.fileupload.IOUtils;
// import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
// import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.upload_biasa_aja.dto.FileDataDto;
import com.example.upload_biasa_aja.entity.FileData;
import com.example.upload_biasa_aja.repos.FileDataRepository;
import com.example.upload_biasa_aja.service.MapperService;
import com.example.upload_biasa_aja.service.UpdownService;



@Controller
public class MyController {
    @Value("${resources.location}")
    private String resourceLocations;

    @Autowired
    private UpdownService updownService;

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private MapperService mapperService;

    
    @GetMapping(value="/download",produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getPdf(@RequestParam UUID param) throws IOException {
        byte[] result = updownService.download(param);
        return result;
    }

    @GetMapping(value="/download1")
    public ResponseEntity<byte[]> tes(@RequestParam UUID param) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        // headers.set("Content-Disposition", "attachment; filename=tes.pdf");
        // response.setHeader("Content-Disposition", "attachment; filename=" + zipFile.getFileName());
        byte[] result = updownService.download(param);
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(result);
    }
    
    @GetMapping(value="/form")
    public String formUpload(@RequestParam Optional<UUID> fileId,Model model) {
        FileDataDto fileDataDto = null;
        if(fileId.isPresent()) {
            FileData fileData = fileDataRepository.findById(fileId.get()).get();
            fileDataDto = mapperService.entityToDto(fileData);
        }
        else {
            fileDataDto = new FileDataDto();
        }
        model.addAttribute("fileDataDto", fileDataDto);
        return "form";
    }
    

    @GetMapping(value="/index")
    public String getIndex(Model model) {
        // fileDataRepository.findAll().stream().forEach(x->{System.out.println(x.getFileName());});
        model.addAttribute("datas", fileDataRepository.findAll());
        return "index";
    }

    @GetMapping(value="/")
    public String setBlank() {
        return "redirect:/index";
    }
    
    

    @RequestMapping(value="/save",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String save(FileDataDto fileDataDto) throws IllegalStateException, IOException {
        FileData fileData = null;
        if(fileDataDto.getFileId()!=null) {
            fileData = mapperService.dtoToEntity(fileDataDto);
            // updownService.upload(fileDataDto.getMultipartFile(),fileData.getFileId());
        }
        else {
            fileData = new FileData();
            fileData.setFileName(fileDataDto.getMultipartFile().getOriginalFilename());
            // updownService.upload(fileDataDto.getMultipartFile(),fileData.getFileId());
        } 
       fileDataRepository.save(fileData);
       updownService.upload(fileDataDto.getMultipartFile(),fileData.getFileId());
    //    System.out.println(fileData.getFileId());
       return "redirect:/index";
    }

    @GetMapping(value="/delete")
    public String getMethodName(@RequestParam UUID param) throws Exception {
        FileData fileData = fileDataRepository.getReferenceById(param);
        updownService.deleteFile(param);
        fileDataRepository.delete(fileData);
        return "redirect:/index";
    }
    
    

}
