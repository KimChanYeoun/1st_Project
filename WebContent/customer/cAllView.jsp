<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {font-size: 9pt; }
#content {height: 400px;}
#content table{width:70%; margin: 30px auto;}
#content td { text-align: center; padding: 20px;}
#content caption { font-size: 1.3em; text-align: center;}
#content div { text-align: center; }
#content span.currentPage{color:red; font-weight: bold;}
#content a {text-decoration: none;}
</style>
<style>
</style>
</head>
<body>
<c:if test="${not empty adminLoginResult }">
	<script>
		alert('${adminLoginResult}');
	</script>
</c:if>
<c:if test="${not empty adminLoginError }">
	<script>
		history.back();
	</script>
</c:if>
<jsp:include page="../main/projectHeader.jsp"/>
<div id="content">
	<table>
		<caption>전체회원보기</caption>	
		<tr>
			<c:forEach var="customer" items="${cAllView }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
				<td>
				<a href="${conPath }/cinform.do?cId=${customer.cId }&cpageNum=${param.cpageNum }'">
					<img src="${conPath }/mPhotoUp/${customer.cPhoto}" width="150"><br>
					${customer.cName}<br>
					(${customer.cId})	
					</a>	
				</td>
			</c:forEach>
		</tr>
	</table>
	<div class="paging">
		<a href="${conPath }/allView.do?cpageNum=1"> &lt;&lt; </a>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage>BLOCKSIZE }">
			<a href="${conPath }/allView.do?cpageNum=${startPage-1 }"> &lt; </a>
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage<=BLOCKSIZE }">
			&lt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i==cpageNum }">
				<span class="currentPage">[ ${i} ]</span>
			</c:if>
			<c:if test="${i!=cpageNum }">
				[ <a href="${conPath }/allView.do?cpageNum=${i }">${i }</a> ]
			</c:if>
		</c:forEach>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage<pageCnt }">
			<a href="${conPath }/allView.do?cpageNum=${endPage+1 }"> &gt; </a> 
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage==pageCnt }">
			&gt; 
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<a href="${conPath }/allView.do?cpageNum=${pageCnt}"> &gt;&gt; </a>
	</div>
</div>
<jsp:include page="../main/projectFooter.jsp"/>
</body>
</html>