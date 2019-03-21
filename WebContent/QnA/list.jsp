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
		var qNum = Number($(this).children(0).eq(0).text());
		var qIndent = Number($(this).children(0).eq(0).children(0).eq(0).val());
			if (qIndent==0) {
				location.href = '${conPath}/qcontent_view.ao?qNum='+qNum+'&qpageNum=${qpageNum}';
			}else if(qIndent!=0){
				location.href = '${conPath}/qrcontent_view.ao?qNum='+qNum+'&qpageNum=${qpageNum}';
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
						<a href="${conPath }/qwrite_view.ao">글쓰기</a>
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
				<th>조회수</th>
				<th>날짜</th>
				<th>ip</th>
			</tr>
			<c:if test="${totCnt==0 }">
				<tr>
					<td colspan="6">글이 없습니다</td>
				</tr>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<c:forEach items="${qlist }" var="dto">
					<tr>
						<td>${dto.qNum }<input type="hidden" value="${dto.qIndent }"></td>
							<td>${dto.cId }${dto.aId }</td>
						<td class="left"><p><c:forEach var="i" begin="1"
								end="${dto.qIndent }">
								<c:if test="${i==dto.qIndent }">└─</c:if>
								<c:if test="${i!=dto.qIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach>
							<c:if test="${dto.qIndent == 0 }">
							<a href="${conPath }/qcontent_view.ao?qNum=${dto.qNum}&qpageNum=${qpageNum}">${dto.qTitle }</a>
							</c:if>
							<c:if test="${dto.qIndent != 0}">
							<a href="${conPath }/qrcontent_view.ao?qNum=${dto.qNum}&qpageNum=${qpageNum}">${dto.qTitle }</a>
							</c:if>
							</p>
						</td>
						<td>${dto.qHit }</td>
						<td><fmt:formatDate value="${dto.qDate }" type="date"
								dateStyle="short" /></td>
						<td>${dto.qIp }</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
		<div class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/qlist.ao?qpageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
			<c:forEach var="i" begin="${startPage }" end="${endPage }">
				<c:if test="${i == pageNum }">
					<b> [ ${i } ] </b>
				</c:if>
				<c:if test="${i != pageNum }">
				[ <a href="${conPath }/qlist.ao?qpageNum=${i}"> ${i } </a> ]
			</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/qlist.ao?qpageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
		</div>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>
















