package com.hyegyeong.hola.service;


import com.hyegyeong.hola.dao.MyDiaryDAO;
import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
@Component
@Slf4j
public class MyDiaryServiceImpl implements MyDiaryService {


    @Setter(onMethod_ = @Autowired)
    private MyDiaryDAO myDiaryDAO;


    /**
     * 새로운 다이어리를 추가한다
     * @param diaryDTO 추가하려는 내용을 담고있는 다이어리 객체
     * @throws BusinessException if fail to save Diary, throws exception
     */
    @Override
    public void insertDiary (DiaryDTO diaryDTO) throws BusinessException {
        log.info("insertDiary");
        if(diaryDTO.getOpnFlag() == null) {
            diaryDTO.setOpnFlag("Y");
        }
        if(diaryDTO.getMoodCode() == null) {
            diaryDTO.setMoodCode("NO");
        }
        int recordNums = myDiaryDAO.insertDiary(diaryDTO);  //insert 영향을 받은 레코드의 수 알아내
        if(recordNums == 0 || recordNums > 1) {   //하나의 게시물만 생성됐는지 확인하고 아닐 경우 예외발생
            new BusinessException(ErrorCode.INSERT_FAIL);
        }
    }

    /**
     * 특정 회원이 작성한 모든 다이어리 객체를 가져온다
     * @param memberId 회원 아이
     * @return 특정 회원이 작성한 모든 다이어리 객체
     */
    @Override
    public List<DiaryDTO> selectDiaryList (int memberId) {
        log.info("selectDiaryList()");
        return myDiaryDAO.selectDiaryList(memberId);
    }

    /**
     * 특정 다이어리 하나를 읽는다
     * @param diaryId 읽어려는 다이어리의 아이디
     * @return 읽어온 다이어리 객체
     * @throws BusinessException if fail to select one Diary, throws exception
     */
    @Override
    public DiaryDTO selectDiary (int diaryId) throws BusinessException {
        log.info("selectDiary(diaryId)");
        return myDiaryDAO.selectDiary(diaryId);
    }

    /**
     * 다이어리 내용을 수정한다
     * 중간에 입력안된 속성은 여기서 처리해주는걸로 수정해야함. 수정 시간 업데이트 필요함
     * @param diaryDTO 수정하려는 다이어리의 아이디
     * @return 수정한 다이어리 객체
     * @throws BusinessException if fail to update Diary, throws exception
     */
    @Override
    public DiaryDTO updateDiary (DiaryDTO diaryDTO) throws BusinessException {
        log.info("updateDiary(diaryDTO)");
        if(diaryDTO.getOpnFlag() == null) {
            diaryDTO.setOpnFlag("Y");
        }
        if(diaryDTO.getMoodCode() == null) {
            diaryDTO.setMoodCode("NO");
        }
        int recordNums = myDiaryDAO.updateDiary(diaryDTO);  //update 영향을 받은 레코드 수 알아내서
        if(recordNums == 0) {   //업데이트된게 없으면 예외던지기
            new BusinessException(ErrorCode.BOARD_NOT_EXIST);
        }
        //수정완료되면 수정된 다이어리 내용을 리턴
        return selectDiary(diaryDTO.getDiaryId());
    }

    /**
     * 다이어리를 삭제한다
     * @param diaryId 삭제하려는 다이어리 아이디
     * @throws BusinessException if fail to delete Diary, throws exception
     */
    @Override
    public void deleteDiary (int diaryId) throws BusinessException {
        log.info("deleteDiary(diaryId)");
        int recordNums = myDiaryDAO.deleteDiary(diaryId);    //delete 영향을 받은 레코드 수 알아내서
        if(recordNums == 0) {   //삭제된 데이터가 없으면 예외
            new BusinessException(ErrorCode.BOARD_NOT_EXIST);
        }
    }
}
