<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/style.css" rel="stylesheet">
<style>
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(
	function() {
		$('#cIdConfirm').click(function() {
			var cId = $('input[name="cId"]').val();
			$.ajax({
				url : '${conPath}/cIdConfirm.do',
				type : 'get',
				dataType : 'html',
				data : "cId=" + cId,
				success : function(data) {
					$('#idConfirmMsg').html("<b>" + data + "</b>");
				}
			});
		});
		$('input[name="pwChk"]').keyup(
			function() {
				if ($('input[name="cPw"]').val() == $(
					'input[name="pwChk"]').val()) {
					    $('#pwChkMsg').html('<b>비밀번호 일치</b>');
					} else {
						$('#pwChkMsg').html('<b>비밀번호 불일치</b>');
					}
				});
			});
	function joinInfoChk() {
		if (join_frm.cEmail.value.length != 0
				&& (join_frm.cEmail.value.indexOf('@') == -1
						|| join_frm.cEmail.value.indexOf('@') == 0
						|| join_frm.cEmail.value.indexOf('@') == join_frm.cEmail.value.length - 1 
						|| join_frm.cEmail.value.indexOf('@') != join_frm.cEmail.value.lastIndexOf('@'))) {
			alert('메일 형식을 확인하세요');
			join_frm.cEmail.focus();
			return false;
		}
		return true;
	}
</script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd',
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
						'8월', '9월', '10월', '11월', '12월' ],
			showMonthAfterYear : true,
			yearSuffix : '년',
			showOtherMonths : true,
			dayNamesMin : [ '월', '화', '수', '목', '금', '토', '일' ],
			maxDate : -1,
			onSelect : function(dateText) {
				var today = new Date();
				var y = today.getFullYear();
				var m = today.getMonth() + 1;
				if (('' + m).length == 1) {
					m = '0' + m; 
				}
				var d = today.getDate();
				if (('' + d).length == 1) {
					d = '0' + d;
				}
				var todayStr = y + '-' + m + '-' + d;
			}
		});//datepicker 함수 끝
	});
</script>
</head>
<body>
	<div class="logo">
			<a href="${conPath}/main.do"> <img
				src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Flag_of_South_Korea.svg/225px-Flag_of_South_Korea.svg.png"
				alt="사이트로고" />
			</a>
		</div>
	<div id="content">
		<form action="${conPath}/join.do" method="post"
			enctype="multipart/form-data" name="join_frm"
			onsubmit="return joinInfoChk()">
			<table>
				<caption>회원가입</caption>
				<tr>
					<th><label for="cId">아이디</label></th>
					<td><input type="text" id="cId" name="cId" required="required">
						<input type="button" id="cIdConfirm" value="중복체크"><br>
						<div id="idConfirmMsg">&nbsp; &nbsp;</div></td>
				</tr>
				<tr>
					<th><label for="cPw">비밀번호</label></th>
					<td><input type="password" id="cPw" name="cPw" required="required"></td>
				</tr>
				<tr>
					<th><label for="pwChk">비번확인</label></th>
					<td><input type="password" id="pwChk" name="pwChk" required="required"><br>
						<div id="pwChkMsg">&nbsp; &nbsp; &nbsp;</div></td>
				</tr>
				<tr>
					<th><label for="cName">이름</label></th>
					<td><input type="text" id="cName" name="cName" required="required"></td>
				</tr>
				<tr>
					<th><label for="cTel">전화번호</label></th>
					<td><input type="text" id="cTel" name="cTel" required="required"
						placeholder="'-'는 제외한 숫자만 입력해주세요"></td>
				</tr>
				<tr>
					<th><label for="cGender">성별</label></th>
					<td><input type="radio" id="cGender" name="cGender" value="남"
						required="required" checked="checked">남 &nbsp; <input
						type="radio" name="cGender" value="여" required="required">여</td>
				</tr>
				<tr>
					<th><label for="cEmail">메일주소</label></th>
					<td><input type="text" id="cEmail" name="cEmail" required="required"></td>
				</tr>
				<tr>
					<th><label for="cPhoto">프로필사진</label></th>
					<td><input type="file" id="cPhoto" name="cPhoto"></td>
				</tr>
				<tr>
					<th><label for="datepicker">생년월일</label></th>
					<td><input type="text" name="cBirth" id="datepicker" required="required"></td>
				</tr>
				<tr>
					<th><label for="cAddr">주소</label></th>
					<td><input type="text" id="cAddr" name="cAddr" required="required"></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="회원가입"> 
					<input type="button" value="로그인"
						onclick="location='${conPath}/loginView.do'"></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/projectFooter.jsp" />
</body>
</html>