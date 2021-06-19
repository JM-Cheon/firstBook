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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
	                    1 : 1 문의
	                </div>
	                <div class="notice_body_box">
	                    <div class="notice_detail">
	                        <table>
	                            <tr height="50" class="tr_underline">
	                                <td width="120">
	                                    글번호 : <c:out value="${ requestScope.qna.postNum }"/>
	                                </td>
	                                <td width="400">
	                                    제목 : <c:out value="${ requestScope.qna.qnaTitle }"/>
	                                </td>
	                                <td width="220">
	                                    작성자 : <c:out value="${ requestScope.qna.memNum.memName }"/>
	                                </td>
	                            </tr>
	                            <tr height="50" class="tr_underline">
	                                <td></td>
	                                <td></td>
	                                <td>
	                                    작성일자 : <c:out value="${ requestScope.qna.qnaDate }"/>
	                                </td>
	                            </tr>
	                            <tr height="300" class="tr_underline">
	                                <td colspan="3">
	                                    <c:out value="${ requestScope.qna.qnaContent }"/>
	                                </td>
	                            </tr>
	                            <tr height="50" class="tr_underline">
	                            	<td colspan="3" align="center">
	                            		<input type="text" name="replyContent" class="replyContent" id="replyContent"><button id="send" class="replySend">전송</button>
	                            	</td>
	                            </tr>
	                        </table>
	                        <div class="reply-zone">
		                        <table id="reply">
		                        </table>
	                        </div>
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
    	const no = ${ requestScope.qna.postNum };
	    if(document.getElementById("back")) {
			const $back = document.getElementById("back");
			$back.onclick = function(){
				location.href = "${ pageContext.servletContext.contextPath }/member/qna";
			}
		}
	    
		$(function(){
			
			$.ajax({
				url: "/firstbook/member/qnaReply",
				method : "GET",
				data: {no:no},
				dataType: "json",
				success: function(data){
					console.log(data);
					for(var i = 0; i < data.length; i++){
						$("#reply").append("<tr><td>"+data[i].member.memName+"</td><td align=\"right\">"+data[i].replyDate+"</td></tr><tr class=\"tr_underline\"><td colspan=\"2\" width=\"740\">"+data[i].replyContent+"</td></tr>")
					}
				},
				error: function(request, status, error){
					alert("code:" + request.status + "\n" + "error:" + error);
				}
			});
		});
		
		$("#send").click(function(){
			
			var replyContent = $("#replyContent").val();
			
			if(replyContent == "" || replyContent == null || replyContent == " "){
				alert("빈칸 없이 입력해주세요.");
			} else {
				
				$.ajax({
					url: "/firstbook/member/qnaReply",
					method : "POST",
					data: { 
							no:no,
							replyContent:replyContent
						  },
					dataType: "json",
					success: function(data){
						$("#reply").empty();
						for(var i = 0; i < data.length; i++){
							$("#reply").append("<tr><td>"+data[i].member.memName+"</td><td align=\"right\">"+data[i].replyDate+"</td></tr><tr class=\"tr_underline\"><td colspan=\"2\" width=\"740\">"+data[i].replyContent+"</td></tr>")
						}
					},
					error: function(request, status, error){
						alert("code:" + request.status + "\n" + "error:" + error);
					}
				});
				$("#replyContent").val("");
			}
			
		});
	</script>
</body>
</html>