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
<jsp:include page="../main/projectHeader.jsp" />
<div id="content">
	<form action="${conPath}/QnAModify_view.ao" method="post">
		<input type="hidden" name="qpageNum" value="${param.qpageNum }">
		<input type="hidden" name="qNum" value="${qcontent_view.qNum }">
		<table>
				 <caption>${qcontent_view.qNum }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${qcontent_view.cId}${qcontent_view.aId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${qcontent_view.qTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${qcontent_view.qContent}</pre></td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${not empty customer and customer.cId eq qcontent_view.cId}">
				 				<input type="submit" value="수정">
				 			</c:if>	
				 			<c:if test="${not empty admin and admin.aId eq qcontent_view.aId}">
				 				<input type="button" value="수정" onclick="location='${conPath}/aQnAModify_view.ao?qNum=${content_view.qNum }&qpageNum=${param.qpageNum }'">
				 			</c:if>
				 			<c:if test="${empty customer and not empty admin or (not empty customer and customer.cId eq qcontent_view.cId)}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/qdelete.ao?qNum=${qcontent_view.qNum }&qpageNum=${param.qpageNum }'">
				 			</c:if>
				 			<c:if test="${not empty admin }">
				 				<input type="button" value="답변"
				 				onclick="location='${conPath}/qreply_view.ao?qNum=${qcontent_view.qNum}&qpageNum=${param.qpageNum}'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/qlist.ao?qpageNum=${param.qpageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>














