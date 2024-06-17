package com.PPC.exception;

import com.PPC.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public  ResponseEntity<Object> InvalidRequestHandler(InvalidRequestException e){
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse = apiResponse.buildErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getErrorCode(), e.getErrorMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }





}
