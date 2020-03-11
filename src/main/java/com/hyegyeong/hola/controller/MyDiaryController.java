package com.hyegyeong.hola.controller;

import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.service.MyDiaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyDiaryController {

    private MyDiaryService myDiaryService;

    /* try catch 말고 어노테이션으로 처리하기
    @PostMapping("/m")
    public void saveDiary() {

        try {
            myDiaryService.insertDiay();
        } catch(BusinessException e){
            String msg = e.getMessage();
            System.out.println(msg);
            System.out.println();
            e.printStackTrace();;
        }
    }
    */
}
