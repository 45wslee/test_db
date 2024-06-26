<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/admin/table/tableList.js" defer></script>
<c:import url="/EgovPageLink?link=admin/inc/header"/>

<form id="frmHst" name="frmHst" method="post">
    <input type="hidden" id="historyState" name="historyState" value="1">
    <input type="hidden" id="userSn" name="userSn">
</form>

<!-- s:content -->
<div class="content_wrap">
    <p class="content_header_title">Pages <i class="fa-solid fa-angle-right"></i> Table List</p>
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
                <div class="date_wrap">
                    <div class="date_input">
                        <input id="startDate" name="startDate" type="text" class="start_date" placeholder="시작일" readonly/>
                        <i class="fa-regular fa-calendar"></i>
                    </div>
                    <i class="fa-solid fa-minus"></i>
                    <div class="date_input">
                        <input id="endDate" name="endDate" type="text" class="end_date" placeholder="종료일" readonly/>
                        <i class="fa-regular fa-calendar"></i>
                    </div>
                </div>
                <div class="select_wrap">
                    <select id="searchCnd1" name="searchCnd1">
                        <option value="">전체</option>
                        <option value="">option 01</option>
                        <option value="">option 02</option>
                        <option value="">option 03</option>
                    </select>
                    <i class="fa-solid fa-angle-down"></i>
                </div>
                <div class="select_wrap">
                    <select id="searchCnd2" name="searchCnd2">
                        <option value="">전체</option>
                        <option value="">option 01</option>
                        <option value="">option 02</option>
                        <option value="">option 03</option>
                    </select>
                    <i class="fa-solid fa-angle-down"></i>
                </div>
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
                <col width="5%"/>
                <col width="10%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width=""/>
                <col width="8%"/>
                <col width="8%"/>
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
                <th>Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Status</th>
                <th>Joined</th>
                <th>Select</th>
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
                        <tr class="cursor_pointer" onclick="detailView('{{= ntcSn}}')">
                            <td>
                                <div class="checks table_checks">
                                    <input type="checkbox" class="itemChk" id="itemChk5"/>
                                    <label for="itemChk5"></label>
                                </div>
                            </td>
                            <td class="tbl_num fc-grey">{{= (paginationInfo.totalRecordCount - ((paginationInfo.currentPageNo - 1) * paginationInfo.recordCountPerPage + $index))}}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    {{/each}}
                {{/if}}
            </script>
            <%--<tr>
                <td>
                    <div class="checks table_checks">
                        <input type="checkbox" class="itemChk" id="itemChk0"/>
                        <label for="itemChk0"></label>
                    </div>
                </td>
                <td>06</td>
                <td>Ricky Antony</td>
                <td>ricky@example.com</td>
                <td>(201) 200-1851</td>
                <td>2392 Main Avenue, Penasauka</td>
                <td><p class="badge badge_rounded badge_dark">Dark</p></td>
                <td>2023-01-02</td>
                <td>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="checks table_checks">
                        <input type="checkbox" class="itemChk" id="itemChk1"/>
                        <label for="itemChk1"></label>
                    </div>
                </td>
                <td>05</td>
                <td>Emma Watson</td>
                <td>emma@example.com</td>
                <td>(212) 228-8403</td>
                <td>2289 5th Avenue, New York, New York, 10037</td>
                <td><p class="badge badge_rounded badge_red">Red</p></td>
                <td>2023-03-04</td>
                <td>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="checks table_checks">
                        <input type="checkbox" class="itemChk" id="itemChk2"/>
                        <label for="itemChk2"></label>
                    </div>
                </td>
                <td>04</td>
                <td>Antony Hopkins</td>
                <td>antony@example.com</td>
                <td>(901) 324-3127</td>
                <td>3448 Ile De France St #242, Fort Wainwright, Alaska, 99703</td>
                <td><p class="badge badge_rounded badge_yellow">Yellow</p></td>
                <td>2023-05-06</td>
                <td>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="checks table_checks">
                        <input type="checkbox" class="itemChk" id="itemChk3"/>
                        <label for="itemChk3"></label>
                    </div>
                </td>
                <td>03</td>
                <td>Jennifer Schramm</td>
                <td>jennifer@example.com</td>
                <td>(828) 382-9631</td>
                <td>659 Hannah Street, Charlotte, NC 28273</td>
                <td><p class="badge badge_rounded badge_green">Green</p></td>
                <td>2023-07-08</td>
                <td>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="checks table_checks">
                        <input type="checkbox" class="itemChk" id="itemChk4"/>
                        <label for="itemChk4"></label>
                    </div>
                </td>
                <td>02</td>
                <td>Marie Cohen</td>
                <td>cohen@example.com</td>
                <td>(480) 610-3481</td>
                <td>3291 Hillside Street, Mesa, AZ 85201</td>
                <td><p class="badge badge_rounded badge_sky">Sky</p></td>
                <td>2023-09-10</td>
                <td>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="checks table_checks">
                        <input type="checkbox" class="itemChk" id="itemChk5"/>
                        <label for="itemChk5"></label>
                    </div>
                </td>
                <td>01</td>
                <td>Suzanne Martinez</td>
                <td>suzanne@example.com</td>
                <td>(212) 344-9983</td>
                <td>4895 Farnum Road, New York, NY 10004</td>
                <td><p class="badge badge_rounded badge_blue">Blue</p></td>
                <td>2023-11-12</td>
                <td>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </td>
            </tr>
            <!-- 리스트가 없을 경우 tr : colspan 숫자도 신경써서 작업해주세요. -->
            <tr class="list_none">
                <td colspan="9">데이터가 없습니다.</td>
            </tr>--%>
        </table>
    </div>
    <!-- e:table -->

    <!-- s:bottom pagination / button -->
    <div class="content_bottom">
        <div class="content_bottom_left"></div>
        <div class="content_bottom_center pagination" id="pagination">
        </div>
        <div class="content_bottom_right">
            <button type="button" class="btn btn_dark">Regist</button>
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

