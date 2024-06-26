<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<c:import url="/EgovPageLink?link=admin/inc/header"/>
<!-- s:content -->
<div class="content_wrap">
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Swiper</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Basic Swiper (Navigation, Pagination, Loop)</p>
        <div class="item_group">
            <div class="swiper swiper_example">
                <div class="swiper-wrapper">
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/12/15/13/51/polynesia-3021072_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/11/29/13/49/christmas-baubles-1869989_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/12/04/21/22/snowman-1882635_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/30/09/30/hot-chocolate-1782623_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2020/12/15/20/50/christmas-5834904_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/21/14/46/fox-1758183_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/10/10/07/48/hills-2836301_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/21/14/50/plouzane-1758197_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2013/02/21/19/06/drink-84533_1280.jpg" alt="img example"/></div>
                </div>
                <div class="fa-solid fa-arrow-right swiper-button-next"></div>
                <div class="fa-solid fa-arrow-left swiper-button-prev"></div>
                <div class="swiper-pagination"></div>
            </div>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Thumbs Swiper (Navigation, Loop)</p>
        <div class="item_group item_column_group item_swiper">
            <div class="swiper swiper_thumbs_example">
                <div class="swiper-wrapper">
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/12/15/13/51/polynesia-3021072_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/11/29/13/49/christmas-baubles-1869989_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/12/04/21/22/snowman-1882635_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/30/09/30/hot-chocolate-1782623_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2020/12/15/20/50/christmas-5834904_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/21/14/46/fox-1758183_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/10/10/07/48/hills-2836301_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/21/14/50/plouzane-1758197_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2013/02/21/19/06/drink-84533_1280.jpg" alt="img example"/></div>
                </div>
                <div class="fa-solid fa-arrow-right swiper-button-next"></div>
                <div class="fa-solid fa-arrow-left swiper-button-prev"></div>
            </div>
            <div class="swiper swiper_thumbs_pagination_example">
                <div class="swiper-wrapper">
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/12/15/13/51/polynesia-3021072_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/11/29/13/49/christmas-baubles-1869989_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/12/04/21/22/snowman-1882635_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/30/09/30/hot-chocolate-1782623_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2020/12/15/20/50/christmas-5834904_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/21/14/46/fox-1758183_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2017/10/10/07/48/hills-2836301_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2016/10/21/14/50/plouzane-1758197_1280.jpg" alt="img example"/></div>
                    <div class="swiper-slide"><img src="https://cdn.pixabay.com/photo/2013/02/21/19/06/drink-84533_1280.jpg" alt="img example"/></div>
                </div>
            </div>
        </div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
</div>
<script>
    // 이미지 슬라이드
    var swiper = new Swiper(".swiper_example", {
        autoHeight: true,
        loop      : true,
        effect    : "fade",
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        pagination: {
            el       : ".swiper-pagination",
            clickable: true,
        },
    });

    var swiperThumbs = new Swiper(".swiper_thumbs_pagination_example", {
        loop               : true,
        spaceBetween       : 10,
        slidesPerView      : 5,
        freeMode           : true,
        watchSlidesProgress: true,
    });

    var swiperThumbs2 = new Swiper(".swiper_thumbs_example", {
        autoHeight: true,
        loop      : true,
        effect    : "fade",
        navigation: {
            nextEl: ".swiper-button-next",
            prevEl: ".swiper-button-prev",
        },
        thumbs    : {
            swiper: swiperThumbs,
        },
    });
</script>
</body>
</html>
