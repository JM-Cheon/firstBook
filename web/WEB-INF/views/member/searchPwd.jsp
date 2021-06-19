<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FirstBook</title>
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
                <form action="${ pageContext.servletContext.contextPath }/member/searchPwd" method="post">
                    <div class="login_input_box">
                        이름 <input type="text" name="memName" required>
                    </div>
                    <div class="login_input_box">
                        아이디 <input type="text" name="memId" required>
                    </div>
                    <div class="email_input_box">
                        이메일 <input type="text" name="memEmail">
                         @ 
                        <select name="memEmail2">
                        	<option value="naver.com">naver.com</option>
                        	<option value="gmail.com">gmail.com</option>
                        </select>
                    </div>
                    <button type="submit">
                        Search
                    </button>
                </form>
                <div class="login_nav">
                    <a href="${ pageContext.servletContext.contextPath}">홈으로</a>
                </div>
            </div>
        </main>
        <jsp:include page="../common/footer_member.jsp"/>
    </div>
</body>
</html>