<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<script type="text/javascript">

(function(){
	
	const failedCode = "${requestScope.failedCode}";
	
	var failedMessage = "";
	
	var movePath = "";
	
	switch(failedCode){
	
	case "noCoin" : 
		failedMessage = "적립된 코인이 없습니다.";
		movePath = "${ pageContext.servletContext.contextPath}/webnovel/detail";
		break;
		
	}
	
	})();
</script>

</body>
</html>