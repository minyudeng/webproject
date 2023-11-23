package com.webproject.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(String objectName, MultipartFile file,String oldName);
    Boolean delFile(String objectName);
}
