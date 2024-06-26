<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>
<c:import url="/EgovPageLink?link=admin/inc/header"/>
<!-- s:content -->
<div class="content_wrap">
    <p class="content_header_title">Components <i class="fa-solid fa-angle-right"></i> Modals</p>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Basic Modal</p>
        <div class="item_group">
            <button type="button" class="btn btn_blue btn_popup">Launch Demo Modal</button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Optional Sizes Modal</p>
        <div class="item_group">
            <button type="button" class="btn btn_dark btn_popup">Basic Modal</button>
            <button type="button" class="btn btn_green btn_popup_02">Large Modal</button>
            <button type="button" class="btn btn_red btn_popup_03">Extra Large Modal</button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Form Modal</p>
        <div class="item_group">
            <button type="button" class="btn btn_sky btn_popup_04">Launch Demo Modal</button>
        </div>
    </div>
    <div class="components_group">
        <p class="heading"><i class="fa-solid fa-ellipsis-vertical"></i>Modal Footer None</p>
        <div class="item_group">
            <button type="button" class="btn btn_yellow btn_popup_05">Launch Demo Modal</button>
        </div>
    </div>
    <!-- s:footer -->
    <c:import url="/EgovPageLink?link=admin/inc/footer"/>
    <!-- e:footer -->
</div>
<!-- e:content -->

<!-- s:modal -->
<div class="modal_all_wrap">

    <div class="modal_wrap modal_basic">
        <div class="overlay"></div>
        <div class="modal_content">
            <div class="modal_header">
                <p class="modal_title">Modal Title</p>
                <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal_body">
                <h4>Centered Modal</h4>
                <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
            </div>
            <div class="modal_footer">
                <button type="button" class="btn btn_red btn_close">Close</button>
            </div>
        </div>
    </div>

    <div class="modal_wrap modal_wrap_lg">
        <div class="overlay"></div>
        <div class="modal_content modal_lg">
            <div class="modal_header">
                <p class="modal_title">Large Modal Title</p>
                <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal_body">
                <h4>Centered Modal</h4>
                <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
            </div>
            <div class="modal_footer">
                <button type="button" class="btn btn_red btn_close">Close</button>
            </div>
        </div>
    </div>

    <div class="modal_wrap modal_wrap_xl">
        <div class="overlay"></div>
        <div class="modal_content modal_xl">
            <div class="modal_header">
                <p class="modal_title">Extra Large Modal Title</p>
                <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal_body">
                <h4>Centered Modal</h4>
                <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
            </div>
            <div class="modal_footer">
                <button type="button" class="btn btn_red btn_close">Close</button>
            </div>
        </div>
    </div>

    <div class="modal_wrap modal_wrap_form">
        <div class="overlay"></div>
        <div class="modal_content">
            <div class="modal_header">
                <p class="modal_title">Modal Title</p>
                <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal_body">
                <div class="form_group">
                    <p class="label">Name<i class="fa-solid fa-asterisk text_red"></i></p>
                    <input type="text" placeholder="Name"/>
                </div>
                <div class="form_group">
                    <p class="label">Email<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="email_group">
                        <input type="text" placeholder="이메일을"/>
                        <i class="fa-solid fa-at"></i>
                        <input type="text" placeholder="입력하세요."/>
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
                </div>
                <div class="form_group">
                    <p class="label">Phone<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="tel_group">
                        <input type="text" placeholder="000" maxlength="3"/>
                        <i class="fa-solid fa-minus"></i>
                        <input type="text" placeholder="0000" maxlength="4"/>
                        <i class="fa-solid fa-minus"></i>
                        <input type="text" placeholder="0000" maxlength="4"/>
                    </div>
                </div>
                <div class="form_group">
                    <p>Select</p>
                    <div class="select_wrap">
                        <select>
                            <option value="">전체</option>
                            <option value="">option 01</option>
                            <option value="">option 02</option>
                            <option value="">option 03</option>
                        </select>
                        <i class="fa-solid fa-angle-down"></i>
                    </div>
                </div>
                <div class="form_group">
                    <p class="label">Button Input Group<i class="fa-solid fa-asterisk text_red"></i></p>
                    <div class="form_btn_group">
                        <input type="text" placeholder="Input"/>
                        <button type="button" class="btn btn_blue">button</button>
                    </div>
                </div>
            </div>
            <div class="modal_footer">
                <button type="button" class="btn btn_red btn_close">Close</button>
                <button type="button" class="btn btn_dark">Regist</button>
            </div>
        </div>
    </div>

    <div class="modal_wrap modal_footer_none_wrap">
        <div class="overlay"></div>
        <div class="modal_content modal_footer_none">
            <div class="modal_header">
                <p class="modal_title">Modal Title</p>
                <button type="button" class="btn btn_close"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal_body">
                <h4>Centered Modal</h4>
                <p>Cras mattis consectetur purus sit amet fermentum. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Morbi leo risus, porta ac consectetur ac, vestibulum at eros.</p>
            </div>
        </div>
    </div>
</div>
<!-- e:modal -->
</body>
</html>
