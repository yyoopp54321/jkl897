package com.example.jkl.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {
    Map<String, Object> fileUpload(MultipartFile file, String tempPath );

}
