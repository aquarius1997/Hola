package com.hyegyeong.hola.controller;

import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.service.MyDiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/my-diaries")
public class MyDiaryController {

    private MyDiaryService myDiaryService;

    /**
     * Create New Diary
     * @param diaryDTO DiaryDTO
     * @throws BusinessException if fail to save Diary, throws exception
     */
    @PostMapping("/diary")
    public void saveDiary (@RequestBody DiaryDTO diaryDTO) throws BusinessException{
        log.info("saveDiary.. diaryDTO : " + diaryDTO);
        myDiaryService.insertDiary(diaryDTO);
    }

    /**
     * Get All User's Trip Diary
     * 이후 사용자 PK가 아니라 닉네임(사용자 설정 아이디)으로 변경해야함
     * @param memberId User Id
     * @return All Trip Diary's List
     */
    @GetMapping("/{memberId}")
    public ModelAndView getDiaryList (@PathVariable("memberId") final int memberId) {
        log.info("getDiaryList.. @PathVariable(memberId)  : " + memberId);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("my-diaries/list");
        modelAndView.addObject("memberId", memberId);
        modelAndView.addObject("diaryList", myDiaryService.selectDiaryList(memberId));
        return modelAndView;
    }

    /**
     * Get One User's Trip Diary
     * @param memberId User Id
     * @param diaryId Diary Id
     * @return Diary ModelAndView
     * @throws BusinessException if fail to get one Diary, throws exception
     */
    @GetMapping("/{memberId}/{diaryId}")
    public ModelAndView getDiary (@PathVariable("memberId") final int memberId,
                                              @PathVariable("diaryId") final int diaryId) throws BusinessException {
        log.info("getDiary...");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("my-diaries/diary");
        modelAndView.addObject("memberId", memberId);
        modelAndView.addObject("diary", myDiaryService.selectDiary(diaryId));
        return modelAndView;
    }

    /**
     * Get Modify Page
     * @param memberId User Id
     * @param diaryId Diary Id
     * @return Modify ModelAndView
     * @throws BusinessException
     */
    @GetMapping("/{memberId}/{diaryId}/detail")
    public ModelAndView getModify (@PathVariable("memberId") final int memberId,
                                  @PathVariable("diaryId") final int diaryId) throws BusinessException {
        log.info("getModify...");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("my-diaries/modify");
        modelAndView.addObject("memberId", memberId);
        modelAndView.addObject("diary", myDiaryService.selectDiary(diaryId));
        return modelAndView;
    }


    /**
     * Update Trip Diary
     * @param diaryDTO DiaryDTO
     * @return Updated Trip Diary
     * @throws BusinessException if fail to update one Diary, throws exception
     */
    @PutMapping("/diary")
    public String updateDiary (@RequestBody DiaryDTO diaryDTO) throws BusinessException {
        log.info("updateDiary...");

        DiaryDTO diaryDTO1 = myDiaryService.updateDiary(diaryDTO);

        return "redirect:/my-diaries/" + diaryDTO1.getMemberId();
    }

/*    @PutMapping("/diary")
    public ResponseEntity<DiaryDTO> updateDiary (@RequestBody DiaryDTO diaryDTO) throws BusinessException {
        log.info("updateDiary...");
        return ResponseEntity.status(HttpStatus.OK).body(myDiaryService.updateDiary(diaryDTO));
    }*/

    /**
     * Delete Trip Diary
     * @param diaryId DiaryDTO
     * @return result of delete operation
     * @throws BusinessException if fail to delete one Diary, throws exception
     */
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<String> deleteDiary (@PathVariable("diaryId") final int diaryId) throws BusinessException {
        log.info("deleteDiary...");
        myDiaryService.deleteDiary(diaryId);
        return ResponseEntity.status(HttpStatus.OK).body("Delete success");
    }


}
