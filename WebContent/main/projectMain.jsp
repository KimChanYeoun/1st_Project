<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/section-top.css" rel="stylesheet"
	type="text/css" />
<link href="${conPath }/css/section-side.css" rel="stylesheet"
	type="text/css" />
<link href="${conPath }/css/section-middle.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<jsp:include page="../main/projectHeader.jsp" />
	<section>
		<div id="sectop">
			<div class="newsdate">
				<div class="notice">
					<table>
						<tr>
							<th colspan="2">공지사항</th>
							<td><a href="${conPath }/list.ao">더보기</a></td>
						</tr>
						<c:if test="${totCnt==0 }">
							<tr>
								<td colspan="6">글이 없습니다</td>
							</tr>
						</c:if>
						<c:if test="${totCnt!=0 }">
							<c:forEach items="${nlist }" var="dto">
								<tr>
									<td>${dto.nNum }</td>
									<td class="left"><p>
											<a
												href="${conPath }/content_view.ao?nNum=${dto.nNum}&npageNum=${npageNum}">${dto.nTitle }</a>
										</p></td>
									<td><fmt:formatDate value="${dto.nDate }" type="date"
											dateStyle="short" /></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<div class="upgrade">
					<table>
						<tr>
							<th colspan="2">업데이트</th>
							<td><a href="${conPath }/ulist.ao">더보기</a></td>
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
									<td class="left"><p>
											<a
												href="${conPath }/ucontent_view.ao?uNum=${dto.uNum}&upageNum=${upageNum}">${dto.uTitle }</a>
										</p></td>
									<td><fmt:formatDate value="${dto.uDate }" type="date"
											dateStyle="short" /></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
			</div>
			<div class="shop">
				<table>
				<tr>
				<td>금주추천게임
				</td>
				</tr>
					<tr>
						<td><a href="#"> <img
								src="${conPath }/img/bomber040806_01.jpg"
								alt="광고배너" /><br>봄버맨
						</a></td>
						<td><a href="#"> <img
								src="${conPath }/img/bobble_resz001.png" alt="광고배너" /><br>보글보글
						</a></td>
						<td><a href="#"> <img
								src="${conPath }/img/다운로드.jpg" alt="광고배너" /><br>에어라이더
						</a></td>
					</tr>
				</table>
			</div>
		</div>
		<div id="secside">
			<div class="login">
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
				<c:if test="${empty customer and empty admin}">
					<form action="${conPath}/login.do" method="post">
						<table>
							<tr>
								<th colspan="2"><a href="${conPath}/main.do"> <img
										src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Flag_of_South_Korea.svg/225px-Flag_of_South_Korea.svg.png"
										alt="사이트로고" />KOREA
								</a></th>
							</tr>
							<tr>
								<th colspan="2"><input type="text" name="cId"
									placeholder="아이디" height="20px" /></th>
							</tr>
							<tr>
								<th colspan="2"><input type="password" name="cPw"
									placeholder="비밀번호" /></th>
							</tr>
							<tr>
								<th><input type="submit" name="login" value="로그인" /></th>
								<th><input type="button" name="join" value="회원가입"
									onclick="location='${conPath}/joinView.do'" /></th>
							</tr>
						</table>
					</form>
				</c:if>
				<c:if test="${not empty customer or not empty admin}">
					<form action="${conPath}/logout.do" method="post">
						<table id="my">
							<tr>
								<td>${customer.cId }${admin.aId }님환영합니다</td>
							</tr>
							<tr>
								<th>게시판 쓴 글</th>
								<td>${bcontent_view }개&nbsp;</td>
								<th>갤러리 쓴 글</th>
								<td>${gcontent_view }개&nbsp;</td>
							</tr>
							<tr>
								<th>에러게시판 쓴 글</th>
								<td>${econtent_view }개&nbsp;</td>
								<th>QnA 쓴 글</th>
								<td>${qcontent_view }개&nbsp;</td>
							</tr>
							<tr>
								<th colspan="4"><input type="submit" name="logout"
									value="로그아웃" /> <input type="button" name="myinform"
									value="내정보보기" onclick="location='${conPath}/myinform.do'" /></th>
							</tr>
						</table>
					</form>
				</c:if>
			</div>
			<div class="minibanner">
				<a href="#"> <img src="${conPath }/img/Preview.x.jpg" alt="광고배너" />
				</a>
			</div>
		</div>
		<div id="secmiddle">
			<table>
				<tr>
					<th colspan="4">갤러리 게시판</th>
					<td><a href="${conPath }/list.so">더보기</a></td>
				</tr>
				<c:if test="${totCnt==0 }">
					<tr>
						<td colspan="6">글이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${glist }" var="dto">
						<td><p>
								<a
									href="${conPath }/content_view.so?gNum=${dto.gNum}&gpageNum=${gpageNum }">
									<img src="${conPath }/fileboardUp/${dto.gfileName}"><br>
									${dto.gTitle}<br> ${dto.cId}
								</a>
							</p></td>
					</c:forEach>
				</c:if>
			</table>
		</div>
		<div id="secbottom"></div>
	</section>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>