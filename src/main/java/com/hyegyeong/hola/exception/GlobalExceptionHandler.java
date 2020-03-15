package com.hyegyeong.hola.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * RestController에서 발생하는 예외를 Global 레벨에서 처리
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //이후에 에러 관리페이지 따로 만들어야함!!!!
    //BusinessException에 대해 전역적으로 예외 처리
    @ExceptionHandler(value = BusinessException.class)
    public String handleBusinessExceptionForGlobal (BusinessException e) {
        log.error(e.getMessage());
        return  e.getMessage();
    }
}
