<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="/firstbook/resources/css/common/reset.css">
    <link rel="stylesheet" href="/firstbook/resources/css/common/main.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="./firstbook/resources/js/book/lib/jquery.min.js"></script>
</head>

<body>
    <jsp:include page="../common/header.jsp" />

    <div id="wrap">
    <div class="top_main">
        <div class="size_box"></div>

        <section>
            <div class="section">
                <div class="main">
                    <div class="banner">
                      <div class="slide-view">
                            <ul class="slide-wrap clearfix">
                                <li class="slide slide3"></li>
                                <li class="slide slide1"></li>
                                <li class="slide slide2"></li>
                                <li class="slide slide3"></li>
                                <li class="slide slide1"></li>
                            </ul>
                        </div>
                        <div class="banner_btns_next">
                            <a href="javascript:" class="next-btn"></a>
                        </div>
                        <div class="banner_btns_prev">
                            <a href="javascript:" class="prev-btn"></a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <section2>
        <div clss="awards"><br><br>
                <h2>수상작</h2>
            <c:forEach items="${ requestScope.contestList }" var="contest">
                <div class="awards_box">
                    <img src="<c:out value=" ${ contest.competNovImgLocation }" />" width = "200px"><br>
                    <c:out value="${ contest.novTitle }" />
                </div>
            </c:forEach>
        </div>
		</section2>
        <br clear="both">
        <div class="serialization">
            <h2>연재중</h2>
            
            <br clear="both">
            <div class="tab-content">
                 <c:forEach items="${ requestScope.webNovelList }" var="webNovelList">
                <div class="awards_box">
                    <img src="<c:out value=" ${ webNovelList.webNovImgLocation }" />"  height="286" width = "200px"><br>
                    <c:out value="${ webNovelList.webNovTitle }" />
                </div>
            </c:forEach> 
               
            </div>
        </div>
        </div>
    
        <div >
            <img src="/firstbook/resources/image/main/mid_bn.jpg">
        </div>
         <div class="top_main">
        <div class="book">
            <h2>단행본</h2>
            <c:forEach items="${ requestScope.NovelList }" var="novelList">
                <div class="awards_box">
                    <img src="<c:out value=" ${ novelList.imgLocation }" />" width = "200px"><br>
                    <c:out value="${ novelList.novTitle }" />
                </div>
            </c:forEach>
        </div>
</div>
    </div>

    <jsp:include page="../common/footer.jsp" />
    <script src="/firstbook/resources/js/book/slide.js"></script>
</body>

</html>