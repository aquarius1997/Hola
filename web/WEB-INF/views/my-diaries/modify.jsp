<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/mydiariesHeader.jsp"%>


<!--Diary-->
<section class="diary">
    <form id="form1" name="form1" method="post">
        <div class="diaryMain">
            <span>
                <img src="/resources/mydiary/images/IMG_1417.jpeg" alt="" style="cursor:pointer"/>
                <span id="diaryText">
                    <span id="diaryTitle"><input class="input-default" id="input-title" name="title" value="<c:out value="${diary.title}"/>" placeholder="Title"></span>
                    <span id="diaryDetails">
                        <div><textarea class="input-default" name="content" id="input-content" placeholder="Content"><c:out value="${diary.content}"/></textarea></div>
                    </span>
                </span>
            </span>
        </div>

        <div class="outer-diaryEx">
            <div class="diaryEx">
                <span>
                    <p>Mood</p>

                    <select name="mood_code" class="select-basic">
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
                    <select name="opn_flag" class="select-basic">
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



           console.log($("#title").val());

           if(operation === 'remove') {
               formObj.attr("action", "");
           } else if (operation === 'modify') {
               $.ajax({
                   url : "http://localhost:8080/my-diaries/diary",
                   type : "PUT",
                   data : $("#title").val(),
                   dataType : "json",
                   success: function(result) {
                       console.log(result);
                   },
                   error: function (xhr, textStatus, errorThrown) {
                       console.log('Error in Operation');
                   }
               });
           }
       });
    });
</script>

<%@include file="../includes/mydiariesFooter.jsp"%>
