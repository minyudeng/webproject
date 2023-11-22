package com.webproject.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String upload(String objectName, MultipartFile file,String oldName);
    public Boolean delFile(String objectName);
}
