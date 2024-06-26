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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Buttons</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Basic Button</p>
        <div class="item_group">
            <button type="button" class="btn btn_dark">btn_dark</button>
            <button type="button" class="btn btn_light">btn_light</button>
            <button type="button" class="btn btn_red">btn_red</button>
            <button type="button" class="btn btn_yellow">btn_yellow</button>
            <button type="button" class="btn btn_green">btn_green</button>
            <button type="button" class="btn btn_sky">btn_sky</button>
            <button type="button" class="btn btn_blue">btn_blue</button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Icon Button</p>
        <div class="item_group">
            <button type="button" class="btn btn_dark">badge_dark <i class="fa-solid fa-circle-question"></i></button>
            <button type="button" class="btn btn_light">btn_light <i class="fa-solid fa-circle-question"></i></button>
            <button type="button" class="btn btn_red">badge_red <i class="fa-solid fa-square-xmark"></i></button>
            <button type="button" class="btn btn_yellow">badge_yellow <i class="fa-solid fa-triangle-exclamation"></i></button>
            <button type="button" class="btn btn_green">badge_green <i class="fa-solid fa-square-check"></i></button>
            <button type="button" class="btn btn_sky">badge_sky <i class="fa-solid fa-circle-info"></i></button>
            <button type="button" class="btn btn_blue">badge_blue <i class="fa-solid fa-square-plus"></i></button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Outline Button</p>
        <div class="item_group">
            <button type="button" class="btn btn_outline_dark">btn_outline_dark</button>
            <button type="button" class="btn btn_outline_light">btn_outline_light</button>
            <button type="button" class="btn btn_outline_red">btn_outline_red</button>
            <button type="button" class="btn btn_outline_yellow">btn_outline_yellow</button>
            <button type="button" class="btn btn_outline_green">btn_outline_green</button>
            <button type="button" class="btn btn_outline_sky">btn_outline_sky</button>
            <button type="button" class="btn btn_outline_blue">btn_outline_blue</button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Outline Icon Button</p>
        <div class="item_group">
            <button type="button" class="btn btn_outline_dark">btn_outline_dark <i class="fa-solid fa-circle-question"></i></button>
            <button type="button" class="btn btn_outline_light">btn_outline_light <i class="fa-solid fa-circle-question"></i></button>
            <button type="button" class="btn btn_outline_red">btn_outline_red <i class="fa-solid fa-square-xmark"></i></button>
            <button type="button" class="btn btn_outline_yellow">btn_outline_yellow <i class="fa-solid fa-triangle-exclamation"></i></button>
            <button type="button" class="btn btn_outline_green">btn_outline_green <i class="fa-solid fa-square-check"></i></button>
            <button type="button" class="btn btn_outline_sky">btn_outline_sky <i class="fa-solid fa-circle-info"></i></button>
            <button type="button" class="btn btn_outline_blue">btn_outline_blue <i class="fa-solid fa-square-plus"></i></button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Soft Button</p>
        <div class="item_group">
            <button type="button" class="btn btn_soft_dark">btn_soft_dark</button>
            <button type="button" class="btn btn_soft_red">btn_soft_red</button>
            <button type="button" class="btn btn_soft_yellow">btn_soft_yellow</button>
            <button type="button" class="btn btn_soft_green">btn_soft_green</button>
            <button type="button" class="btn btn_soft_sky">btn_soft_sky</button>
            <button type="button" class="btn btn_soft_blue">btn_soft_blue</button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Soft Icon Button</p>
        <div class="item_group">
            <button type="button" class="btn btn_soft_dark">btn_soft_dark <i class="fa-solid fa-circle-question"></i></button>
            <button type="button" class="btn btn_soft_red">btn_soft_red <i class="fa-solid fa-square-xmark"></i></button>
            <button type="button" class="btn btn_soft_yellow">btn_soft_yellow <i class="fa-solid fa-triangle-exclamation"></i></button>
            <button type="button" class="btn btn_soft_green">btn_soft_green <i class="fa-solid fa-square-check"></i></button>
            <button type="button" class="btn btn_soft_sky">btn_soft_sky <i class="fa-solid fa-circle-info"></i></button>
            <button type="button" class="btn btn_soft_blue">btn_soft_blue <i class="fa-solid fa-square-plus"></i></button>
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
