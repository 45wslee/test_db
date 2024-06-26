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
    <p class="content_header_title">Pages <i class="fa-solid fa-angle-right"></i> Table View</p>
    <table class="form_table">
        <colgroup>
            <col width="15%" />
            <col width="35%" />
            <col width="15%" />
            <col width="" />
        </colgroup>
        <tbody>
        <tr>
            <th>Name</th>
            <td>Rootlab</td>
            <th>Phone Number</th>
            <td>010-1234-5678</td>
        </tr>
        <tr>
            <th>Email</th>
            <td>rootlab@rootlab.xyz</td>
            <th>Date</th>
            <td>2023-11-29 13:00:00</td>
        </tr>
        <tr>
            <th>Address</th>
            <td colspan="3">2392 Main Avenue, Penasauka</td>
        </tr>
        <tr>
            <th>status</th>
            <td colspan="3"><p class="badge badge_rounded badge_blue">Blue</p></td>
        </tr>
        <tr>
            <th>첨부 파일</th>
            <td colspan="3">
                <div class="file_view_wrap">
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_01.jpg <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_02.png <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_03.gif <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_04.jpg <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_05.png <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_06.gif <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_07.jpg <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_08.png <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_09.gif <i class="fa-solid fa-download"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_10.jpg <i class="fa-solid fa-download"></i></p>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="content_bottom">
        <div class="content_bottom_left"></div>
        <div class="content_bottom_center">
            <button type="button" class="btn btn_dark">목록으로</button>
            <button type="button" class="btn btn_yellow">수정하기</button>
            <button type="button" class="btn btn_red">삭제하기</button>
        </div>
        <div class="content_bottom_right"></div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
</body>
</html>