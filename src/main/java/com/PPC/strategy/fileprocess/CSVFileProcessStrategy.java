package com.PPC.strategy.fileprocess;

import com.PPC.constants.ApplicationConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class CSVFileProcessStrategy implements FileProcessStrategy{


    @Override
    public void process(MultipartFile file) {

    }

    @Override
    public String getFileType() {
        return ApplicationConstants.TEXT_CSV;
    }
}
