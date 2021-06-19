<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="/firstbook/resources/css/admin/main.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<body>
	<jsp:include page="adminHeader.jsp"/>
		

	
		<section>
		

		<c:set var="now" value="<%=new java.util.Date()%>" />
		 <c:set var="sysTime"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd E hh:mm:ss" /></c:set>
	
		
		<article id="a1" style="box-shadow: 10px 10px;">
		<div id="date">
			<figure>
				<figcaption>
				<label style="color:white; font-size:50px;"> 관리 현황표 </label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label style="color:white;">현재시각 :</label>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out value="${sysTime}" />
				</figcaption>
			</figure>
		</div>
		
		</article>
		
		
		
		<article id="a2" style="position:absolute; border:solid 3px; left: 400px; width:410px; height:400px; box-shadow: 10px 10px;">
			

			
			
			
			<label style="font-size:30px; background-color: red; color:white"> 회원관리 </label>
			<br> <br>
			<table style="position:absolute; left:55px; border:solid 1px; width:300px;">
				<tr>
					<th> 총 회원수 </th>
					<th> 10대	</th>
					<th> 20대	</th>
					<th> 30대	</th>
					<th> 40대	</th>
					<th> 50대	</th>
					
				</tr>
				<tr align="center">
					<td>
						<c:out value="${ requestScope.allMemberCount }"/>
					</td>
					<td>
						<c:out value="${ requestScope.member10s }"/>
					</td>
					<td>
						<c:out value="${ requestScope.member20s }"/>
					</td>
					<td>
						<c:out value="${ requestScope.member30s }"/>
					</td>
					<td>
						<c:out value="${ requestScope.member40s }"/>
					</td>
					<td>
						<c:out value="${ requestScope.member50s }"/>
					</td>
				</tr>
			
			</table> 
			
			<br><br> <br><br><br>
			
			
			<div style="width:400px">
    			<canvas id="memChart"></canvas>
			</div>
			
			
			
		</article>
		
		
		
		<article id="a3" style="position:absolute; border:solid 3px; width:410px; height:400px; box-shadow: 10px 10px;">
		
			<label style="font-size:30px; background-color: red; color:white"> 웹소설관리 </label>
			
			<table style="position:absolute; top:70px; width:400px; left:5px; ">
				
				<tr>
					<th>총소설수</th>
					<th>판타지</th>
					<th>현대판타지</th>
					<th>스포츠</th>
					<th>대체역사</th>
					<th>로맨스</th>
				
				</tr>
				
				<tr align="center">
					<td> <c:out value="${ requestScope.allWebNovelCount }"/></td>
					<td> <c:out value="${ requestScope.fantasyCount }"/></td>
					<td> <c:out value="${ requestScope.modernFatasyCount }"/></td>
					<td> <c:out value="${ requestScope.sportsCount }"/></td>
					<td> <c:out value="${ requestScope.fantasyHistoryCount }"/></td>
					<td> <c:out value="${ requestScope.romanceCount }"/></td>
								
				</tr>
			
			</table>
		
			<div style="width:400px; position:absolute; top:170px;">
    			<canvas id="novelChart"></canvas>
			</div>
		
		</article>
		
		
		
		<article id="a4">
		
			급상승 웹소설
		
		</article>
		
		
		
		<article id="a5">
			
			급상승 단행본
		
		</article>
	
	
	
	</section>
	
	<script>
	
	/*--------------------memChart--------------------------*/
	
	
	
				var ctx = document.getElementById("memChart").getContext('2d');
				
				var myChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: ["총회원수", "10대", "20대", "30대", "40대", "50대"],
				        datasets: [{
				            label: '총회원수 / 연령대별 회원수',
				            data: [ ${ requestScope.allMemberCount }, 
				                   	${ requestScope.member10s }, 
				                   	${ requestScope.member20s }, 
				                   	${ requestScope.member30s }, 
				                   	${ requestScope.member40s }, 
				                   	${ requestScope.member50s },
				            	   ],
				            backgroundColor: [
				                'rgba(54, 162, 235, 3)',
				                'rgba(255, 99, 132, 0.5)',
				                'rgba(54, 162, 235, 0.5)',
				                'rgba(255, 206, 86, 0.5)',
				                'rgba(75, 192, 192, 0.5)',
				                'rgba(153, 102, 255, 0.5)'
				            ],
				            borderColor: [
				                'rgba(54, 162, 235, 1)',
				                'rgba(255,99,132,1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)'
				            ],
				            borderWidth: 3
				        }]
				    },
				    options: {
				        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
	/*--------------------novelChart--------------------------*/
	
				var ctx = document.getElementById("novelChart").getContext('2d');
				
				var novelChart = new Chart(ctx, {
				    type: 'bar',
				    data: {
				        labels: ["총소설수", "판타지", "현대판타지", "스포츠", "대체역사", "로맨스"],
				        datasets: [{
				            label: '총소설수 / 장르별 소설수',
				            data: [ ${ requestScope.allWebNovelCount }, 
				                   	${ requestScope.fantasyCount }, 
				                   	${ requestScope.modernFatasyCount }, 
				                   	${ requestScope.sportsCount }, 
				                   	${ requestScope.fantasyHistoryCount }, 
				                   	${ requestScope.romanceCount },
				            	   ],
				            backgroundColor: [
				                'rgba(54, 162, 235, 3)',
				                'rgba(255, 99, 132, 0.5)',
				                'rgba(54, 162, 235, 0.5)',
				                'rgba(255, 206, 86, 0.5)',
				                'rgba(75, 192, 192, 0.5)',
				                'rgba(153, 102, 255, 0.5)'
				            ],
				            borderColor: [
				                'rgba(54, 162, 235, 1)',
				                'rgba(255,99,132,1)',
				                'rgba(54, 162, 235, 1)',
				                'rgba(255, 206, 86, 1)',
				                'rgba(75, 192, 192, 1)',
				                'rgba(153, 102, 255, 1)'
				            ],
				            borderWidth: 3
				        }]
				    },
				    options: {
				        maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero:true
				                }
				            }]
				        }
				    }
				});
	
	
	
			</script>
	
	
	
</body>
</html>