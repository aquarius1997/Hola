<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
                <img src="/resources/mydiary/images/IMG_1417.jpeg" alt=""/>
            </span>
        </article>
    </c:forEach>
</section>




