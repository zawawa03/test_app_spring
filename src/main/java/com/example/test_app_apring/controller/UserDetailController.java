package com.example.test_app_apring.controller;

import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import com.example.test_app_apring.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserDetailController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserDetailController (UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/detail/{userId:.+}")
    public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {
        MUser user = userService.getUserOne(userId);
        user.setPassword(null);

        form = modelMapper.map(user, UserDetailForm.class);
        form.setSalaryList(user.getSalaryList());

        model.addAttribute("userDetailForm", form);

        return "user/detail";
    }

    @PostMapping(value="/detail", params="update")
    public String updateUser(UserDetailForm form, Model model) {
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());

        return "redirect:/user/list";
    }

    @PostMapping(value="/detail", params="delete")
    public String deleteUser(UserDetailForm form, Model model) {
        userService.deleteUserOne(form.getUserId());

        return "redirect:/user/list";
    }
}