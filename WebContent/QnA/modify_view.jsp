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
		<form action="${conPath }/QnAModify.ao" method="post" >
			<input type="hidden" name="qpageNum" value="${param.qpageNum }">
			<input type="hidden" name="qNum" value="${qmodify_view.qNum }">
			<table>
				<caption>${qmodify_view.qNum }번글 수정(page:${param.qpageNum })</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required" size="30"
						value="${qmodify_view.cId }${qmodify_view.aId }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="qTitle" required="required"
						size="30" value="${qmodify_view.qTitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" cols="32" name="qContent">${qmodify_view.qContent }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정"> <input
						type="button" value="목록"
						onclick="location='${conPath}/qlist.ao?qpageNum=${param.qpageNum }'">
						<input type="reset" value="취소" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>