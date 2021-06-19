<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>메인페이지</title>

    <!-- reset.css style.css -->
    <link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
    <link rel="stylesheet" href="/firstbook/resources/css/book/book_sale.css">
</head>

<body>
       <jsp:include page="../common/header.jsp"></jsp:include>
    <div id="wrap">
        <div class="size_box"></div>
        <div class="main_box">
            <div class="introduction">
                <div class="book_img">
                    <img src="/firstbook/resources/image/book/book_cover.png">
                </div>
                <div class="book_name">
                    <div class="book_introduction">

                        <div class="free_shipping">무료배송</div>
                        <div class="book_title">악당은 살고 싶다</div>
                        <div class="book_Information">Hajime Isayama 지음 | First Book | 2021년 03월 08일 출간</div>
                    </div>

                    <div class="book_price">

                        <div class="price">정가 : 5,000원</div>
                        <div class="sale_price">판매가 : </div>
                        <div class="font_red">4,500원</div>

                    </div>
                    <div class="book_announcement">
                        <div class="shipping">
                            <span>배송비 : 무료배송 적용 상품 배송비 안내</span><br>
                            <span>배송일정 : 택사로인한 늦게 배송될 수도 있습니다</span><br>
                            <span>지금 주문하면 내일(20일,토) 도착 예정 배송일정 안내</span><br>
                            <span>바로드림 : 인터넷으로 주문하고 매장에서 직접 수령</span>
                        </div>
                        <button type="submit">구매하기</button>
                        <button type="submit">찜하기</button>
                    </div>
                </div>
                <div class="event_banaer">
                    <div class="banner_img">이벤트 배너 부분</div>
                </div>
            </div>

        </div>

        <div class="book_content">
            <div class="book_content_bold">책소개</div>
            <hr>
            내 어린시절 우연히<br>
            들었던 믿지 못 할 한마디<br>
            이 세상을 다 준다는 매혹적인 얘기<br>
            내게 꿈을 심어 주었어<br>
            말 도 안돼 고갤 저어도<br>
            내안의 나 나를 보고 속삭여<br>
            세상은 꿈 꾸는 자의 것이라고<br>
            용기를 내 넌 할 수 있어<br>
            쉼 없이 흘러가는 시간<br>
            이대로 보낼 수는 없잖아<br>
            함께 도전하는거야<br>
            너와 나 두 손을 잡고<br>
            우리들 모두의 꿈을 모아서<br>
            외로움과 두려움이<br>
            우릴 힘들게 하여도<br>
            결코 피하지 않아<br>
            끝없이 펼쳐진 드넓은 바다에<br>
            희망이 우릴 부르니까<br>
            거센 바람 높은 파도가<br>
            우리 앞길 막아서도<br>
            결코 두렵지 않아<br>
            끝없이 펼쳐진 수많은 시련들<br>
            밝은 내일 위한 거야<br>
            말 도 안돼 고갤 저어도<br>
            내안의 나 나를 보고 속삭여<br>
            세상은 꿈 꾸는 자의 것이라고<br>
            용기를 내 넌 할 수 있어<br>

        </div>
        <div class="size_box"></div>
    </div>
        <jsp:include page="../common/footer.jsp"/>

</body>

</html>