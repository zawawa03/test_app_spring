package com.example.test_app_apring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice {
    @ExceptionHandler(DataAccessException.class)
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {
        log.error("DBエラー発生", e);
        model.addAttribute("error", "");
        model.addAttribute("message", "SignupControllerで例外が発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {
        log.error("システムエラー発生", e);
        model.addAttribute("error", "");
        model.addAttribute("message", "SignupControllerで例外が発生しました");
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        return "error";
    }
}