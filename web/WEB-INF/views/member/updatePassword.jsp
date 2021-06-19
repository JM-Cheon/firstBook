<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
<link rel="stylesheet" href="/firstbook/resources/css/member/style.css">
</head>
<body>
	<jsp:include page="../common/header.jsp"/>
	
    <div id="wrap">
        <main id="main" class="top_main">
            <div class="login_form">
                <div class="login_title">
                    비밀번호변경
                </div>
                <form action="${ pageContext.servletContext.contextPath }/member/updatePassword" method="post">
                    <div class="login_input_box">
                        아이디 <input type="text" id="memId" name="memId" required>
                    </div>
                    <div class="login_input_box">
                        비밀번호 <input type="password" id="memPwd" name="memPwd" required>
                    </div>
                    <div class="login_input_box">
                        비밀번호확인 <input type="password" id="memPwYn" name="memPwYn" required>
                    </div>
                    <p id=pwCheckResult>비밀번호는 4글자 이상 작성해주세요.</p>
                    <button type="submit" onclick="return check();">
                        변경하기
                    </button>
                </form>
                <div class="login_nav">
                        <a href="${ pageContext.servletContext.contextPath}">홈으로</a>
                </div>
            </div>
        </main>
    </div>
    
    <jsp:include page="../common/footer_member.jsp"/>
	
	<script>
		var pwYn = "N";
		
		$("#memPwYn").keyup(function(){
			if($("#memPwYn").val().length > 3) {
				var memPwd = $("#memPwd").val();
				var memPwYn = $("#memPwYn").val();
				if(memPwd == memPwYn){
					$("#pwCheckResult").text("비밀번호가 일치합니다.");
					pwYn = "Y"
				} else {
					$("#pwCheckResult").text("비밀번호가 일치하지않습니다.");
					pwYn = "N"
				}
			} else if($("#memPwYn").val().length <= 3 ) {
				$("#pwCheckResult").text("비밀번호는 4글자 이상 작성해주세요.");
				pwYn = "N"
			}	
		});
		
		$("#memPwd").keyup(function(){
			if($("#memPwd").val().length > 3) {
				var memPwd = $("#memPwd").val();
				var memPwYn = $("#memPwYn").val();
				if(memPwd == memPwYn){
					$("#pwCheckResult").text("비밀번호가 일치합니다.");
					pwYn = "Y"
				} else {
					$("#pwCheckResult").text("비밀번호가 일치하지않습니다.");
					pwYn = "N"
				}
			} else if($("#memPwd").val().length <= 3 ) {
				$("#pwCheckResult").text("비밀번호는 4글자 이상 작성해주세요.");
				pwYn = "N"
			}	
		});
		
		function check() {
			if(pwYn == "N") {
				alert("비밀번호확인이 완료되지 않았습니다.");
				return false;
			} else {
				return true;
			}
		}
		
	</script>
</body>
</html>