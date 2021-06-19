<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	    <div id="wrap" >
	    <form action="${ pageContext.servletContext.contextPath}/admin/novel/regist" method = "post" encType="multipart/form-data">
        <div class="adminResgitTitle">
            웹소설 등록
        </div>
        <div class="adminRegistcontext">
            <div class="imgUploadDiv" id="imgUploadArea">
                    <img id="titleImg" width="350" height="200">
            </div>
            <div class="registContext1">
                <table>
                    <tr>
                        <td>제목 : </td>
                        <td><input type="text" class="title" name="title"></td>
                    </tr>
                    <tr>
                        <td>작가명 : </td>
                        <td><input type="text" class="author" name="author"></td>
                    </tr>
                    <tr>
                        <td>출판사 : </td>
                        <td><input type="text" class="publisher" name="publisher"></td>
                    </tr>
                    <tr>
                        <td>소설 비공개 여부 : </td>
                        <td><input type="radio" id="yes" name="YN" value="Y" >
                            <label for="yes">Y</label> 
                            <input type="radio" id="no" name="YN" value="N" >
                            <label for="no">N</label>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="registContext2">
                    <select name="category" id="categoryList" name="categoryList">
                        <option value="MF">현대판타지</option>
                        <option value="F">판타지</option>
                        <option value="OF">무협</option>
                        <option value="ROM">로맨스</option>
                        <option value="SPO">스포츠</option>
                        <option value="FH">대체역사</option>
                    </select>

                    <select name="dayOfWeek" id="dayOfWeekList" name="dayOfWeekList">
                        <option value="Mon">월요일</option>
                        <option value="Thues">화요일</option>
                        <option value="Wed">수요일</option>
                        <option value="Thurs">목요일</option>
                        <option value="Fri">금요일</option>
                        <option value="Sat">토요일</option>
                        <option value="Sun">일요일</option>
                    </select>

                <table>
                    <tr>
                        <td>작품소개</td>
                        <td><textarea name="webNovelIntro" id="webNovelIntro" cols="30" rows="10" ></textarea></td>
                    </tr>
                    <tr>
                        <td>가격 : </td>
                        <td><input type="text" name="webNovlePrice" id="webNovlePrice"></td>
                    </tr>
                    <tr>
                    	<td colspan="2"><button type="reset" id="cancleNotice">취소하기</button>
					<button type="submit">등록하기</button> </td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="file-area">
					<input type="file" id="thumbnailImg1" name="thumbnailImg1" onchange="loadImg(this,1)">
		</div>
        </form>
    </div>
    
    <script> 
	const $titleImgArea = document.getElementById("imgUploadArea");
	
	$titleImgArea.onclick = function() {
		document.getElementById("thumbnailImg1").click();
	}
	/* 이미지 파일 미리보기 */
	function loadImg(value, num) {
		if(value.files && value.files[0]) {
			const reader = new FileReader();
			
			reader.onload = function(e) {
				switch(num) {
					case 1: document.getElementById("titleImg").src = e.target.result;
							break;
				}
			}
			/* readAsDataURL은 Blob이나 file에서 읽어오는 역할을 함 */
			/* Blob(Binary Large Object) : 바이너리 형태의 큰 객체(이미지, 사운드, 비디오같은 멀티미디어 객체) */
			/* 우리가 이미지를 브라우저에 뿌려주기 위해서는 base64 encoded string으로 변환해 주어야 한다. */
			reader.readAsDataURL(value.files[0]);
		}
	}
    </script>
    
    
    
    
    
</body>
</html>