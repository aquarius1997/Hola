<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/mydiariesHeader.jsp"%>

<!--Diary-->
<section class="diary">
    <div>
        <span>
            <img src="/resources/mydiary/images/IMG_1417.jpeg" alt="" style="cursor:pointer"/>
            <span id="diaryText">
                <span id="diaryTitle"><h2><c:out value="${diary.title}"/></h2></span>
                <span id="diaryDetails">
                    <div><c:out value="${diary.content}"/></div>
                </span>
            </span>
        </span>
    </div>
    <div class="extra-info">
        <span>heart : <c:out value="${diary.heart}"/></span>
        <span>mood_code : <c:out value="${diary.moodCode}"/></span>
        <span>reg_date : <c:out value="${diary.regDate}"/></span>
    </div>
</section>

<div class="buttons">
    <!-- 수정버튼-->
    <button data-oper="modify" class="btn-default"><a href="/my-diaries/<c:out value="${memberId}"/>/<c:out value="${diary.diaryId}"/>/detail">Modify</a></button>

    <!--삭제버튼 -->
    <button data-oper="remove" class="btn-danger">Remove</button>
</div>



<%@include file="../includes/mydiariesFooter.jsp"%>


