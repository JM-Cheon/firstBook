 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/contest/contestDetail.css">
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">


</head>
<body>
<div id="wrap">
        <jsp:include page="adminHeader.jsp"></jsp:include>
<div class="size_box"></div>
    <section>
        <div class="section1">
            <div class="section1_content">
                <div class="section1_content_left">
                    <img src="<c:out value="${ requestScope.contestlList[0].competNovImgLocation }" />" alt="" height="350px">

                </div>
                <div class="section1_content_right">
                    <div class="font section1_content_right_first">
                        <c:out value="${ requestScope.contestlList[0].novTitle }" />
                    </div>
                    <div class="font section1_content_right_second ">
                        <c:out value="${ requestScope.contestlList[0].ageLimit }" />세 이용가<br>
                        작가: <c:out value="${ requestScope.memberList[0].memName }" /><br>
                        #<c:out value="${ requestScope.category }" />
                    </div>
                    <div class="font section1_content_right_third">
                        작품소개
                    </div>
                    <div class="font section1_content_right_fourth">
                        <c:out value="${ requestScope.contestlList[0].novInfo }" /><c:out value="${ sessionScope.loginMember.memId }" />
                    </div>
                    <div class="font section1_content_right_five">
                    <form action="${ pageContext.servletContext.contextPath }/admin/contestActiveYn" method="get">	
                        <button type="submit" class="firstBtn">활성화/비활성화</button>            
						&nbsp;&nbsp;&nbsp;&nbsp; 
						<label style="font-size: 20px;">활성화 여부 : </label> 
						
						 	<input type="text" name="activeYn" value="${ requestScope.contestlList[0].competActYn }" style="color:red; font-size: 20px;">
						 	<input type="hidden" name="competNum" value="${ requestScope.contestlList[0].competNum }"> 
					</form>
                    </div>

                </div>
            </div>
        </div>

    </section>
    <section>
        <div class="font section2_title">
            &nbsp &nbsp &nbsp매주 화요일 연재
        </div>
        <div class="section2">
            <div class="section2_space"></div>
             <c:forEach items="${ requestScope.contestDetailList }" var="DetailList">
            <div class="font section2_content">
                <div class="section2_content_img">
                    <img src="<c:out value="${ requestScope.contestlList[0].competNovImgLocation }" />" alt="" width="160px">
                </div>
                <div class="section2_content_main">
                    <c:out value="${ DetailList.novContent }" /><br>
                    <c:out value="${ DetailList.competDate }" />
                </div>
                <div class="section2_content_bt">
                    <button type="button" class="section2_content_btn" 
                    onclick="test(${ DetailList.competNum },${ DetailList.chapterNum })">수정하기</button>
                </div>
            </div>
            <div class="section2_space"></div>
			</c:forEach>
            
            

    </section>
    <section>
        <div class="section3">
            <div class="font section3_title">
                &nbsp &nbsp &nbsp댓글
            </div>
            <div class="section3_writing">

                <!-- 댓글 등록 -->
                <div class="section3_writing_box">
                    <textarea name="댓글달기" class="section3_writing_text" rows="6"></textarea>
                </div>
                <div class="section3_writing_button" >
                    <button type="button" class="button">등록하기</button>
                </div>

            </div>
        </div>

        <div class="section3_space"></div>

        <!-- 댓글 현황 보기 -->
        <div class="section3_writing_view">
            <div class="section3_writing_view_first">
                <div class="section3_nickname">여행을 가본다</div>
                <div class="section3_time">1분전</div>
            </div>
            <div class="section3_content">난 다이아 급이라구</div>
        </div>
        <!-- 댓글 현황 보기 -->
        <div class="section3_writing_view">
            <div class="section3_writing_view_first">
                <div class="section3_nickname">여행을 가본다</div>
                <div class="section3_time">1분전</div>
            </div>
            <div class="section3_content">난 다이아 급이라구</div>
        </div>
    </section>
</div>
<script>
/* 		$("#voting").click(function(){
			const competNum = $(this).find("label").text();	
			location.href="${ pageContext.servletContext.contextPath }/contest/selectVoting?competNum=" + competNum;
		}); */
		
		
		const link = "${ pageContext.servletContext.contextPath}/admin/contestEditor";

		function test(var1, var2){
				
			var t = var1;
			var t2 = var2; 
			console.log(t);
			console.log(t2);
			
			/* if(t2 == 'N'){
				 alert("해당 웹소설의 " + t + "화는 준비 중에 있습니다!")
				 
				} else if (t2 == 'Y'){ */
					
			location.href = link  + "?competNum=" + parseInt(t) +  "&chapterNum=" + parseInt(t2) + "&first=" + 1;			
				/* } */

			}
</script> 
</body>
</html>