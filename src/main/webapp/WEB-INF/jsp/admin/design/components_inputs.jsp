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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Inputs</p>
    <div class="components_group input_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Input Type Text</p>
        <div class="item_group">
            <input type="text" placeholder="Input Type Text">
        </div>
    </div>
    <div class="components_group input_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Input Type Password</p>
        <div class="item_group">
            <input type="password" placeholder="Input Type Password">
        </div>
    </div>
    <div class="components_group input_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Input Type Number</p>
        <div class="item_group">
            <input type="number" placeholder="Input Type Number">
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
