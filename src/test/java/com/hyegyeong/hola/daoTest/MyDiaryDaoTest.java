package com.hyegyeong.hola.daoTest;

import com.hyegyeong.hola.dao.MyDiaryDAO;
import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/applicationContext.xml"})
public class MyDiaryDaoTest {

    @Inject
    private MyDiaryDAO myDiaryDAO;

    /**
     * 회원이 새로운 여행 다이어리를 작성해 저장한다
     * (원래 파라미터로 들어온 다이어리 객체에서 회원 번호를 알아내서 저장하나, 회원 번호를 임의로 지정해주었음)
     * @throws Exception
     */
    @Test
    public void testInsertDiary() throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setTitle("testTitle3");
        diaryDTO.setContent("test Content3");
        diaryDTO.setMemberId(2);
        diaryDTO.setMoodCode("NO");
        diaryDTO.setOpnFlag("Y");

        int resultNums = myDiaryDAO.insertDiary(diaryDTO);
        log.info("success insert Diary : " + resultNums);
    }

    /**
     * 특정 회원이 작성한 다이어리를 모두 읽어온다
     * @throws BusinessException
     */
    @Test
    public void testSelectDiaryList() throws Exception {
        myDiaryDAO.selectDiaryList(1);
    }

    /**
     * 작성한 다이어리들 중 하나의 다이어리를 읽어온다.
     * @throws Exception
     */
    @Test
    public void testSelectDiary() throws Exception {
        myDiaryDAO.selectDiary(1);  //읽으려는 다이어리의 ID
    }

    /**
     * diaryId에 해당하는 다이어리를 수정한다
     * @throws Exception
     */
    @Test
    public void testUpdateDiary() throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDiaryId(1);
        diaryDTO.setTitle("updated Title3");
        diaryDTO.setContent("updated Content1");
        diaryDTO.setMoodCode("NA");
        diaryDTO.setOpnFlag("Y");

        int resultNums = myDiaryDAO.updateDiary(diaryDTO);
        log.info("success update Diary : " + resultNums);
    }

    @Test
    public void testDeleteDiary() throws Exception {
        int resultNums = myDiaryDAO.deleteDiary(1); //1번 다이어리를 삭제(삭제여부를 Y로 업데)
        log.info("success delete Diary : " + resultNums);
    }


}
