package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @RequestMapping({
        "/", 
        "/{x:^(?!api$).*$}", 
        "/{x:^(?!api$).*$}/**"
    })
    public String forward() {
        return "forward:/index.html";
    }
}


