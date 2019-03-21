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
		<form action="${conPath }/boradModify.do" method="post" >
			<input type="hidden" name="bpageNum" value="${param.bpageNum }">
			<input type="hidden" name="bNum" value="${bmodify_view.bNum }">
			<table>
				<caption>${bmodify_view.bNum }번글 수정(page:${param.bpageNum })</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required" size="30"
						value="${bmodify_view.cId }" readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="bTitle" required="required"
						size="30" value="${bmodify_view.bTitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" cols="32" name="bContent">${bmodify_view.bContent }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정"> <input
						type="button" value="목록"
						onclick="location='${conPath}/list.do?bpageNum=${param.bpageNum }'">
						<input type="reset" value="취소" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>