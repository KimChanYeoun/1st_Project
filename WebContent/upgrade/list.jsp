<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
</head>
<style>
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('tr').click(function() {
			var uNum = Number($(this).children(0).eq(0).text());
			if(!isNaN(uNum)){
				location.href = '${conPath}/ucontent_view.ao?uNum='+uNum+'&upageNum=${upageNum}';
			}
		});
	});
</script>
<body>
	<jsp:include page="../main/projectHeader.jsp" />
	<div id="content">
		<c:if test="${not empty resultMsg }">
			<script>
				alert('${resultMsg}');
			</script>
		</c:if>
		<c:if test="${not empty loginErrorMsg}">
			<script>
				alert('${loginErrorMsg}');
				history.back();
			</script>
		</c:if>
		<c:if test="${not empty errorMsg}">
			<script>
				alert('${errorMsg}');
				history.back();
			</script>
		</c:if>
		<table>
			<tr>
				<td><c:if test="${not empty admin }">
						<a href="${conPath }/uwrite_view.ao">글쓰기</a>
					</c:if> 
					<c:if test="${ empty admin }">
						업데이트 게시판입니다
					</c:if> 
			</tr>
		</table>
		<table>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>글제목</th>
				<th>날짜</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6">글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${ulist }" var="dto">
					<tr>
						<td>${dto.uNum }</td>
						<td>${dto.aId }</td>
						<td class="left"> 
						<p><a href="${conPath }/ucontent_view.ao?uNum=${dto.uNum}&upageNum=${upageNum}">${dto.uTitle }</a></p>
						</td>
						<td><fmt:formatDate value="${dto.uDate }" type="date"
								dateStyle="short" /></td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/ulist.ao?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/ulist.ao?pageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/ulist.ao?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>

