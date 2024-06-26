<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:eval expression="@environment.getProperty('common.javascript.version')" var="jsVersion"/>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="format-detection" content="telephone=no"/>
    <title><spring:message code="info.test.message"/></title>

    <!-- meta tag : description, title, keywords, image는 홈페이지마다 바꿔야 합니다. -->
    <meta name="description" content="웹페이지 설명" />
    <meta name="keywords" content="메타태그, meta, tag, html" />
    <meta property="og:title" content="웹페이지 이름" />
    <meta property="og:type" content="website" />
    <meta property="og:description" content="웹페이지 설명" />
    <meta property="og:image" content="${pageContext.request.contextPath}/resources/images/og_image.jpg" />
    <meta property="og:image:width" content="1200" />
    <meta property="og:image:height" content="630" />
    <meta property="og:url" content="" />

    <!-- favicon -->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico"/>
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/resources/images/favicon-16x16.png"/>
    <link rel="icon" type="image/png" sizes="32x32" href="${pageContext.request.contextPath}/resources/images/favicon-32x32.png"/>
    <link rel="apple-touch-icon" sizes="180x180" href="${pageContext.request.contextPath}/resources/images/apple-touch-icon.png"/>

    <!-- css -->
    <link href="${pageContext.request.contextPath}/resources/css/air-datepicker.css?ver=${jsVersion}" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/fontawesome.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/sweetalert2.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/swiper.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/sptglobal.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/custom.css?ver=${jsVersion}" rel="stylesheet"/>

    <!-- js :: lib -->
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery-3.7.1.min.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery.form.min.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery.tmpl.min.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery-ui.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery.history.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/air-datepicker.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/full_calendar.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery.timepicker.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/sweetalert/sweetalert2.all.min.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/vue/vue.min.js?ver=${jsVersion}"></script>

    <!-- js -->
    <script src="${pageContext.request.contextPath}/resources/js/common/design/chart.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/design/chart-datalabel.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/design/chart_design.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/design/design.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/design/swiper.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/common.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/common-ecma6.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/pagination.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/ckeditor5/ckeditor.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/ckeditor5/ImageUploadAdapter.js?ver=${jsVersion}"></script>

    <script src="${pageContext.request.contextPath}/resources/js/lib/sweetalert/sweet.js?ver=${jsVersion}"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/sweetalert/sweet-ecma6.js?ver=${jsVersion}"></script>

    <!-- common message js -->
    <script src="${pageContext.request.contextPath}/js/message.js?time=<%=session.getLastAccessedTime()%>"></script>
    <script src="${pageContext.request.contextPath}/resources/js/logout.js?ver=${jsVersion}" defer></script>
    <%-- <script src="${pageContext.request.contextPath}/js/common/utils/login-preventor.js?ver=${jsVersion}"></script> --%>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>

