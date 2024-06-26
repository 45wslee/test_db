<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <title><spring:message code="info.test.message"/></title>

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

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery-3.6.0.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/jquery/jquery.form.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/utils/common.js" defer></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/member.js" defer></script>
    <input type="hidden" id="contextPath" value="<%=request.getContextPath()%>"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/crypto/jsencrypt.min.js" defer></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/crypto/crypto.js" defer></script>

    <!-- sweetalert -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/sweetalert/sweetalert2.all.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/sweetalert/sweet.js"></script>
</head>
<body>
    <!-- RSA -->
    <input type="hidden" id="publicKeyStr" value="${publicKeyStr}"/>

    <form id="joinForm" name="joinForm" method="post">
        <table>
            <tbody>
            <tr>
                <td><label for="mbrId">아이디</label></td>
                <td><input type="text" name="mbrId" id="mbrId"/></td>
                <td><label for="mbrNm">이름</label></td>
                <td><input type="text" name="mbrNm" id="mbrNm"/></td>
            </tr>

            <tr>
                <td><label for="mbrPswd">비밀번호</label></td>
                <td><input type="password" name="mbrPswd" id="mbrPswd"/></td>
                <td><label for="mbrPwdConfirm">비밀번호 확인</label></td>
                <td><input type="password" name="mbrPwdConfirm" id="mbrPwdConfirm"/></td>
            </tr>

            <tr>
                <td><label for="mbrPhoneNo">휴대폰번호</label></td>
                <td colspan="3"><input type="text" name="mbrTelno" id="mbrTelno"/></td>
            </tr>
            </tbody>
        </table>

        <button type="button" onclick="join(); return false">회원가입</button>
    </form>

</body>
</html>
