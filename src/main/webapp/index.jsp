<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/crypto/crypto.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common/utils/common.js"></script>
    <script src="${pageContext.request.contextPath}/js/message.js?time=<%=session.getLastAccessedTime()%>"></script>
</head>
<script>
    console.log(getMessage("info.test.message"));

    let l_Key = "12345WebSocket!@";

    // let aesText = AesText("안녕하세요");
    // alert(aesText);

    let aesDText = aesDecrypt("d93MZZnGkNHywxPIB+bAEA==");
    alert(aesDText);
</script>
<body>
<h1><spring:message code='info.test.message'/></h1>
<form id="frm" method="post" enctype="multipart/form-data">
    <input type="text" name="title"/>
    <textarea name="desc"></textarea>
</form>
</body>
</html>
