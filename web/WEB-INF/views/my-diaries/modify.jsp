<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/mydiariesHeader.jsp"%>

<style>
    /*.input-default {*/
    /*    border-radius:0.375em;*/
    /*    border: solid 1px rgba(210, 215, 217, 0.75);*/
    /*    width:100%;*/

    /*}*/
</style>

<!--Diary-->
<section class="diary">
    <form role="form" action="/my-diaries/diary" method="post">
                    <span>
                        <img src="/resources/mydiary/images/IMG_1417.jpeg" alt="" style="cursor:pointer"/>
                        <span id="diaryText">
                            <span id="diaryTitle"><h2><input class="input-default" id="input-title" name="title" value="title" placeholder="Title"></h2></span>
                            <span id="diaryDetails">
                                <div><input class="input-default" name="content" id="input-content" value="content" placeholder="Content"></div>
                            </span>
                        </span>
                    </span>

    </form>

</section>


<%@include file="../includes/mydiariesFooter.jsp"%>
