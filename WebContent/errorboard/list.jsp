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
			var eNum = Number($(this).children(0).eq(0).text());
			if(!isNaN(eNum)){
				location.href = '${conPath}/econtent_view.so?eNum='+eNum+'&epageNum=${epageNum}';
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
				<td><c:if test="${not empty customer }">
						<a href="${conPath }/ewrite_view.so">글쓰기</a>
					</c:if> <c:if test="${empty customer }">
						<a href="${conPath }/loginView.do">글쓰기는 사용자 로그인 이후에만 가능합니다</a>
					</c:if></td>
			</tr>
		</table>
		<table>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>글제목</th>
				<th>날짜</th>
				<th>아이피</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6">글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${elist }" var="dto">
					<tr>
						<td>${dto.eNum }</td>
						<td>${dto.cId }${dto.aId }</td>
						<td class="left"><p><c:forEach var="i" begin="1"
								end="${dto.eIndent }">
								<c:if test="${i==dto.eIndent }">└─</c:if>
								<c:if test="${i!=dto.eIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach> <a
							href="${conPath }/econtent_view.so?eNum=${dto.eNum}&epageNum=${epageNum}">${dto.eTitle }</a></p>
						</td>
						<td><fmt:formatDate value="${dto.eDate }" type="date"
								dateStyle="short" /></td>
						<td>${dto.eIp }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/elist.so?epageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/elist.so?epageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/elist.so?epageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>
















