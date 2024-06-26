<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<c:import url="/EgovPageLink?link=admin/inc/header"/>

<div class="content_wrap">
    <p class="content_header_title">권한 관리 <i class="fa-solid fa-angle-right"></i> 권한 등록/수정</p>
    <form id="frm" name="frm" onsubmit="return false">
        <input type="hidden" name="roleCd" id="roleCd" value="${roleCd}">
        <div class="table_area">
            <table class="form_table">
                <colgroup>
                    <col width="8.88%">
                    <col width="41%">
                    <col width="8.88%">
                    <col>
                </colgroup>
                <tbody>
                <tr>
                    <th>권한명</th>
                    <td><input type="text" class="W300" name="roleNm" v-model="authrtInfo.roleNm"/></td>
                    <th>사용여부</th>
                    <td>
                        <div class="check_radio_group">
                            <div class="radios">
                                <input type="radio" id="use" name="useYn" value="Y" v-model="authrtInfo.useYn">
                                <label for="use">사용</label>
                            </div>
                            <div class="radios">
                                <input type="radio" id="use2" name="useYn" value="N" v-model="authrtInfo.useYn">
                                <label for="use2">미사용</label>
                            </div>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <!-- s:table -->
        <div class="content_middle">
            <table class="list_table">
                <colgroup>
                    <col/>
                    <col/>
                    <col/>
                </colgroup>
                <thead>
                <tr>
                    <th>대메뉴</th>
                    <th>중메뉴</th>
                    <th>소메뉴</th>
                </tr>
                </thead>
                <tbody id="authList">
                <tr class="cursor_pointer" v-if="authInfo.depthList.length > 0" v-for="(item, index) in authInfo.depthList">
                    <td class="first">
                        <template>
                            <div class="checks table_checks">
                                <input type="checkbox" @change="menuChk1(item)" class="itemChk" :id="'menu1lv'+index" name="menuCd" :value="item.t1menu" v-model="chkArray"/>
                                <label v-bind:for="'menu1lv'+index"></label>{{item.menuNameLv1}}
                            </div>
                        </template>
                    </td>

                    <td class="second">
                        <template v-if="item.menuNameLv2 != null">
                            <div class="checks table_checks">
                                <input type="checkbox" @change="menuChk2(item, true)" class="itemChk" :id="'menu2lv'+index" name="menuCd" :value="item.t2menu" v-model="chkArray"/>
                                <label v-bind:for="'menu2lv'+index"></label>{{item.menuNameLv2}}
                            </div>
                        </template>
                    </td>

                    <td class="menu3Td">
                        <template v-if="item.menuNameLv3 != null">
                            <div class="checks table_checks">
                                <input type="checkbox" @change="menuChk3(item, true)" class="itemChk" :id="'menu3lv'+index" name="menuCd" :value="item.t3menu" v-model="chkArray"/>
                                <label v-bind:for="'menu3lv'+index"></label>{{item.menuNameLv3}}
                            </div>
                        </template>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
    <!-- e:table -->
    <!-- s:bottom pagination / button -->
    <div class="content_bottom">
        <div class="content_bottom_left"></div>
        <div class="content_bottom_center pagination" id="pagination">
        </div>
        <div class="content_bottom_right">
            <button type="button" @click="savePopup" class="btn btn_dark">upsert</button>
        </div>
    </div>
    <!-- e:bottom pagination / button -->
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/spt/authDetail.js" defer></script>
<!-- e:content -->
</body>
</html>
