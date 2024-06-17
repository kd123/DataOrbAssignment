package com.PPC.constants;


import lombok.Getter;

@Getter
public enum ErrorConstant {

    BAD_REQUEST_INVALID_FILE_TYPE("PAY_400_1","The file format provided is invalid"),
    BAD_REQUEST_FILE_PROCESSING("PAY_400_2","An error occurred, please try again"),
    BAD_REQUEST_INVALID_DATA("PAY_400_3","Data cannot be null"),
    BAD_REQUEST_INVALID_FIRST_NAME("PAY_400_4","First name cannot be empty"),
    BAD_REQUEST_INVALID_DESIGNATION("PAY_400_6","Designation cannot be empty"),
    BAD_REQUEST_INVALID_EVENT("PAY_400_7","Event type is invalid"),
    BAD_REQUEST_EMPTY_COMPANY_NAME("PAY_400_8","Company name cannot be empty");

    private String errorCode;
    private String errorMessage;

    ErrorConstant(String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
