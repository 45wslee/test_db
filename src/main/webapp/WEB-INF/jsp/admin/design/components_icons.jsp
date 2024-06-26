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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Icons</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>How to use</p>
        <div class="item_group item_use_group">
            <p><i class="fa-solid fa-1"></i>fontawesome.min.css 를 선언해줍니다.</p>
            <p><i class="fa-solid fa-2"></i>i 태그에 class를 선언합니다. &#40;html 코드 또는 검사툴로 class 확인해보시면 됩니다.&#41;</p>
            <p><i class="fa-solid fa-3"></i> 밑에 나열되어 아이콘 말고 <a href="https://fontawesome.com/search?o=r&m=free" class="text_sky">https://fontawesome.com/search?o=r&m=free</a>에 다양한 아이콘이 있으니 참조하세요.</p>
            <p><i class="fa-solid fa-4"></i>3번 주소에 있는 아이콘들은 무료 이용 가능합니다.</p>
            <p class="text_red">※ 주의 할 점 : fonts > fontawesome 경로에서 폰트 파일을 css에서 불러오기 때문에 css안에 경로 지정 필수입니다. 지금은 되어있지만 폴더명을 바꾸거나 폴더 안에 폴더가 들어간다면 바꿔야 합니다.</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Arrows</p>
        <div class="item_group icon_group">
            <i class="fa-solid fa-angle-left"></i>
            <i class="fa-solid fa-angle-right"></i>
            <i class="fa-solid fa-angle-up"></i>
            <i class="fa-solid fa-angle-down"></i>
            <i class="fa-solid fa-angles-left"></i>
            <i class="fa-solid fa-angles-right"></i>
            <i class="fa-solid fa-angles-up"></i>
            <i class="fa-solid fa-angles-down"></i>
            <i class="fa-solid fa-arrow-left"></i>
            <i class="fa-solid fa-arrow-right"></i>
            <i class="fa-solid fa-arrow-up"></i>
            <i class="fa-solid fa-arrow-down"></i>
            <i class="fa-solid fa-arrow-left-long"></i>
            <i class="fa-solid fa-arrow-right-long"></i>
            <i class="fa-solid fa-arrow-up-long"></i>
            <i class="fa-solid fa-arrow-down-long"></i>
            <i class="fa-solid fa-left-long"></i>
            <i class="fa-solid fa-right-long"></i>
            <i class="fa-solid fa-up-long"></i>
            <i class="fa-solid fa-down-long"></i>
            <i class="fa-solid fa-circle-left"></i>
            <i class="fa-solid fa-circle-right"></i>
            <i class="fa-solid fa-circle-up"></i>
            <i class="fa-solid fa-circle-down"></i>
            <i class="fa-solid fa-circle-chevron-left"></i>
            <i class="fa-solid fa-circle-chevron-right"></i>
            <i class="fa-solid fa-circle-chevron-up"></i>
            <i class="fa-solid fa-circle-chevron-down"></i>
            <i class="fa-solid fa-circle-arrow-left"></i>
            <i class="fa-solid fa-circle-arrow-right"></i>
            <i class="fa-solid fa-circle-arrow-up"></i>
            <i class="fa-solid fa-circle-arrow-down"></i>
            <i class="fa-regular fa-circle-left"></i>
            <i class="fa-regular fa-circle-right"></i>
            <i class="fa-regular fa-circle-up"></i>
            <i class="fa-regular fa-circle-down"></i>
            <i class="fa-solid fa-square-caret-left"></i>
            <i class="fa-solid fa-square-caret-right"></i>
            <i class="fa-solid fa-square-caret-up"></i>
            <i class="fa-solid fa-square-caret-down"></i>
            <i class="fa-regular fa-square-caret-left"></i>
            <i class="fa-regular fa-square-caret-right"></i>
            <i class="fa-regular fa-square-caret-up"></i>
            <i class="fa-regular fa-square-caret-down"></i>
            <i class="fa-solid fa-caret-left"></i>
            <i class="fa-solid fa-caret-right"></i>
            <i class="fa-solid fa-caret-up"></i>
            <i class="fa-solid fa-caret-down"></i>
            <i class="fa-solid fa-upload"></i>
            <i class="fa-solid fa-download"></i>
            <i class="fa-solid fa-arrow-rotate-left"></i>
            <i class="fa-solid fa-arrow-rotate-right"></i>
            <i class="fa-solid fa-cloud-arrow-up"></i>
            <i class="fa-solid fa-cloud-arrow-down"></i>
            <i class="fa-solid fa-share-from-square"></i>
            <i class="fa-regular fa-share-from-square"></i>
            <i class="fa-solid fa-sort"></i>
            <i class="fa-solid fa-arrows-left-right"></i>
            <i class="fa-solid fa-arrows-up-down"></i>
            <i class="fa-solid fa-arrow-trend-up"></i>
            <i class="fa-solid fa-arrow-trend-down"></i>
            <i class="fa-solid fa-arrow-right-to-bracket"></i>
            <i class="fa-solid fa-arrow-right-from-bracket"></i>
            <i class="fa-solid fa-right-to-bracket"></i>
            <i class="fa-solid fa-right-from-bracket"></i>
            <i class="fa-solid fa-rotate"></i>
            <i class="fa-solid fa-shuffle"></i>
            <i class="fa-solid fa-up-right-from-square"></i>
            <i class="fa-solid fa-square-arrow-up-right"></i>
            <i class="fa-solid fa-square-up-right"></i>
            <i class="fa-solid fa-arrow-up-9-1"></i>
            <i class="fa-solid fa-arrow-up-1-9"></i>
            <i class="fa-solid fa-arrow-down-9-1"></i>
            <i class="fa-solid fa-arrow-down-1-9"></i>
            <i class="fa-solid fa-arrow-up-a-z"></i>
            <i class="fa-solid fa-arrow-up-z-a"></i>
            <i class="fa-solid fa-arrow-down-a-z"></i>
            <i class="fa-solid fa-arrow-down-z-a"></i>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Calendar</p>
        <div class="item_group icon_group">
            <i class="fa-solid fa-calendar"></i>
            <i class="fa-regular fa-calendar"></i>
            <i class="fa-solid fa-calendar-days"></i>
            <i class="fa-regular fa-calendar-days"></i>
            <i class="fa-solid fa-calendar-week"></i>
            <i class="fa-solid fa-calendar-xmark"></i>
            <i class="fa-solid fa-calendar-plus"></i>
            <i class="fa-solid fa-calendar-minus"></i>
            <i class="fa-solid fa-calendar-day"></i>
            <i class="fa-solid fa-calendar-check"></i>
            <i class="fa-solid fa-calendar-xmark"></i>
            <i class="fa-regular fa-calendar-plus"></i>
            <i class="fa-regular fa-calendar-minus"></i>
            <i class="fa-regular fa-calendar-check"></i>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Writing</p>
        <div class="item_group icon_group">
            <i class="fa-solid fa-envelope"></i>
            <i class="fa-regular fa-envelope"></i>
            <i class="fa-solid fa-paperclip"></i>
            <i class="fa-solid fa-file"></i>
            <i class="fa-regular fa-file"></i>
            <i class="fa-solid fa-pen"></i>
            <i class="fa-solid fa-book"></i>
            <i class="fa-solid fa-bookmark"></i>
            <i class="fa-regular fa-bookmark"></i>
            <i class="fa-solid fa-pen-to-square"></i>
            <i class="fa-regular fa-pen-to-square"></i>
            <i class="fa-solid fa-folder"></i>
            <i class="fa-regular fa-folder"></i>
            <i class="fa-solid fa-folder-open"></i>
            <i class="fa-regular fa-folder-open"></i>
            <i class="fa-solid fa-thumbtack"></i>
            <i class="fa-solid fa-paper-plane"></i>
            <i class="fa-regular fa-paper-plane"></i>
            <i class="fa-solid fa-quote-left"></i>
            <i class="fa-solid fa-quote-right"></i>
            <i class="fa-solid fa-pencil"></i>
            <i class="fa-solid fa-newspaper"></i>
            <i class="fa-regular fa-newspaper"></i>
            <i class="fa-solid fa-keyboard"></i>
            <i class="fa-regular fa-keyboard"></i>
            <i class="fa-solid fa-eraser"></i>
            <i class="fa-solid fa-square-pen"></i>
            <i class="fa-solid fa-pen-clip"></i>
            <i class="fa-solid fa-note-sticky"></i>
            <i class="fa-regular fa-note-sticky"></i>
            <i class="fa-solid fa-file-lines"></i>
            <i class="fa-regular fa-file-lines"></i>
            <i class="fa-solid fa-box-archive"></i>
            <i class="fa-solid fa-book-bookmark"></i>
            <i class="fa-solid fa-envelope-open"></i>
            <i class="fa-regular fa-envelope-open"></i>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>User</p>
        <div class="item_group icon_group">
            <i class="fa-solid fa-user"></i>
            <i class="fa-regular fa-user"></i>
            <i class="fa-solid fa-circle-user"></i>
            <i class="fa-regular fa-circle-user"></i>
            <i class="fa-solid fa-face-smile"></i>
            <i class="fa-regular fa-face-smile"></i>
            <i class="fa-solid fa-person"></i>
            <i class="fa-solid fa-person-dress"></i>
            <i class="fa-solid fa-child"></i>
            <i class="fa-solid fa-child-dress"></i>
            <i class="fa-solid fa-restroom"></i>
            <i class="fa-solid fa-address-book"></i>
            <i class="fa-regular fa-address-book"></i>
            <i class="fa-solid fa-address-card"></i>
            <i class="fa-regular fa-address-card"></i>
            <i class="fa-solid fa-clipboard-user"></i>
            <i class="fa-solid fa-id-badge"></i>
            <i class="fa-regular fa-id-badge"></i>
            <i class="fa-solid fa-id-card"></i>
            <i class="fa-regular fa-id-card"></i>
            <i class="fa-solid fa-user-plus"></i>
            <i class="fa-solid fa-user-minus"></i>
            <i class="fa-solid fa-user-xmark"></i>
            <i class="fa-solid fa-user-check"></i>
            <i class="fa-solid fa-user-shield"></i>
            <i class="fa-solid fa-user-pen"></i>
            <i class="fa-solid fa-user-clock"></i>
            <i class="fa-solid fa-user-lock"></i>
            <i class="fa-solid fa-user-gear"></i>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Brands</p>
        <div class="item_group icon_group">
            <i class="fa-brands fa-facebook"></i>
            <i class="fa-brands fa-facebook-messenger"></i>
            <i class="fa-brands fa-facebook-f"></i>
            <i class="fa-brands fa-square-facebook"></i>
            <i class="fa-brands fa-twitter"></i>
            <i class="fa-brands fa-square-twitter"></i>
            <i class="fa-brands fa-instagram"></i>
            <i class="fa-brands fa-square-instagram"></i>
            <i class="fa-brands fa-tiktok"></i>
            <i class="fa-brands fa-linkedin"></i>
            <i class="fa-brands fa-git"></i>
            <i class="fa-brands fa-square-git"></i>
            <i class="fa-brands fa-github"></i>
            <i class="fa-brands fa-gitlab"></i>
            <i class="fa-brands fa-square-github"></i>
            <i class="fa-brands fa-square-gitlab"></i>
            <i class="fa-solid fa-code-branch"></i>
            <i class="fa-brands fa-discord"></i>
            <i class="fa-brands fa-youtube"></i>
            <i class="fa-brands fa-figma"></i>
            <i class="fa-brands fa-apple"></i>
            <i class="fa-brands fa-google"></i>
            <i class="fa-brands fa-windows"></i>
            <i class="fa-brands fa-android"></i>
            <i class="fa-brands fa-line"></i>
            <i class="fa-brands fa-html5"></i>
            <i class="fa-brands fa-telegram"></i>
            <i class="fa-brands fa-cc-visa"></i>
            <i class="fa-brands fa-google-play"></i>
            <i class="fa-brands fa-microsoft"></i>
            <i class="fa-brands fa-twitch"></i>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Etc</p>
        <div class="item_group icon_group">
            <i class="fa-solid fa-house"></i>
            <i class="fa-solid fa-magnifying-glass"></i>
            <i class="fa-solid fa-check"></i>
            <i class="fa-solid fa-square-check"></i>
            <i class="fa-regular fa-square-check"></i>
            <i class="fa-solid fa-circle-check"></i>
            <i class="fa-regular fa-circle-check"></i>
            <i class="fa-solid fa-image"></i>
            <i class="fa-regular fa-image"></i>
            <i class="fa-solid fa-phone"></i>
            <i class="fa-solid fa-bars"></i>
            <i class="fa-solid fa-location-dot"></i>
            <i class="fa-solid fa-circle-xmark"></i>
            <i class="fa-regular fa-circle-xmark"></i>
            <i class="fa-solid fa-xmark"></i>
            <i class="fa-solid fa-square-xmark"></i>
            <i class="fa-solid fa-camera-retro"></i>
            <i class="fa-solid fa-camera"></i>
            <i class="fa-solid fa-video"></i>
            <i class="fa-solid fa-shield-halved"></i>
            <i class="fa-solid fa-bell"></i>
            <i class="fa-regular fa-bell"></i>
            <i class="fa-solid fa-circle-info"></i>
            <i class="fa-solid fa-info"></i>
            <i class="fa-solid fa-circle-exclamation"></i>
            <i class="fa-solid fa-triangle-exclamation"></i>
            <i class="fa-solid fa-exclamation"></i>
            <i class="fa-solid fa-question"></i>
            <i class="fa-solid fa-gear"></i>
            <i class="fa-solid fa-gears"></i>
            <i class="fa-solid fa-trash"></i>
            <i class="fa-solid fa-trash-can"></i>
            <i class="fa-solid fa-trash-arrow-up"></i>
            <i class="fa-solid fa-lock"></i>
            <i class="fa-solid fa-unlock"></i>
            <i class="fa-solid fa-plus"></i>
            <i class="fa-solid fa-square-plus"></i>
            <i class="fa-regular fa-square-plus"></i>
            <i class="fa-solid fa-minus"></i>
            <i class="fa-solid fa-square-minus"></i>
            <i class="fa-regular fa-square-minus"></i>
            <i class="fa-solid fa-code"></i>
            <i class="fa-solid fa-ticket"></i>
            <i class="fa-solid fa-hashtag"></i>
            <i class="fa-solid fa-earth-americas"></i>
            <i class="fa-solid fa-layer-group"></i>
            <i class="fa-solid fa-link"></i>
            <i class="fa-solid fa-shop"></i>
            <i class="fa-solid fa-store"></i>
            <i class="fa-solid fa-backward"></i>
            <i class="fa-solid fa-play"></i>
            <i class="fa-solid fa-pause"></i>
            <i class="fa-solid fa-stop"></i>
            <i class="fa-solid fa-forward"></i>
            <i class="fa-solid fa-server"></i>
            <i class="fa-solid fa-power-off"></i>
            <i class="fa-solid fa-sitemap"></i>
            <i class="fa-solid fa-language"></i>
            <i class="fa-solid fa-microchip"></i>
            <i class="fa-solid fa-volume-xmark"></i>
            <i class="fa-solid fa-volume-off"></i>
            <i class="fa-solid fa-volume-low"></i>
            <i class="fa-solid fa-volume-high"></i>
            <i class="fa-solid fa-list-ul"></i>
            <i class="fa-solid fa-list-ol"></i>
            <i class="fa-solid fa-list-check"></i>
        </div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
</div>
</body>
</html>
