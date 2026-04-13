package com.example.test_app_apring.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    /** サービス開始前にログを出力する */
    @Before("execution(* *..*.*UserService.*(..))")
    public void startLog(JoinPoint jp){
        log.info("メソッド開始" + jp.getSignature());
    }

    /** サービス実行後にログを出力 */
    @After("execution(* *..*.*UserService.*(..))")
    public void endLog(JoinPoint jp){
        log.info("メソッド終了" + jp.getSignature());
    }

    /** コントローラー実行前後でログを出力 */
    //@Around("bean(*Controller)")
    //@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {
        log.info("メソッド開始" + jp.getSignature());

        try{
            Object result = jp.proceed();
            log.info("メソッド終了" + jp.getSignature());
            return result;
        } catch (Exception e) {
            log.error("メソッド異常終了" + jp.getSignature());
            throw e;
        }
    }
}