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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Tooltip And Popover</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Basic Tooltip</p>
        <div class="item_group">
            <div class="tooltip_wrap">
                <p class="tooltip_title btn btn_dark">Basic Tooltip</p>
                <div class="tooltip">
                    <p class="tooltip_text">Basic Tooltip</p>
                </div>
            </div>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Direction Tooltip</p>
        <div class="item_group">
            <div class="tooltip_wrap">
                <p class="tooltip_title btn btn_sky">Tooltip On Top</p>
                <div class="tooltip">
                    <p class="tooltip_text">Tooltip On Top</p>
                </div>
            </div>
            <div class="tooltip_wrap">
                <p class="tooltip_title btn btn_sky">Tooltip On Bottom</p>
                <div class="tooltip bottom">
                    <p class="tooltip_text">Tooltip On Bottom</p>
                </div>
            </div>
            <div class="tooltip_wrap">
                <p class="tooltip_title btn btn_sky">Tooltip On Left</p>
                <div class="tooltip left">
                    <p class="tooltip_text">Tooltip On Left</p>
                </div>
            </div>
            <div class="tooltip_wrap">
                <p class="tooltip_title btn btn_sky">Tooltip On Right</p>
                <div class="tooltip right">
                    <p class="tooltip_text">Tooltip On Right</p>
                </div>
            </div>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Basic Popover (마우스 우클릭 시 이벤트 발생 : 우클릭 위치에 따라 popover 위치 바뀜)</p>
        <div class="item_group">
            <p class="popover_title btn btn_dark" id="popover">Basic Popover</p>
        </div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
</div>

<div class="popover hide" id="popoverContent">
    <p class="popover_text">
        <span>Basic Popover</span>
        <i class="fa-solid fa-xmark popover_close"></i>
    </p>
    <div class="popover_content">
        <p><i class="fa-solid fa-circle"></i>List Item 01</p>
        <p><i class="fa-solid fa-circle"></i>List Item 02</p>
        <p><i class="fa-solid fa-circle"></i>List Item 03</p>
    </div>
</div>
<script>
    var popover = document.getElementById("popover");
    var popMenu = document.getElementById("popoverContent");

    // popover 마우스 오른쪽 버튼 클릭 했을 때 메뉴바 나오는 거 막기
    popover.addEventListener("contextmenu", function (e) {
        e.preventDefault();

        popMenu.style.display = "block";

        popMenu.style.top = event.pageY + "px";
        popMenu.style.left = event.pageX + "px";
    });

    // popover 창 안에서 x 버튼 클릭 시 닫히는 이벤트 발생
    $(".popover_close").on("click", function () {
        popMenu.style.display = "none";
    });

    // 바깥 영역 클릭했을 때 popover 닫히는 이벤트 발생
    document.addEventListener('mouseup', function (e) {
        if (!$(e.target).closest(popover).length && !$(e.target).closest(popMenu).length) {
            popMenu.style.display = "none";
        }
    });
</script>
</body>
</html>
