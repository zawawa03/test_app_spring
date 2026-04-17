package com.example.test_app_apring.rest;

import com.example.test_app_apring.domain.user.model.MUser;
import com.example.test_app_apring.domain.user.service.UserService;
import com.example.test_app_apring.form.GroupOrder;
import com.example.test_app_apring.form.SignupForm;
import com.example.test_app_apring.form.UserDetailForm;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final MessageSource messageSource;

    public UserRestController (UserService userService, ModelMapper modelMapper,  MessageSource messageSource) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.messageSource = messageSource;
    }
    //ユーザーを更新
    @PutMapping("/update")
    public int updateUser(UserDetailForm form) {
        userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());

        return 0;
    }

    //ユーザーを削除
    @DeleteMapping("/delete")
    public int deleteUser(UserDetailForm form) {
        userService.deleteUserOne(form.getUserId());
        return 0;
    }

    //ユーザーを登録
    @PostMapping("/signup/rest")
    public RestResult postSignup(@Validated(GroupOrder.class) SignupForm form,
                                 BindingResult bindingResult, Locale locale) {
        //入力チェック結果
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            //エラーメッセージ取得
            for (FieldError error : bindingResult.getFieldErrors()) {
                String message = messageSource.getMessage(error, locale);
                errors.put(error.getField(), message);
            }
            //エラー結果の返却
            return new RestResult(90, errors);
        }

        //formをMUerクラスに変換
        MUser user = modelMapper.map(form, MUser.class);

        //ユーザー登録
        userService.signup(user);

        //結果の返却
        return new RestResult(0, null);
    }
}