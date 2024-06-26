<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<c:import url="/EgovPageLink?link=admin/inc/header"/>
<script src="${pageContext.request.contextPath}/resources/js/admin/table/tableEdit.js?ver=${jsVersion}" defer></script>

<!-- s:content -->
<div class="content_wrap">
    <p class="content_header_title">Pages <i class="fa-solid fa-angle-right"></i> Table Editor Form</p>
    <p class="text_red text_right mb10">* 필수입력</p>
    <table class="form_table">
        <colgroup>
            <col width="15%"/>
            <col width=""/>
        </colgroup>
        <tbody>
        <tr>
            <th>Select <i class="fa-solid fa-asterisk text_red"></i></th>
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
            <th>Select Dropdonw <i class="fa-solid fa-asterisk text_red"></i></th>
            <td>
                <div class="dropdown_wrap">
                    <button type="button" class="btn btn_dropdown">Dropdown Item 01</button>
                    <div class="dropdown_item">
                        <p class="dropdown_item_txt">Dropdown Item 01</p>
                        <p class="dropdown_item_txt">Dropdown Item 02</p>
                        <p class="dropdown_item_txt">Dropdown Item 03</p>
                        <p class="dropdown_item_txt">Dropdown Item 04</p>
                        <p class="dropdown_item_txt">Dropdown Item 05</p>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <th>Input <i class="fa-solid fa-asterisk text_red"></i></th>
            <td><input type="text" placeholder="내용을 입력하세요."/></td>
        </tr>
        <tr>
            <th>Input Date</th>
            <td>
                <div class="date_input">
                    <input class="only_date" type="text" placeholder="날짜를 선택하세요." readonly/>
                    <i class="fa-regular fa-calendar"></i>
                </div>
            </td>
        </tr>
        <tr>
            <th>Input Phone</th>
            <td>
                <div class="phone_group">
                    <input id="userTel" type="text" maxlength="3" placeholder="000"/>
                    <i class="fa-solid fa-minus"></i>
                    <input id="userTel_02" type="text" maxlength="4" placeholder="0000"/>
                    <i class="fa-solid fa-minus"></i>
                    <input id="userTel_03" type="text" maxlength="4" placeholder="0000"/>
                </div>
            </td>
        </tr>
        <tr>
            <th>Input Email</th>
            <td>
                <div class="email_group">
                    <input type="text" id="userMail_01" placeholder="이메일을"/>
                    <i class="fa-solid fa-at"></i>
                    <input type="text" id="userMail_02" placeholder="입력하세요."/>
                    <div class="select_wrap">
                        <select id="emailDomainSelector">
                            <option value="">직접입력</option>
                            <option value="naver.com">naver.com</option>
                            <option value="gmail.com">gmail.com</option>
                            <option value="daum.net">daum.net</option>
                            <option value="hanmail.net">hanmail.net</option>
                            <option value="nate.com">nate.com</option>
                            <option value="yahoo.com">yahoo.com</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <th>Input Button Group</th>
            <td>
                <div class="form_group">
                    <input type="text" placeholder="내용을 입력하세요."/>
                    <button type="button" class="btn btn_blue">Button</button>
                </div>
            </td>
        </tr>
        <tr>
            <th>ckeditor</th>
            <td>
                <textarea name="textarea" id="editorCn" cols="30" rows="10" placeholder="내용을 입력하세요."></textarea>
            </td>
        </tr>
        <tr>
            <th>Upload File (파일 하나일 경우)</th>
            <td>
                <div class="form_group">
                    <input type="text" class="upload_name" placeholder=".hwp, .docx, .pptx, .xlsx, .pdf, .mp4, .png, .jpg, .zip" value="" readonly />
                    <label for="file" class="btn btn_blue cursor_pointer">파일 찾기</label>
                    <input type="file" class="upload_input" id="file" />
                </div>
            </td>
        </tr>
        <tr>
            <th>Upload File (파일 여러개일 경우)</th>
            <td>
                <div class="form_group file_many_group">
                    <label for="fileMany" class="btn btn_blue cursor_pointer">파일 찾기 <i class="fa-regular fa-folder-open"></i></label>
                    <input type="file" class="upload_input" id="fileMany" accept=".hwp, .pptx, .docx, .xlsx, .pdf, .png, .jpg, .jpeg">
                </div>
                <div class="file_regist_wrap">
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_01.jpg <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_02.png <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_03.gif <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_04.jpg <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_05.png <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_06.gif <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_07.jpg <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_08.png <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_09.gif <i class="fa-solid fa-xmark text_red"></i></p>
                    <p class="badge badge_rounded badge_dark cursor_pointer">file_name_10.jpg <i class="fa-solid fa-xmark text_red"></i></p>
                </div>
            </td>
        </tr>
        <tr>
            <th>Plus Minus</th>
            <td class="input_add_group">
                <div class="form_group add_group">
                    <input type="text" placeholder="Input Text"/>
                    <button type="button" class="btn btn_blue btn_add">추가<i class="fa-solid fa-plus"></i></button>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="content_bottom">
        <div class="content_bottom_left"></div>
        <div class="content_bottom_center">
            <button type="button" class="btn btn_dark">목록으로</button>
            <button type="button" class="btn btn_blue">등록하기</button>
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
