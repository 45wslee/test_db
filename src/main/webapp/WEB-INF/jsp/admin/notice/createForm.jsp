<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Title</title>

    <!-- css -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admglobal.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery-3.6.0.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery.form.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/utils/common.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/utils/file.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/notice/notice.js" defer></script>

    <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>">
</head>
<body>

<div class="contents_area">
    <div class="contents">

        <div class="pageTitle">
            <h1>공지사항</h1>
        </div>

        <div class="table_wrap">

            <div class="tableTitle">
                <h3></h3>
                <p>(*) 필수입력</p>
            </div>
            <div class="table_area">
                <form id="frm" name="frm" method="post" enctype="multipart/form-data">
                    <table class="contentsTable1">
                        <colgroup>
                            <col width="10%">
                            <col width="40%">
                            <col width="10%">
                            <col width="">
                        </colgroup>
                        <tr>
                            <th class="red_dot">제목</th>
                            <td colspan="3"><input type="text" class="W1200" name="ntcTtl"></td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td colspan="3">
                                <div class="MgB16">
                                    <label for="files"><button type="button" class="btn08 btn_grey" onclick="document.frm.files.click()">파일선택</button></label>
                                    <input type="file" name="files" id="files" multiple style="display: none" onchange="handleFiles(this.files)"/>
                                    <span>※ 첨부파일 당 최대 5MB까지 업로드 가능하며 최대 5까지 등록할 수 있습니다.</span>
                                </div>
                                <div class="conTable_file" id="file_contents">
<%--                                    <p>첨부파일.hwp<button type="button"><img src="${pageContext.request.contextPath}/images/adm/file_delete.png"></button></p>--%>
<%--                                    <p>첨부파일.hwp<button type="button"><img src="${pageContext.request.contextPath}/images/adm/file_delete.png"></button></p>--%>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th class="red_dot">내용</th>
                            <td colspan="3"><textarea name="ntcCn"></textarea></td>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="table_bottom">
                <div class="table_bot_left">
                    <button type="button" class="btn04 btn_grey_line2">목록</button>
                </div>
                <div class="table_bot_center"></div>
                <div class="table_bot_right">
                    <button type="button" class="btn04 btn_blue2" onclick="saveNotice()">저장-JPA</button>
                    <button type="button" class="btn04 btn_blue2" onclick="saveNoticeWithMyBatis()">저장-MyBatis</button>
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
</body>
</html>