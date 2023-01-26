package com.project.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
