<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../includes/mydiariesHeader.jsp"%>


<!-- Banner -->
<section id="banner">
    <span class="profileImg"><img src="/resources/mydiary/images/profileImg.JPG" alt="그림아직지정못함"></span>
    <span>
        <header id="profileText">
            <h1>User Message</h1>
        </header>
    </span>
</section>

<!-- diaries : 다이어리 사진들 보여주기-->
<section class="diaries">
    <c:forEach items="${diaryList}" var="diary">
        <article>
            <span class="image">
                <img src="/resources/mydiary/images/IMG_1417.jpeg" alt="" onclick="location.href='/my-diaries/<c:out value="${memberId}"/>/<c:out value="${diary.diaryId}"/>'" style="cursor:pointer"/>
            </span>
        </article>
    </c:forEach>
</section>



<%@include file="../includes/mydiariesFooter.jsp"%>


