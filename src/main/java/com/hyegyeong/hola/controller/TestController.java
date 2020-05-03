package com.hyegyeong.hola.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Controller
@AllArgsConstructor
public class TestController {

    @RequestMapping("/test")
    public String test() {
        String str = "index";
        return str;
    }
}
