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
	<form action="${conPath}/upgradeModify_view.ao" method="post">
		<input type="hidden" name="upageNum" value="${upageNum }">
		<input type="hidden" name="uNum" value="${ucontent_view.uNum }">
		<table>
				 <caption>${ucontent_view.uNum }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${ucontent_view.aId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${ucontent_view.uTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${ucontent_view.uContent}</pre></td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${not empty admin}">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			<c:if test="${not empty admin}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/udelete.ao?uNum=${ucontent_view.uNum }&upageNum=${param.upageNum }'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/ulist.ao?upageNum=${upageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>














