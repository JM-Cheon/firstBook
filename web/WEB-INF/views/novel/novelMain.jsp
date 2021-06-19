<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/firstbook/resources/css/contest/header.css">

<body>
	<jsp:include page="/WEB-INF/views/common/header.jsp"/>
	
	<div style="background:skyblue; position:absolute; top:500px; width:2000px; height:500px;">
			
			<table border="1">
			<tr>
			
		<c:forEach var="novel" items="${requestScope.NovelList}">
			
			<td><c:out value="${ novel.novTitle }"/></td>
			<td><c:out value="${ novel.categoryName.categoryName }"/></td> 
			<td> <button type="button" onclick="go(${ novel.novNum })">상세보기</button> </td>
			
		</c:forEach>
			</tr> 
			</table>
	
	</div>
	
	<script type="text/javascript">
	


	function go(val1) {
		var t = val1;		
		
	
	  if(t !=null) 
			
		location.href = "${ pageContext.servletContext.contextPath }/novel/detail?NovNum="
				+ parseInt(t);
	  }
	</script>
	
	
		
</body>
</html>