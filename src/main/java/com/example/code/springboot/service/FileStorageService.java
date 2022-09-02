package com.example.code.springboot.service;

import com.example.code.springboot.domain.FileStorage;
import com.example.code.springboot.domain.FileStorageStatus;
import com.example.code.springboot.repository.FileStorageRepository;
import jdk.jpackage.internal.Log;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class FileStorageService {
    private final FileStorageRepository fileStorageRepository;

    @Value("${upload.folder}")
    private String uploadFolder;

    private final Hashids hashids;

    public FileStorageService(FileStorageRepository fileStorageRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.hashids = new Hashids(getClass().getName(), 6);
    }

    public void save(MultipartFile multipartFile) {
        FileStorage fileStorage = new FileStorage();
        fileStorage.setName(multipartFile.getOriginalFilename());
        fileStorage.setExstantion(getExt(multipartFile.getOriginalFilename()));
        fileStorage.setFileSize(multipartFile.getSize());
        fileStorage.setConcatType(multipartFile.getContentType());
        fileStorage.setStorageStatus(FileStorageStatus.DRAFT);
        fileStorageRepository.save(fileStorage);

        Date now = new Date();
        File file = new File(String.format("%s/upload_files/%d/%d/%d/", this.uploadFolder, 1900 + now.getYear(), 1 + now.getMonth(), now.getDate()));
        if (!file.exists() && file.mkdirs()) {
            System.out.println("aytilgan papkalar yaratildi");
        }
        fileStorage.setHashId(hashids.encode(fileStorage.getId()));
        fileStorage.setFilePath(String.format("upload_files/%d/%d/%d/%s.%s",
                1900 + now.getYear(),
                1 + now.getMonth(),
                now.getDate(),
                fileStorage.getHashId(),
                fileStorage.getExstantion()));
        fileStorageRepository.save(fileStorage);
        uploadFolder=file.getAbsolutePath();
        File file1= new File(uploadFolder,String.format("%s.%s",fileStorage.getHashId(),fileStorage.getExstantion()));
        try{
          multipartFile.transferTo(file1);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Transactional(readOnly = true)
    public FileStorage findByHashId(String hashId){
        return fileStorageRepository.findByHashId(hashId);
    }
    public void delete(String hashId){
        FileStorage fileStorage= fileStorageRepository.findByHashId(hashId);
        File file=new File(String.format("%s/%s",uploadFolder,fileStorage.getFilePath()));
        if (file.delete()) {
            fileStorageRepository.delete(fileStorage);
        }
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void deleteAllDraft(){
        List<FileStorage> storages=fileStorageRepository.findAllByStorageStatus(FileStorageStatus.DRAFT);
        for (FileStorage fileStorage :storages){
            delete(fileStorage.getHashId());
        }
    }

    private String getExt(String fileName) {
        String ext = null;
        if (fileName != null && !fileName.isEmpty()) {
            int dot = fileName.lastIndexOf('.');
            if (dot > 0 && dot <= fileName.length() - 2) {
                ext = fileName.substring(dot + 1);
            }
        }
        return ext;
    }
}
