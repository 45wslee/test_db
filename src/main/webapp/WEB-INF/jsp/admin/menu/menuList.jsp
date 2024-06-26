<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/menu/menuList.js" defer></script>

<c:import url="/EgovPageLink?link=admin/inc/header"/>

<!-- s:content -->
<div class="content_wrap">
    <form id="frm" name="frm">
        <input type="hidden" id="menuCd" name="menuCd" value="">
        <input type="hidden" id="depthLength" name="depthLength" value="">
        <p class="content_header_title">메뉴 관리 <i class="fa-solid fa-angle-right"></i> 메뉴 목록</p>
        <div class="menu_group">
            <div class="menu_list_wrap">
                <p class="title mb10">전체 메뉴 목록</p>
                <!-- 메뉴가 없을 떄 -->
                <!-- <p class="menu_list_none"><i class="fa-solid fa-triangle-exclamation"></i><br>등록된 메뉴가 없습니다.<br>신규등록을 눌러 메뉴를 추가해주세요.</p> -->

                <!-- 메뉴가 있을 떄 -->
                <div class="menu_list">
                    <ul class="menu_list_01" id="menuListUl">
                        <li>
                            <!-- TODO: 1. 메뉴가 선택되어 있는 경우 on 클래스가 들어가야 합니다. 2. 하위 메뉴가 없으면 i가 없어야 합니다. -->
                            <p class="menu_list_item on">게시판<i class="fa-solid fa-angle-down"></i></p>
                            <ul class="menu_list_02">
                                <li>
                                    <p class="menu_list_item">공지사항<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">공지사항 메뉴/리스트</p>
                                            <p class="menu_list_item">공지사항 등록/수정</p>
                                            <p class="menu_list_item">공지사항 삭제</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">FAQ</p>
                                </li>
                                <li>
                                    <p class="menu_list_item">자료실</p>
                                </li>
                                <li>
                                    <p class="menu_list_item">설문조사</p>
                                </li>
                            </ul>
                        </li>
                        <!-- <li>
                            <p class="menu_list_item">장비정보<i class="fa-solid fa-angle-down"></i></p>
                            <ul class="menu_list_02">
                                <li>
                                    <p class="menu_list_item">장비정보관리<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">장비정보관리 메뉴/리스트</p>
                                            <p class="menu_list_item">장비정보관리 등록</p>
                                            <p class="menu_list_item">장비정보관리 수정</p>
                                            <p class="menu_list_item">장비정보관리 엑셀다운로드</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">장비유지 보수관리<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">장비유지 보수관리 메뉴/리스트</p>
                                            <p class="menu_list_item">장비유지 보수관리 등록</p>
                                            <p class="menu_list_item">장비유지 보수관리 수정</p>
                                            <p class="menu_list_item">장비유지 보수관리 엑센다운로드</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">장비운영 일지관리<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">장비유지 일지관리 메뉴/리스트</p>
                                            <p class="menu_list_item">장비유지 일지관리 등록</p>
                                            <p class="menu_list_item">장비유지 일지관리 수정</p>
                                            <p class="menu_list_item">장비유지 일지관리 엑센다운로드</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">시험/공정 항목관리<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">시험/공정 항목관리 메뉴/리스트</p>
                                            <p class="menu_list_item">시험/공정 항목관리 등록</p>
                                            <p class="menu_list_item">시험/공정 항목관리 수정</p>
                                            <p class="menu_list_item">시험/공정 항목관리 엑센다운로드</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">장비활용 문의관리<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">장비활용 문의관리 메뉴/리스트</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">장비표준 분류관리<i class="fa-solid fa-angle-down"></i></p>
                                    <ul class="menu_list_03">
                                        <li>
                                            <p class="menu_list_item">장비표준 분류관리 메뉴/리스트</p>
                                        </li>
                                    </ul>
                                </li>
                                <li>
                                    <p class="menu_list_item">불용장비정보관리</p>
                                </li>
                                <li>
                                    <p class="menu_list_item">장비코디네이팅관리</p>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <p class="menu_list_item">예약관리</p>
                        </li>
                        <li>
                            <p class="menu_list_item">통계</p>
                        </li> -->
                    </ul>
                </div>
            </div>
            <div class="menu_form_wrap">
                <p class="title mb10">메뉴 정보 <span class="text_red">* 필수입력</span></p>
                <!-- 메뉴가 선택되어 있지 않을 떄 -->
                <p class="menu_list_none" id="menuFormTableNo"><i class="fa-solid fa-triangle-exclamation"></i><br>메뉴 목록에서 메뉴를 선택해주세요.</p>

                <!-- 메뉴가 선택되어 있을 떄 -->
                <table class="form_table" id="menuFormTable" style="display:none;">
                    <colgroup>
                        <col width="20%"/>
                        <col width="30%"/>
                        <col width="20%"/>
                        <col width="30%"/>
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
                                <select id="parntMenuCd" name="parntMenuCd"></select>
                                <i class="fa-solid fa-angle-down"></i>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th>정렬 <i class="fa-solid fa-asterisk text_red"></i></th>
                        <td colspan="3"><input type="text" maxlength="2" name="sort" id="sort" oninput="this.value= onlyNumber(this.value);" placeholder="내용을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <th>메뉴 이름 <i class="fa-solid fa-asterisk text_red"></i></th>
                        <td colspan="3"><input type="text" maxlength="10" name="menuNm" id="menuNm" placeholder="내용을 입력하세요."/></td>
                    </tr>
                    <tr>
                        <th>메뉴 주소 <i class="fa-solid fa-asterisk text_red"></i></th>
                        <td colspan="3"><input type="text" maxlength="50" name="menuUrl" id="menuUrl" placeholder="내용을 입력하세요."/></td>
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
            </div>
        </div>
        <div class="content_bottom">
            <div class="content_bottom_left"></div>
            <div class="content_bottom_center">
                <button type="button" class="btn btn_dark" onclick="openMenuPopup()">신규등록</button> <!-- 누르면 팝업뜨기 -->
                <button type="button" class="btn btn_red" onclick="deleteMenuAlert()">삭제하기</button>
                <button type="button" class="btn btn_blue" onclick="menuAlert()">저장하기</button>
            </div>
            <div class="content_bottom_right"></div>
        </div>
        <!-- s:footer -->
        <c:import url="/EgovPageLink?link=admin/inc/footer"/>
        <!-- e:footer -->
    </form>
