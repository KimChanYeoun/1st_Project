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
<style>
</style>
</head>
<body>
	<jsp:include page="../main/projectHeader.jsp" />
	<div id="content">
		<form action="${conPath }/galleryModify.so" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="gpageNum" value="${param.gpageNum }">
			<input type="hidden" name="gNum" value="${gmodify_view.gNum }">
			<input type="hidden" name="dbgFileName"
				value="${gmodify_view.gfileName }">
			<table>
				<caption>${gmodify_view.gNum }번글 수정(page:${param.gpageNum })</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required" size="30"
						value="${gmodify_view.cId }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="gTitle" required="required"
						size="30" value="${gmodify_view.gTitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" cols="32" name="gContent">${gmodify_view.gContent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="gfileName"> 원첨부파일: <c:if
							test="${not empty gmodify_view.gfileName }">
							<a href="${conPath }/fileboardUp/${gmodify_view.gfileName}"
								target="_blank">${gmodify_view.gfileName}</a>
						</c:if> <c:if test="${empty gmodify_view.gfileName }">
						 		첨부파일없음
						 	</c:if></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정"> <input
						type="button" value="목록"
						onclick="location='${conPath}/list.so?gpageNum=${param.gpageNum }'">
						<input type="reset" value="취소" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>