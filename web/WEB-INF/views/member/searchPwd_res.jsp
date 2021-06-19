<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FirstBook</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
<link rel="stylesheet" href="/firstbook/resources/css/member/style.css">
</head>
<body>
    <div id="wrap">
    	<jsp:include page="../common/header.jsp"/>
        <main id="main" class="top_main">
            <div class="login_form">
                <div class="login_title">
                    비밀번호 찾기
                </div>
                <div class="searchId_input_box">
                        인증키가 전송되었습니다.<br>
                        해당 시간 안에 입력해주세요. <br>
                        <p id="timer"></p>
                </div>
                <div class="login_input_box">
                    인증키 <input type="text" id="key" required>
                </div>
                <button id="compareKey">
                    확인
                </button>
                <div class="login_nav">
                    <a href="${ pageContext.servletContext.contextPath}">홈으로</a>
                </div>
            </div>
        </main>
        <jsp:include page="../common/footer_member.jsp"/>
    </div>
    <script>
		
	    $(function() {
			var time = 10*60;
			var min = parseInt(time / 60);
			var second = time % 60;
			
			$("#timer").text(min + "분" + second + "초");
			
			var timer = setInterval(function() {
				time --;
				var min = parseInt(time / 60);
				var second = time % 60;
				$("#timer").empty();
				$("#timer").text(min + "분" + second + "초");
				if(time == 0){
					alert("제한 시간이 초과되었습니다.");
					location.href = "${ pageContext.servletContext.contextPath }";
				}
			}, 1000)
		});
    
    	$("#compareKey").click(function() {
    		const emailKey = "${ requestScope.emailKey }";
    		var key = $("#key").val();
    		
    		if(emailKey == key){
    			alert("인증에 성공하여 비밀번호 변경 페이지로 넘어갑니다.");
    			location.href = "${ pageContext.servletContext.contextPath }/member/updatePassword";
    		} else {
    			alert("일치하지 않습니다.");
    		}
    	});
    </script>
</body>
</html>