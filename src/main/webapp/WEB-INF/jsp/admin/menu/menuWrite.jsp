<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/menu/menuWrite.js" defer></script>

<c:import url="/EgovPageLink?link=admin/inc/header"/>

<!-- s:content -->
<div class="content_wrap">
    <form id="frm" name="frm">
        <input type="hidden" id="menuCd" name="menuCd" value="">
        <input type="hidden" id="depthLength" name="depthLength" value="">
        <p class="content_header_title">Pages <i class="fa-solid fa-angle-right"></i> Table Form</p>
        <p class="text_red text_right mb10">* 필수입력</p>
        <table class="form_table">
            <colgroup>
                <col width="15%"/>
                <col width="35%"/>
                <col width="15%"/>
                <col width="35%"/>
            </colgroup>
            <tbody>
            <tr>
                <th>메뉴 분류 코드 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3">
                    <div class="select_wrap">
                        <select id="menuCls" name="menuCls" onchange="changeMenu(this)">
                            <option value="0">대분류</option>
                            <option value="1">중분류</option>
                            <option value="2">소분류</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <th>부모 메뉴 코드 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3">
                    <div class="select_wrap">
                        <select id="parntMenuCd" name="parntMenuCd">
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <th>정렬 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3"><input type="number" maxlength="2" name="sort" placeholder="내용을 입력하세요."/></td>
            </tr>
            <tr>
                <th>메뉴 이름 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3"><input type="text" maxlength="10" name="menuNm" placeholder="내용을 입력하세요."/></td>
            </tr>
            <tr>
                <th>메뉴 주소 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3"><input type="text" maxlength="50" name="menuUrl" placeholder="내용을 입력하세요."/></td>
            </tr>
            <tr>
                <th>시스템 코드 여부</th>
                <td>
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="systemYn_01" name="systemYn" value="Y"/>
                            <label for="systemYn_01">시스템</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="systemYn_02" name="systemYn" value="N"/>
                            <label for="systemYn_02">전체</label>
                        </div>
                    </div>
                </td>
                <th>외부 사이트 여부</th>
                <td>
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="externalYn_01" name="externalYn" value="Y"/>
                            <label for="externalYn_01">외부</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="externalYn_02" name="externalYn" value="N"/>
                            <label for="externalYn_02">내부</label>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <th>사용 여부 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3">
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="item_01" name="useYn" value="Y" checked/>
                            <label for="item_01">YES</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="item_02" name="useYn" value="N"/>
                            <label for="item_02">NO</label>
                        </div>
                    </div>
                </td>
            </tr>
            <tr id="iconTr">
                <th>아이콘 <i class="fa-solid fa-asterisk text_red"></i></th>
                <td colspan="3">
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="menuIconCls_01" name="menuIconCls" value="fa-solid fa-house"/>
                            <label for="menuIconCls_01"><i class="fa-solid fa-house"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_02" name="menuIconCls" value="fa-solid fa-lock"/>
                            <label for="menuIconCls_02"><i class="fa-solid fa-lock"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_03" name="menuIconCls" value="fa-solid fa-user"/>
                            <label for="menuIconCls_03"><i class="fa-solid fa-user"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_04" name="menuIconCls" value="fa-solid fa-layer-group"/>
                            <label for="menuIconCls_04"><i class="fa-solid fa-layer-group"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_05" name="menuIconCls" value="fa-solid fa-calendar-days"/>
                            <label for="menuIconCls_05"><i class="fa-solid fa-calendar-days"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_06" name="menuIconCls" value="fa-solid fa-database"/>
                            <label for="menuIconCls_06"><i class="fa-solid fa-database"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_07" name="menuIconCls" value="fa-solid fa-paste"/>
                            <label for="menuIconCls_07"><i class="fa-solid fa-paste"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_08" name="menuIconCls" value="fa-solid fa-message"/>
                            <label for="menuIconCls_08"><i class="fa-solid fa-message"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_09" name="menuIconCls" value="fa-solid fa-display"/>
                            <label for="menuIconCls_09"><i class="fa-solid fa-display"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_10" name="menuIconCls" value="fa-solid fa-pen-to-square"/>
                            <label for="menuIconCls_10"><i class="fa-solid fa-pen-to-square"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_11" name="menuIconCls" value="fa-solid fa-circle-question"/>
                            <label for="menuIconCls_11"><i class="fa-solid fa-circle-question"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_12" name="menuIconCls" value="fa-solid fa-user-group"/>
                            <label for="menuIconCls_12"><i class="fa-solid fa-user-group"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_13" name="menuIconCls" value="fa-solid fa-gear"/>
                            <label for="menuIconCls_13"><i class="fa-solid fa-gear"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_14" name="menuIconCls" value="fa-solid fa-image"/>
                            <label for="menuIconCls_14"><i class="fa-solid fa-image"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_15" name="menuIconCls" value="fa-solid fa-circle-user"/>
                            <label for="menuIconCls_15"><i class="fa-solid fa-circle-user"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_16" name="menuIconCls" value="fa-solid fa-address-book"/>
                            <label for="menuIconCls_16"><i class="fa-solid fa-address-book"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_17" name="menuIconCls" value="fa-solid fa-clock"/>
                            <label for="menuIconCls_17"><i class="fa-solid fa-clock"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_18" name="menuIconCls" value="fa-solid fa-store"/>
                            <label for="menuIconCls_18"><i class="fa-solid fa-store"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_19" name="menuIconCls" value="fa-solid fa-sitemap"/>
                            <label for="menuIconCls_19"><i class="fa-solid fa-sitemap"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_20" name="menuIconCls" value="fa-solid fa-cube"/>
                            <label for="menuIconCls_20"><i class="fa-solid fa-cube"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_21" name="menuIconCls" value="fa-solid fa-cubes"/>
                            <label for="menuIconCls_21"><i class="fa-solid fa-cubes"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_22" name="menuIconCls" value="fa-solid fa-chart-line"/>
                            <label for="menuIconCls_22"><i class="fa-solid fa-chart-line"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_23" name="menuIconCls" value="fa-solid fa-city"/>
                            <label for="menuIconCls_23"><i class="fa-solid fa-city"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_24" name="menuIconCls" value="fa-solid fa-microchip"/>
                            <label for="menuIconCls_24"><i class="fa-solid fa-microchip"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_25" name="menuIconCls" value="fa-solid fa-microphone"/>
                            <label for="menuIconCls_25"><i class="fa-solid fa-microphone"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_26" name="menuIconCls" value="fa-solid fa-user-group"/>
                            <label for="menuIconCls_26"><i class="fa-solid fa-user-group"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_27" name="menuIconCls" value="fa-solid fa-power-off"/>
                            <label for="menuIconCls_27"><i class="fa-solid fa-power-off"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_28" name="menuIconCls" value="fa-solid fa-headset"/>
                            <label for="menuIconCls_28"><i class="fa-solid fa-headset"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_29" name="menuIconCls" value="fa-solid fa-map-location-dot"/>
                            <label for="menuIconCls_29"><i class="fa-solid fa-map-location-dot"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="menuIconCls_30" name="menuIconCls" value="fa-solid fa-shield-halved"/>
                            <label for="menuIconCls_30"><i class="fa-solid fa-shield-halved"></i></label>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="content_bottom">
            <div class="content_bottom_left"></div>
            <div class="content_bottom_center">
                <button type="button" class="btn btn_dark" onclick="goMenuList()">목록으로</button>
                <button onclick="menuAlert()" type="button" class="btn btn_blue">등록하기</button>
            </div>
            <div class="content_bottom_right"></div>
        </div>
        <!-- s:footer -->
        <c:import url="/EgovPageLink?link=admin/inc/footer"/>
        <!-- e:footer -->
    </form>
</div>
<!-- e:content -->
</body>
</html>