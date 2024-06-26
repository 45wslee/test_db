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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Typography / Margin / Padding</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Color</p>
        <div class="item_group">
            <p class="text_dark">text_dark</p>
            <p class="text_light">text_light</p>
            <p class="text_red">text_red</p>
            <p class="text_yellow">text_yellow</p>
            <p class="text_green">text_green</p>
            <p class="text_sky">text_sky</p>
            <p class="text_blue">text_blue</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Text Align</p>
        <div class="item_group item_column_group">
            <p class="text_left">text_left</p>
            <p class="text_center">text_center</p>
            <p class="text_right">text_right</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Margin Class</p>
        <div class="item_group item_text_group">
            <p class="text_red">숫자는 px값입니다.</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">margin</span> : m05, m10, m15, m20, m25, m30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">margin-top</span> : mt05, mt10, mt15, mt20, mt25, mt30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">margin-bottom</span> : mb05, mb10, mb15, mb20, mb25, mb30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">margin-left</span> : ml05, ml10, ml15, ml20, ml25, ml30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">margin-right</span> : mr05, mr10, mr15, mr20, mr25, mr30</p>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Padding Class</p>
        <div class="item_group item_text_group">
            <p class="text_red">숫자는 px값입니다.</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">padding</span> : p05, p10, p15, p20, p25, p30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">padding-top</span> : pt05, pt10, pt15, pt20, pt25, pt30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">padding-bottom</span> : pb05, pb10, pb15, pb20, pb25, pb30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">padding-left</span> : pl05, pl10, pl15, pl20, pl25, pl30</p>
            <p><i class="fa-solid fa-hashtag text_sky"></i> <span class="text_sky">padding-right</span> : pr05, pr10, pr15, pr20, pr25, pr30</p>
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
