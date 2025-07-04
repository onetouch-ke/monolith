package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping(value = {
        "/",                     // 루트
        "/{path:^(?!api|static|favicon\\.ico|.*\\.(js|css|png|json)).*$}", 
        "/**/{path:^(?!api|static|favicon\\.ico|.*\\.(js|css|png|json)).*$}" 
    })
    public String forward() {
        return "forward:/index.html";
    }
}