</div>
<!-- e:content -->

<!-- s:modal -->
<div class="modal_all_wrap">

    <div class="modal_wrap" id="menuListPopup">
    <form id="popupFrm" name="popupFrm">
    <input type="hidden" id="popupMenuCd" name="menuCd" value="">
    <input type="hidden" id="popupDepthLength" name="depthLength" value="">
        <div class="overlay"></div>
        <div class="modal_content">
            <div class="modal_header">
                <p class="modal_title">메뉴 신규 등록</p>
                <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal_body">
                <p class="text_red text_right mb10">* 필수입력</p>
                <div class="form_group">
                    <p class="label">메뉴 분류 코드<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="select_wrap">
                        <select id="popupMenuCls" name="menuCls" onchange="popupChangeMenu(this)">
                            <option value="0">대분류</option>
                            <option value="1">중분류</option>
                            <option value="2">소분류</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </div>
                <div class="form_group">
                    <p class="label">부모 메뉴 코드<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="select_wrap">
                        <select id="popupParntMenuCd" name="parntMenuCd"></select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </div>
                <div class="form_group">
                    <p class="label">정렬<i class="fa-solid fa-asterisk text_red"></i></p>
                    <input type="text" maxlength="2" name="sort" placeholder="내용을 입력하세요." oninput="this.value= onlyNumber(this.value);" value="" />
                </div>
                <div class="form_group">
                    <p class="label">메뉴 이름<i class="fa-solid fa-asterisk text_red"></i></p>
                    <input type="text" maxlength="10" name="menuNm" placeholder="내용을 입력하세요." value=""/>
                </div>
                <div class="form_group">
                    <p class="label">메뉴 주소<i class="fa-solid fa-asterisk text_red"></i></p>
                    <input type="text" maxlength="50" name="menuUrl" placeholder="내용을 입력하세요." value=""/>
                </div>
                <div class="form_group">
                    <p class="label">시스템 코드 여부</p>
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="popupSystemYn_01" name="systemYn" value="Y"/>
                            <label for="popupSystemYn_01">시스템</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupSystemYn_02" name="systemYn" value="N"/>
                            <label for="popupSystemYn_02">전체</label>
                        </div>
                    </div>
                </div>
                <div class="form_group">
                    <p class="label">외부 사이트 여부</p>
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="popupExternalYn_01" name="externalYn" value="Y"/>
                            <label for="popupExternalYn_01">외부</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupExternalYn_02" name="externalYn" value="N"/>
                            <label for="popupExternalYn_02">내부</label>
                        </div>
                    </div>
                </div>
                <div class="form_group">
                    <p class="label">사용 여부<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="popupItem_01" name="useYn" value="Y" checked/>
                            <label for="popupItem_01">YES</label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupItem_02" name="useYn" value="N"/>
                            <label for="popupItem_02">NO</label>
                        </div>
                    </div>
                </div>
                <div class="form_group" id="popupIconTr">
                    <p class="label">아이콘<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="check_radio_group">
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_01" name="menuIconCls" value="fa-solid fa-house"/>
                            <label for="popupMenuIconCls_01"><i class="fa-solid fa-house"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_02" name="menuIconCls" value="fa-solid fa-lock"/>
                            <label for="popupMenuIconCls_02"><i class="fa-solid fa-lock"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_03" name="menuIconCls" value="fa-solid fa-user"/>
                            <label for="popupMenuIconCls_03"><i class="fa-solid fa-user"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_04" name="menuIconCls" value="fa-solid fa-layer-group"/>
                            <label for="popupMenuIconCls_04"><i class="fa-solid fa-layer-group"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_05" name="menuIconCls" value="fa-solid fa-calendar-days"/>
                            <label for="popupMenuIconCls_05"><i class="fa-solid fa-calendar-days"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_06" name="menuIconCls" value="fa-solid fa-database"/>
                            <label for="popupMenuIconCls_06"><i class="fa-solid fa-database"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_07" name="menuIconCls" value="fa-solid fa-paste"/>
                            <label for="popupMenuIconCls_07"><i class="fa-solid fa-paste"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_08" name="menuIconCls" value="fa-solid fa-message"/>
                            <label for="popupMenuIconCls_08"><i class="fa-solid fa-message"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_09" name="menuIconCls" value="fa-solid fa-display"/>
                            <label for="popupMenuIconCls_09"><i class="fa-solid fa-display"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_10" name="menuIconCls" value="fa-solid fa-pen-to-square"/>
                            <label for="popupMenuIconCls_10"><i class="fa-solid fa-pen-to-square"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_11" name="menuIconCls" value="fa-solid fa-circle-question"/>
                            <label for="popupMenuIconCls_11"><i class="fa-solid fa-circle-question"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_12" name="menuIconCls" value="fa-solid fa-user-group"/>
                            <label for="popupMenuIconCls_12"><i class="fa-solid fa-user-group"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_13" name="menuIconCls" value="fa-solid fa-gear"/>
                            <label for="popupMenuIconCls_13"><i class="fa-solid fa-gear"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_14" name="menuIconCls" value="fa-solid fa-image"/>
                            <label for="popupMenuIconCls_14"><i class="fa-solid fa-image"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_15" name="menuIconCls" value="fa-solid fa-circle-user"/>
                            <label for="popupMenuIconCls_15"><i class="fa-solid fa-circle-user"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_16" name="menuIconCls" value="fa-solid fa-address-book"/>
                            <label for="popupMenuIconCls_16"><i class="fa-solid fa-address-book"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_17" name="menuIconCls" value="fa-solid fa-clock"/>
                            <label for="popupMenuIconCls_17"><i class="fa-solid fa-clock"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_18" name="menuIconCls" value="fa-solid fa-store"/>
                            <label for="popupMenuIconCls_18"><i class="fa-solid fa-store"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_19" name="menuIconCls" value="fa-solid fa-sitemap"/>
                            <label for="popupMenuIconCls_19"><i class="fa-solid fa-sitemap"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_20" name="menuIconCls" value="fa-solid fa-cube"/>
                            <label for="popupMenuIconCls_20"><i class="fa-solid fa-cube"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_21" name="menuIconCls" value="fa-solid fa-cubes"/>
                            <label for="popupMenuIconCls_21"><i class="fa-solid fa-cubes"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_22" name="menuIconCls" value="fa-solid fa-chart-line"/>
                            <label for="popupMenuIconCls_22"><i class="fa-solid fa-chart-line"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_23" name="menuIconCls" value="fa-solid fa-city"/>
                            <label for="popupMenuIconCls_23"><i class="fa-solid fa-city"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_24" name="menuIconCls" value="fa-solid fa-microchip"/>
                            <label for="popupMenuIconCls_24"><i class="fa-solid fa-microchip"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_25" name="menuIconCls" value="fa-solid fa-microphone"/>
                            <label for="popupMenuIconCls_25"><i class="fa-solid fa-microphone"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_26" name="menuIconCls" value="fa-solid fa-user-group"/>
                            <label for="popupMenuIconCls_26"><i class="fa-solid fa-user-group"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_27" name="menuIconCls" value="fa-solid fa-power-off"/>
                            <label for="popupMenuIconCls_27"><i class="fa-solid fa-power-off"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_28" name="menuIconCls" value="fa-solid fa-headset"/>
                            <label for="popupMenuIconCls_28"><i class="fa-solid fa-headset"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_29" name="menuIconCls" value="fa-solid fa-map-location-dot"/>
                            <label for="popupMenuIconCls_29"><i class="fa-solid fa-map-location-dot"></i></label>
                        </div>
                        <div class="radios">
                            <input type="radio" id="popupMenuIconCls_30" name="menuIconCls" value="fa-solid fa-shield-halved"/>
                            <label for="popupMenuIconCls_30"><i class="fa-solid fa-shield-halved"></i></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal_footer">
                <button type="button" class="btn btn_red " onclick="menuPopupCancel()">취소</button>
                <button type="button" class="btn btn_blue" onclick="menuAlertPoup()">저장</button>
            </div>
        </div>
        </form>
    </div>

</div>
<!-- e:modal -->
</body>
</html>