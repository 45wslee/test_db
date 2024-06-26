<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
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
    <link href="${pageContext.request.contextPath}/resources/css/fontawesome.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/air-datepicker.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/sweetalert2.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/resources/css/sptglobal.css" rel="stylesheet"/>

    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery-3.7.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery.form.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/common/utils/common.js" defer></script>

    <!-- RSA -->
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/rsa/rsa.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/rsa/jsbn.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/rsa/prng4.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/common/utils/rsa/rng.js"></script>

    <!-- sweetalert -->
    <script src="${pageContext.request.contextPath}/resources/js/lib/sweetalert/sweetalert2.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/lib/sweetalert/sweet.js"></script>

    <!-- common message js -->
    <script src="${pageContext.request.contextPath}/js/message.js?time=<%=session.getLastAccessedTime()%>"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js" defer></script>
    <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>
</head>
<body>
<input type="hidden" id="publicKeyStr" value="${publicKeyStr}"/>

<div class="auth_wrap">
    <div class="auth_bg">
        <img src="${pageContext.request.contextPath}/resources/images/auth_bg.jpg" alt="login background"/>
    </div>
    <div class="auth_content">
        <form id="loginForm" name="loginForm" method="post" action="/loginProc">
            <p class="auth_title">관리자 로그인</p>
            <p class="auth_sub_title">로그인 하려면 정보를 입력하세요.</p>
            <div class="auth_form_wrap">
                <div class="auth_form_group">
                    <label for="mbrId">아이디</label>
                    <input id="mbrId" name="mbrId" type="text" placeholder="아이디를 입력하세요."/>
                </div>
                <div class="auth_form_group">
                    <label for="mbrPswd">비밀번호</label>
                    <input id="mbrPswd" name="mbrPswd" type="password" placeholder="비밀번호를 입력하세요."/>
                </div>
                <div class="memory_forgot">
                    <div class="checks">
                        <input type="checkbox" id="memory"/>
                        <label for="memory">아이디 / 비밀번호 기억하기</label>
                    </div>
                    <div class="forgot_item">
                        <a href="auth_id_find.html" class="text_blue">아이디 찾기</a>
                        <span></span>
                        <a href="auth_password_find.html" class="text_blue">비밀번호 찾기</a>
                    </div>
                </div>
                <a onclick="login()" id="loginBtn" class="btn btn_blue auth_btn"><i class="fa-solid fa-arrow-right-to-bracket"></i>로그인</a>
                <!-- <a onclick="securityAjaxLogin()" id="securityLoginBtn" class="btn btn_blue auth_btn"><i class="fa-solid fa-arrow-right-to-bracket"></i>security ajax 로그인</a> -->
                <%--<button class="btn btn_blue auth_btn"><i class="fa-solid fa-arrow-right-to-bracket"></i>security submit 로그인</button>--%>
                <div class="regist_question">
                    <span>계정이 없으신가요?</span>
                    <a href="auth_regist_agree.html" class="text_blue">회원가입</a>
                </div>
            </div>
        </form>
    </div>

</div>
<%--<form id="loginForm" name="loginForm" method="post" action="/member/login">
    <label for="mbrId">아이디</label>
    <input type="text" name="id" id="id"/>
    <label for="mbrPswd">비밀번호</label>
    <input type="password" name="pw" id="pw"/>
    <button type="submit">스프링시큐리티로그인</button>
    &lt;%&ndash;    <button type="button" onclick="login();">로그인</button>
        <button type="button" onclick="login2();">로그인2</button>
        <button type="button" onclick="login3();">로그인3</button>
        <button type="button" onclick="login4();">로그인4</button>
        <button type="button" onclick="goPage('/join')">회원가입</button>&ndash;%&gt;
</form>--%>
</body>
</html>
