package com.hyegyeong.hola.mydiary.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MydiaryDto {
    private int diaryId;   //게시물번호
    private String title;   //제목
    private String content; //본문
    private int memberId;    //작성자(회원번호)
    private String moodCode;    //MoodCode
    private int heart; //하트수
    private String opnFlag; //공유여부
    private Date regDate;   //작성날짜
    private Date updDate;   //수정날짜
    private String delFlag;  //삭제여부다

    //첨부파일관련
    private String[] files;

    public void setFiles(String[] files) {  //게시글 입력/수정시 첨부파일 개수를 업데이트한다
        this.files = files;
    }
}
