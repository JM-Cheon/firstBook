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
	                    1 : 1 문의
	                </div>
	                <div class="notice_body_box">
	                    <table>
		                    <c:forEach var="qna" items="${ requestScope.qnaList }">
		                        <tr>
		                            <td height="40" class="tdline">${ qna.postNum } ) ${ qna.qnaTitle } <img src="/firstbook/resources/image/under-arrow.png"></td>
		                        </tr>
		                    </c:forEach>
	                    </table>
	                    <div class="board_button">
	                    	<button id="insertQna">작성하기</button><br><br>
	                    
	                    	<button id="startPage"><<</button>
			
							<c:if test="${ requestScope.pageInfo.pageNo == 1 }">
								<button disabled><</button>
							</c:if>
							<c:if test="${ requestScope.pageInfo.pageNo > 1 }">
								<button id="prevPage"><</button>
							</c:if>
							
							<c:forEach var="p" begin="${ requestScope.pageInfo.startPage }" end="${ requestScope.pageInfo.endPage }" step="1">
								<c:if test="${ requestScope.pageInfo.pageNo eq p }">
									<button disabled><c:out value="${ p }"/></button>
								</c:if>
								<c:if test="${ requestScope.pageInfo.pageNo ne p }">
									<button onclick="pageButtonAction(this.innerText);"><c:out value="${ p }"/></button>
								</c:if>
							</c:forEach>
							
							<c:if test="${ requestScope.pageInfo.pageNo == requestScope.pageInfo.maxPage }">
								<button disabled>></button>
							</c:if>
							<c:if test="${ requestScope.pageInfo.pageNo < requestScope.pageInfo.maxPage }">
								<button id="nextPage">></button>
							</c:if>
							
							<button id="maxPage">>></button>
	                    </div>
	                </div>
	            </div>
	        </div>
        </main>
    </div>
    <br clear="both">
    <jsp:include page="../common/footer.jsp"/>
    
    <script>
		const link = "${ pageContext.servletContext.contextPath }/member/qna";
		
		if(document.getElementById("insertQna")){
			const $insertQna = document.getElementById("insertQna");
			$insertQna.onclick = function(){
				location.href = "${ pageContext.servletContext.contextPath }/member/insertQna";
			}
		}
		
		/* 원하는 페이지 클릭시 실행되는 콜백 함수 */
		function pageButtonAction(text){
			location.href = link + "?currentPage=" + text;
		}
		
		/* 첫 페이지 버튼 함수 */
		if(document.getElementById("startPage")) {
			const $searchStartPage = document.getElementById("startPage");
			$searchStartPage.onclick = function(){
				location.href = link + "?currentPage=1";
			}
		}
		
		/* 마지막 페이지 버튼 함수 */
		if(document.getElementById("maxPage")) {
			const $searchMaxPage = document.getElementById("maxPage");
			$searchMaxPage.onclick = function(){
				location.href = link + "?currentPage=${ requestScope.pageInfo.maxPage }";
			}
		}
		
		/* 이전 페이지 버튼 함수 */
		if(document.getElementById("prevPage")) {
			const $searchPrevPage = document.getElementById("prevPage");
			$searchPrevPage.onclick = function(){
				location.href = link + "?currentPage=${ requestScope.pageInfo.pageNo - 1 }";
			}
		}
		
		/* 다음 페이지 버튼 함수 */
		if(document.getElementById("nextPage")) {
			const $searchNextPage = document.getElementById("nextPage");
			$searchNextPage.onclick = function(){
				location.href = link + "?currentPage=${ requestScope.pageInfo.pageNo + 1 }";					
			}
		}
		
		/* 게시글 관련 css 및 이벤트 처리(마우스 호버 및 클릭) */
		if(document.getElementsByTagName("td")) {
			const $tds = document.getElementsByTagName("td");
			for(var i = 0; i < $tds.length; i++) {
				
				$tds[i].onclick = function() {
					var array = this.parentNode.children[0].innerText.split(' ');
					const noticeNum = array[0];
					location.href = "${ pageContext.servletContext.contextPath }/member/qnaDetail?no=" + noticeNum;
				}
			}
		}
	</script>
</body>
</html>