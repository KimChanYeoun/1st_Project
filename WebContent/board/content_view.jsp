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
	<form action="${conPath}/boradModify_view.do" method="post">
		<input type="hidden" name="bpageNum" value="${param.bpageNum }">
		<input type="hidden" name="bNum" value="${bcontent_view.bNum }">
		<table>
				 <caption>${bcontent_view.bNum }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${bcontent_view.cId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${bcontent_view.bTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${bcontent_view.bContent}</pre></td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${customer.cId eq bcontent_view.cId }">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			<c:if test="${customer.cId eq bcontent_view.cId or not empty admin}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/delete.do?bNum=${bcontent_view.bNum }&bpageNum=${param.bpageNum }'">
				 			</c:if>
				 			<c:if test="${not empty customer }">
				 				<input type="button" value="답변"
				 				onclick="location='${conPath}/reply_view.do?bNum=${bcontent_view.bNum}&bpageNum=${param.bpageNum}'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/list.do?bpageNum=${param.bpageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>














