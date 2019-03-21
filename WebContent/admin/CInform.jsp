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
	<input type="hidden" name="dbmPhoto" value="${cAllView.cPhoto}">
	<table>
		<caption>회원정보보기</caption>
		<tr><th>아이디</th>
			<td>${content_view.cId }</td>
			<td colspan="2" rowspan="5">
			<img src="${conPath}/mPhotoUp/${content_view.cPhoto}" height="100"></td>
		</tr>
		<tr><th>비밀번호</th>
			<td>${content_view.cPw }</td>
		</tr>
		<tr><th>이름</th>
			<td>${content_view.cName }</td>
		</tr>
		<tr><th>성별</th>
			<td>${content_view.cGender }</td>
		</tr>
		<tr><th>전화번호</th>
				<td>${content_view.cTel }</td>
		</tr>
		<tr><th>메일주소</th>
			<td colspan="3">${content_view.cEmail }</td>
		</tr>
		<tr><th>생년월일</th>
				<td colspan="3">${content_view.cBirth}</td>
		</tr>
		<tr><th>주소</th>
			<td colspan="3">${content_view.cAddr }</td>
		</tr>
		<tr><th>게시판 쓴 글</th>
			<td>${bcontent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/mylist.do?cId=${content_view.cId }'"></td>
			<th>갤러리 쓴 글</th>
			<td>${gcontent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/myglist.so?cId=${content_view.cId }'"></td>
		</tr>
		<tr><th>에러게시판 쓴 글</th>
			<td>${econtent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/myelist.so?cId=${content_view.cId }'"></td>
			<th>QnA 쓴 글</th>
			<td>${qcontent_view }개
			<input type="button" value="글보기" onclick="location='${conPath}/myQlist.ao?cId=${content_view.cId }'"></td>
		</tr>
		<tr><td colspan="4">
				<input type="button" value="회원탈퇴" onclick="location='${conPath}/cDelete.do'">
				<input type="reset" value="뒤로가기" onclick="history.back(-1);">
			</td>
		</tr>
	</table>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>