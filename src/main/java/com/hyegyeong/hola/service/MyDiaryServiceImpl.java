package com.hyegyeong.hola.service;


import com.hyegyeong.hola.dao.MyDiaryDAO;
import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@AllArgsConstructor
@Service
@Component
@Slf4j
public class MyDiaryServiceImpl implements MyDiaryService{


    @Setter(onMethod_ = @Autowired)
    private MyDiaryDAO myDiaryDAO;


    /**
     * 새로운 다이어리를 추가한다
     * @param diaryDTO 추가하려는 내용을 담고있는 다이어리 객체
     */
    @Override
    public void insertDiary(DiaryDTO diaryDTO) throws BusinessException {
        log.info("Call - insertDiary from MyDiaryServiceImpl");
        int recordNum = myDiaryDAO.insertDiary(diaryDTO);
        if(recordNum == 0 || recordNum > 1) {   //하나의 게시물만 생성됐는지 확인하고 아닐 경우 예외발생
            new BusinessException(ErrorCode.INSERT_FAIL);
        }
    }

    /**
     * 작성한 모든 다이어리 객체를 가져온다 (추후 수정 필요. 멤버 아이디가 변수로 들어가야함)
     * @return 작성한 모든 다이어리 객체
     */
    @Override
    public List<DiaryDTO> selectDiaryList() {
        log.info("Call - selectDiaryList() from MyDiaryServiceImpl");
        return myDiaryDAO.selectDiaryList();
    }

    /**
     * 특정 다이어리 하나를 읽는다
     * @param diaryId 읽어려는 다이어리의 아이디
     * @return 읽어온 다이어리 객체
     */
    @Override
    public DiaryDTO selectDiary(Long diaryId) throws BusinessException {
        log.info("Call - selectDiary(diaryId) from MyDiaryServiceImpl");
        return myDiaryDAO.selectDiary(diaryId);
    }

    /**
     * 다이어리 내용을 수정한다
     * @param diaryDTO 수정하려는 다이어리의 아이디
     * @return 수정한 다이어리 객체
     */
    @Override
    public DiaryDTO updateDiary(DiaryDTO diaryDTO) {
        log.info("Call - updateDiary(diaryDTO) from MyDiaryServiceImpl");
        
    }

    /**
     * 다이어리를 삭제한다
     * @param diaryId 삭제하려는 다이어리 아이디
     */
    @Override
    public void deleteDiary(Long diaryId) {

    }
}
