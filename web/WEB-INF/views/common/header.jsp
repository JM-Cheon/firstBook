<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/contest/header.css">
<link rel="stylesheet" href="/firstbook/resources/css/member/style.css">
</head>
<body>
	
	<header class="header" class="fixed">
            <div class="border">
                <div class="logo_box">
                    <a href="${ pageContext.servletContext.contextPath }" title="more"><img src="/firstbook/resources/image/contest/logo.png" alt="more"></a>

                </div>

				<c:if test="${ empty sessionScope.loginMember }">
	                <div class="menu_box" id="menu_box">
	                    <nav class="nav">
	                        <ul>
	                            <li><a href="" class="main-btn">연재</a></li>
	                            <li><a href="" class="main-btn">완결</a></li>
	                            <li><a href="" class="main-btn">공모전</a></li>
	                            <li><a href="" class="main-btn">이벤트</a></li>
	                            <li><a href="" class="main-btn">고객문의</a></li>
	                            <li><a href="" class="main-btn">단행본</a></li>
	                            
	                        </ul>
	                    </nav>
	                </div>
                </c:if>
				<c:if test="${ !empty sessionScope.loginMember }">
	                <div class="menu_box">
	                    <nav class="nav">
	                        <ul>
	                            <li><a href="${ pageContext.servletContext.contextPath }/webnovel/list" class="main-btn">연재</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/webnovel/finished" class="main-btn">완결</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/contest/list" class="main-btn">공모전</a></li>
	                            <li><a href="#" class="main-btn">이벤트</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/member/notice" class="main-btn">고객문의</a></li>
	                            <li><a href="${ pageContext.servletContext.contextPath }/book/list" class="main-btn">단행본</a></li>
								<c:if test="${ sessionScope.loginMember.memId eq 'admin' }">

									<li><a href="${ pageContext.servletContext.contextPath }/admin/main" class="main-btn">관리자 메인</a></li>

								</c:if>
	                        </ul>
	                    </nav>
	                </div>
                </c:if>

                <div class="login_box">
	                <c:if test="${ empty sessionScope.loginMember }">
	                    <ul>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/login" class="main-btn">로그인</a></li>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/regist" class="main-btn">회원가입</a></li>
	                    </ul>
	                </c:if>
	                <c:if test="${ !empty sessionScope.loginMember }">
	                    <ul>
	                        <li id="coin"><a href="#" class="main-btn">코인: <c:out value="${ sessionScope.loginMember.memCoin }"/></a></li>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/mypage" class="main-btn"><c:out value="${ sessionScope.loginMember.memName }"/>님</a></li>
	                        <li><a href="${ pageContext.servletContext.contextPath }/member/logout" class="main-btn">로그아웃</a></li>
	                    </ul>
	                </c:if>
                </div>

            </div>
        </header>
        <div class="pop">
	        <div class="pop_title">
	        	코인 패키지
	        </div>
	        <div class="pop_close" id="pop_close">
	        	X
	        </div>
	        <form action="${ pageContext.servletContext.contextPath }/member/insertCoin" method="post">
		        <table>
		        	<tr>
		        		<td class="pop_td">
		        			<img src="/firstbook/resources/image/coin.svg"> 
		        			코인 1개
			        		<div>
			        			<input type="radio" value="1" name="amount">
				        		100원
			        		</div>
			        	<td>
		        		<td class="pop_td">
		        			<img src="/firstbook/resources/image/coin.svg"> 
		        			코인 200개
		        			<div>
			        			<input type="radio" value="200" name="amount">
				        		20,000원
			        		</div>
		        		<td>
		        	</tr>
		        	<tr>
		        		<td class="pop_td">
			        		<img src="/firstbook/resources/image/coin.svg"> 
			        		코인 10개
			        		<div>
			        			<input type="radio" value="10" name="amount">
				        		1,000원
			        		</div>
		        		<td>
			        		<td class="pop_td"><img src="/firstbook/resources/image/coin.svg"> 
			        		코인 300개
			        		<div>
			        			<input type="radio" value="300" name="amount">
				        		30,000원
			        		</div>
						<td>
		        	</tr>
		        	<tr>
		        		<td class="pop_td">
			        		<img src="/firstbook/resources/image/coin.svg"> 
			        		코인 30개
			        		<div>
			        			<input type="radio" value="30" name="amount">
				        		3,000원
			        		</div>
		        		<td>
	      				<td class="pop_td">
		      				<img src="/firstbook/resources/image/coin.svg"> 
		      				코인 500개
		      				<div>
			        			<input type="radio" value="500" name="amount">
				        		50,000원
			        		</div>
	      				<td>
	      			</tr>
	      			<tr>
	      				<td class="pop_td">
		      				<img src="/firstbook/resources/image/coin.svg"> 
		      				코인 50개
		      				<div>
			        			<input type="radio" value="50" name="amount">
				        		5,000원
			        		</div>
	      				<td>
	      				<td class="pop_td">
		      				<img src="/firstbook/resources/image/coin.svg"> 
		      				코인 700개
		      				<div>
			        			<input type="radio" value="700" name="amount">
				        		70,000원
			        		</div>
	      				<td>
	      			</tr>
	      			<tr>
	      				<td class="pop_td">
		      				<img src="/firstbook/resources/image/coin.svg"> 
		      				코인 100개
		      				<div>
			        			<input type="radio" value="100" name="amount">
				        		10,000원
			        		</div>
	      				<td>
	      				<td class="pop_td">
		      				<img src="/firstbook/resources/image/coin.svg"> 
		      				코인 1000개
		      				<div>
			        			<input type="radio" value="1000" name="amount">
				        		100,000원
			        		</div>
	      				<td>
	      			</tr>
	      			<tr>
	      				<td colspan="4" align="center"><button class="pop_button">구매하기</button></td>
	      			</tr>
	      		</table>
      		</form>
		</div>
        <script>
        	$("#menu_box").click(function() {
        		if(${ empty sessionScope.loginMember }){
        			alert("로그인 후 이용가능합니다.");
        		}
        	});
        	
        	$("#coin").click(function() {
        		$(".pop").fadeIn(100);
        	});
        	
        	$("#pop_close").click(function() {
        		$(".pop").fadeOut(100);
        	});
        	
        	
        </script>
</body>
</html>