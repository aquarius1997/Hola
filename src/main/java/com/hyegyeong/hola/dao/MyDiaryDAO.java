package com.hyegyeong.hola.dao;

import com.hyegyeong.hola.dto.DiaryDTO;
import com.hyegyeong.hola.exception.BusinessException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
@Mapper
public class MyDiaryDAO {

    @Inject
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.hyegyeong.hola.mappers.diaryMapper";

    /**
     * 새로운 다이어리 추기
     * @param diaryDTO 추가하려는 내용을 담고있는 다이어리 객체
     * @return insert에 의해 영향을 받은 레코드 수
     */
    public int insertDiary(DiaryDTO diaryDTO) {
        return sqlSession.insert(NAMESPACE + ".insertDiary", diaryDTO);
    }

    /**
     * 작성한 모든 다이어리 객체를 가져온다 (추후 수정필요. 멤버 아이디가 변수로 들어가야 함)
     * @return 작성한 모든 다이어리 객체
     */
    public List<DiaryDTO> selectDiaryList() {
        List<DiaryDTO> diaryList;
        diaryList = sqlSession.selectList(NAMESPACE + ".selectDiaryList");
        return diaryList;
    }

    /**
     * 특정 다이어리 하나를 읽어온다
     * @param diaryId 읽으려는 다이어리의 아이디
     * @return 읽어온 다이어리 객체
     */
    public DiaryDTO selectDiary(Long diaryId) throws Exception {
        DiaryDTO diaryDTO = sqlSession.selectOne(NAMESPACE + ".selectDiary", diaryId);
        return diaryDTO;
    }

    /**
     * 다이어리 내용을 수정한다
     * @param diaryDTO 수정하려는 다이어리의 아이디
     * @return update영향을 받은 레코드 수
     */
    public int updateDiary(DiaryDTO diaryDTO) {
        return sqlSession.update(NAMESPACE + ".updateDiary", diaryDTO);
    }

    /**
     * 다이어리를 삭제한다
     * @param diaryId 삭제하려는 다이어리 이이디
     * @return delete영향을 받은 레코드 수
     */
    public int deleteDiary(Long diaryId) {
        return sqlSession.delete(NAMESPACE + ".deleteDiary", diaryId);
    }

}
