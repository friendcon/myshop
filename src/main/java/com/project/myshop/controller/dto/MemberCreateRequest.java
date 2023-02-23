package com.project.myshop.controller.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCreateRequest {
    @NotBlank(message = "아이디를 입력하세요")
    @Pattern(message = "아이디는 소문자와 숫자를 포함하여 6~20 길이를 가져야 합니다.", regexp = "^[a-z](?=.*?[a-z])(?=.*?[0-9])[a-z0-9]{5,20}")
    String username;
    @NotBlank(message = "비밀번호를 입력하세요")
    @Pattern(message = "비밀번호는 소문자, 숫자, 특수문자를 1개 이상 포함하여 5~20 길이를 가져야 합니다", regexp = "^[a-z](?=.*?[a-z])(?=.*?[0-9])(?=.*?[~!@#$%^&*_-])[a-z0-9~!@#$%^&*_-]{7,20}")
    String password;
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    String email;
    String profileImgUrl;
    String profileImgTumUrl;

    @AssertFalse(message = "아이디 중복 체크를 확인해주세요")
    Boolean isIdDuplicate;
    @AssertTrue(message = "아이디 형식을 확인해주세요")
    Boolean isIdCorrect;
    @AssertTrue(message = "비밀번호 형식을 확인해주세요")
    Boolean passwordIsCorrect;
    @Builder
    public MemberCreateRequest(String username, String password, String email, String profileImgUrl, String profileImgTumUrl, Boolean isIdDuplicate, Boolean isIdCorrect, Boolean passwordIsCorrect, Boolean emailIsCorrect) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.profileImgTumUrl = profileImgTumUrl;
        this.isIdDuplicate = isIdDuplicate;
        this.isIdCorrect = isIdCorrect;
        this.passwordIsCorrect = passwordIsCorrect;
    }
}
