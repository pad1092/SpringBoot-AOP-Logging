package com.pad.loggingwithaop.exception;

import com.pad.loggingwithaop.payload.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
        ResponseDTO responsePayload = ResponseDTO.builder().detail(errorDetails)
                                                    .status(HttpStatus.NOT_FOUND)
                                                    .isSuccess(false).build();
        return new ResponseEntity<>(responsePayload, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleInternalException(Exception ex, WebRequest webRequest){
        ex.printStackTrace();
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), webRequest.getDescription(false));
        ResponseDTO responsePayload = ResponseDTO.builder().detail(errorDetails)
                                                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                                            .isSuccess(false).build();
        return new ResponseEntity<>(responsePayload, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
