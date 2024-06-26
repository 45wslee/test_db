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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Badges</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Basic Badge</p>
        <div class="item_group">
            <p class="badge badge_dark">badge_dark</p>
            <p class="badge badge_red">badge_red</p>
            <p class="badge badge_yellow">badge_yellow</p>
            <p class="badge badge_green">badge_green</p>
            <p class="badge badge_sky">badge_sky</p>
            <p class="badge badge_blue">badge_blue</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Rounded Badge</p>
        <div class="item_group">
            <p class="badge badge_rounded badge_dark">badge_dark</p>
            <p class="badge badge_rounded badge_red">badge_red</p>
            <p class="badge badge_rounded badge_yellow">badge_yellow</p>
            <p class="badge badge_rounded badge_green">badge_green</p>
            <p class="badge badge_rounded badge_sky">badge_sky</p>
            <p class="badge badge_rounded badge_blue">badge_blue</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Icon Badge</p>
        <div class="item_group">
            <p class="badge badge_dark">badge_dark <i class="fa-solid fa-circle-question"></i></p>
            <p class="badge badge_red">badge_red <i class="fa-solid fa-square-xmark"></i></p>
            <p class="badge badge_yellow">badge_yellow <i class="fa-solid fa-triangle-exclamation"></i></p>
            <p class="badge badge_green">badge_green <i class="fa-solid fa-square-check"></i></p>
            <p class="badge badge_sky">badge_sky <i class="fa-solid fa-circle-info"></i></p>
            <p class="badge badge_blue">badge_blue <i class="fa-solid fa-square-plus"></i></p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Solid Badge</p>
        <div class="item_group">
            <p class="badge badge_solid_dark">badge_solid_dark</p>
            <p class="badge badge_solid_red">badge_solid_red</p>
            <p class="badge badge_solid_yellow">badge_solid_yellow</p>
            <p class="badge badge_solid_green">badge_solid_green</p>
            <p class="badge badge_solid_sky">badge_solid_sky</p>
            <p class="badge badge_solid_blue">badge_solid_blue</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Solid Rounded Badge</p>
        <div class="item_group">
            <p class="badge badge_rounded badge_solid_dark">badge_solid_dark</p>
            <p class="badge badge_rounded badge_solid_red">badge_solid_red</p>
            <p class="badge badge_rounded badge_solid_yellow">badge_solid_yellow</p>
            <p class="badge badge_rounded badge_solid_green">badge_solid_green</p>
            <p class="badge badge_rounded badge_solid_sky">badge_solid_sky</p>
            <p class="badge badge_rounded badge_solid_blue">badge_solid_blue</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Solid Icon Badge</p>
        <div class="item_group">
            <p class="badge badge_solid_dark">badge_solid_dark <i class="fa-solid fa-circle-question"></i></p>
            <p class="badge badge_solid_red">badge_solid_red <i class="fa-solid fa-square-xmark"></i></p>
            <p class="badge badge_solid_yellow">badge_solid_yellow <i class="fa-solid fa-triangle-exclamation"></i></p>
            <p class="badge badge_solid_green">badge_solid_green <i class="fa-solid fa-square-check"></i></p>
            <p class="badge badge_solid_sky">badge_solid_sky <i class="fa-solid fa-circle-info"></i></p>
            <p class="badge badge_solid_blue">badge_solid_blue <i class="fa-solid fa-square-plus"></i></p>
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
