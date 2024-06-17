package com.PPC.strategy.fileprocess;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileProcessStrategy {
    void process(MultipartFile file) throws IOException;
    String getFileType();
}
