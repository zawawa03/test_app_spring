package com.example.test_app_apring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j

public class ErrorAspect {
    @AfterThrowing(value = "execution(* *..*..*(..)) &&" + "(bean(*Controller) || bean(*Repository))", throwing="ex")
    public void throwingNull(DataAccessException ex) {
        log.error("DataAccessExcepttionが発生しました");
    }
}