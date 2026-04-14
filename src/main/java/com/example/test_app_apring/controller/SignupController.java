package com.example.test_app_apring.controller;

import java.util.Locale;
import java.util.Map;

import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import com.example.test_app_apring.form.GroupOrder;
import com.example.test_app_apring.form.SignupForm;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import com.example.test_app_apring.sevice.UserApplicationService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
@Slf4j
public class SignupController {
    private final UserApplicationService userApplicationService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public SignupController (UserApplicationService userApplicationService,
                             ModelMapper modelMapper,
                             UserService userService) {
        this.userApplicationService = userApplicationService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }



    @GetMapping("/signup")
    public String getSignup(Model model, Locale locale, @ModelAttribute("signupForm") SignupForm form){
        Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
        model.addAttribute("genderMap", genderMap);
        return "user/signup";
    }

    @PostMapping("/signup")
    public String postSignup(Model model, Locale locale, @ModelAttribute("signupForm") @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return getSignup(model, locale, form);
        }

        log.info(form.toString());

        MUser user = modelMapper.map(form, MUser.class);
        userService.signup(user);

        return "redirect:/login";
    }

    /** 例外処理 */
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {
        model.addAttribute("error", "");

        model.addAttribute("message", "SignupControllerで例外が発生しました");

        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        model.addAttribute("error", "");
        model.addAttribute("message", "SignupControllerで例外が発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
}
