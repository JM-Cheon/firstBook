<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet" href="/firstbook/resources/css/webnovel/webnovel.css">
<body>
<jsp:include page="/WEB-INF/views/admin/adminHeader.jsp"/>
	<c:set var="webNovel" value="${ requestScope.webNovelUpdate }"/>
	
	<%-- <form action="${ pageContext.servletContext.contextPath }/admin/novel/update" method="post"> --%> 
			

	<br><br><br><br><br>			
		
	  <div id="wrap">
        <section class="sec1">
            <table class="tab_main">
                <tr>
                    <td rowspan="5" class="main_img">
                        <img src="${ requestScope.webnovel.webNovImgLocation}" class="main_img_real" alt="메인사진">
                    </td>
                    <td rowspan="5" class="space"></td>
                    <td class="space2"></td>
                </tr>
                <tr>
                    <td rowspan="2">
                        <div class="title_div">
                            <h2 class="title"> <c:out value="${ requestScope.webnovel.webNovTitle }"/> <br><br><br></h2>
                            <h3 class="title_small">
                                작가 : <c:out value="${ requestScope.webnovel.webNovAuthor }"/><br> 
                                #<c:out value="${ requestScope.webnovel.categoryName.categoryName }"/> <br><br>
                            </h3>
                       </div>
                        <div class="subtitle_div">
                            <h3 class="subtitle"> 작품소개<br><br> </h3>
                            <h3 class="subtitle_small">
                                <c:out value="${ requestScope.webnovel.webNovInform }"/>
                                <br><br>
                            </h3>
                        </div>
                    </td>
                </tr>
                <tr>
                </tr>
 
                <tr>
                    <td class="space2"></td>
                </tr>
            </table>
        </section>
        <!-- </form> -->
        
        <section class="sec2">
            <hr>
            <h4 class="everynday">매주 <c:out value="${ requestScope.webnovel.dayOfWeek }" />요일 연재</h4>
            <br><br>
            	<table class="tab_mid">
            	    <!-- 5화까지 무료! -->
            	<c:forEach var="webNovelChap" items="${ requestScope.webnoveldetail}"> 
            	<tr>
                    <td class="chap_img"><img src="${ requestScope.webnovel.webNovImgLocation}" class="chap_img_real" alt="1화부터사진">
                    </td>
                    <td class="prolog">
                        <h2 class="chap_date"> 등록 날짜 :  ${ webNovelChap.webChapNumDate} </h2>                       
                    </td> 
                    <td class="freeBtn">               
                      <button class="freebtn_real" id="freebtn_real_2" type="button" onclick="webView(${ webNovelChap.webNovChapNum.webNovChapNum}, '${webNovelChap.chapReadOrNot}')" >
                    무료보기 </button>
                    <input type="hidden" id="webNovNum" value= "${ webNovelChap.webNovChapNum.webNovChapNum}"> 
             		 
             		</td>
                </tr>
                </c:forEach>
                  
                <!-- 6화부터 결제! -->
              <c:forEach var="webNovelChap2" items="${ requestScope.webnoveldetail2}"> 
            	<tr>
  
            		<td class="chap_img"><img src="${ requestScope.webnovel.webNovImgLocation}" class="chap_img_real" alt="1화부터사진">
                    </td>
                    <td class="prolog">
                        <h2 class="chap_date"> 등록 날짜 :  ${ webNovelChap2.webChapNumDate} </h2>                       
                    </td> 
                    <td class="freeBtn">
                    <form action="notFreeForm" method="get">
            		 <button class="freebtn_real"  type="button"  onclick="web(${ webNovelChap2.webNovChapNum.webNovChapNum}, '${webNovelChap2.chapReadOrNot}', ${ webNovelChap2.chapPerPrice }, '${webNovelChap2.chapPerIsUsed }', ${sessionScope.loginMember.memNum }, ${webNovelChap2.webNovNum.webNovNum } )" >
                    ${ webNovelChap2.webNovChapNum.webNovChapNum}화 보기 </button>
                    <input type="hidden" name="webNovNum"  value= "${ webNovelChap2.webNovChapNum.webNovChapNum}">

			
            		</form>
            		</td> 
                </tr>
                </c:forEach>   
                    	
            </table>

        </section>
        
  
    </div>
   
  <script>
   
	const link = "${ pageContext.servletContext.contextPath}/admin/chapList";

	/* 1 ~ 5화 까지 보기 */
	function webView(var1, var2){
			
		var t = var1;
		var test = var2;
		console.log(t);
		console.log(test);
		
		/* 선택한 웹소설 챕터가 없는 경우 */
		if(test == 'N'){
			 alert("해당 웹소설의 " + t + "화는 준비 중에 있습니다!")
			 
		/* 선택한 웹소설 챕터가 있는 경우 */
			} else if (test == 'Y'){
				
			location.href = link  + "?currentWebNov=" + ${ requestScope.webnovel.webNovNum} + "&currentChap=" + t;			
			}
		}
	
	/* 6화부터 보기 */
	 function web(var1, var2, var3, var4, var5, var6){
				
		var webNovChapNum = var1;
		var chapReadOrNot = var2;
		var chapPerPrice = var3;
		var chapPerIsUsed = var4;
		var memId = var5;
		var webNovNum = var6;

		console.log(webNovChapNum);
		console.log(chapReadOrNot);
		console.log(chapPerPrice);
		console.log(chapPerIsUsed);
		console.log(memId);

	
		/* 웹소설이 준비 되지 않은 경우 */
		if(chapReadOrNot == 'N'){
			
			 alert("해당 웹소설의 " + webNovChapNum + "화는 준비 중에 있습니다!")
			
		/* 웹소설이 준비된 경우 */
		} else if (chapReadOrNot == 'Y') {
			
		/* 선택한 웹소설을 이미 구매한 경우 */
			if(chapPerIsUsed == 'Y '){
		
			location.href = link  + "?currentWebNov=" + ${ requestScope.webnovel.webNovNum} + "&currentChap=" + webNovChapNum;			
		
			} else if (chapPerIsUsed == 'N ' ){			// 웹소설 챕터를 추가 구매해야하는 경우
				
				/* 코인이 100이하여서 추가구매를 해야하는 경우 */
				if( parseInt(${sessionScope.loginMember.memCoin}) <= parseInt(100)) {
			
					console.log(${sessionScope.loginMember.memCoin});
					alert('"현재 " + ${sessionScope.loginMember.memName} + "님의 현재 코인은" + ${sessionScope.loginMember.memCoin} + "으로 웹소설을 보시려면 코인을 추가로 구매하셔야 합니다."');
			
					
				/* 코인이 100이상이여서 추가구매를 하지않아도 되는 경우 */
				} else if(  parseInt(${sessionScope.loginMember.memCoin}) > parseInt(100)) {
					
					location.href = "${ pageContext.servletContext.contextPath}/member/chargeCoin?memId=" + ${sessionScope.loginMember.memNum} + "&webNovNum=" + webNovNum + "&webNovChapNum=" + webNovChapNum;

					return;
				}
			}
		}
	} 
				

	

		

			</script>		
	
</body>
</html>