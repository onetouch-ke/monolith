package com.example.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping(value = {
        "/{path:[^\\.]*}",              // 슬래시 + 점 없는 단일 경로
        "/{path:^(?!api|static).*$}/**" // api, static 제외 모든 서브 경로
    })
    public String forward() {
        System.out.println("Forwarding to index.html");
        return "forward:/index.html";
    }
}
