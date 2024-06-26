<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/menu/menuView.js" defer></script>
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
            <th>메뉴 분류 코드</th>
            <td colspan="3"><c:out value="${result.menuCls}"/></td>
        </tr>
        <tr>
            <th>부모 메뉴 코드</th>
            <td colspan="3"><c:out value="${result.parntMenuCd}"/></td>
        </tr>
        <tr>
            <th>정렬</th>
            <td colspan="3"><c:out value="${result.sort}"/></td>
        </tr>
        <tr>
            <th>메뉴 이름</th>
            <td colspan="3"><c:out value="${result.menuNm}"/></td>
        </tr>
        <tr>
            <th>메뉴 주소</th>
            <td colspan="3"><c:out value="${result.menuUrl}"/></td>
        </tr>
        <tr>
            <th>시스템 코드 여부</th>
             <td>
                 <div class="check_radio_group">
                     <div class="radios">
                         <input type="radio" id="systemYn_01" name="systemYn" value="Y" disabled/>
                         <label for="systemYn_01">시스템</label>
                     </div>
                     <div class="radios">
                         <input type="radio" id="systemYn_02" name="systemYn" value="N" disabled/>
                         <label for="systemYn_02">전체</label>
                     </div>
                 </div>
             </td>
             <th>외부 사이트 여부</th>
             <td>
                 <div class="check_radio_group">
                     <div class="radios">
                         <input type="radio" id="externalYn_01" name="externalYn" value="Y" disabled/>
                         <label for="externalYn_01">외부</label>
                     </div>
                     <div class="radios">
                         <input type="radio" id="externalYn_02" name="externalYn" value="N" disabled/>
                         <label for="externalYn_02">내부</label>
                     </div>
                 </div>
             </td>
        </tr>
        <tr>
            <th>사용 여부</th>
            <td colspan="3">
                <div class="check_radio_group">
                    <div class="radios">
                        <input type="radio" id="item_01" name="useYn" value="Y" disabled/>
                        <label for="item_01">YES</label>
                    </div>
                    <div class="radios">
                        <input type="radio" id="item_02" name="useYn" value="N" disabled/>
                        <label for="item_02">No</label>
                    </div>
                </div>
            </td>
        </tr>
        <tr id="iconTr">
            <th>아이콘</th>
            <td colspan="3">
                <i class=""></i>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="content_bottom">
        <div class="content_bottom_left"></div>
        <div class="content_bottom_center">
            <button type="button" class="btn btn_dark" onclick="goMenuList()">목록으로</button>
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
