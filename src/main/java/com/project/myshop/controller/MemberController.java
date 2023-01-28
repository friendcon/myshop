package com.project.myshop.controller;

import com.project.myshop.controller.dto.MemberCreateRequest;
import com.project.myshop.controller.dto.ResultResponse;
import com.project.myshop.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<ResultResponse<String>> signUp(@RequestBody MemberCreateRequest memberCreateRequest) {
        memberService.signUp(memberCreateRequest);
        System.out.println(memberCreateRequest.toString());
        ResultResponse response = ResultResponse.builder()
                .resultCode("200")
                .message("회원가입이 완료되었습니다.")
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
