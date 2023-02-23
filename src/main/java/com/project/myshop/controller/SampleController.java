package com.project.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
    @GetMapping("/")
    public String hello() {
        System.out.println("메인페이지에 들어왔음");
        return "index";
    }
}
