package com.project.myshop.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberImgResponse {
    private String imgUrl;
    private String tumImgUrl;

    @Builder
    public MemberImgResponse(String imgUrl, String tumImgUrl) {
        this.imgUrl = imgUrl;
        this.tumImgUrl = tumImgUrl;
    }
}
