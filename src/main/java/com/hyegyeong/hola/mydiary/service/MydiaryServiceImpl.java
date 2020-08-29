package com.hyegyeong.hola.mydiary.service;

import com.hyegyeong.hola.commons.util.FileUtils;
import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.exception.ErrorCode;
import com.hyegyeong.hola.mydiary.dao.MydiaryDao;
import com.hyegyeong.hola.mydiary.dto.MydiaryDto;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
@Component
@Slf4j
public class MydiaryServiceImpl implements MydiaryService {

    @Setter(onMethod_ = @Autowired)
    private MydiaryDao myDiaryDao;

    @Resource(name="fileUtils")
    private FileUtils fileUtils;

    /**
     * 새로운 다이어리를 추가한다
     * @param diaryDTO 추가하려는 내용을 담고있는 다이어리 객체
     * @oaran multipartHttpServletRequest 첨부파일의 파라미터 값을 받는다
     * @throws BusinessException if fail to save Diary, throws exception
     */
    @Transactional
    @Override
    public void insertDiary (MydiaryDto diaryDTO, MultipartHttpServletRequest multipartHttpServletRequest) throws BusinessException {
        log.info("insertDiary");
        if(diaryDTO.getOpnFlag() == null) {
            diaryDTO.setOpnFlag("Y");
        }
        if(diaryDTO.getMoodCode() == null) {
            diaryDTO.setMoodCode("NO");
        }
        int recordNums = myDiaryDao.insertDiary(diaryDTO);  //insert 영향을 받은 레코드의 수 알아내
        if(recordNums == 0 || recordNums > 1) {   //하나의 게시물만 생성됐는지 확인하고 아닐 경우 예외발생
            new BusinessException(ErrorCode.INSERT_FAIL);
        }

        try {
            log.info("첨부파일 추가");
            List<Map<String, Object>> list = fileUtils.parseInsertFileInfo(diaryDTO, multipartHttpServletRequest);

            int size = list.size();
            for(int i=0; i<size; i++) {
                myDiaryDao.insertFile(list.get(i));
            }
        } catch (Exception e) {
            log.info("Exception in insertDiary(첨부파일 추가 시 Exception 발생");
            e.printStackTrace();
        }

    }

    /**
     * 특정 회원이 작성한 모든 다이어리 객체를 가져온다
     * @param memberId 회원 아이
     * @return 특정 회원이 작성한 모든 다이어리 객체
     */
    @Override
    public List<MydiaryDto> selectDiaryList (int memberId) {
        log.info("selectDiaryList()");
        return myDiaryDao.selectDiaryList(memberId);
    }

    /**
     * 특정 다이어리 하나를 읽는다
     * @param diaryId 읽어려는 다이어리의 아이디
     * @return 읽어온 다이어리 객체
     * @throws BusinessException if fail to select one Diary, throws exception
     */
    @Override
    public MydiaryDto selectDiary (int diaryId) throws BusinessException {
        log.info("selectDiary(diaryId)");
        return myDiaryDao.selectDiary(diaryId);
    }

    /**
     * 다이어리 내용을 수정한다
     * 중간에 입력안된 속성은 여기서 처리해주는걸로 수정해야함. 수정 시간 업데이트 필요함
     * @param diaryDTO 수정하려는 다이어리의 아이디
     * @return 수정한 다이어리 객체
     * @throws BusinessException if fail to update Diary, throws exception
     */
    @Override
    public MydiaryDto updateDiary (MydiaryDto diaryDTO) throws BusinessException {
        log.info("updateDiary(diaryDTO)");
        if(diaryDTO.getOpnFlag() == null) {
            diaryDTO.setOpnFlag("Y");
        }
        if(diaryDTO.getMoodCode() == null) {
            diaryDTO.setMoodCode("NO");
        }
        int recordNums = myDiaryDao.updateDiary(diaryDTO);  //update 영향을 받은 레코드 수 알아내서
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
        int recordNums = myDiaryDao.deleteDiary(diaryId);    //delete 영향을 받은 레코드 수 알아내서
        if(recordNums == 0) {   //삭제된 데이터가 없으면 예외
            new BusinessException(ErrorCode.BOARD_NOT_EXIST);
        }
    }
}
