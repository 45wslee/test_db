<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<%--<script type="module" src="${pageContext.request.contextPath}/resources/js/admin/spt/test.js" defer></script>--%>
<c:import url="/EgovPageLink?link=admin/inc/header"/>

<!-- s:content -->
<div class="content_wrap" v-cloak>
    <p class="content_header_title">공통코드 관리 <i class="fa-solid fa-angle-right"></i> 공통코드 목록</p>
    <div class="common_code_wrap">
        <div class="left">
            <div class="top">
                <div class="form_group">
                    <input class="code_id upper" id="pMainCd" type="text" placeholder="코드 ID" maxlength="3" oninput="this.value = onlyEnglish(this.value, true);" @input="toUpperCase"/>
                    <input class="code_name" id="pCodeNm" type="text" placeholder="공통코드명" maxlength="25">
                    <button type="button" @click="duplicateCode" class="btn btn_dark">중복 확인</button>
                    <button type="button" @click="addParent" class="btn btn_red">추가</button>
                </div>
                <div class="search_group">
                    <div class="search_input_wrap">
                        <input id="searchWrd1" name="searchWrd1" class="search" type="text" placeholder="공통코드명을 입력하세요."/>
                        <i class="fa-regular fa-circle-xmark search_delete hidden" onClick="clearInput(this)"></i>
                    </div>
                    <button type="button" @click="searchCode" class="btn btn_blue">검색</button>
                </div>
            </div>
            <div class="table_wrap">
                <table class="list_table hover_table">
                    <colgroup>
                        <col width="20%"/>
                        <col width=""/>
                        <col width="15%"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>코드 ID</th>
                        <th>공통코드명</th>
                        <th>수정</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="list_none" v-if="this.codeList.length == 0">
                        <td colspan="3">데이터가 없습니다.</td>
                    </tr>
                    <tr class="cursor_pointer" v-else v-for="(item, index) in this.codeList">
                        <td>{{item.totalCd}}</td>
                        <td @click="selectCode(false, item.mainCd, index)">{{item.codeNm}}</td>
                        <td>
                            <div class="btn_group">
                                <button type="button" class="btn btn_soft_blue" @click="codePopup(item.codeNm, item.totalCd)">수정</button>
                            </div>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="right">
            <div class="top">
                <button type="button" class="btn btn_sky" @click="subCodePopup(true, this.selCd)">코드 추가</button>
            </div>
            <div class="table_wrap">
                <table class="list_table hover_table">
                    <colgroup>
                        <col width="8%"/>
                        <col width="10%"/>
                        <col width=""/>
                        <col width="10%"/>
                        <col width="21%"/>
                        <col width="10%"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th>순번</th>
                        <th>코드</th>
                        <th>하위코드명</th>
                        <th>사용여부</th>
                        <th>마지막수정일</th>
                        <th>정렬순서</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr class="list_none" v-if="this.subCodeList.length == 0">
                        <td colspan="6">데이터가 없습니다.</td>
                    </tr>
                    <tr class="cursor_pointer" v-else v-for="(item, index) in this.subCodeList">
                        <td>{{index+1}}</td>
                        <td @click="subCodePopup(false, item.totalCd)">{{item.totalCd}}</td>
                        <td @click="subCodePopup(false, item.totalCd)"> {{item.codeNm}}</td>
                        <td>
                            <p class="badge badge_rounded badge_red" :class="{ badge_red : item.useYn === '미사용',  badge_green : item.useYn === '사용' }">{{item.useYn}}</p>
                            <%--<p class="badge badge_rounded badge_green">{{item.useYn}}</p>--%>
                        </td>
                        <td>{{item.sysregdate}}</td>
                        <td>
                            {{item.sort}}
                            <%--<div class="btn_group btns_group btns_group">
                                <button @click="codeMove('up')" type="button" class="btn btn_soft_dark"><i class="fa-solid fa-arrow-up"></i></button>
                                <button @click="codeMove('down')" type="button" class="btn btn_soft_dark"><i class="fa-solid fa-arrow-down"></i></button>
                            </div>--%>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->

