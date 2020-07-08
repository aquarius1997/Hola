<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@include file="../includes/mydiariesHeader.jsp"%>

<!--Register New Diary Form-->
<section>
    <form id="form">
        <div>
            <input id="register-title" value="<c:out value="${diary.title}"/>" placeholder="Input Your Diary Title">
        </div>
        <div>
            <textarea id="register-Detail" placeholder="Input Your Diary Texts"><c:out value="${diary.content}"/></textarea>
        </div>
        <div class="register-diaryEx">
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
            <p>공개설정</p>
            <select id="opnFlag" class="select-basic">
                <option value="<c:out value="${diary.opnFlag}"/>"><c:out value="${diary.opnFlag}"/></option>
                <option value="N">비공개</option>
                <option value="Y">공개</option>
            </select>
        </div>
        <div>
            <button data-oper="register" class="btn-default" type="submit">Register</button>
        </div>
    </form>
</section>

<script type="text/javascript">
    $(document).ready(function() {
        $('button').on("click", function(e) {
            e.preventDefault();
            var operation = $(this).data("oper");

            if(operation === 'register') {
                console.log("register clicked");
                var formObj = {};
                formObj["title"] = $("#register-title").val();
                formObj["content"] = $("#register-Detail").val();
                formObj["moodCode"] = $("#moodCode").val();
                formObj["opnFlag"] = $("#opnFlag").val();
                formObj["memberId"] = <c:out value="${memberId}"/>
                formObj["diaryId"] = <c:out value="${diary.diaryId}"/>

                console.long(formObj);

                $.ajax({
                    type:"post",
                    url:"/my-diaries/<c:out value=\"${memberId}\"/>",
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
    })
</script>



<%@include file="../includes/mydiariesFooter.jsp"%>
