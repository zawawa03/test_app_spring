package com.example.test_app_apring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }

    @PostMapping("/hello")
    public String postRequest(@RequestParam("name") String str, Model model) {
        model.addAttribute("sample", str);
        return "response";
    }
}