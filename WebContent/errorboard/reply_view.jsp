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
<style>}
</style>
</head>
<body>
	<jsp:include page="../main/projectHeader.jsp" />
	<div id="content">
		<form action="${conPath }/ereply.so" method="post"
			enctype="multipart/form-data">
			<!-- reply.do시 필요한 정보 원글 : bGroup, bStep, bIndent
		                       지금저장할 답변글 : bName, bTitle, bContent
		                       pageNum -->
			<input type="text" name="eGroup" value="${ereply_view.eGroup }">
			<input type="text" name="eStep" value="${ereply_view.eStep }">
			<input type="text" name="eIndent" value="${ereply_view.eIndent }">
			<input type="text" name="epageNum" value="${param.epageNum }">
			<table>
				<caption>${ereply_view.eNum }번글의답변쓰기 폼</caption>
				<tr>
					<td>작성자</td>
					<td>${admin.aId }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td><input type="text" name="eTitle" required="required"
						size="30" value="[답]${ereply_view.eTitle }"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="eContent" rows="3" required="required"
							cols="32"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="efileName"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="답변쓰기"> <input
						type="reset" value="취소"> <input type="button" value="목록"
						onclick="location.href='${conPath}/elist.so'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>