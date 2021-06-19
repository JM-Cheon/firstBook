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
    <link rel="stylesheet" href="/firstbook/resources/css/book/book.css">

    <script src="./firstbook/resources/js/book/lib/jquery.min.js"></script>
</head>

<body>
    <div id="wrap">

       <jsp:include page="../common/header.jsp"/>
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
            <div class="section_joint">
                <div class="best_book">베스트셀러 TOP5</div>
                <div class="top_book"><a href="${ pageContext.servletContext.contextPath}/book/bookView"><img src="/firstbook/resources/image/book/book_cover.png" alt="more" width="185px"></a>
                    <div>그녀는 다<br>계획이 있다</div>
                </div>
                <div class="top2_book"><img src="/firstbook/resources/image/book/book_cover.png" alt="more" width="150px">
                    <div>그녀는 다<br>계획이 있다</div>
                </div>
                <div class="top2_book"><img src="/firstbook/resources/image/book/book_cover.png" alt="more" width="150px">
                    <div>그녀는 다<br>계획이 있다</div>
                </div>
                <div class="top2_book"><img src="/firstbook/resources/image/book/book_cover.png" alt="more" width="150px">
                    <div>그녀는 다<br>계획이 있다</div>
                </div>
                <div class="top2_book"><img src="/firstbook/resources/image/book/book_cover.png" alt="more" width="150px">
                    <div>그녀는 다<br>계획이 있다</div>
                </div>

            </div>
        </section2>
        <section3>
            <div class="section3">
            </div>
        </section3>
        <section4>
            <div class="section_joint">
                <div class="this_year">올해 급상승한 단행본</div>
                <div class="this_year_img"><a href="#"><img src="/firstbook/resources/image/book/plus.PNG" alt="more"></a></div>
                <div class="this_year_Three">
                    <div class="this_year_box1">
                        <div>급상승 단행본</div>
                        <div><img src="/firstbook/resources/image/book/this_year_box1_img.JPG" alt="more"></div>
                    </div>
                    <div class="this_year_box2">
                        <div><img src="/firstbook/resources/image/book/this_year_box2_img.JPG" alt="more"></div>
                        <div>라플라스의 마녀</div>
                    </div>
                    <div class="this_year_box3">
                        <div><img src="/firstbook/resources/image/book/this_year_box2_img.JPG" alt="more"></div>
                        <div>라플라스의 마녀</div>
                    </div>
                    <div class="this_year_box4">
                        <div><img src="/firstbook/resources/image/book/this_year_box2_img.JPG" alt="more"></div>
                        <div>라플라스의 마녀</div>
                    </div>

                </div>
            </div>
        </section4>

    </div>

    <div class="size_box"></div>
    <script src="/firstbook/resources/js/book/slideBook.js"></script>
</body>
</html>