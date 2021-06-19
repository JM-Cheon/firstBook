<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.pg { border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;
		 width:10px;	}

</style>

<link rel="stylesheet"
	href="/firstbook/resources/css/webnovel/webViewer.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<body>
	<jsp:include page="/WEB-INF/views/admin/adminHeader.jsp"/>
	<br><br><br><br><br><br>
	
	<form action="${ pageContext.servletContext.contextPath }/admin/webModify" method="post">
	<div id="wrap">
		<h1>${requestScope.pageInfo.pageNo }</h1>
		<h1> <input type="hidden" name="webNovNum" value="${ requestScope.currentWebNov }"/></h1>
		<h1> <input type="hidden" name="currentChap" value="${ requestScope.currentChap }"/></h1>
		<section class="sec1">
			<table class="tab_viewer">
				<c:choose>
					<c:when test="${!empty requestScope.currentChap }">
						<tr>
							<td class="goback">
								<button onclick="goback()" class="gobackToMain" type="button">목록으로</button>
							</td>
							<td colspan="2" class="title">
								<p class="title_real"> <c:out value="${ requestScope.title.webNovTitle }"/></p>
							</td>
							<td class="nextback"><c:if
									test="${requestScope.pageInfo.pageNo == 1 }">
									<button class="backbtn" disabled><</button>
								</c:if> <c:if test="${requestScope.pageInfo.pageNo > 1 }">
									<button id="searchStartPage" class="backbtn" type="button"
										onclick="back(this)"><</button>
								</c:if>
								 <%-- <c:if test="${ requestScope.pageInfo.pageNo lt requestScope.pageInfo.maxPage }"> --%>
									<button type="button" id="searchNextPage" class="nextbtn" onclick="next()">></button>
								<%-- </c:if> --%>
								</td>
						</tr>
					</c:when>
				</c:choose>
			
				<tr>
				    <c:forEach var="perChapter" items="${requestScope.perChap }" varStatus="status">
				                <td colspan="2" class="page"><textarea class="pagediv1" name="body${ status.index }" style="resize:none;"><c:out value="${ perChapter.webNovPageContent }"/></textarea>
				            </td>
				    </c:forEach>
				            </tr>
				    
				    <tr>
				    
				    <c:forEach var="perChapter" items="${requestScope.perChap }"  varStatus="status2">
				        <td colspan="2" class="pageNum" >
				            <input class="pg" name="pg${ status2.index }" value="<c:out value="${ perChapter.webNovPageNum.webNovPageNum }"/>">pg
				        </td>
				    </c:forEach>
				
				    </tr>
				
				<tr>	
				<td colspan="2"> <button type="submit" class="btn" onclick="modifyButtonAction(this.innerText);">수정하기</button> </td>
				</tr>

				
			</table>
		</section>

	</div>
	</form>


	<script>
/* 	 const $searchNextPage = document.getElementById("searchNextPage"); 
	console.log($searchNextPage); */
	const link = "${ pageContext.servletContext.contextPath }/admin/chapList"; 
	const gobackLink = "${ pageContext.servletContext.contextPath }/admin/update"; 
	
	const modifyLink = "${ pageContext.servletContext.contextPath }/admin/webModify";

	
	function next(){
		
		var t = ${pageInfo.pageNo};
		/* 다음 쪽이 계속 있을 때 */
		if(${pageInfo.pageNo} < ${pageInfo.maxPage}){
		location.href = link  + "?currentWebNov=${ requestScope.currentWebNov}&currentChap=${ requestScope.currentChap}&currentPage=" + (parseInt(t) + 1);
		}else {		// 모든 내용을 봤을 때 알람!
			alert('${ requestScope.title.webNovTitle }의 ${ requestScope.currentChap} 화를 모두 보셨습니다! ');
		}	
	}
	/* 뒤쪽 내용을 볼 때 */
	function back(){
		
		var t = ${pageInfo.pageNo};

		location.href = link  + "?currentWebNov=${ requestScope.currentWebNov}&currentChap=${ requestScope.currentChap}&currentPage=" + (parseInt(t) - 1);
	}
	/* 목록으로 돌아가기 */
	function goback(){
		
		location.href = gobackLink  + "?webNovNum=${ requestScope.currentWebNov}";
	}
	
	/* 수정사항 do post 처리 */
	function modifyButtonAction(text) {
		location.href = modifyLink + text + "?webNovNum=${ requestScope.currentWebNov}"
	}
	
	
	
	</script>
</body>
</html>