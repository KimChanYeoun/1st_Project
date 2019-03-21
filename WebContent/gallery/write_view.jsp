<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<style>
</style>
</head>
<body>
<jsp:include page="../main/projectHeader.jsp"/>
<div id="content">
	<form action="${conPath }/write.so" method="post" enctype="multipart/form-data">
		<table>
			<caption>글쓰기 폼</caption>
			<tr><td>제목</td><td><input type="text" name="gTitle"
							required="required" size="30"></td></tr>
			<tr><td>본문</td><td><textarea name="gContent" rows="3" 
							cols="32"></textarea></td></tr>
			<tr><td>첨부파일</td><td><input type="file" name="gfileName"></td></tr>
			<tr><td colspan="2">
						<input type="submit" value="글쓰기">
						<input type="reset" value="취소">
						<input type="button" value="목록"
							onclick="location.href='${conPath}/list.so'">
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>
















