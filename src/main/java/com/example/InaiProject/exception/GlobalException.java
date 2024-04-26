package com.example.InaiProject.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomException> handlerException(CustomException e) {
        CustomException customException = new CustomException(e.getMessage(), e.getHttpStatus());
        return new ResponseEntity<>(customException, e.getHttpStatus());
    }
}
