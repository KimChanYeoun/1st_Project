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
		<form action="${conPath }/upgradeModify.ao" method="post" >
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="uNum" value="${modify_view.uNum }">
			<table>
				<caption>${modify_view.uNum }번글 수정(page:${param.pageNum })</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required" size="30"
						value="${modify_view.aId }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="uTitle" required="required"
						size="30" value="${modify_view.uTitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" cols="32" name="nContent">${modify_view.uContent }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정"> <input
						type="button" value="목록"
						onclick="location='${conPath}/ulist.ao?pageNum=${param.pageNum }'">
						<input type="reset" value="취소" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>