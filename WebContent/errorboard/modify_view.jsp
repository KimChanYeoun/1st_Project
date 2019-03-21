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
		<form action="${conPath }/errorboardModify.so" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="epageNum" value="${param.epageNum }">
			<input type="hidden" name="eNum" value="${emodify_view.eNum }">
			<input type="hidden" name="dbeFileName"
				value="${emodify_view.efileName }">
			<table>
				<caption>${emodify_view.eNum }번글 수정(page:${param.epageNum })</caption>
				<tr>
					<th>작성자</th>
					<td><input type="text" required="required" size="30"
						value="${emodify_view.cId }${emodify_view.aId }"
						readonly="readonly"></td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input type="text" name="eTitle" required="required"
						size="30" value="${emodify_view.eTitle }"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea rows="5" cols="32" name="eContent">${emodify_view.eContent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td><input type="file" name="efileName"> 원첨부파일: <c:if
							test="${not empty emodify_view.efileName }">
							<a href="${conPath }/fileboardUp/${emodify_view.efileName}"
								target="_blank">${emodify_view.efileName}</a>
						</c:if> <c:if test="${empty emodify_view.efileName }">
						 		첨부파일없음
						 	</c:if></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="수정"> <input
						type="button" value="목록"
						onclick="location='${conPath}/elist.so?epageNum=${param.epageNum }'">
						<input type="reset" value="취소" onclick="history.back()"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>