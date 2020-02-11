package com.example.jkl.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
     String upload(MultipartFile file, String uploadPath);
}
