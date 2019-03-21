<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath}/css/style.css" rel="stylesheet">
<style>	
</style>
</head>
<body>
<jsp:include page="../main/projectHeader.jsp"/>
<div id="content">
	<form action="${conPath}/modify.do" method="post"
		enctype="multipart/form-data" name="frm">
	<input type="hidden" name="dbmPhoto" value="${customer.cPhoto}">
	<table>
		<caption>회원정보수정</caption>
		<tr><th>아이디</th>
			<td><input type="text" name="cId" value="${customer.cId }" readonly="readonly"></td>
			<td rowspan="4">
			<img src="${conPath}/mPhotoUp/${customer.cPhoto}" height="100"></td>
		</tr>
		<tr><th>비밀번호</th>
			<td><input type="password" name="cPw" value="${customer.cPw }"></td>
		</tr>
		<tr><th>이름</th>
			<td><input type="text" name="cName" value="${customer.cName }" readonly="readonly"></td>
		</tr>
		<tr><th>전화번호</th>
				<td><input type="text" name="cTel" value="${customer.cTel }" placeholder="'-'는 제외한 숫자만 입력해주세요"></td>
		</tr>
		<tr><th>메일주소</th>
			<td colspan="2"><input type="email" name="cEmail" value="${customer.cEmail }"></td>
		</tr>
		<tr><th>사진</th>
			<td colspan="2"><input type="file" name="cPhoto"></td>
		</tr>
		<tr><th>생년월일</th>
				<td colspan="2"><input type="date" name="cBirth" value="${customer.cBirth}" readonly="readonly"></td>
		</tr>
		<tr><th>주소</th>
			<td colspan="2"><input type="text" name="cAddr" value="${customer.cAddr }"></td>
		</tr>
		<tr><td colspan="3">
				<input type="submit" value="정보수정완료">
				<input type="reset" value="리셋">
				<input type="reset" value="뒤로가기" onclick="history.back(-1);">
			</td>
		</tr>
	</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>