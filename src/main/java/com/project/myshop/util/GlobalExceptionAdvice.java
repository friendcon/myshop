package com.project.myshop.util;

import com.project.myshop.controller.dto.ResultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionAdvice extends Exception {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResultResponse<Object>> methodArgumentNotValidationException(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getFieldErrors();

        ResultResponse<Object> resultResponse = ResultResponse.builder()
                .resultCode("400")
                .message(getErrorMessage(fieldErrors))
                .build();
        return new ResponseEntity(resultResponse, HttpStatus.BAD_REQUEST);
    }

    public String getErrorMessage(List<FieldError> fieldErrors) {
        FieldError fieldError = fieldErrors.get(fieldErrors.size()-1);
        return fieldError.getDefaultMessage();
    }


}
