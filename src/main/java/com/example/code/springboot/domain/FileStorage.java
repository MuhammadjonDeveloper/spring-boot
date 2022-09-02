package com.example.code.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class FileStorage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String exstantion;

    private String hashId;

    private Long fileSize;

    private String concatType;

    private String filePath;

    @Enumerated(EnumType.STRING)
    private FileStorageStatus storageStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExstantion() {
        return exstantion;
    }

    public void setExstantion(String exstantion) {
        this.exstantion = exstantion;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getConcatType() {
        return concatType;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public void setConcatType(String concatType) {
        this.concatType = concatType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileStorageStatus getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(FileStorageStatus storageStatus) {
        this.storageStatus = storageStatus;
    }
}
