<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/admin/webnovel/adminWebnovelMain.css">

<style >


</style>
</head>
<body>
<jsp:include page="/WEB-INF/views/admin/adminHeader.jsp"/>
<br><br><br><br><br><br><br><br>
    <div id="wrap">
        <div class="top_main">        
        <section class="sec1">
            <div class="admin_webnov web_whole_div">
                <div class="admin_webnov web_title">
                    <table class="admin_webnov web_title_tab" >
                        <tr class="web_title_head">
                            <th class="introRadio"> </th>
                            <th class="introImg"> 이미지 </th>
                            <th class="introTitle"> 제목 </th>
                            <th class="introAuthor"> 작가 </th>
                            <th class="introDayofWeek"> 연재요일 </th>
                            <th class="introPublisher"> 출판사 </th>
                            <th class="introSum"> 공개 여부</th>
<!--                             <th class="introCategory"> 카테고리코드</th>
                            <th class="introPrice"> 가격</th>
                            <th class="introinform"> 소개 </th>
                            <th class="introFinishedorNot"> 완결 여부 </th> -->
                        </tr>
                    </table>
                </div>
                <div class="admin_webnov web_list">
                <form id="form" method=get>
                    <table class="admin_webnov web_list_tab" >
                    	<c:forEach var="adminWebnovel" items="${ requestScope.adminWebNovelList }">
                        <tr >
                            <td class="webList webradio"> <input type="radio" name="checkOrNot"> </td>
                            <td class="webList webimg"> <img class="webImgList" src="${ adminWebnovel.webNovImgLocation }" id="imgWebnovel"></td>
                            <td class="webList webtitle"> ${ adminWebnovel.webNovTitle } </td>
                            <td class="webList webauthor"> ${ adminWebnovel.webNovAuthor } </td>
                            <td class="webList webDayofWeek"> ${ adminWebnovel.dayOfWeek } </td>
                            <td class="webList webdate"> ${ adminWebnovel.webNovPublisher } </td>
                            <td class="webList websum"> ${ adminWebnovel.webNovOpenOrClose } </td>
                            <td class="webList websum"> ${ adminWebnovel.webNovNum } </td>
<%--                             <td class="webList webCategory"> ${ adminWebnovel.categoryCode } </td>
                            <td class="webList webprice"> ${ adminWebnovel.chapPerCoin } </td>
                            <td class="webList webInform"> ${ adminWebnovel.webNovInform } </td>
                            <td class="webList websFinishedorNot"> ${ adminWebnovel.finishedOrNot } </td> --%>
                        </tr>                 
		                    <input class="webList" type="hidden" id="imgLocation" name="imgLocation" >
		                    <input class="webList" type="hidden" id="webTitle" name="webTitle">
		                    <input class="webList" type="hidden" id="webAuthor" name="webAuthor" >
		                    <input class="webList" type="hidden" id="dayOfWeek" name="dayOfWeek" >
		                    <input class="webList" type="hidden" id="webNovPublisher" name="webNovPublisher" >
		                    <input class="webList" type="hidden" id="webNovOpenOrClose" name="webNovOpenOrClose" >
		                    <input class="webList" type="hidden" id="webNovNum" name="webNovNum" >
