package com.PPC.service;

import com.PPC.constants.ErrorConstant;
import com.PPC.exception.FileProcessException;
import com.PPC.strategy.fileprocess.FileProcessHandler;
import com.PPC.strategy.fileprocess.FileProcessStrategy;
import com.PPC.strategy.fileprocess.TextFileProcessStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private FileProcessHandler fileProcessHandler;

    @Autowired
    private TextFileProcessStrategy textFileProcessStrategy;

    @Override
    public void upload(MultipartFile file) {
        Path tempFile;
        String mimeType;
        try {
            tempFile = Files.createTempFile("upload-", file.getOriginalFilename());
            Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);
            mimeType= Files.probeContentType(tempFile);
        }
        catch (Exception ex){
            log.error("An error has occurred while accessing file {}",ex.getMessage());
            throw new FileProcessException(ErrorConstant.BAD_REQUEST_FILE_PROCESSING.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_FILE_PROCESSING.getErrorMessage());
        }
        fileProcessHandler.getFileProcessStrategy(mimeType);
        try {
            textFileProcessStrategy.process(file);
        }
        catch (Exception e){
            log.error("An error has occurred while processing file {}",e.getMessage());
            throw new FileProcessException(ErrorConstant.BAD_REQUEST_FILE_PROCESSING.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_FILE_PROCESSING.getErrorMessage());
        }

    }
}
