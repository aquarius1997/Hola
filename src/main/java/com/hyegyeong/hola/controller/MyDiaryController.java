package com.hyegyeong.hola.controller;

import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.service.MyDiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/my-diaries")
public class MyDiaryController {

    private MyDiaryService myDiaryService;

    //Create My Trip Diary

    /**
     * Create new trip diary
     */
    @PostMapping("/diary")
    public void saveDiary(@RequestBody DiaryDTO diaryDTO) throws BusinessException{
        myDiaryService.insertDiary(diaryDTO);
    }


}
