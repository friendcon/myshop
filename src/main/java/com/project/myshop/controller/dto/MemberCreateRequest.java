package com.project.myshop.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreateRequest {
    String username;
    String password;
    String email;
    String profileImgUrl;
    String profileImgTumUrl;

    @Builder
    public MemberCreateRequest(String username, String password, String email, String profileImgUrl, String profileImgTumUrl) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImgUrl = profileImgUrl;
        this.profileImgTumUrl = profileImgTumUrl;
    }
}