<!-- 		                    <input class="webList" type="hidden" id="categoryCode" name="categoryCode" >
		                    <input class="webList" type="hidden" id="chapPerCoin" name="chapPerCoin" >
		                    <input class="webList" type="hidden" id="webNovInform" name="webNovInform" >
		                    <input class="webList" type="hidden" id="finishedOrNot" name="finishedOrNot" > -->
                       </c:forEach>
                    </table>
                    
                 </form>
                 

                 
                </div>
            </div>        
        </section>
        <section class="sec2">
      <!--       <div class="sec2 search_div">
                <div class="sec2 search_div search_div_real">
                    작품조회
                </div> 
                <table class="sec2 search_div_title" >
                    <tr>
                        <td>
                            <form action="" method="get">
                                <label for="">제목 : </label>
                                <input type="text"> <br><br>
                                <label for="">작가 : </label>
                                <input type="text"> <br><br>
                            </form>
                        </td>
                    </tr>
                </table>
                <div class="sec2 search_btn_div">
                    <button class="searchBtn">조회</button>
                </div>              
            </div>    -->
        </section>
        <section class="sec3" style="position: absolute; left:1200px; top:500px;">
            <div class="enrollModifyDeletePosition">
                <br><br>
                <button class="enrollModifyDelete" id="registNewNovel">작품 등록</button>
                <br><br>
                <button class="enrollModifyDelete" type="submit" onclick="update()">수정</button>
                <br><br>
		

                <button class="enrollModifyDelete" type="submit" onclick="closeOrOpen();" >공개/비공개</button>
             

            </div>
        </section>

    </div>
    </div>    

<script>
	/* 새로운 웹소설 등록용 메소드 */
	if (document.getElementById("registNewNovel")) {
		const $registNewNovel = document.getElementById("registNewNovel");
		$registNewNovel.onclick = function() {
			location.href = "/firstbook/admin/novel/regist";
		}
	}
	/* 수정용 메소드 */

	if (document.getElementsByClassName("webList")) {
		const $tds = document.getElementsByClassName("webList");
		console.log($tds);
		for (var i = 0; i < $tds.length; i++) {

			$tds[i].onmouseenter = function() {
				this.parentNode.style.backgroundColor = "grey";
				this.parentNode.style.cursor = "pointer";
			}

			
			 $tds[i].onmouseout = function() {
				this.parentNode.style.background = "white";
			}
			 
		
			$tds[i].onclick = function() {
				this.parentNode.style.backgroundColor = "gray";
				document.getElementById("imgLocation").value = this.parentNode.children[1].children[0].getAttribute("src");
				document.getElementById("webTitle").value  = this.parentNode.children[2].innerText;
				document.getElementById("webAuthor").value  = this.parentNode.children[3].innerText;
				document.getElementById("dayOfWeek").value  = this.parentNode.children[4].innerText;
				document.getElementById("webNovPublisher").value  = this.parentNode.children[5].innerText;
				document.getElementById("webNovOpenOrClose").value  = this.parentNode.children[6].innerText;
				document.getElementById("webNovNum").value  = this.parentNode.children[7].innerText;
				/* document.getElementById("categoryCode").value  = this.parentNode.children[7].innerText;
				document.getElementById("chapPerCoin").value  = this.parentNode.children[8].innerText;
				document.getElementById("webNovInform").value  = this.parentNode.children[9].innerText;
				document.getElementById("finishedOrNot").value  = this.parentNode.children[10].innerText; */
				
				
				/* const no = this.parentNode.children[0].innerText; */
				/* var location = document.getElementById("imgLocation").value; */
				/* var title = document.getElementById("webTitle").value;
				var author = document.getElementById("webAuthor").value; */
				/* console.log(this.parentNode.children[1].children[0].getAttribute("src"));
				console.log(document.getElementById("imgLocation").value);
				console.log(document.getElementById("webTitle").value); */
				/* console.log(title); */
				
				/* 게시물 번호까지 알아 냈으니 게시물 상세보기는 공지사항 상세보기를 참조하여 작성 */
			}
		}
	}
	
	
	
	function update(){
		document.getElementById("form").submit();
		location.href ="${ pageContext.servletContext.contextPath}/admin/novel/update?webNovNum=" + document.getElementById("webNovNum").value 
	}

	function closeOrOpen(){
		document.getElementById("form").submit();
		location.href ="${ pageContext.servletContext.contextPath}/admin/webnovelCloseOpen?webNovNum=" + document.getElementById("webNovNum").value
	}
	

</script>
</body>
</html>









