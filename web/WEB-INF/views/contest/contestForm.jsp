<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>FirstBook</title>
    <link rel="stylesheet" href="/firstbook/resources/css/contest/contest.css">
    <link rel="stylesheet" href="/firstbook/resources/css/contest/reset.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>

<body>
    <div id="wrap">
        <jsp:include page="../common/header.jsp"></jsp:include>
        <div class="size_box"></div>
        <section>
            <div class="section">
            	<!-- 16강 -->
                <div class="rank rank1"><c:out value="${ requestScope.contestListRankEight[0].novTitle }" /></div>
                <div class="rank rank2"><c:out value="${ requestScope.contestListRankEight[1].novTitle }" /></div>
                <div class="rank rank3"><c:out value="${ requestScope.contestListRankEight[2].novTitle }" /></div>
                <div class="rank rank4"><c:out value="${ requestScope.contestListRankEight[3].novTitle }" /></div>
                <div class="rank rank5"><c:out value="${ requestScope.contestListRankEight[4].novTitle }" /></div>
                <div class="rank rank6"><c:out value="${ requestScope.contestListRankEight[5].novTitle }" /></div>
                <div class="rank rank7"><c:out value="${ requestScope.contestListRankEight[6].novTitle }" /></div>
                <div class="rank rank8"><c:out value="${ requestScope.contestListRankEight[7].novTitle }" /></div>
                <div class="rank rank9"><c:out value="${ requestScope.contestListRankEight[8].novTitle }" /></div>
                <div class="rank rank10"><c:out value="${ requestScope.contestListRankEight[9].novTitle }" /></div>
                <div class="rank rank11"><c:out value="${ requestScope.contestListRankEight[10].novTitle }" /></div>
                <div class="rank rank12"><c:out value="${ requestScope.contestListRankEight[11].novTitle }" /></div>
                <div class="rank rank13"><c:out value="${ requestScope.contestListRankEight[12].novTitle }" /></div>
                <div class="rank rank14"><c:out value="${ requestScope.contestListRankEight[13].novTitle }" /></div>
                <div class="rank rank15"><c:out value="${ requestScope.contestListRankEight[14].novTitle }" /></div>
                <div class="rank rank16"><c:out value="${ requestScope.contestListRankEight[15].novTitle }" /></div> 
                <!-- 8강 -->
               
                <c:if test="${ requestScope.date8 eq '2'}">
                 <div class="rank rank17"><c:out value="${ requestScope.contestListRankEight[0].novTitle }" /></div>
                 <div class="rank rank18"><c:out value="${ requestScope.contestListRankEight[1].novTitle }" /></div>
                 <div class="rank rank19"><c:out value="${ requestScope.contestListRankEight[0].novTitle }" /></div>
                 <div class="rank rank20"><c:out value="${ requestScope.contestListRankEight[1].novTitle }" /></div>
                 <div class="rank rank21"><c:out value="${ requestScope.contestListRankEight[2].novTitle }" /></div>
                 <div class="rank rank22"><c:out value="${ requestScope.contestListRankEight[3].novTitle }" /></div>
                 <div class="rank rank23"><c:out value="${ requestScope.contestListRankEight[4].novTitle }" /></div>
                 <div class="rank rank24"><c:out value="${ requestScope.contestListRankEight[5].novTitle }" /></div>
                 </c:if>
                 <!-- 4강 -->
                 <c:if test="${ requestScope.date4 eq '3'}">
                 <div class="rank rank25"><c:out value="${ requestScope.contestListRankEight[0].novTitle }" /></div>
                 <div class="rank rank26"><c:out value="${ requestScope.contestListRankEight[1].novTitle }" /></div>
                 <div class="rank rank27"><c:out value="${ requestScope.contestListRankEight[2].novTitle }" /></div>
                 <div class="rank rank28"><c:out value="${ requestScope.contestListRankEight[3].novTitle }" /></div>
                 </c:if>
                 <!-- 결승전 -->
                 <c:if test="${ requestScope.date eq '1'}">
                 <div class="rank29"><c:out value="${ requestScope.contestListRankEight[0].novTitle }" /></div>
                 <div class="rank30"><c:out value="${ requestScope.contestListRankEight[1].novTitle }" /></div>
                 </c:if>
            </div>
        </section>
        <aside>
            <div class="section2">
                <div class="tupyo">투<br>표<br>하<br>기</div>
                <div class="work_tupyo">
                    <div class="work_row1">
                    <!-- 16강 -->
                    <c:forEach items="${ requestScope.contestList }" var="contest" begin="0" end="${ requestScope.number }" step="1">
                            <div class="work" id="btn">
                                <img src="<c:out value="${ contest.competNovImgLocation}" />" width="125px">
                                <div class="work_title">
                                    <c:out value="${ contest.novTitle }" />
                                </div>
                                    <button id="btn" class="work_button" type="button">투표<label class="label_none"><c:out value="${ contest.competNum }" /></label></button>
                            </div>
                        </c:forEach>      
                     
                    
               
                    </div>
                </div>
					<div class="tupyo">
					<a class="tupyoa" href="${pageContext.servletContext.contextPath }/contestAll/list?competSsn=${ requestScope.contestListRankEight[0].competSsn }">
					지난 공모전<br>작품 보기</a>
					</div>
            </div>
        </aside>
    </div>
    <div class="size_box"></div>
     <jsp:include page="../common/footer.jsp"/>
    <script>
		$("#btn > button").click(function(){
			const competNum = $(this).find("label").text(); // 사진 게시글 번호
			/* console.log(no); */
			location.href="${ pageContext.servletContext.contextPath }/contest/voting?competNum=" + competNum;
});
	</script>
</body>

</html>