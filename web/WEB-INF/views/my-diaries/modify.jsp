<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/mydiariesHeader.jsp"%>

<!--Diary-->
<section class="diary">
    <form id="form">
        <div class="diaryMain">
            <span>
                <img src="/resources/mydiary/images/IMG_1417.jpeg" alt="" style="cursor:pointer"/>
                <span id="diaryText">
                    <span id="diaryTitle"><input class="input-default" id="input-title" value="<c:out value="${diary.title}"/>" placeholder="Title"></span>
                    <span id="diaryDetails">
                        <div><textarea class="input-default" id="input-content" placeholder="Content"><c:out value="${diary.content}"/></textarea></div>
                    </span>
                </span>
            </span>
        </div>

        <div class="outer-diaryEx">
            <div class="diaryEx">
                <span>
                    <p>Mood</p>

                    <select id="moodCode" class="select-basic">
                        <option value="<c:out value="${diary.moodCode}"/>"><c:out value="${diary.moodCode}"/></option>
                        <option value="NA">Angry</option>
                        <option value="ND">Depressed</option>
                        <option value="NO">Nothing</option>
                        <option value="NA">Sad</option>
                        <option value="PA">Amused</option>
                        <option value="PC">Cheerful</option>
                        <option value="PH">Happy</option>
                        <option value="PL">Loving</option>
                        <option value="PP">Peaceful</option>
                    </select>
                </span>
                <span>
                    <p>공개 설정</p>
                    <select id="opnFlag" class="select-basic">
                        <option value="<c:out value="${diary.opnFlag}"/>"><c:out value="${diary.opnFlag}"/></option>
                        <option value="N">비공개</option>
                        <option value="Y">공개</option>
                    </select>
                </span>
            </div>
            <button data-oper="remove" class="btn-danger" type="submit">Remove</button>
            <button data-oper="modify" class="btn-default" type="submit">Modify</button>
        </div>

    </form>

</section>


<!--Diary-->

<script type="text/javascript">
    $(document).ready(function() {

       $('button').on("click", function(e) {
           e.preventDefault();

           var operation = $(this).data("oper");


           if(operation === 'remove') {

           } else if (operation === 'modify') {
                console.log("modify clicked");

                var formObj = {}
                formObj["title"] = $("#input-title").val();
                formObj["content"] = $("#input-content").val();
                formObj["moodCode"] = $("#moodCode").val();
                formObj["opnFlag"] = $("#opnFlag").val();
                formObj["memberId"] = <c:out value="${memberId}"/>
                formObj["diaryId"] = <c:out value="${diary.diaryId}"/>

                console.log(formObj);

               $.ajax({
                    type:"put",
                    url:"/my-diaries/diary",
                    contentType: "application/json; charset=UTF-8",
                    dataType: "json",
                    data:JSON.stringify(formObj),
                   success : function() {
                       console.log("SUCCESS");
                       window.location.href = "/my-diaries/<c:out value="${memberId}"/>/<c:out value="${diary.diaryId}"/>";
                   }
                });
           }
       });
    });
</script>

<%@include file="../includes/mydiariesFooter.jsp"%>
