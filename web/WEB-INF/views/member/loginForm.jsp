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
    <jsp:include page="../common/header.jsp"/>
    <div id="wrap">
        <main id="main" class="top_main">
            <div class="login_form">
                <div class="login_title">
                    로그인
                </div>
                <form action="${ pageContext.servletContext.contextPath }/member/login" method="post">
                    <div class="login_input_box">
                        아이디 <input type="text" name="memId" required>
                    </div>
                    <div class="login_input_box">
                        비밀번호 <input type="password" name="memPwd" required>
                    </div>
                    <button type="submit">
                        Login
                    </button>
                </form>
                <div class="login_nav">
                    <ul>
                        <li><a href="${ pageContext.servletContext.contextPath }/member/searchId">아이디 찾기</a></li>
                        <li><a href="${ pageContext.servletContext.contextPath }/member/searchPwd">비밀번호 찾기</a></li>
                        <li><a href="${ pageContext.servletContext.contextPath }/member/regist">회원가입</a></li>
                    </ul>
                </div>
            </div>
        </main>
    </div>
    <jsp:include page="../common/footer_member.jsp"/>
</body>
</html>