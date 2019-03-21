<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<style>
#content td {
	padding: 10px 20px;
}
</style>
</head>
<body>
		<div class="logo">
			<a href="${conPath}/main.do"> <img
				src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Flag_of_South_Korea.svg/225px-Flag_of_South_Korea.svg.png"
				alt="사이트로고" />
			</a>
		</div>
		<div id="content">
			<c:if test="${not empty resultMsg }">
				<script>
					alert('${resultMsg}');
				</script>
			</c:if>
			<c:if test="${not empty errorMsg }">
				<script>
					alert('${errorMsg}');
					history.back();
				</script>
			</c:if>
			<form action="${conPath}/login.do" method="post">
				<table style="width: 50%;">
					<caption>사용자 로그인</caption>
					<tr>
						<th><label for="cId">아이디</label></th>
						<td><input type="text" id="cId" name="cId" value="${cId }"
							required="required" autofocus="autofocus"></td>
					</tr>
					<tr>
						<th><label for="cPw">비밀번호</label></th>
						<td><input type="password" id="cPw" name="cPw"
							required="required"></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="로그인"> <input
							type="button" value="회원가입"
							onclick="location='${conPath}/joinView.do'"></td>
					</tr>
				</table>
			</form>
		</div>
		<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>