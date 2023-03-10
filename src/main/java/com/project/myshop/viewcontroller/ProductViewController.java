package com.project.myshop.viewcontroller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/product")
public class ProductViewController {
    @GetMapping("/fashion")
    public void getFashionPage() {
        log.info("fashion page. . .");
    }
}
