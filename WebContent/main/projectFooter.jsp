<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/footer.css" rel="stylesheet" type="text/css" />
</head>
<body>
<footer>
        <div class="office_logo"><img src="${conPath }/img/logo.png" alt="office_logo" /></div>
        <div class="office_address"><img src="${conPath }/img/address.png" alt="office_address" /></div>
        <div class="Partners"><a href="${conPath }/adminloginView.do">Partners</a></div>
        <div class="copyright">
            copyright ⓒ tjoeunIT co ., Ltd. reserverd
        </div>
    </footer>
</body>
</html>