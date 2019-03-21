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
	<form action="${conPath}/noticeModify_view.ao" method="post">
		<input type="hidden" name="npageNum" value="${npageNum }">
		<input type="hidden" name="nNum" value="${ncontent_view.nNum }">
		<table>
				 <caption>${ncontent_view.nNum }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${ncontent_view.aId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${ncontent_view.nTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${ncontent_view.nContent}</pre></td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${not empty admin}">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			<c:if test="${not empty admin}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/delete.ao?nNum=${ncontent_view.nNum }&npageNum=${param.npageNum }'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/list.ao?npageNum=${npageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>