<!-- s:modal -->
<!-- 하위코드 수정 : start -->
<div class="modal_all_wrap">
    <form id="codeForm" name="codeForm">
        <div class="modal_wrap" id="codePopup">
            <div class="overlay"></div>
            <div class="modal_content">
                <div class="modal_header">
                    <p class="modal_title">공통코드 정보</p>
                    <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
                </div>
                <div class="modal_body">
                    <p class="text_red text_right mb10">* 필수입력</p>
                    <div class="form_group">
                        <p class="label">코드명</p>
                        <input class="reset" type="text" id="codeNm" name="codeNm" placeholder="공통코드명" value="value text" maxlength="25"/>
                    </div>
                    <div class="form_group">
                        <p class="label">코드<i class="fa-solid fa-asterisk text_red"></i></p>
                        <input class="reset" type="text" id="totalCd" name="totalCd" placeholder="코드" value="value text" maxlength="6"/>
                    </div>
                    <div class="form_group">
                        <p class="label">메인코드<i class="fa-solid fa-asterisk text_red"></i></p>
                        <input class="reset upper" type="text" id="mainCd" name="mainCd" placeholder="상위코드명" value="value text" maxlength="3" oninput="this.value = onlyEnglish(this.value, true); setTotalCd()"/>
                    </div>
                    <div class="form_group">
                        <p class="label">서브코드<i class="fa-solid fa-asterisk text_red"></i></p>
                        <input class="reset" type="text" id="subCd" name="subCd" placeholder="하위코드명" value="value text" maxlength="3" oninput="this.value = onlyNumber(this.value); setTotalCd()"/>
                    </div>
                    <div class="form_group">
                        <p class="label">정렬순서<i class="fa-solid fa-asterisk text_red"></i></p>
                        <input class="reset" type="text" id="sort" name="sort" placeholder="순번" value="value text" maxlength="3" oninput="this.value = onlyNumber(this.value)"/>
                    </div>
                    <div class="form_group">
                        <p class="label">사용여부<i class="fa-solid fa-asterisk text_red"></i></p>
                        <div class="check_radio_group">
                            <div class="radios">
                                <input class="reset" type="radio" id="item_01" name="useYn" value="Y">
                                <label for="item_01">사용</label>
                            </div>
                            <div class="radios">
                                <input class="reset" type="radio" id="item_02" name="useYn" value="N">
                                <label for="item_02">미사용</label>
                            </div>
                        </div>
                    </div>
                    <div class="form_group">
                        <p class="label">시스템 코드 유무<i class="fa-solid fa-asterisk text_red"></i></p>
                        <div class="check_radio_group">
                            <div class="radios">
                                <input class="reset" type="radio" id="system1" name="systemYn" value="Y">
                                <label for="system1">예</label>
                            </div>
                            <div class="radios">
                                <input class="reset" type="radio" id="system2" name="systemYn" value="N">
                                <label for="system2">아니오</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal_footer">
                    <button type="button" class="btn btn_red btn_close">취소</button>
                    <button type="button" onclick="upsertSubCode(false)" class="btn btn_blue">저장</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 하위코드 수정 : end -->

<!-- 상위코드 수정 : start -->
<div class="modal_all_wrap">
    <form id="pCodeForm" name="pCodeForm">
        <div class="modal_wrap" id="pCodePopup">
            <div class="overlay"></div>
            <div class="modal_content">
                <div class="modal_header">
                    <p class="modal_title">공통코드 정보</p>
                    <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
                </div>
                <div class="modal_body">
                    <p class="text_red text_right mb10">* 필수입력</p>
                    <div class="form_group">
                        <p class="label">코드명</p>
                        <input class="reset" type="text" name="codeNm" placeholder="공통코드명" value="value text" maxlength="25"/>
                    </div>
                    <div class="form_group">
                        <p class="label">코드<i class="fa-solid fa-asterisk text_red"></i></p>
                        <input class="reset" type="text" name="totalCd" placeholder="코드" value="value text" maxlength="6" readonly/>
                    </div>
                    <div class="form_group">
                        <p class="label">사용여부<i class="fa-solid fa-asterisk text_red"></i></p>
                        <div class="check_radio_group">
                            <div class="radios">
                                <input class="reset" type="radio" id="useYn1" name="useYn" value="Y">
                                <label for="useYn1">사용</label>
                            </div>
                            <div class="radios">
                                <input class="reset" type="radio" id="useYn2" name="useYn" value="N">
                                <label for="useYn2">미사용</label>
                            </div>
                        </div>
                    </div>
                    <div class="form_group">
                        <p class="label">시스템 코드 유무<i class="fa-solid fa-asterisk text_red"></i></p>
                        <div class="check_radio_group">
                            <div class="radios">
                                <input class="reset" type="radio" id="systemYn1" name="systemYn" value="Y">
                                <label for="systemYn1">예</label>
                            </div>
                            <div class="radios">
                                <input class="reset" type="radio" id="systemYn2" name="systemYn" value="N">
                                <label for="systemYn2">아니오</label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal_footer">
                    <button type="button" class="btn btn_red btn_close">취소</button>
                    <button type="button" onclick="upsertSubCode(true)" class="btn btn_blue">저장</button>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 상위코드 수정 : end -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/spt/code.js" defer></script>
<!-- e:modal -->
</body>
</html>