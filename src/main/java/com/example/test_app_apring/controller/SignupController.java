package com.example.test_app_apring.controller;

import java.util.Map;

import org.springframework.ui.Model;
import com.example.test_app_apring.sevice.UserApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SignupController {
    private final UserApplicationService userApplicationService;

    public SignupController (UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping("/signup")
    public String getSignup(Model model){
        Map<String, Integer> genderMap = userApplicationService.getGenderMap();
        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    @PostMapping("/signup")
    public String postSignup(){
        return "redirect:/login";
    }

}
