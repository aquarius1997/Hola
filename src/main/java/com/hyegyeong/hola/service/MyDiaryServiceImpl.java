package com.hyegyeong.hola.service;


import com.hyegyeong.hola.dao.MyDiaryDAO;
import com.hyegyeong.hola.dto.DiaryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Component
public class MyDiaryServiceImpl implements MyDiaryService{

    private static final Logger logger = LoggerFactory.getLogger(MyDiaryServiceImpl.class);

    @Setter(onMethod_ = @Autowired)
    private MyDiaryDAO myDiaryDAO;


    /**
     * 새로운 다이어리를 추가한다
     * @param diaryDTO 추가하려는 내용을 담고있는 다이어리 객체
     */
    public void insertDiay(DiaryDTO diaryDTO) {
        logger.info("Call - insertDiary from MyDiaryServiceImpl");
    };

    /**
     * 작성한 모든 다이어리 객체를 가져온다 (추후 수정 필요. 멤버 아이디가 변수로 들어가야함)
     * @return 작성한 모든 다이어리 객체
     */
    public List<DiaryDTO> selectDiaryList() {

    };

    /**
     * 특정 다이어리 하나를 읽는다
     * @param diaryId 읽어려는 다이어리의 아이디
     * @return 읽어온 다이어리 객체
     */
    public DiaryDTO selectDiary(Long diaryId) {

    };

    /**
     * 다이어리 내용을 수정한다
     * @param diaryDTO 수정하려는 다이어리의 아이디
     * @return 수정한 다이어리 객체
     */
    public DiaryDTO updateDiary(DiaryDTO diaryDTO) {

    };

    /**
     * 다이어리를 삭제한다
     * @param diaryId 삭제하려는 다이어리 아이디
     */
    public void deleteDiary(Long diaryId) {

    };
}
