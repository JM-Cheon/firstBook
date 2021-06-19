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
<link rel="stylesheet" href="/firstbook/resources/css/webnovel/webnovel.css">
<link rel="stylesheet" href="/firstbook/resources/css/common/reset.css"> 
<link rel="stylesheet" href="/firstbook/resources/css/member/style.css">
<link rel="shortcut icon" href="#">
</head>
<body>
<jsp:include page="../common/header.jsp"/>
		<br><br><br><br><br><br>
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
                                # <c:out value="${ requestScope.webnovel.categoryName.categoryName }"/> <br><br>
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
                    <td> <button type="button" class="firstBtn" onclick="firstChap()">첫화보기</button> 

                        <button  type="button" id="wishBtn" class="likeBtn" >♥</button>
                        <input class="loginMember" type="hidden" id="loginMember" name="loginMember" value="${sessionScope.loginMember.memNum }">
						<input class="wishList" type="hidden" id="wishList" name="wishList" value="${ requestScope.webnovel.webNovNum }" >

                    </td>
                </tr>
                <tr>
                    <td class="space2"></td>
                </tr>
            </table>
        </section>
        
        
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
                        <h2 class="chap_date"> 연재 날짜 :  ${ webNovelChap.webChapNumDate} </h2>
                        <br>  
                        <br>                    
                        <h2 class="chap_date"> 소제목 :  ${ webNovelChap.chapPerTitle} </h2>                         
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
                        <h2 class="chap_date"> 연재 날짜 :  ${ webNovelChap2.webChapNumDate} </h2>
                        <br>
                        <br>                       
                        <h2 class="chap_date"> 소제목 :  ${ webNovelChap2.chapPerTitle} </h2>                       
                    </td> 
                    <td class="freeBtn">
                    
            		 <button class="freebtn_real"  type="button"  onclick="web(${ webNovelChap2.webNovChapNum.webNovChapNum}, '${webNovelChap2.chapReadOrNot}', ${ webNovelChap2.chapPerPrice }, '${webNovelChap2.chapPerIsUsed }', '${sessionScope.loginMember.memId }', ${webNovelChap2.webNovNum.webNovNum }, '${sessionScope.loginMember.memPwd }' )" >
                    ${ webNovelChap2.webNovChapNum.webNovChapNum}화 보기 </button>
                    <input type="hidden" name="webNovNum"  value= "${ webNovelChap2.webNovChapNum.webNovChapNum}">

				<%-- 	<input type="hidden" name="webIdajax" id="webIdajax" > 
					<input type="hidden" name="webchapNumajax" id="webchapNumajax" name="webchapNumajax" >
					<input type="hidden" name="webNumajax" id="webNumajax" name="webNumajax" >
					<input type="hidden" name="chapReadOrNot" id="chapReadOrNot" name="chapReadOrNot" >
					<input type="hidden" name="chapPerPrice" id="chapPerPrice" name="chapPerPrice" >
					<input type="hidden" name="chapPerIsUsed" id="chapPerIsUsed" name="chapPerIsUsed" > --%>
            		
            		</td> 
                </tr>
                </c:forEach>   
                    	
            </table>

        </section>
        
         
        <section class="sec3">
            <hr>
            <h3 class="reply"> 댓글 <br></h3>
            
            <div class="replydiv" width="300px">
            	
                <textarea name="댓글달기" class="replytext" id="replytext"  rows="6"></textarea>
            <p><span id="count">0</span>/100</p>
            </div>
            
            <button type="button" class="repliedbtn" id="repliedbtn"> 등록하기 </button>
            <br><br><br><br><br><br><br><br>
            
			<div class="div_reply_ajax">
				<%--  <tr>
					<td class="repliedId_ajax">
						<p class="repliedId_real_ajax">${selectWebnovReply.memId.memId }</p>
					</td>
					<td class="repliedtime_ajax">
						<p class="repliedtime_real_ajax">${selectWebnovReply.replyDate }</p>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="replied_ajax">
						<p class="replied_real_ajax">${selectWebnovReply.replyContent }</p>
					</td>
				</tr> --%>
			</div>
			
			<table class="tab_reply">
             <c:forEach var="selectWebnovReply" items="${ requestScope.selectWebnovReply}">
                <tr>
                    <td class="repliedId">
                        <p class="repliedId_real"> ${selectWebnovReply.memId.memId }  </p>
                    </td>
                    <td class="repliedUpdate">
                    	<c:if test="${selectWebnovReply.memId.memId == sessionScope.loginMember.memId}">
                        <button class="repliedUpdate_real"  value="${selectWebnovReply.replyNum }" onclick="doUpdateDelete(0)"> 수정 </button>
                    	</c:if>
                    </td>
                    <td class="repliedDelete">
                    	 <c:if test="${selectWebnovReply.memId.memId == sessionScope.loginMember.memId}"> 
                        <button class="repliedDelete_real" id="replyNum" value="${selectWebnovReply.replyNum }" onclick="doUpdateDelete(1)"> 삭제 </button>
                        <input type="hidden">
                        </c:if> 
                    </td>
                    <td class="repliedtime">
                        <p class="repliedtime_real"> ${selectWebnovReply.replyDate }</p>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" class="replied">
                        <p class="replied_real">  ${selectWebnovReply.replyContent }</p>
                    </td>
                </tr>
             </c:forEach> 
            </table>
        </section>
    </div>
    </body>
  <script>

  /* 댓글 100자 초과시 초과되었다고 알림 */
  $(function(){
	
	  $("#replytext").keyup(function(){
		
		  var inputLength = $(this).val().length;
		  
		  $("#count").text(inputLength);
		  
		  var replyRemain = 100 - inputLength;
		  
		  if(replyRemain >= 0) {
			  
               $("#count").parent().css("color","black");
               
               const target = document.getElementById('repliedbtn');
               target.disabled = false;
           }else{
               $("#count").parent().css("color","red");
               
               const target = document.getElementById('repliedbtn');
               target.disabled = true;
               
            }
	  })
	  
  }) 
  
  	/* 챕터리스트 링크 */
	const link = "${ pageContext.servletContext.contextPath}/webnovel/chapList";

	/* 댓글 ajax */
	$("#repliedbtn").click(function(){
		
		var replyText = document.getElementById("replytext").value;
		if(replyText == ""){
			
			alert("댓글은 빈칸으로 작성할 수 없습니다. 댓글을 작성해주세요");
		} else{
		
		var replytext = document.getElementById("replytext").value; 
		var webNovNum = '${ requestScope.webnovel.webNovNum}';
		var memNum = '${ sessionScope.loginMember.memNum}';
		
		console.log(replytext);
		console.log(webNovNum); 
		$.ajax({
			
			method : "GET",
			url : "/firstbook/webnovel/reply/send",
			data : {
					replytext : replytext,
					webNovNum : webNovNum,
					memNum : memNum
				},
			
			success : function(data) {
				 console.log(data); 
				 var section = $(".div_reply_ajax"); 
				 $(".div_reply_ajax").text(""); 
                 for(var i = 0 ; i < data.length ; i++){
                   console.log(data[i].replyDate);
                   console.log(data[i].replyContent);
                   console.log(data[i].memId.memId);
                 
				
	            	var postList =  $("<table>");
	            	var moreList = 
	            	  $( "<tr>" + "<td class='repliedId_ajax'>" + "<p class='repliedId_real_ajax'>" + 
		            	data[i].memId.memId + "</p>" + "</td>" + "<td class='repliedtime_ajax'>" +
		            	"<p class='repliedtime_real_ajax'>" + data[i].replyDate + "</p>" +
		            	"</td>" + "</tr>" + "<tr>" + "<td colspan='2' class='replied_ajax'>" +
		            	"<p class='replied_real_ajax'>" + data[i].replyContent + "</p>" +
		            	"</td>" + "</tr>" + "</table>" );    	
	            	      
	                postList.append(moreList); 
	                section.append(postList);  
	             } 
	          }, 
				error: function(error, status){
		                console.log(error);
		                console.log(status);
				}
				
			});
		
		var reset = "";
		document.getElementById("replytext").value = reset; 
		}
	});
	
	/* 댓글 수정 및 삭제 기능 */
	function doUpdateDelete(value) {
		
		
		var replyNum = document.getElementById("replyNum").value;
		var webNovNum = '${ requestScope.webnovel.webNovNum}';
		var memNum = '${ sessionScope.loginMember.memNum}';
		/* 댓글 수정 링크 */
		if(value == 0){	
			
		<%--	location.href = "${ pageContext.servletContext.contextPath}/webnovel/reply/update?" + --%>
			
		location.href = "#";
			/* 댓글 삭제 링크 */
		} else if(value == 1) {
			
			$.ajax({
				
				method : "GET",
				url : "/firstbook/webnovel/reply/delete",
				data : {
					replyNum : replyNum,
					webNovNum : webNovNum,
					memNum : memNum
					},
				
					success : function(data) {
						 console.log(data); 
						 var section = $(".div_reply_ajax"); 
						 $(".div_reply_ajax").html(""); 
		                 for(var i = 0 ; i < data.length ; i++){
		                   console.log(data[i].replyDate);
		                   console.log(data[i].replyContent);
		                   console.log(data[i].memId.memId);
		                 
						
			            	var postList =  $("<table>");
			            	var moreList = 
			            	  $( "<tr>" + "<td class='repliedId_ajax'>" + "<p class='repliedId_real_ajax'>" + 
				            	data[i].memId.memId + "</p>" + "</td>" + 
				            	"<p class='repliedtime_real_ajax'>" + data[i].replyDate + "</p>" +
				            	"</td>" + "</tr>" + "<tr>" + "<td colspan='2' class='replied_ajax'>" +
				            	"<p class='replied_real_ajax'>" + data[i].replyContent + "</p>" +
				            	"</td>" + "</tr>" + "</table>" );    	
			            	      
			                postList.append(moreList); 
			                section.append(postList);   
			             } 

		          }, 
					error: function(error, status){
			                console.log(error);
			                console.log(status);
					}
					
				});			
			
		}
	}
	
	

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
	 function web(var1, var2, var3, var4, var5, var6, var7){
				
		var webNovChapNum = var1;
		var chapReadOrNot = var2;
		var chapPerPrice = var3;
		var chapPerIsUsed = var4;
		var memId = var5;
		var webNovNum = var6;
		var memPwd = var7;

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
			
			return;
			} else if (chapPerIsUsed == 'N ' ){			// 웹소설 챕터를 추가 구매해야하는 경우
				
				/* 코인이 100이하여서 추가구매를 해야하는 경우 */
				if( ${sessionScope.loginMember.memCoin} < parseInt(100)) {
			
					console.log(${sessionScope.loginMember.memCoin});
					alert('"현재 ${sessionScope.loginMember.memName}님의 코인은 ${sessionScope.loginMember.memCoin} 코인으로 웹소설을 보시려면 코인을 추가로 구매하셔야 합니다."');
					return;
			
					
				/* 코인이 100이상이여서 추가구매를 하지않아도 되는 경우 */
				} else if( ${sessionScope.loginMember.memCoin} >= parseInt(100)) {
					

					location.href = "${ pageContext.servletContext.contextPath}/member/chargeCoin?memId=${sessionScope.loginMember.memId}" + "&webNovNum=" + webNovNum + "&webNovChapNum=" + webNovChapNum; 

					
				}
			}
		}
	} 
				
	/* 첫화보기를 눌렀을 때 */
	function firstChap(){

		location.href = link  + "?currentWebNov=" + ${ requestScope.webnovel.webNovNum} + "&currentChap=" + 1;

	}
	
	/* 위시리스트 버튼 ajax */
	$("#wishBtn").click(function(){
 			
 			var wishlistWebnovNum = document.getElementById("wishList").value;
 			var loginMember = document.getElementById("loginMember").value;
 			console.log('wish' + wishlistWebnovNum);
 			console.log('login' + loginMember);
 	  
 				$.ajax({
 					url : "/firstbook/member/wishlistupdate",
 					method : "GET",
 					data : {
 						wishlistWebnovNum : wishlistWebnovNum,
 						loginMember : loginMember
 						},
 				
 					success: function (data) {
 				
 						 }, 
 						 
 						error: function(error,status){
 				                console.log(error);
 				                console.log(status);
 					}
 				});			
	}); 
	
	
	
 	
 	 /* n화 볼 때마다 업데이트 되는 ajax */	
 	<%-- $(function(){
        var currentPage = ${ requestScope.pageInfo.pageNo };
        console.log('currentPage : ' + currentPage);
        var currentWebNov = ${ requestScope.webnovel.webNovNum};
        
        $(window).scroll(function(){   
           if($(window).scrollTop() + $(window).height() + 3 > $(document).height()) {
              currentPage++;   
              /* console.log('currentlink' + currnetlink); */
                $.ajax({
                   url : '/firstbook/ajax/test',
                    type : 'get',  
                    data : { 
                          currentPage : currentPage,
                          currentWebNov : currentWebNov
                          },
                    success : function(data) {
                       console.log(currentPage);
                       if( currentPage === 1 ){
                          console.log(data);
                       } else {
                           var section = $(".sec2");
                          
                          for(var i = 0 ; i < data.length ; i++){
                             console.log(data[i].postNo);
                             console.log(data[i].postTitle);
                             console.log(data[i].minPrice);
                          }
                          } 
                           	var postList = $("<table>").addClass("post");
                             var moreList = 
                             $("<tr id='thumbnail'>" + "<td colspan='2px'>" + data[i].postNo + "</td>" + "<tr>"
                                  + "<tr id='title'>" + "<td colspan='2px'>" + data[i].postTitle + "</td>" + "<tr>"
                                  + "<tr id='minPrice'>" + "<td width='80px'>" + "최소입찰가" + "</td>" 
                                     + "<td align='right'>" + data[i].minPrice + " 원 " +"</td>"+ "</tr>");
                             
                             postList.append(moreList);
                             section.append(postList); 
                          } 
                       } 
                    },
                    error : function(error) {
                       console.log("에러다 개발자야 뭐하냐!");
                    }
                });      /* ajax 종료 */
           }
        })      /* 스크롤 페이징 함수 종료 */
     }); --%>
	
	</script> 
</html>