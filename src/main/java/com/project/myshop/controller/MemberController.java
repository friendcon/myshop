package com.project.myshop.controller;

import com.project.myshop.controller.dto.MemberCreateRequest;
import com.project.myshop.controller.dto.MemberImgResponse;
import com.project.myshop.controller.dto.ResultResponse;
import com.project.myshop.service.MemberService;
import com.project.myshop.util.S3UploadService;
import com.project.myshop.util.customexception.IdDuplicateException;
import com.project.myshop.util.customexception.IdNotValidationException;
import com.project.myshop.util.customexception.SignUpFieldNotValidationException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    private S3UploadService s3UploadService;

    public MemberController(MemberService memberService, S3UploadService s3UploadService) {
        this.memberService = memberService;
        this.s3UploadService = s3UploadService;
    }

    @PostMapping
    public ResponseEntity<ResultResponse<String>> doSignUp(@Valid MemberCreateRequest memberCreateRequest) {
        if(memberCreateRequest.getIsIdCorrect() == null || memberCreateRequest.getPasswordIsCorrect() == null || memberCreateRequest.getIsIdDuplicate() == null) {
            throw new SignUpFieldNotValidationException("유효성 체크를 해주세요.");
        }

        memberService.signUp(memberCreateRequest);
        ResultResponse response = ResultResponse.builder()
                .resultCode("200")
                .message("회원가입이 완료되었습니다.")
                .build();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/checkId/{username}")
    public ResponseEntity<ResultResponse<Boolean>> checkDuplicate(@PathVariable(value = "username", required = true) String username) {

        String idPattern = "^[a-z](?=.*?[a-z])(?=.*?[0-9])[a-z0-9]{5,20}";
        if (username == null) {
            throw new NullPointerException("아이디는 6~20자의 소문자, 숫자 조합으로 이루어져야 합니다.");
        } else if(!username.matches(idPattern)) {
            throw new IdNotValidationException("아이디는 6~20자의 소문자, 숫자 조합으로 이루어져야 합니다.");
        }

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

    @PostMapping(value = "/uploadImg",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MemberImgResponse> uploadMemberImg(@RequestParam("imgFile") MultipartFile multipartFile) throws IOException {
        MemberImgResponse imgResponse = s3UploadService.upload(multipartFile, "member");
        /*ResultResponse<Object> response = ResultResponse.builder()
                .resultCode("200")
                .message("성공적으로 이미지를 업로드하였습니다.")
                .data(imgResponse)
                .build();*/
        return new ResponseEntity(imgResponse, HttpStatus.OK);
    }
}
