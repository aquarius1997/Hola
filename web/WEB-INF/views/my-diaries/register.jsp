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
        <div style="height: 300px;">
            <textarea id="register-Detail" placeholder="Input Your Diary Texts"><c:out value="${diary.content}"/></textarea>
        </div>
        <!-- 첨부파일 영역 -->
        <div class="fileDrop">
            <br/>
            <br/>
            <br/>
            <br/>
            <p style="text-align: center"><i class="fa fa-paperclip"></i>첨부파일을 드래그 해주세요.</p>
        </div>
        <!-- end 첨부파일 영역 -->
        <div class="register-diaryEx">
            <p style="margin-left: 10px">Mood</p>
            <select id="moodCode" class="select-basic">
                <option value="NO"/>None</option>
                <option value="NA">Angry</option>
                <option value="ND">Depressed</option>
                <option value="NO">None</option>
                <option value="NA">Sad</option>
                <option value="PA">Amused</option>
                <option value="PC">Cheerful</option>
                <option value="PH">Happy</option>
                <option value="PL">Loving</option>
                <option value="PP">Peaceful</option>
            </select>
            <p>공개설정</p>
            <select id="opnFlag" class="select-basic" style="margin-right: 10px">
                <option value="Y">공개</option>
                <option value="N">비공개</option>
                <option value="Y">공개</option>
            </select>
        </div>
        <div style="margin-left: auto;margin-right:auto;width: fit-content;margin-top: 10px;">
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

                //첨부파일 이벤트를 처리한다
                var that = $(this);
                filesSubmit(that);

                var formObj = {};
                formObj["title"] = $("#register-title").val();
                formObj["content"] = $("#register-Detail").val();
                formObj["moodCode"] = $("#moodCode").val();
                formObj["opnFlag"] = $("#opnFlag").val();
                formObj["memberId"] = <c:out value="${memberId}"/>
                formObj["diaryId"] = <c:out value="${diary.diaryId}"/>

                console.log(formObj);

                $.ajax({
                    type:"post",
                    url:"/my-diaries/<c:out value="${memberId}"/>",
                    contentType: "application/json; charset=UTF-8",
                    dataType: "json",
                    data:JSON.stringify(formObj),
                    success : function() {
                        console.log("SUCCESS");
                        window.location.href = "/my-diaries/<c:out value="${memberId}"/>";
                    }
                });
            }
        });
    });

    $(document).on("click", ".delBtn", function (event) {
        event.preventDefault();
        var that = $(this);
        deleteFileWrtPage(that);
    });
</script>

<!-- Handlebars 템플릿을 이용해 HTML 코드를 동적으로 생성-->
<script id="fileTemplate" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img">
            <img src="{{imgSrc}}" alt="Attachment">
        </span>
        <div class="mailbox-attachment-info">
            <a href="{{originalFileUrl}}" class="mailbox-attachment-name">
                <i class="fa fa-paperclip"></i> {{originalFileName}}
            </a>
            <a href="{{fullName}}" class="btn btn-default btn-xs pull-right delBtn">
                <i class="fa fa-fw fa-remove"></i>
            </a>
        </div>
    </li>
</script>

<script type="text/javascript" src="/resources/mydiary/assets/js/article_file_upload.js"></script>



<%@include file="../includes/mydiariesFooter.jsp"%>
