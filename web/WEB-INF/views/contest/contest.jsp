<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>FirstBook</title>
    <link rel="stylesheet" href="/firstbook/resources/css/contest/contestAll.css">
    <link rel="stylesheet" href="/firstbook/resources/css/contest/reset.css">
</head>

<body>

   
    	
        <jsp:include page="../common/header.jsp"></jsp:include>
        
        <section class="section1">
        	<div class="section1_bg">
        	<div class="section1_main"></div></div>
        	<div class="section1_content_head">
        	<div class="section1_h1">분기별 공모전 작품</div>
        	
        	
        	
        	</div>
        	
			<div class="section1_content">
			

			<c:forEach items="${ requestScope.contestListTime }" var="contest">
			<c:if test="${ contest.competSsn != requestScope.contestList[0].competSsn }">
			<a href="${ pageContext.servletContext.contextPath }/contestAll/list?competSsn=${ contest.competSsn }">
			<div class="font_size off"><c:out value="${ contest.competSsn }" /></div>
			</a>
			</c:if>
			
			<c:if test="${ contest.competSsn == requestScope.contestList[0].competSsn }">
			<a href="${ pageContext.servletContext.contextPath }/contestAll/list?competSsn=${ contest.competSsn }">
			<div class="font_size on"><c:out value="${ contest.competSsn }" /></div>
			</a>
			</c:if>
		
			</c:forEach>
			</div>
			
			<div class="section1_fild">
			
			 <c:forEach items="${ requestScope.contestList }" var="contest">
                            <div class="work" id="btn">
                                <img src="<c:out value="${ contest.competNovImgLocation }" />" width="125px">
                                <div class="work_title">
                                    <c:out value="${ contest.novTitle }" />
                                </div>
                                    <button id="btn" class="work_button" type="button">보러가기<label class="label_none"><c:out value="${ contest.competNum }" /></label></button>
                            </div>
               </c:forEach>
			</div>
		
        </section>
        
         <script>
		$("#btn > button").click(function(){
			const competNum = $(this).find("label").text();	// 사진 게시글 번호
			/* console.log(no); */
			location.href="${ pageContext.servletContext.contextPath }/contest/voting?competNum=" + competNum;
		});
	</script>

       <jsp:include page="../common/footer.jsp"/>

</body>

</html>