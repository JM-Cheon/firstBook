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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
        <jsp:include page="adminHeader.jsp"></jsp:include>

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
	            
	            <form action="${ pageContext.servletContext.contextPath}/admin/noticeModify" method>
	            <div class="notice">
	                <div class="notice_title_box">
	                    공지사항
	                </div>
	                <div class="notice_body_box">
	                    <div class="notice_detail">
	                        <table>
	                            <tr height="50" class="tr_underline">
	                                <td width="120" >
	                                    글번호 : <input name="noticeNum" id="noticeNum" readonly value="<c:out value="${ requestScope.notice.noticeNum }"/>">
	                                </td>
	                                <td width="400" >
	                                    제목 : <textarea name="noticeName" id="noticeName" style ="margin: 0px; width: 314px; height: 28px; resize:none;"><c:out value="${ requestScope.notice.noticeName }"/></textarea>
	                                </td>
	                                <td width="220">
	                                    작성자 : 관리자
	                                </td>
	                            </tr>
	                            <tr height="50" class="tr_underline">
	                                <td >
	                                	조회수 : <input id="noticeViewCount" readonly value="<c:out value="${ requestScope.notice.noticeViewCount }"/>"> 
	                                </td>
	                                
	                                <td align="center" id="noticeDate">
	                                    작성일자 : <c:out value="${ requestScope.notice.noticeDate }"/>
	                                </td>
	                                <td>
	                                	<button type="submit" style="color:blue; font-size: 20px; border : solid 1px" 
	                                	onclick="modify()">수정</button>
	                                	
	                                	<button type="button" style="color:red; font-size: 20px; border : solid 1px"
	                                	onclick="deactive()">활성화/비활성화</button>
	                                </td>
	                                
	                            </tr>
	                            <tr height="500">
	                                <td colspan="3">
	                                    <textarea id="noticeContent" name="noticeContent" style ="margin: 0px; width: 580px; height: 457px; resize: none;"><c:out value="${ requestScope.notice.noticeContent }"/></textarea> 
	                                </td>
	                            </tr>
	                        </table>
	                    </div>
	                    <div class="board_button">
	                        <button id="back">돌아가기</button>
	                    </div>
	                </div>
	            </div>
   			</form>
	        </div>
        </main>
    </div>
    <br clear="both">
    <jsp:include page="../common/footer.jsp"/>
    
    <script>

    	const link = "${ pageContext.servletContext.contextPath}/admin/noticeModify";

    	$(function(){
    	const noticeNum = document.getElementById('noticeNum').value;
    	const noticeName = document.getElementById('noticeName').value;
    	const noticeContent = document.getElementById('noticeContent').value;

	  	 console.log(noticeNum);		
	  	 console.log(noticeName);		
	  	 console.log(noticeContent);		

    function modify(){
    	
	    location.href = link + "?noticeNum=" + noticeNum + "&noticeName=" + noticeName + "&noticeContent=" + noticeContent;
/* 	    location.href= "${ pageContext.servletContext.contextPath}/admin/noticeModify?noticeNum=${requestScope.notice.noticeNum }&noticeName=${ requestScope.notice.noticeName }&noticeViewCount=${ requestScope.notice.noticeViewCount }&noticeContent=${ requestScope.notice.noticeContent}"; */
    	
    	}	
    
    });
    	
    	

    	
    	
	    		
    	
   
    
    function deactive(){
    	
	    location.href= "${ pageContext.servletContext.contextPath}/admin/noticeDeactive?noticeNum=${requestScope.notice.noticeNum }&noticeStatus=${ requestScope.notice.noticeStatus }"
    	
    	
    }
    
    		

	</script>
</body>
</html>