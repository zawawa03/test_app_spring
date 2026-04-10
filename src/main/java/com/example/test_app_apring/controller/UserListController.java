package com.example.test_app_apring.controller;


import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserListController {
    private final UserService userService;

    public UserListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String getUserList(Model model){
        List<MUser> userList = userService.getUsers();
        model.addAttribute("userList", userList);
        return "user/list";
    }
}