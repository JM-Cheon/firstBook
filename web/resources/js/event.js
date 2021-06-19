 
 window.onload = function() {
	/* 무료보기 버튼 눌렀을 때 */
	if(document.getElementById("freebtn_real_2")) {
			/* $를 안해줘도 되지만 명시적으로 해줌으로써 객체임을 알림 */
			/* const라는 final같이 변하지 않는 자료형에 담아줌 */
		const $freebtn_real_2 = document.getElementById("freebtn_real_2");
		/* 익명함수 (이벤트가 발생하면) */
		$freebtn_real_2.onclick = function(){
			/* get방식으로 요청 */
			location.href = "/firstbook/webnovel/chapList";
		}
	}

}