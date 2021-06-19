<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="/firstbook/resources/css/webnovel/webViewer.css">
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
<link rel="stylesheet" href="/firstbook/resources/css/member/style.css">
<link rel="shortcut icon" href="#">

</head>
<body>
<jsp:include page="../common/header.jsp"></jsp:include>
<br><br><br><br><br><br><br>
	 <div id="wrap">
		<h1>${requestScope.pageInfo.pageNo }</h1>
		<section class="sec1">
			<table class="tab_viewer">				
						<tr>
							<td class="goback">
								<button onclick="list(${ requestScope.competNum })" class="gobackToMain" type="button">목록으로</button>
							</td>
							<td colspan="2" class="title">
								<p class="title_real"> <c:out value="${ requestScope.contestList[0].novTitle }"/></p>
							</td>
							<td class="nextback">
									<!-- <button class="backbtn" disabled><</button> -->
								
								<c:if test="${ requestScope.first != 1 }">
									<button id="searchStartPage" class="backbtn" onclick="back(${ requestScope.competNum },${ requestScope.chapterNum })"><</button>
								</c:if>
								<c:if test="${ requestScope.first != lastPage-1 }">
									<button type="button" id="searchNextPage" class="nextbtn" onclick="test(${ requestScope.competNum },${ requestScope.chapterNum })">></button>
								</c:if>
								</td>
						</tr>

			
						<tr>
			
							<td colspan="2" class="page"><pre class="pagediv1"><c:out value="${ requestScope.viewPage[0].competContent }"/></pre>
                    	</td>
							<td colspan="2" class="page"><pre class="pagediv1"><c:out value="${ requestScope.viewPageTwo[0].competContent }"/></pre>
                    	</td>
				
						</tr>
				
				<tr>
				
		
					<td colspan="2" class="pageNum">
						<p class="page1Num_real"> <c:out value="${ requestScope.viewPage[0].pages }"/> page</p>
					</td>
					<td colspan="2" class="pageNum">
						<p class="page1Num_real"> <c:out value="${ requestScope.viewPageTwo[0].pages }"/> page</p>
					</td>
			

				</tr>
			</table>
		</section>

	</div>
	 <jsp:include page="../common/footer.jsp"/>
<script>		
		const link = "${ pageContext.servletContext.contextPath}/contest/SelectView";
		function test(var1, var2){
				
			var t = var1;
			var t2 = var2; 
			
			var first = ${ requestScope.first } + 2;

			location.href = link  + "?competNum=" + parseInt(t) +  "&chapterNum=" + parseInt(t2) + "&first=" + first;			

			}
		
		function back(var3, var4){
			
			var t = var3;
			var t2 = var4; 
		
			var first = ${ requestScope.first } - 2;
			
			location.href = link  + "?competNum=" + parseInt(t) +  "&chapterNum=" + parseInt(t2) + "&first=" + first;			
		}	
		const link2 = "${ pageContext.servletContext.contextPath}/contest/voting";
	function list(var5){
			
			var t = var5;
		
			location.href = link2  + "?competNum=" + parseInt(t);			
		}	
</script> 
	
</body>
</html>