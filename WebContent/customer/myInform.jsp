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
	<form action="${conPath}/modifyView.do" method="post"
		enctype="multipart/form-data" name="frm">
	<input type="hidden" name="dbmPhoto" value="${customer.cPhoto}">
	<table>
		<caption>회원정보수정</caption>
		<tr><th>아이디</th>
			<td>${customer.cId }</td>
			<td colspan="2" rowspan="5">
			<img src="${conPath}/mPhotoUp/${customer.cPhoto}" height="100"></td>
		</tr>
		<tr><th>비밀번호</th>
			<td>${customer.cPw }</td>
		</tr>
		<tr><th>이름</th>
			<td>${customer.cName }</td>
		</tr>
		<tr><th>성별</th>
			<td>${customer.cGender }</td>
		</tr>
		<tr><th>전화번호</th>
				<td>${customer.cTel }</td>
		</tr>
		<tr><th>메일주소</th>
			<td colspan="3">${customer.cEmail }</td>
		</tr>
		<tr><th>생년월일</th>
				<td colspan="3">${customer.cBirth}</td>
		</tr>
		<tr><th>주소</th>
			<td colspan="3">${customer.cAddr }</td>
		</tr>
		<tr><th>게시판 쓴 글</th>
			<td>${bcontent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/mylist.do'"></td>
			<th>갤러리 쓴 글</th>
			<td>${gcontent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/myglist.so'"></td>
		</tr>
		<tr><th>에러게시판 쓴 글</th>
			<td>${econtent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/myelist.so'"></td>
			<th>QnA 쓴 글</th>
			<td>${qcontent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/myQlist.ao'"></td>
		</tr>
		<tr><td colspan="4">
				<input type="submit" value="정보수정하기">
				<input type="button" value="회원탈퇴" onclick="location='${conPath}/cDelete.do'">
				<input type="reset" value="뒤로가기" onclick="history.back(-1);">
			</td>
		</tr>
	</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>