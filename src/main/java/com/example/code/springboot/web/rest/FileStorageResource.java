package com.example.code.springboot.web.rest;

import com.example.code.springboot.domain.FileStorage;
import com.example.code.springboot.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URLEncoder;

@Controller
@RequestMapping("/api")
public class FileStorageResource {
    private FileStorageService fileStorageService;

    @Value("${upload.folder}")
    private String uploadFolder;

    public FileStorageResource(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestParam("file")MultipartFile multipartFile){
        fileStorageService.save(multipartFile);
        return ResponseEntity.ok(multipartFile.getOriginalFilename()+"file saqlandi");
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity previewFile(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline; fileName=\""+ URLEncoder.encode(fileStorage.getFilePath()))
                .contentType(MediaType.parseMediaType(fileStorage.getConcatType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,fileStorage.getFilePath())));
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity downloadFile(@PathVariable String hashId) throws MalformedURLException {
        FileStorage fileStorage = fileStorageService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\""+ URLEncoder.encode(fileStorage.getFilePath()))
                .contentType(MediaType.parseMediaType(fileStorage.getConcatType()))
                .contentLength(fileStorage.getFileSize())
                .body(new FileUrlResource(String.format("%s/%s",uploadFolder,fileStorage.getFilePath())));
    }

    @DeleteMapping("/delete/{hashId}")
    public  ResponseEntity delete(@PathVariable String hashId){
        fileStorageService.delete(hashId);
        return ResponseEntity.ok("File o\'chirildi");
    }
}
