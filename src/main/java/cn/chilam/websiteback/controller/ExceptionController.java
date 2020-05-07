package cn.chilam.websiteback.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: website-back
 * @description: 异常捕获
 * @author: chilam
 * @create: 2020-04-02 21:44
 **/
@RestControllerAdvice
public class ExceptionController {

    // 捕捉CustomRealm抛出的异常
    @ExceptionHandler
    public void handleShiroException(Exception e) {
        System.out.print(e);
    }
}
