<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FirstBook</title>
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
<link rel="stylesheet" href="/firstbook/resources/css/member/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<jsp:include page="../common/header.jsp"/>

    <div id="wrap">
        <main id="main" class="top_main">
            <div class="mypage">
                <div class="my_nav">
                    <ul>
                        <li class="my_nav_line on">홈</li>
                    </ul>
                    <p class="my_nav_line">웹소설</p>
                    <ul>
                        <li>내 서재</li>
                        <li>위시리스트</li>
                        <li>회원정보</li>
                        <li>결제내역</li>
                        <li>충전내역</li>
                        <li>회원탈퇴</li>
                    </ul>
                    <p class="my_nav_line">단행본</p>
                    <ul>
                        <li>장바구니</li>
                        <li>구매내역</li>
                    </ul>
                </div>
                <div class="my_contents">
                    <div class="my_home on">
                        <p>보유 코인 : <c:out value="${ sessionScope.loginMember.memCoin }"/></p>
                        <button id="freeCoin">무료 코인 받기</button>
                        <h2>내 서재</h2>
                        <div class="my_home_book">
	                        <c:forEach var="useCoin" items="${ requestScope.useCoinList }" begin="0" end="4" step="1">
	                        	<div>
		                        	<a href="${ pageContext.servletContext.contextPath }/webnovel/detail?webNovNum=${ useCoin.webNov.webNovNum }">
		                            	<img src="${ useCoin.webNov.webNovImgLocation }">
			                        </a>
		                            <p>
		                            	${ useCoin.webNov.webNovTitle } ${ useCoin.chapterNum }화<br>
		                            	${ useCoin.webNov.webNovAuthor }
		                            </p>
		                        </div>
	                        </c:forEach>
                        </div>
                        <h2>위시리스트</h2>
                        <div>
	                        <c:forEach var="wish" items="${ requestScope.wishlist }" begin="0" end="4" step="1">
	                        	<div>
		                        	<a href="${ pageContext.servletContext.contextPath }/webnovel/detail?webNovNum=${ wish.webNov.webNovNum }">
			                            <img src="${ wish.webNov.webNovImgLocation }">
			                        </a>
		                            <p>
		                            	${ wish.webNov.webNovTitle }<br>
		                            	${ wish.webNov.webNovAuthor }
		                            </p>
		                        </div>
	                        </c:forEach>
                        </div>
                    </div>
                    <div class="my_lib">
                        <h2>내 서재</h2>
                        <div>
                        <c:forEach var="useCoin" items="${ requestScope.useCoinList }">
                        	<div>
                        		<a href="${ pageContext.servletContext.contextPath }/webnovel/detail?webNovNum=${ useCoin.webNov.webNovNum }">
	                            	<img src="${ useCoin.webNov.webNovImgLocation }">
	                            </a>
	                            <p>
	                            	${ useCoin.webNov.webNovTitle } ${ useCoin.chapterNum }화<br>
	                            	${ useCoin.webNov.webNovAuthor }
	                            </p>
	                        </div>
                        </c:forEach>
                        </div>
                    </div>
                    <div class="my_wish">
                        <h2>위시리스트</h2>
                        <div>
                        <c:forEach var="wish" items="${ requestScope.wishlist }">
                        	<div>
                        		<a href="${ pageContext.servletContext.contextPath }/webnovel/detail?webNovNum=${ wish.webNov.webNovNum }">
	                            	<img src="${ wish.webNov.webNovImgLocation }">
	                            </a>
	                            <p>
	                            	${ wish.webNov.webNovTitle }<br>
	                            	${ wish.webNov.webNovAuthor }
	                            </p>
	                        </div>
                        </c:forEach>
                        </div>
                    </div>
                    <div class="my_member">
                    	
	                    <h2>회원정보</h2>
                        <p>가입일 : ${ sessionScope.loginMember.memEnrollDate }</p>
                        <form action="${ pageContext.servletContext.contextPath }/member/update" method="post">
		                    <div class="login_input_box">
			                    이름 <input type="text" name="memName" value="${ sessionScope.loginMember.memName }" required>
			                </div>
			                <div class="login_input_box">
			                    아이디 <input type="text" id="memId" name="memId" value="${ sessionScope.loginMember.memId }" readonly>
			                </div>
			                <div class="login_input_box">
			                    비밀번호 <input type="password" id="memPwd" name="memPwd" required>
			                </div>
			                <div class="login_input_box">
		                        생년월일 <input type="date" name="memBirthDate" value="${ sessionScope.loginMember.memBirthDate }" required>
		                    </div>
			                <div class="email_input_box">
		                        이메일 <input type="text" name="memEmail" value="${ fn:split(sessionScope.loginMember.memEmail,'@')[0] }">
		                         @ 
		                        <select name="memEmail2">
		                        	<option value="naver.com" <c:if test="${ fn:split(sessionScope.loginMember.memEmail,'@')[1] eq 'naver.com' }">selected</c:if> >naver.com</option>
		                        	<option value="google.com" <c:if test="${ fn:split(sessionScope.loginMember.memEmail,'@')[1] eq 'google.com' }">selected</c:if>>google.com</option>
		                        </select>
		                    </div>
		                    <button type="button" id="searchZipCode" class="chkButton">
		                        주소 검색
		                    </button>
			                <div class="login_input_box">
		                        우편번호 <input type="text" name="memZipCode" id="memZipCode" value="${ fn:split(sessionScope.loginMember.memAddress,'$')[0] }" readonly>
		                    </div>
		                    <div class="login_input_box">
		                        주소 <input type="text" name="memAddress" id="memAddress" value="${ fn:split(sessionScope.loginMember.memAddress,'$')[1] }" readonly>
		                    </div>
		                    <div class="login_input_box">
		                        상세주소 <input type="text" name="memDetailAddress" id="memDetailAddress" value="${ fn:split(sessionScope.loginMember.memAddress,'$')[2] }" required>
		                    </div>
		                    <button type="submit" onclick="return check();">
		                        수정하기
		                    </button>
	                    </form>
                    </div>
                    <div class="my_weblist">
                        <h2>결제내역</h2>
                        <div>
	                        <table>
	                        	<tr>
	                        		<td class="my_weblist_title">웹소설명</td>
	                        		<td class="my_weblist_title">회차</td>
	                        		<td class="my_weblist_title">사용코인</td>
	                        		<td class="my_weblist_title">구매날짜</td>
	                        	</tr>
	                        	<c:forEach var="useCoin" items="${ requestScope.useCoinList }">
		                        	<tr>
		                        		<td class="my_weblist_body">${ useCoin.webNov.webNovTitle }</td>
		                        		<td class="my_weblist_body">${ useCoin.chapterNum }</td>
		                        		<td class="my_weblist_body">${ useCoin.coinUsage }</td>
		                        		<td class="my_weblist_body">${ useCoin.coinUseDate }</td>
		                        	</tr>
		                        </c:forEach>
	                        	
	                        </table>
                        </div>
                    </div>
                    <div class="my_weblist">
                        <h2>충전내역</h2>
                        <div>
	                        <table>
	                        	<tr>
	                        		<td class="my_weblist_title">충전일시</td>
	                        		<td class="my_weblist_title">충전갯수</td>
	                        		<td class="my_weblist_title">충전금액</td>
	                        		<td class="my_weblist_title">무료코인여부</td>
	                        	</tr>
	                        	<c:forEach var="coinCharge" items="${ requestScope.coinChargelist }">
		                        	<tr>
		                        		<td class="my_weblist_body">${ coinCharge.coinChargeDate }</td>
		                        		<td class="my_weblist_body">${ coinCharge.coinChargeCount }</td>
		                        		<td class="my_weblist_body">${ coinCharge.coinChargeTotal }</td>
		                        		<td class="my_weblist_body">${ coinCharge.weeklyCoinYn }</td>
		                        	</tr>
		                        </c:forEach>
	                        	
	                        </table>
                        </div>
                    </div>
                    <div class="my_bye">
                        <div>
	                        <h2>회원탈퇴</h2>
	                        <p>
	                        	정말 탈퇴하시겠다면<br>
	                        	비밀번호를 입력해주세요
	                        </p>
	                        <form action="${ pageContext.servletContext.contextPath }/member/delete" method="post">
	                        	<input type="password" name="memPwd">
	                        	<button type="submit">Distroy</button>
	                        </form>
                        </div>
                    </div>
                    <div class="my_basket">
                        <h2>장바구니</h2>
                    </div>
                    <div class="my_booklist">
                        <h2>구매내역</h2>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <jsp:include page="../common/footer.jsp"/>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    	const memNum = ${ sessionScope.loginMember.memNum };
    	const memWeeklyCoinYn = "${ sessionScope.loginMember.memWeeklyCoinYn }";
    	console.log(memWeeklyCoinYn);
    	var now = new Date();
    	var weekOfDay = now.getDay();
    	var hour = now.getHours();
    	var min = now.getMinutes();
    	
    	
        $(document).ready(function() {
            $(".my_nav > ul > li").click(function() {
                var idx = $(".my_nav > ul > li").index($(this));
                $(".my_nav > ul > li").removeClass("on");
                $(".my_nav > ul > li").eq(idx).addClass("on");
                $(".my_contents > div").hide();
                $(".my_contents > div").eq(idx).show();
            });
            
            /* if(weekOfDay == 5 && hour == 16 && min == 14 && memWeeklyCoinYn == "N"){
            	location.href = "${ pageContext.servletContext.contextPath }/member/updateFreeCoinYn";
            } */
            
            if(memWeeklyCoinYn == 'N'){
            	$("#freeCoin").css("background-color","#ccc");
            	$("#freeCoin").css("color","#fff");
            	$("#freeCoin").attr("disabled","disabled");
            }  
            
        });
        
        $("#freeCoin").click(function() {
        	$.ajax({
				url: "/firstbook/member/freeCoin",
				type: "get",
				data: {
						memNum : memNum
					  },
				success: function(data){
					if(data == "1"){
						alert("코인 지급 완료!");
						$("#freeCoin").css("background-color","#ccc");
		            	$("#freeCoin").css("color","#fff");
		            	$("#freeCoin").attr("disabled","disabled");
		            	location.reload();
					} else {
						alert("코인 지급이 불가능합니다.");
					}
					
				},
				error: function(request, status, error){
					alert("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
				}
        	
        	});
        });
    	
    	const $searchZipCode = document.getElementById("searchZipCode");
    		
    	$searchZipCode.onclick = function(){
    			
    		new daum.Postcode({
    				
    			oncomplete : function(data){
    					
    				document.getElementById("memZipCode").value = data.zonecode;
    				document.getElementById("memAddress").value = data.address;
    				document.getElementById("memDetailAddress").focus();
    					
    			}
    				
    		}).open();
    			
    	}
    </script>
</body>
</html>