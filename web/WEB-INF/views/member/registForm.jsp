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
                    회원가입
                </div>
                <form action="${ pageContext.servletContext.contextPath }/member/regist" method="post">
                    <div class="login_input_box">
                        이름 <input type="text" name="memName" required>
                    </div>
                    <div class="login_input_box">
                        아이디 <input type="text" id="memId" name="memId" required>
                    </div>
                    <p id=idCheckResult>아이디는 영문 대소문자와 숫자 4~12자리로 작성해주세요.</p>
                    <div class="login_input_box">
                        비밀번호 <input type="password" id="memPwd" name="memPwd" required>
                    </div>
                    <div class="login_input_box">
                        비밀번호확인 <input type="password" id="memPwYn" name="memPwYn" required>
                    </div>
                    <p id=pwCheckResult>비밀번호는 영문 대소문자와 숫자 4~12자리로 작성해주세요.</p>
                    <div class="login_input_box">
                        생년월일 <input type="date" name="memBirthDate" required>
                    </div>
                    <div class="email_input_box">
                        이메일 <input type="text" name="memEmail" id="memEmail">
                         @ 
                        <select name="memEmail2">
                        	<option value="naver.com">naver.com</option>
                        	<option value="gmail.com">gmail.com</option>
                        </select>
                    </div>
                    <p id=emailCheckResult></p>
                    <button type="button" id="searchZipCode" class="chkButton">
                        주소 검색
                    </button>
                    <div class="login_input_box">
                        우편번호 <input type="text" name="memZipCode" id="memZipCode" readonly>
                    </div>
                    <div class="login_input_box">
                        주소 <input type="text" name="memAddress" id="memAddress" readonly>
                    </div>
                    <div class="login_input_box">
                        상세주소 <input type="text" name="memDetailAddress" id="memDetailAddress" required>
                    </div>
                    <button type="submit" onclick="return check();">
                        Join
                    </button>
                </form>
                <div class="login_nav">
                        <a href="${ pageContext.servletContext.contextPath}">홈으로</a>
                </div>
            </div>
        </main>
    </div>
    
    <jsp:include page="../common/footer_member.jsp"/>
	
	<!-- 다음 우편번호 api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
		const $searchZipCode = document.getElementById("searchZipCode");
		var regExp = /^[a-zA-z0-9]{4,12}$/;
		var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]$/;
		var idYn = "N";
		var pwYn = "N";
		var emailYn = "N";
		
		$searchZipCode.onclick = function(){
			
			new daum.Postcode({
				oncomplete : function(data){
					document.getElementById("memZipCode").value = data.zonecode;
					document.getElementById("memAddress").value = data.address;
					document.getElementById("memDetailAddress").focus();
				}
			}).open();
		}
		
		$("#memId").keyup(function(){
			var memId = $("#memId").val();
			if(regExp.test(memId)) {
					
				$.ajax({
					url: "/firstbook/member/idCheck",
					type: "post",
					data: {memId : memId},
					success: function(data){
						if(data == ""){
							$("#idCheckResult").text("사용가능한 아이디 입니다.");
							idYn = "Y";
						} else {
							$("#idCheckResult").text("이미 사용중인 아이디 입니다.");
							idYn = "N"
						}
					},
					error: function(request, status, error){
						alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
					}
				});
			} else {
				$("#idCheckResult").text("아이디는 영문 대소문자와 숫자 4~12자리로 작성해주세요.");
				idYn = "N"
			}	
		});
		
		$("#memPwYn").keyup(function(){
			var memPwd = $("#memPwd").val();
			var memPwYn = $("#memPwYn").val();
			if(regExp.test(memPwYn)) {
				if(memPwd == memPwYn){
					$("#pwCheckResult").text("비밀번호가 일치합니다.");
					pwYn = "Y"
				} else {
					$("#pwCheckResult").text("비밀번호가 일치하지않습니다.");
					pwYn = "N"
				}
			} else {
				$("#pwCheckResult").text("비밀번호는 영문 대소문자와 숫자 4~12자리로 작성해주세요.");
				pwYn = "N"
			}	
		});
		
		$("#memPwd").keyup(function(){
			var memPwd = $("#memPwd").val();
			var memPwYn = $("#memPwYn").val();
			if(regExp.test(memPwd)) {
				if(memPwd == memPwYn){
					$("#pwCheckResult").text("비밀번호가 일치합니다.");
					pwYn = "Y"
				} else {
					$("#pwCheckResult").text("비밀번호가 일치하지않습니다.");
					pwYn = "N"
				}
			} else {
				$("#pwCheckResult").text("비밀번호는 영문 대소문자와 숫자 4~12자리로 작성해주세요.");
				pwYn = "N"
			}	
		});

		$("#memEmail").keyup(function(){
			var memEmail = $("#memEmail").val();
			if(emailRegExp.test(memEmail)) {
				$("#emailCheckResult").text("");
				emailYn = "Y"
			} else {
				$("#emailCheckResult").text("이메일 형식이 올바르지 않습니다.");
				emailYn = "N"
			}	
		});
		
		function check() {
			if(idYn == "N"){
				alert("아이디 중복확인이 완료되지 않았습니다.");
				return false;
			} else if(pwYn == "N") {
				alert("비밀번호확인이 완료되지 않았습니다.");
				return false;
			} else if(emailYn == "N") {
				alert("이메일 형식이 올바르지 않습니다.");
				return false;
			} else if($("#memZipCode").val() == "") {
				alert("빈칸 없이 입력해주세요.");
				return false;
			} else if($("#memId").val() == $("#memPwd").val()) {
				alert("아이디와 비밀번호는 같을 수 없습니다.");
				return false;
			} else {
				return true;
			}
		}
		
		
		
	</script>
</body>
</html>