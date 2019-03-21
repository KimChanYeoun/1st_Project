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
	<form action="${conPath}/galleryModify_view.so" method="post">
		<input type="hidden" name="gpageNum" value="${gpageNum }">
		<input type="hidden" name="gNum" value="${gcontent_view.gNum }">
		<table>
				 <caption>${gcontent_view.gNum }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${gcontent_view.cId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${gcontent_view.gTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${gcontent_view.gContent}</pre><img src="${conPath }/fileboardUp/${gcontent_view.gfileName}"></td>
				 </tr>
				 <tr><th>첨부파일</th>
						 <td>
						 	<c:if test="${not empty gcontent_view.gfileName }">
						 		<a href="${conPath }/fileboardUp/${gcontent_view.gfileName}" target="_blank">${gcontent_view.gfileName}</a>
						 	</c:if>
						 	<c:if test="${empty gcontent_view.gfileName }">
						 		첨부파일없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${customer.cId eq gcontent_view.cId }">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			<c:if test="${customer.cId eq gcontent_view.cId or not empty admin}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/delete.so?gNum=${gcontent_view.gNum }&gpageNum=${param.gpageNum }'">
				 			</c:if>
				 			<c:if test="${not empty customer }">
				 				<input type="button" value="답변"
				 				onclick="location='${conPath}/reply_view.so?gNum=${gcontent_view.gNum}&gpageNum=${param.gpageNum}'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/list.so?gpageNum=${gpageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>














