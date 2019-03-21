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
	<form action="${conPath}/errorboardModify_view.so" method="post">
		<input type="hidden" name="epageNum" value="${param.epageNum }">
		<input type="hidden" name="eNum" value="${econtent_view.eNum }">
		<table>
				 <caption>${econtent_view.eNum }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${econtent_view.cId}${econtent_view.aId}님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${econtent_view.eTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${econtent_view.eContent}</pre></td>
				 </tr>
				 <tr><th>첨부파일</th>
						 <td>
						 	<c:if test="${not empty econtent_view.efileName }">
						 		<a href="${conPath }/fileboardUp/${econtent_view.efileName}" target="_blank">${econtent_view.efileName}</a>
						 	</c:if>
						 	<c:if test="${empty econtent_view.efileName }">
						 		첨부파일없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><td colspan="2">
				 <c:if test="${not empty customer or not empty admin}">
				   <c:if test="${dto.eIndent != 0}">
				 			<c:if test="${customer.cId eq econtent_view.cId}">
				 				<input type="submit" value="수정">
				 			</c:if>
				 			</c:if>
				 			</c:if>
				 			<c:if test="${empty customer and not empty admin or (not empty customer and customer.cId eq econtent_view.cId)}">
				 				<input type="button" value="삭제"
				 					onclick="location='${conPath}/edelete.so?eNum=${econtent_view.eNum }&epageNum=${param.epageNum }'">
				 			</c:if>
				 			<c:if test="${not empty admin }">
				 				<input type="button" value="답변"
				 				onclick="location='${conPath}/ereply_view.so?eNum=${econtent_view.eNum}&epageNum=${param.epageNum}'">
				 			</c:if>
				 			<input type="button" value="목록"
				 	onclick="location='${conPath}/elist.so?epageNum=${param.epageNum }'">			 
		</table>
	</form>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>














