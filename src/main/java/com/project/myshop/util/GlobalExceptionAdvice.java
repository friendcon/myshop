package com.project.myshop.util;

import com.project.myshop.controller.dto.ResultResponse;
import com.project.myshop.util.customexception.IdDuplicateException;
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

    @ExceptionHandler(IdDuplicateException.class)
    public ResponseEntity<ResultResponse<Object>> idDuplicateException(IdDuplicateException e) {
        ResultResponse<Object> resultResponse = ResultResponse.builder()
                .resultCode("406")
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(resultResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    public String getErrorMessage(List<FieldError> fieldErrors) {
        FieldError fieldError = fieldErrors.get(fieldErrors.size()-1);
        return fieldError.getDefaultMessage();
    }


}
