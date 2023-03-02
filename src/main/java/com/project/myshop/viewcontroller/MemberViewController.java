package com.project.myshop.viewcontroller;

import com.project.myshop.controller.dto.MemberLoginRequest;
import com.project.myshop.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/member")
public class MemberViewController {

    @Autowired
    private MemberService memberService;
    @GetMapping("/signup")
    public void signup() {
        log.info("sign up...");
    }

    @GetMapping("/login")
    public void login() {
        log.info("login. . .");
    }

    @GetMapping("/mypage")
    public void mypage() {
        log.info("mypage . . .");
    }

    @PostMapping("/login")
    public String dologin(@Valid MemberLoginRequest memberLoginRequest, HttpSession httpSession) {
        log.info("do login. . . ");
        boolean result = memberService.loginAuth(memberLoginRequest);
        if(!result) {
            return "redirect:/member/login";
            // throw new LoginFailException("아이디와 패스워드를 확인해주세요.");
        } else {
            httpSession.setAttribute("memberid", memberLoginRequest.getUsername());
            return "redirect:/";
        }

    }

    @GetMapping("/logout")
    public String dologout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
