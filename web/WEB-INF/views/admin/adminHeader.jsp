<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet" href="/firstbook/resources/css/contest/header.css">

<title>Insert title here</title>

</head>
<body>


	<header class="header" class="fixed">
            <div class="border">
                <div class="logo_box">
                    <a href="${ pageContext.servletContext.contextPath }" title="more"><img src="/firstbook/resources/image/contest/logo.png" alt="more"></a>

                </div>

                <div class="menu_box">

                    <nav class="nav">

                        <ul>
                            <li><a href="${ pageContext.servletContext.contextPath }/admin/main">관리자 메인</a></li>
                            <li><a href="${ pageContext.servletContext.contextPath }/admin/memlist">회원 조회</a></li>
                            <li><a href="${ pageContext.servletContext.contextPath }/admin/webNovel">웹소설 관리</a></li>
                           <%--  <li><a href="${ pageContext.servletContext.contextPath }/admin/novel">단행본 관리</a></li> --%>
                            <li><a href="${ pageContext.servletContext.contextPath }/admin/notice">문의관리</a></li>
                            <li><a href="${ pageContext.servletContext.contextPath }/admin/contest">공모전</a></li>
            
                            
                        </ul>
                    </nav>
                </div>
                <div class="login_box">
                    <ul>
                        <!-- <li><a href="#" class="main-btn">충전하기</a></li> -->
                        <li><a href="${ pageContext.servletContext.contextPath }/member/login" class="main-btn">로그인</a></li>
                        <!-- <li><a href="#" class="main-btn">ㅁㅁㅁ님</a></li> -->
                    </ul>
                </div>

            </div>

	</header>

	
</body>
</html>