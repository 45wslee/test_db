<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/spt/authList.js" defer></script>
<c:import url="/EgovPageLink?link=admin/inc/header"/>

<form id="frmHst" name="frmHst" method="post">
    <input type="hidden" id="historyState" name="historyState" value="">
    <input type="hidden" id="userSn" name="userSn">
</form>

<!-- s:content -->
<div class="content_wrap">
    <p class="content_header_title">권한 관리 <i class="fa-solid fa-angle-right"></i> 권한 목록</p>
    <!-- s:total count / search -->
    <form id="frm" name="frm" onsubmit="return false">
        <div class="content_top">
            <div class="table_count">
                <p class="total">총 <span></span>건</p>
                <div class="select_wrap">
                    <select id="recordCountPerPage" name="recordCountPerPage">
                        <option value="10">10개</option>
                        <option value="20">20개</option>
                        <option value="30">30개</option>
                        <option value="40">40개</option>
                        <option value="50">50개</option>
                        <option value="100">100개</option>
                    </select>
                    <i class="fa-solid fa-angle-down"></i>
                </div>
                <p>개씩 보기</p>
            </div>
            <div class="table_search">
                <div class="table_search_input">
                    <div class="search_input_wrap">
                        <input id="searchWrd1" name="searchWrd1" class="search" type="text" placeholder="검색어를 입력하세요."/>
                        <i class="fa-regular fa-circle-xmark search_delete hidden" onClick="clearInput(this)"></i>
                    </div>
                    <button type="button" class="btn btn_blue">검색<i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
        </div>
    </form>
    <!-- e:total count / search -->

    <!-- s:table -->
    <div class="content_middle">
        <table class="list_table">
            <colgroup>
                <col width="3%"/>
                <col width="6%"/>
                <col width="8%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width=""/>
                <col width="8%"/>
            </colgroup>
            <thead>
            <tr>
                <th>
                    <div class="checks table_checks">
                        <input type="checkbox" name="allSelect" id="allSelect"/>
                        <label for="allSelect"></label>
                    </div>
                </th>
                <th>No</th>
                <th>권한코드</th>
                <th>권한명</th>
                <th>권한설명</th>
                <th>접근메뉴</th>
                <th>사용여부</th>
            </tr>
            </thead>
            <tbody id="designList"></tbody>
            <script id="designListTmpl" type="text/x-jquery-tmpl">
                {{if resultList.length == 0}}
                    <tr class="list_none">
                        <td colspan="9">데이터가 없습니다.</td>
                    </tr>
                {{else}}
                    {{each resultList}}
                        <tr class="cursor_pointer" onclick="detailView('{{= roleCd}}')">
                            <td></td>
                            <td class="tbl_num fc-grey">{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                            <td>{{= roleCd}}</td>
                            <td>{{= roleNm}}</td>
                            <td>{{= roleDesc}}</td>
                            <td>{{= menuList}}</td>
                            <td>{{= useYn}}</td>
                        </tr>
                    {{/each}}
                {{/if}}
            </script>
        </table>
    </div>
    <!-- e:table -->

    <!-- s:bottom pagination / button -->
    <div class="content_bottom">
        <div class="content_bottom_left"></div>
        <div class="content_bottom_center pagination" id="pagination">
        </div>
        <div class="content_bottom_right">
            <button type="button" class="btn btn_dark" onclick="detailView('')">Regist</button>
        </div>
    </div>
    <!-- e:bottom pagination / button -->
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->
</body>
</html>

