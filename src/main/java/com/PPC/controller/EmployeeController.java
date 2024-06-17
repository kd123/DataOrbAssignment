package com.PPC.controller;

import com.PPC.service.EmployeeService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") @NotNull MultipartFile file){
        employeeService.upload(file);
        return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
    }

}
