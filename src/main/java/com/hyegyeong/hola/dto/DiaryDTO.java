package com.hyegyeong.hola.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DiaryDTO {
    private Long diaryId;   //게시물번호
    private String title;   //제목
    private String content; //본문
    private String memberId;    //작성자(회원번호)
    private String moodCode;    //MoodCode
    private Long heart; //하트수
    private String opnFlag; //공유여부
    private Date regDate;   //작성날짜
    private Date updDate;   //수정날짜
    private String delFlag;  //삭제여부
}
