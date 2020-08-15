package com.hyegyeong.hola.mydiary.service;

import com.hyegyeong.hola.exception.BusinessException;
import com.hyegyeong.hola.mydiary.dto.MydiaryDto;

import java.util.List;


//이후 트랜잭션 설정하기
public interface MydiaryService {

    /**
     * 새로운 다이어리를 추가한다
     * @param mydiaryDTO 추가하려는 내용을 담고있는 다이어리 객체
     * @throws BusinessException if fail to save Diary, throws exception
     */
    public void insertDiary (MydiaryDto mydiaryDTO) throws BusinessException;

    /**
     * 특정 회원이 작성한 모든 다이어리 객체를 가져온다
     * @param memberId 회원 아이디 (이후에 PK가 아닌 사용자 닉네임으로 변경필수)
     * @return 특정 회원이 작성한 모든 다이어리 객체
     */
    public List<MydiaryDto> selectDiaryList (int memberId);

    /**
     * 특정 다이어리 하나를 읽는다
     * @param diaryId 읽어올 다이어리의 아이디
     * @return 읽어온 다이어리 객체
     * @throws BusinessException if fail to select one Diary, throws exception
     */
    public MydiaryDto selectDiary (int diaryId) throws BusinessException;

    /**
     * 다이어리 내용을 수정한다
     * @param mydiaryDTO 수정하려는 다이어리의 아이디
     * @return 수정한 다이어리 객체
     * @throws BusinessException if fail to update Diary, throws exception
     */
    public MydiaryDto updateDiary (MydiaryDto mydiaryDTO) throws BusinessException;

    /**
     * 다이어리를 삭제한다
     * @param diaryId 삭제하려는 다이어리 아이디
     * @throws BusinessException if fail to delete Diary, throws exception
     */
    public void deleteDiary (int diaryId) throws BusinessException;

}
