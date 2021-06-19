<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	
	(function(){
		
		const failedCode = "${requestScope.failedCode}";
		
		var failedMessage = "";
		
		var movePath = "";
		
		switch(failedCode){
		
		case "insertMember" : 
			failedMessage = "회원 가입에 실패하셨습니다!";
			movePath = "${ pageContext.servletContext.contextPath}/member/regist";
			break;
			
		case "loginMember" : 
			failedMessage = "아이디 혹은 비밀번호가 다릅니다.";
			movePath = "${ pageContext.servletContext.contextPath}/member/login";
			break;


		case "noLogin" : 
			failedMessage = "세션이 만료되었습니다.";
			movePath = "${ pageContext.servletContext.contextPath}/member/login";
			break;
			
		case "perChapContext" : 
			failedMessage = "웹소설 챕터별 내용을 검색하지 못하였습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;

		case "selectAllWebNovel" : 
			failedMessage = "조회된 웹소설이 없습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;

		case "updateMember" : 
			failedMessage = "회원 정보 수정에 실패하셨습니다.";
			movePath = "${ pageContext.servletContext.contextPath}/member/mypage";
			break;
			
		case "deleteMember" : 
			failedMessage = "회원 탈퇴에 실패하셨습니다.";
			movePath = "${ pageContext.servletContext.contextPath}/member/mypage";
			break;
			
		case "webnovelPerChap" : 
			failedMessage = "조회된 웹소설의 챕터가 없습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;
			
		case "adminSelectAllWebNovel" : 
			failedMessage = "관리자 페이지에서 조회된 웹소설이 없습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;
			
		case "adminInsertNewWebNovel" : 
			failedMessage = "관리자 페이지에서  웹소설 등록을 실패하였습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;
			
		case "adminSelectWebNovelDetail" : 
			failedMessage = "관리자 페이지에서 웹소설 수정전 해당 웹소설 조회를 실패하였습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;
			
		case "noticeSelectList" : 
			failedMessage = "공지사항 리스트 불러오기 실패!";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;

		case "selectNoticeDetail" : 
			failedMessage = "공지사항 디테일 불러오기 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/notice";
			break;

		case "faqSelectList" : 
			failedMessage = "자주묻는질문 리스트 불러오기 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/notice";
			break;

		case "selectFaqDetail" : 
			failedMessage = "공지사항 디테일 불러오기 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/notice";
			break;
			
		case "qnaSelectList" : 
			failedMessage = "자주묻는질문 리스트 불러오기 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/notice";
			break;

		case "selectQnaDetail" : 
			failedMessage = "1:1문의 디테일 불러오기 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/qna";
			break;

		case "insertQNA" : 
			failedMessage = "1:1문의 작성 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/qna";
			break;

		case "insertCoin" : 
			failedMessage = "결제에 실패하셨습니다.";
			movePath = "${ pageContext.servletContext.contextPath}";
			break;

		case "searchId" : 
			failedMessage = "해당 정보에 대한 회원이 존재하지 않습니다.";
			movePath = "${ pageContext.servletContext.contextPath}/member/searchId";
			break;

		case "searchPwd" : 
			failedMessage = "해당 정보에 대한 회원이 존재하지 않습니다.";
			movePath = "${ pageContext.servletContext.contextPath}/member/searchPwd";
			break;

		case "updatePassword" : 
			failedMessage = "비밀번호 변경 실패!";
			movePath = "${ pageContext.servletContext.contextPath}/member/searchPwd";

		case "viewFailed" : 
			failedMessage = "공보전 조회 실패";
			movePath = "${ pageContext.servletContext.contextPath}/contest/list";
			break;
		case "main" : 
			failedMessage = "메인 조회 실패";
			movePath = "${ pageContext.servletContext.contextPath}/main/main";
			break;
		case "viewFailed" : 
			failedMessage = "공모전 조회 실패";
			movePath = "${ pageContext.servletContext.contextPath}/contestAll/list";
			break; "contestFailed", "viewFailed"
		}
			alert(failedMessage);
		
			location.replace(movePath);
			
	})();

</script>
	
	
</body>
</html>