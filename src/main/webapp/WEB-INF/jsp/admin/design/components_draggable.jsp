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
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Draggable</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Example</p>
        <div class="item_group item_column_group item_drag_wrap" id="draggableList">
            <p class="drag_item">Make a Registration form that include Name, Email and a Password input field</p>
            <p class="drag_item">Add a pdf file that describes all the symptoms of COVID-19</p>
            <p class="drag_item">Add a cookie notice which will be shown in the bottom of the page and have a link of "Privacy Policy"</p>
            <p class="drag_item">Update profile page layout with cover image and user setting menu</p>
        </div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
<script>
    $(function () {
        $("#draggableList").sortable();
    });
</script>
</body>
</html>
