package com.example.code.springboot.repository;

import com.example.code.springboot.domain.FileStorage;
import com.example.code.springboot.domain.FileStorageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {

    FileStorage findByHashId(String name);

    List<FileStorage> findAllByStorageStatus(FileStorageStatus status);
}
