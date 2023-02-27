package com.project.myshop.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
public class MemberLoginRequest {
    @NotBlank(message = "아이디를 입력해주세요.")
    String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    String password;

    @Builder
    public MemberLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
