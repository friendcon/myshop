package com.project.myshop.viewcontroller;

import com.project.myshop.controller.dto.MemberLoginRequest;
import com.project.myshop.service.MemberService;
import com.project.myshop.util.customexception.LoginFailException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/login")
    public String dologin(@Valid MemberLoginRequest memberLoginRequest, HttpSession httpSession) {
        log.info("do login. . . ");
        boolean result = memberService.loginAuth(memberLoginRequest);
        if(!result) {
            throw new LoginFailException("아이디와 패스워드를 확인해주세요.");
        } else {
            httpSession.setAttribute("memberid", memberLoginRequest.getUsername());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String dologout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}
