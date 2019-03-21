<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(
			function() {
				$('form').submit(
						function() {
							if ($('input[name="cPw"]').val() == $(
									'input[name="cPwDB"]').val()) {
							} else {
								alert('비밀번호 불일치');
								return false;
							}
						});
			});
</script>
</head>
<body>
	<jsp:include page="../main/projectHeader.jsp" />
	<div id="content">
		<form
			action="cDelete_comfirm.do?cid='${customer.cId}${content_view.cId}'"
			method="post">
			<input type="hidden" name="cId"
				value="${customer.cId}${content_view.cId}"> <input
				type="hidden" name="cPwDB"
				value="${customer.cPw}${content_view.cPw}">
			<table>
				<tr>
					<th>아이디</th>
					<td>${customer.cId}${content_view.cId}</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="cPw" required="required"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="탈퇴하기"> <input
						type="reset" value="취소" onclick="history.back(-1)"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>