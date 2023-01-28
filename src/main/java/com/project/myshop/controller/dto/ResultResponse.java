package com.project.myshop.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ResultResponse<T> {
    String resultCode;
    String message;
    T data;

    @Builder
    public ResultResponse(String resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }
}
