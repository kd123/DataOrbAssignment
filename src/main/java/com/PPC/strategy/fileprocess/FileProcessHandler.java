package com.PPC.strategy.fileprocess;

import com.PPC.constants.ErrorConstant;
import com.PPC.exception.InvalidRequestException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Service
@Slf4j
public class FileProcessHandler {

    @Getter
    private Map<String,FileProcessStrategy> fileProcessStrategyMap;

    @Autowired
    public FileProcessHandler(Set<FileProcessStrategy> fileProcessStrategies){
        initialise();
        fileProcessStrategies.forEach(each -> fileProcessStrategyMap.put(each.getFileType(), each));
    }

    private void initialise(){
        fileProcessStrategyMap = new HashMap<>();
    }

    public FileProcessStrategy getFileProcessStrategy(String mimeType){
        if(Objects.isNull(fileProcessStrategyMap.get(mimeType))){
            log.error("No strategy found for mime type {}",mimeType);
            throw new InvalidRequestException(ErrorConstant.BAD_REQUEST_INVALID_FILE_TYPE.getErrorCode(),
                    ErrorConstant.BAD_REQUEST_INVALID_FILE_TYPE.getErrorMessage());

        }
        return fileProcessStrategyMap.get(mimeType);
    }

}