<body>
<input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
<div class="page_wrap">
    <!-- s:sidebar -->
    <div class="sidebar_wrap">
        <div class="logo">
            <a onclick="goPage('/spt/dashboard')" class="logo_img">
                <img src="${pageContext.request.contextPath}/resources/images/logo.svg" alt="logo"/>
            </a>
        </div>
        <div class="sidebar_content">
            <nav class="sidebar_nav">
                <c:import url="/EgovPageLink?link=admin/inc/menu"/>
                <!-- <ul class="sidebar_list_wrap">
                    <li class="sidebar_small_cap sidebar_cap_first">
                        <p>
                            <span class="title">HOME</span>
                            <span class="line"></span>
                        </p>
                    </li>
                    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'dashboard')}"> on</c:if>">
                        <a onclick="goPage('/spt/dashboard')" class="list_title list_title_only"> <i class="fa-solid fa-house"></i>Dashboard </a>
                    </li>
                    <li class="sidebar_small_cap">
                        <p>
                            <span class="title">LAYOUTS & PAGES</span>
                            <span class="line"></span>
                        </p>
                    </li>
                    <li class="sidebar_list_item">
                        <div class="list_title">
                            <a href="#"><i class="fa-solid fa-layer-group"></i>Pages</a>
                            <i class="fa-solid fa-angle-down list_arrow"></i>
                        </div>
                        <ol class="sidebar_menu">
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/table/tableList')"><i class="fa-solid fa-circle"></i>Table List</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/table/tableForm')"><i class="fa-solid fa-circle"></i>Table Form</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/table/tableEditorForm')"><i class="fa-solid fa-circle"></i>Table Editor Form</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/table/tableView')"><i class="fa-solid fa-circle"></i>Table View</a>
                            </li>
                        </ol>
                    </li>
                    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'authentication')}"> on</c:if>">
                        <div class="list_title">
                            <a href="#"><i class="fa-solid fa-lock"></i>Authentication</a>
                            <i class="fa-solid fa-angle-down list_arrow"></i>
                        </div>
                        <ol class="sidebar_menu <c:if test = "${fn:contains(menuLevel3, 'authentication')}"> show</c:if>">
                            <li class="sidebar_menu_item">
                                <a href="auth_login.html"><i class="fa-solid fa-circle"></i>Login</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a href="auth_regist_agree.html"><i class="fa-solid fa-circle"></i>Regist</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a href="auth_id_find.html"><i class="fa-solid fa-circle"></i>Id Find</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a href="auth_password_find.html"><i class="fa-solid fa-circle"></i>Password Find</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a href="auth_id_find_complete.html"><i class="fa-solid fa-circle"></i>Id Complete</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a href="auth_password_find_complete.html"><i class="fa-solid fa-circle"></i>Password Complete</a>
                            </li>
                        </ol>
                    </li>
                    <li class="sidebar_small_cap">
                        <p>
                            <span class="title">UI COMPONENTS</span>
                            <span class="line"></span>
                        </p>
                    </li>
                    <li class="sidebar_list_item <c:if test = "${fn:contains(menuLevel3, 'components')}"> on</c:if>">
                        <div class="list_title">
                            <a href="#"><i class="fa-solid fa-display"></i>Components</a>
                            <i class="fa-solid fa-angle-down list_arrow"></i>
                        </div>
                        <ol class="sidebar_menu <c:if test = "${fn:contains(menuLevel3, 'components')}"> show</c:if>">
                            <li class="sidebar_menu_item <c:if test = "${fn:contains(menuLevel3, 'badges')}"> on</c:if>">
                                <a onclick="goPage('/spt/components/badges')"><i class="fa-solid fa-circle"></i>Badges</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/buttons')"><i class="fa-solid fa-circle"></i>Buttons</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/checks')"><i class="fa-solid fa-circle"></i>Checks And Radios</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/draggable')"><i class="fa-solid fa-circle"></i>Draggable</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/icons')"><i class="fa-solid fa-circle"></i>Icons</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/inputs')"><i class="fa-solid fa-circle"></i>Inputs</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/modals')"><i class="fa-solid fa-circle"></i>Modals</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/swiper')"><i class="fa-solid fa-circle"></i>Swiper</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/tooltip')"><i class="fa-solid fa-circle"></i>Tooltip And Popover</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/typography')"><i class="fa-solid fa-circle"></i>Typography</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a onclick="goPage('/spt/components/typography')"><i class="fa-solid fa-circle"></i>Typography</a>
                            </li>
                        </ol>
                    </li>
                    <li class="sidebar_list_item">
                        <div class="list_title">
                            <a href="#"><i class="fa-solid fa-layer-group"></i>Multi Depth</a>
                            <i class="fa-solid fa-angle-down list_arrow"></i>
                        </div>
                        <ol class="sidebar_menu">
                            <li class="sidebar_menu_item">
                                <a><i class="fa-solid fa-circle"></i>Two Depth</a>
                            </li>
                            <li class="sidebar_menu_item">
                                <a class="list_depth"><i class="fa-solid fa-circle"></i>Three Depth <i class="fa-solid fa-angle-down list_arrow"></i></a>
                                <ul class="sidebar_menu_item_depth">
                                    <li>
                                        <a>가나다라마바사아자차</a>
                                    </li>
                                    <li>
                                        <a>Item 02</a>
                                    </li>
                                    <li>
                                        <a>Item 03</a>
                                    </li>
                                </ul>
                            </li>
                        </ol>
                    </li>
               </ul> -->
            </nav>
            <div class="sidebar_bottom">
                <a onclick="logout()" class="btn">로그아웃<i class="fa-solid fa-arrow-right-from-bracket"></i></a>
            </div>
        </div>
    </div>
    <!-- e:sidebar -->