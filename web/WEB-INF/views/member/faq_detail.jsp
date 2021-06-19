<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	        <div class="notice_area">
	            <div class="left_nav">
	                <div class="left_nav_top">
	                    <ul>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/notice">공지사항</a></li>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/qna">1 : 1 문의</a></li>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/faq">자주묻는질문</a></li>
	                    </ul>
	                </div>
	                <div class="left_nav_under">
	                    <p>고객지원</p>
	                    <p>AM 10 : 00 ~ PM 06 : 00</p>
	                    <p>Lunch PM 12 : 30 ~ PM 01 : 30</p>
	                    <br>
	                    <p>공휴일 제외</p>
	                    <p>02 - 1234 - 1234</p>
	                </div>
	            </div>
	            <div class="notice">
	                <div class="notice_title_box">
	                    자주묻는질문
	                </div>
	                <div class="notice_body_box">
	                    <div class="notice_detail">
	                        <table>
	                            <tr height="50" class="tr_underline">
	                                <td width="120">
	                                    글번호 : <c:out value="${ requestScope.faq.postNum }"/>
	                                </td>
	                                <td width="400">
	                                    제목 : <c:out value="${ requestScope.faq.faqPostTitle }"/>
	                                </td>
	                                <td width="220">
	                                    작성자 : 관리자
	                                </td>
	                            </tr>
	                            <tr height="500">
	                                <td colspan="3">
	                                    <c:out value="${ requestScope.faq.faqPostContent }"/>
	                                </td>
	                            </tr>
	                        </table>
	                    </div>
	                    <div class="board_button">
	                        <button id="back">돌아가기</button>
	                    </div>
	                </div>
	            </div>
	        </div>
        </main>
    </div>
    <br clear="both">
    <jsp:include page="../common/footer.jsp"/>
    
    <script>
	    if(document.getElementById("back")) {
			const $back = document.getElementById("back");
			$back.onclick = function(){
				location.href = "${ pageContext.servletContext.contextPath }/member/faq";
			}
		}
	</script>
</body>
</html>