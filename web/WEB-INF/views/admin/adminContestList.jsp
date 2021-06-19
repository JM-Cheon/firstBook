<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>FirstBook</title>
    <link rel="stylesheet" href="/firstbook/resources/css/contest/contest.css">
    <link rel="stylesheet" href="/firstbook/resources/css/contest/reset.css">
    <link rel="stylesheet" href="/firstbook/resources/css/contest/header.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>

  
    <div id="wrap">
    	
        <jsp:include page="adminHeader.jsp"></jsp:include>

        <div class="size_box"></div>
 
        <aside>
            <div class="section2">
                
                <div class="work_tupyo">
                    <div class="work_row1">
                        <c:forEach items="${ requestScope.contestList }" var="contest">
                            <div class="work" id="btn">
                                <img src="<c:out value="${ contest.competNovImgLocation }" />" width="125px">
                                <div class="work_title">
                                    <c:out value="${ contest.novTitle }" />
                                </div>
                                    <button id="btn" class="work_button" type="button">수정하기<label class="label_none"><c:out value="${ contest.competNum }" /></label></button>
                             	
                                    
                            </div>
                        </c:forEach>
                    </div>

                </div>
					<div class="tupyo">

					</div>
            </div>
        </aside>


	

    </div>

    <div class="size_box"></div>
    
    <script>
		$("#btn > button").click(function(){
			const competNum = $(this).find("label").text();	// 사진 게시글 번호
			/* console.log(no); */
			location.href="${ pageContext.servletContext.contextPath }/admin/contestDetail?competNum=" + competNum;
		});
	</script>
</body>

</html>