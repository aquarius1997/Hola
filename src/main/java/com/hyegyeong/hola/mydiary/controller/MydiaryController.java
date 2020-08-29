package com.hyegyeong.hola.mydiary.controller;

import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.mydiary.dto.MydiaryDto;
import com.hyegyeong.hola.mydiary.service.MydiaryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/my-diaries")
public class MydiaryController {

    private MydiaryService myDiaryService;

    /**
     * Create New Diary
     * @param diaryDTO DiaryDTO
     * @oaran multipartHttpServletRequest 첨부파일의 파라미터 값을 받는다
     * @throws BusinessException if fail to save Diary, throws exception
     */
    @PostMapping("/{memberId}")
    @ResponseBody
    public MydiaryDto saveDiary (@PathVariable("memberId") final int memberId,
                                 @RequestBody MydiaryDto diaryDTO,
                                 MultipartHttpServletRequest multipartHttpServletRequest) throws BusinessException{
        log.info("saveDiary.. diaryDTO : " + diaryDTO);
        myDiaryService.insertDiary(diaryDTO, multipartHttpServletRequest);
        return diaryDTO;
    }

    /**
     * 새로운 다이어리를 등록하는 페이지를 가져온다
     * @param memberId 등록하려는 사람의 memberId
     */
    @GetMapping("/{memberId}/newDiary")
    public ModelAndView getSaveDiaryPage (@PathVariable("memberId") final int memberId) {
        log.info("getSaveDiaryPage...");
        ModelAndView modelAndView = new ModelAndView();
        MydiaryDto diaryDTO = new MydiaryDto();
        modelAndView.setViewName("my-diaries/register");
        modelAndView.addObject("memberId", memberId);
        modelAndView.addObject("diary", diaryDTO);   //새로 추가할 다이어리 내용
        return modelAndView;
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
    @ResponseBody
    public MydiaryDto updateDiary (@RequestBody MydiaryDto diaryDTO) throws BusinessException {
        log.info("updateDiary...");

        MydiaryDto diaryDTO1 = myDiaryService.updateDiary(diaryDTO);
        return diaryDTO1;
    }


    /**
     * Delete Trip Diary
     * @param diaryDTO DiaryDTO
     * @return result of delete operation
     * @throws BusinessException if fail to delete one Diary, throws exception
     */
    @DeleteMapping("/{memberId}")
    @ResponseBody
    public MydiaryDto deleteDiary (@RequestBody MydiaryDto diaryDTO) throws BusinessException {
        log.info("deleteDiary...");
        myDiaryService.deleteDiary(diaryDTO.getDiaryId());
        log.info("ResponseBody check");
        return diaryDTO;
    }


}
