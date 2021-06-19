<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	
	#d1 {
		position: absolute; top :200px; left:900px; right:100px;	
	}
	
	#d2 {
		position: absolute; top :500px; left:300px; right:900px; width:500px;	
	}
	


</style>
<link rel="stylesheet" href="/firstbook/resources/css/contest/header.css">

<body>
<jsp:include page="../common/header.jsp"/>

	<c:set var="novel" value="${ requestScope.NovDetail }"/>

	<br><br><br><br><br><br><br>
	<div id="d1">
		<table >
			<tr>
				
				<td> <label>작품명 : </label> 
				</td>
				<td> <c:out value="${ novel.novTitle }"/>
				</td>
			</tr>
		</table>
		<br>
		<table >
			<tr>
				<td> <label>작가 : </label> 
				</td>
				<td>				
				<c:out value="${ novel.novAuthor }"/>
				</td>
			</tr>
		</table>
		<br>		
		<table >
			<tr>
				<td> 
				<label>장르명 : </label>
				</td>
				<td>
				<c:out value="${ novel.categoryName.categoryName }"/>
				</td>
			</tr>	
		</table>
		<br>
		<c:set var="price1" value="${ novel.price }"/>	
		<table >
			<tr>
				<td> <label>정가 : </label> 
				</td>
				<td>				
				<fmt:formatNumber value="${price1+(1-(price1%1))%1}"/>원
				</td>
			</tr>
		</table>
		<br>
		<c:set var="price2" value="${ novel.price-(novel.price*0.1) }"/>		
		<table >
			<tr>
				<td> <label>판매가 : </label> 
				</td>
				<td>		
						
				<fmt:formatNumber value="${price2+(1-(price2%1))%1}"/>원
				</td>
			</tr>
		</table>		
		
		<br>
		
		<button>구매하기</button>	<button>찜하기</button>
		
		<br>
		
		
	</div>
	
	<div id="d2">
		
		<table>
			
			<tr>
				<td> <label>작품 소개 : </label> </td>
			</tr>	
				<tr>
				<td>
					<c:out value="${ novel.inform }"/>
				
				</td>
			
			</tr>
		
		</table>
	
	</div>
	
	
</body>
</html>