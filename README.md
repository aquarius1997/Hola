# Hola
나만의 여행다이어리를 만들고 공유할 수 있는 웹 어플리케이션

크게 네 가지의 기능을 구현할 예정
1. 인기 게시물(여행 다이어리)을 본다
2. 친구가 올린 게시물들을 본다
3. 전체 게시물을 본다
4. 나의 여행 다이어리를 관리한다

-------------------------------

## Prerequisites

* jdk1.8 (Open jdk - Zulu)
* Spring Framework (4.3.18.RELEASE)
  - JSP
  - Lombok
  - MVC Model
* MySQL DB
* Maven

--------------------------------

## Structure

1. DIARY_MST : 여행 다이어리

2. DIARY_TAGS : 태그 테이블

3. DIARY_ATTACHS : 첨부사진 테이블

4. DIARY_CMTS : 댓글 테이블

5. MEMBERS_MST : 회원 테이블

6. MEMBER_PHOTOS : 회원 사진

7. MEMBER_FOLLS : 회원 팔로우현황

8. AUTHORITIES : 권한정보 테이블

9. ROLES : 역할정보 테이블

10. MOOD_CODES

--------------------------------

## API URL

현재까지 컨트롤러로 구현한 API URL

1. 사용자 페이지

**/my-diaries** 하위

| Method | Path | Parameter | return | Redirect | Path 예시 |
|:---:|:---:|:---:|:---:|:---:|:---:|
|GET|/{memberId}|.|사용자가 등록한 전체 다이어리 View|.|/my-diaries/1|
|GET|/{memberId}/{diaryId}|.|사용자의 특정 다이어리 View|.|/my-diaries/1/1|
|GET|/{memberId}/newDiary|.|등록하고자 하는 새로운 다이어리 View|/{memberId}|/my-diaries/1/newDiary|
|POST|/{memberId}|.|다이어리가 추가 된 이후의 사용자가 등록한 전체 다이어리 View|.|/my-diaries/1|
|GET|/{memberId}/{diaryId}/detail|.|수정(or 삭제)하고자 하는 다이어리 내용 View|/{memberId}|/my-diaries/1/1/detail|
|PUT|/{memberId}/{diaryId}|.|수정한 다이어리 ResponseBody|.|/my-diaries/1/1|
|DELETE|/{memberId}|.|삭제한 다이어리 ResponseBody|.|/my-diaries/1|



