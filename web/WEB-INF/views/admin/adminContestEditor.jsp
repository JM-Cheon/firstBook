<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
        <jsp:include page="adminHeader.jsp"></jsp:include>
<br><br><br><br><br><br>

<form action="${ pageContext.servletContext.contextPath }/admin/contestModify" method="post">
	 
	 <h1> <input type="hidden" name="nop" value="${ requestScope.NumberOfPages }"> </h1>
	 <h1> <input type="hidden" name="competNum" value="${ requestScope.competNum }"> </h1>
	 <h1> <input type="hidden" name="chapterNum" value="${ requestScope.chapterNum }"> </h1>
	 <h1> <input type="hidden" name="first" value="${ requestScope.first }"> </h1>


	 
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
			
							<td colspan="2" class="page"><textarea class="pagediv1" style="resize:none;" name="body0" id="body0"><c:out value="${ requestScope.viewPage[0].competContent }"/></textarea>
                    	</td>
							<td colspan="2" class="page"><textarea class="pagediv1" style="resize:none;" name="body1" id="body1"><c:out value="${ requestScope.viewPageTwo[0].competContent }"/></textarea>
                    	</td>
				
						</tr>
				
				<tr>
				
		
					<td colspan="2" class="pageNum">
						<input id="pg0" name="pg0" value="<c:out value="${ requestScope.viewPage[0].pages }"/>"> page
					</td>
					<td colspan="2" class="pageNum">
						<input id="pg1" name="pg1" value="<c:out value="${ requestScope.viewPageTwo[0].pages }"/>">page
					</td>
			

				</tr>
				<tr>	
	
				<td colspan="2"> <button type="submit" class="btn" id="btn" >수정하기</button> </td>
				</tr>
			</table>
		</section>
				

	</div>
</form>
<script>		
		const link = "${ pageContext.servletContext.contextPath}/admin/contestEditor";
		
		const modifyLink = "${ pageContext.servletContext.contextPath }/admin/contestModify";
		
		function modifyButtonAction(text) {
			location.href = modifyLink + text + "?competNum=${ requestScope.competNum }" + "chapterNum=${ requestScope.chapterNum }" + "first=${ requestScope.first }"
		}
		
		
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
		
		const link2 = "${ pageContext.servletContext.contextPath}/admin/contestDetail";
	
		function list(var5){
			
			var t = var5;
		
			location.href = link2  + "?competNum=" + parseInt(t);			
		}	
	

	
</script> 
	
</body>
</html>