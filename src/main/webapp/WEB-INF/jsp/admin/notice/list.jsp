<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>

    <!-- css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admglobal.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery-3.6.0.min.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery.form.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery.tmpl.min.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/utils/common.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/utils/pagination.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/notice/list.js" defer></script>
</head>
<body>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
<!-- s:container -->
<div class="container">
    <div class="gnb_area">
        <ul class="gnb">
            <li>
                <a href="${pageContext.request.contextPath}/notice">공지사항</a>
            </li>
        </ul>
    </div>

    <div class="contents_area">
        <div class="contents">

            <div class="pageTitle">
                <h1>공지사항</h1>
            </div>

            <div class="table_wrap">
                <form id="frm" name="frm">
                    <input type="hidden" name="page" id="page">
                    <div class="table_top">
                        <div class="table_count">
                            <p>총<b>180</b>건</p>
                            <select name="size" onchange="searchNotice(1)">
                                <option value="1">1개</option>
                                <option value="10">10개</option>
                                <option value="20">20개</option>
                            </select>
                        </div>
                        <div class="table_search">
                            <select class="W130" name="searchCnd">
                                <option value="total">전체</option>
                                <option value="title">제목</option>
                                <option value="content">내용</option>
                                <option value="author">작성자</option>
                            </select>
                            <div class="tableSearch_area">
                                <input type="text" placeholder="검색어를 입력하세요." class="W200" name="searchWrd" >
                                <button type="button" onclick="searchNotice(1)"><img src="${pageContext.request.contextPath}/images/adm/ico_search.png" alt="검색"></button>
                            </div>
                            <button type="button" class="btn01 btn_grey_line">초기화</button>
                        </div>
                    </div>
                </form>

                <div class="table_area">
                    <table class="listTable1">
                        <colgroup>
                            <col width="5%">
                            <col width="">
                            <col width="">
                            <col width="10%">
                            <col width="10%">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목-JPA</th>
                            <th>제목-MyBatis</th>
                            <th>작성자</th>
                            <th>작성일자</th>
                        </tr>
                        </thead>

                        <tbody id="noticeListTbody"></tbody>
                        <script id="noticeTmpl" type="text/x-jquery-tmpl">
                            {{each content}}
                                <tr>
                                    <td>{{= (totalElements - ((number) * size + $index))}}</td>
                                    <td><a onclick="goPage('/notice/{{= ntcSn}}')">{{= ntcTtl}}</a></td>
                                    <td><a onclick="goPage('/notice/mybatis/{{= ntcSn}}')">{{= ntcTtl}}</a></td>
                                    <td>{{= author}}</td>
                                    <td>{{= lastModifiedDate}}</td>
                                </tr>
                            {{/each}}
                        </script>
                    </table>
                </div>

                <div class="table_bottom">
                    <div class="table_bot_left"></div>
                    <div class="table_bot_center pagenation" id="pagination">

                    </div>
                    <div class="table_bot_right">
                        <button type="button" class="btn04 btn_black" onclick="goPage('/notice/new')">등록</button>
                    </div>
                </div>
            </div>
        </div>


        <div class="footer">
            <div class="footer_link">
                <a href="#LINK"><b>개인정보처리방침</b></a>
                <a href="#LINK">이용약관</a>
            </div>
            <p class="footer_text">
                COPYRIGHTⓒ DAEGU METROPOLITAN CITY .ALL RIGHTS RESERVED.
            </p>
        </div>
    </div>
</div>
</body>
</html>