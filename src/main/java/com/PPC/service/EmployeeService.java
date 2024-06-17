package com.PPC.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


public interface EmployeeService {
    void upload(MultipartFile multipartFile);
}
