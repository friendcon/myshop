package com.project.myshop.controller;

import com.project.myshop.controller.dto.MemberCreateRequest;
import com.project.myshop.controller.dto.ResultResponse;
import com.project.myshop.service.MemberService;
import com.project.myshop.util.customexception.IdDuplicateException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<ResultResponse<String>> signUp(@RequestBody @Valid MemberCreateRequest memberCreateRequest) {
        memberService.signUp(memberCreateRequest);
        ResultResponse response = ResultResponse.builder()
                .resultCode("200")
                .message("회원가입이 완료되었습니다.")
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/checkId/{username}")
    public ResponseEntity<ResultResponse<Boolean>> checkDuplicate(@PathVariable("username") String username) {
        Boolean response = memberService.isDuplicate(username);

        if(response == true) {
            throw new IdDuplicateException("중복된 아이디 입니다");
        }

        ResultResponse resultResponse = ResultResponse.builder()
                .resultCode("200")
                .message("사용할 수 있는 아이디입니다")
                .data(response)
                .build();

        return new ResponseEntity<>(resultResponse, HttpStatus.OK);
    }
}
