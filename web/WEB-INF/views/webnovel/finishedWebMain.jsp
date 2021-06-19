<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/webnovel/finishedWebMain.css">
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
</head>
<body>
	<!-- header 삽입 해야 함 -->
	<jsp:include page="../common/header.jsp" />
	<br><br><br><br><br><br><br><br>

	<div id="wrap">
		<main id="main" class="top_main">
			<!-- <div class="everyday"> -->
			<div class="everyday_div">
				<button class="button">
					<p class="everyday_text">판타지</p>
				</button>
				<button class="button">
					<p class="everyday_text">현대판타지</p>
				</button>
				<button class="button">
					<p class="everyday_text">무협</p>
				</button>
				<button class="button">
					<p class="everyday_text">로맨스</p>
				</button>
				<button class="button">
					<p class="everyday_text">스포츠</p>
				</button>
				<button class="button">
					<p class="everyday_text">대체역사</p>
				</button>
			</div>
			
			<div class="everyday_contents">
				<div class="monday on">
					<c:forEach var="finished" items="${ requestScope.webNovelFinishedList }">
						<c:if test="${ finished.categoryCode eq 'F' }">
							<div class="clickWebNovel" >
								<button type="button" onclick="gogo(${ finished.webNovNum },'${finished.webNovOpenOrClose }')">
								<img src="${finished.webNovImgLocation }">
								<p>
									제목 : ${ finished.webNovTitle }<br> 작가 : ${ finished.webNovAuthor }
								</p>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="tuesday">
					<c:forEach var="finished" items="${ requestScope.webNovelFinishedList }">
						<c:if test="${ finished.categoryCode eq 'MF' }">
							<div class="clickWebNovel" >
								<button type="button" onclick="gogo(${ finished.webNovNum },'${finished.webNovOpenOrClose }')">
								<img src="${finished.webNovImgLocation }">
								<p>
									제목 : ${ finished.webNovTitle }<br> 작가 : ${ finished.webNovAuthor }
								</p>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="wednesday">
					<c:forEach var="finished" items="${ requestScope.webNovelFinishedList }">
						<c:if test="${ finished.categoryCode eq 'OF' }">
							<div class="clickWebNovel" >
								<button type="button" onclick="gogo(${ finished.webNovNum },'${finished.webNovOpenOrClose }')">
								<img src="${finished.webNovImgLocation }">
								<p>
									제목 : ${ finished.webNovTitle }<br> 작가 : ${ finished.webNovAuthor }
								</p>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="thursday">
					<c:forEach var="finished" items="${ requestScope.webNovelFinishedList }">
						<c:if test="${ finished.categoryCode eq 'ROM' }">
							<div class="clickWebNovel" >
								<button type="button" onclick="gogo(${ finished.webNovNum },'${finished.webNovOpenOrClose }')">
								<img src="${finished.webNovImgLocation }">
								<p>
									제목 : ${ finished.webNovTitle }<br> 작가 : ${ finished.webNovAuthor }
								</p>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="friday">
					<c:forEach var="finished" items="${ requestScope.webNovelFinishedList }">
						<c:if test="${ finished.categoryCode eq 'SPO' }">
							<div class="clickWebNovel" >
								<button type="button" onclick="gogo(${ finished.webNovNum },'${finished.webNovOpenOrClose }')">
								<img src="${finished.webNovImgLocation }">
								<p>
									제목 : ${ finished.webNovTitle }<br> 작가 : ${ finished.webNovAuthor }
								</p>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<div class="saturday">
					<c:forEach var="finished" items="${ requestScope.webNovelFinishedList }">
						<c:if test="${ finished.categoryCode eq 'FH' }">
							<div class="clickWebNovel" >
								<button type="button" onclick="gogo(${ finished.webNovNum },'${finished.webNovOpenOrClose }')">
								<img src="${finished.webNovImgLocation }">
								<p>
									제목 : ${ finished.webNovTitle }<br> 작가 : ${ finished.webNovAuthor }
								</p>
								</button>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</main>
	</div>
</body>
	<script>
		/* 선택한 웹소설 페이지로 이동하게 하는 메소드 */
		function gogo(val1, val2) {
			var t = val1;
			var t2 = val2;
			console.log(t);
			console.log('들어가기전' + t2);
			
			/* 선택한 웹소설이 조회된 경우 */
		  if(t2 == 'Y') {
				
			location.href = "${ pageContext.servletContext.contextPath }/webnovel/detail/finished?webNovNum="
					+ parseInt(t);
			console.log(t);
			console.log('들어가기후' + t2);
			/* console.log(t2); */
			
			/* 선택한 웹소설이 조회되지 않는경우 */
			} else if(t2 == 'N'){
				alert("해당 웹소설은 현재 중비 중에 있습니다.");
				
			location.href = "${ pageContext.servletContext.contextPath }/webnovel/finished";
			}
			
		}

		/* 각 요일 별 선택시마다 div이 변경되도록 하는 메소드 */
		$(document).ready(function() {
			$(".everyday_div > .button > p").click(function() {
				var idx = $(".everyday_div > .button > p").index($(this));
				$(".everyday_div > .button > p").removeClass("on");
				$(".everyday_div > .button > p").eq(idx).addClass("on");
				$(".everyday_contents > div").hide();
				$(".everyday_contents > div").eq(idx).show();
			});
		});
		

		
		

	</script>

</html>