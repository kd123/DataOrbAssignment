package com.PPC.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class InvalidRequestException extends RuntimeException{

    private String errorCode;
    private String errorMessage;

    public InvalidRequestException(String errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }


}
