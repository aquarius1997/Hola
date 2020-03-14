package com.hyegyeong.hola.controller;

import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.service.MyDiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/my-diaries")
public class MyDiaryController {

    private MyDiaryService myDiaryService;

    /**
     * Create new trip diary
     */
    @PostMapping("/diary")
    public void saveDiary(@RequestBody DiaryDTO diaryDTO) throws BusinessException{
        log.info("saveDiary.. diaryDTO : " + diaryDTO);
        myDiaryService.insertDiary(diaryDTO);
    }

    /**
     * Get All User's Trip Diary
     * @param memberId User Id
     * @return All Trip Diary's List
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<List<DiaryDTO>> getDiaryList(@PathVariable("memberId") final int memberId) {
        log.info("getDiaryList.. @PathVariable(memberId)  : " + memberId);
        return ResponseEntity.status(HttpStatus.OK).body(myDiaryService.selectDiaryList(memberId));
    }

    /**
     * Get One User's Trip Diary
     * 수정필요
     * @param diaryId Diary Id
     * @return DiaryDTO
     */
    @GetMapping("/{memberId}/{diaryId}")
    public ResponseEntity<DiaryDTO> getDiary(@PathVariable("memberId") final int memberId, @PathVariable("diaryId") final int diaryId) {
        log.info("getDiary.. @PathVariable(diaryId) : " + diaryId);
        return ResponseEntity.status(HttpStatus.OK).body(myDiaryService.selectDiary(diaryId));
    }

}
