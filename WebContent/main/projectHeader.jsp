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

<link href="https://fonts.googleapis.com/css?family=Nanum+Brush+Script"
	rel="stylesheet">
<link href="${conPath }/css/header-top.css" rel="stylesheet"
	type="text/css" />
<link href="${conPath }/css/header-middle.css" rel="stylesheet"
	type="text/css" />
<link href="${conPath }/css/header-bottom.css" rel="stylesheet"
	type="text/css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.5/TweenMax.min.js"></script>
<script>
	var current = 0;
	var max = 0;
	var container;
	var interval;
	var interval;
	function animate() {
		var moveX = current * 1600;
		TweenMax.to(container, 0.8, {
			marginLeft : -moveX,
			ease : Expo.easeOut
		});
	}
	function keydown(e) {
		if (e.which == 39) {
			alert('next');
			$("button.next").trigger('click');
		} else if (e.which == 37) {
			alert('prev');
			$("button.prev").trigger('click');
		}
	}
	$(document).ready(function() {
		container = $(".slide ul");
		max = container.children().length;
		$("button.prev").click(function() {
			current--;
			if (current < 0)
				current = max - 1;
			animate();
		});
		$("button.next").click(function() {
			current++;
			if (current > max - 1)
				current = 0;
			animate();
		});
		jQuery(window).on("keydown", keydown);

		interval = setInterval(function() {
			$("button.next").trigger('click');
		}, 3000);
	});
</script>
</head>
<body>
	<header>
		<div id="headtop">
			<div class="logo">
				<a href="${conPath}/main.do"> <img
					src="https://upload.wikimedia.org/wikipedia/commons/thumb/0/09/Flag_of_South_Korea.svg/225px-Flag_of_South_Korea.svg.png"
					alt="사이트로고" />KOREA
				</a>
			</div>
			<c:if test="${empty customer and empty admin}">
				<div class="topmenu">
					<ul>
						<li><a href="${conPath }/joinView.do">join</a></li>
						<li><a href="${conPath }/loginView.do">login</a></li>
					</ul>
				</div>
			</c:if>
			<c:if test="${not empty customer and empty admin}">
				<div class="topmenu">
					<ul>
						<li><a>${customer.cName }님 &nbsp; ▶</a></li>
						<li><a href="${conPath }/myinform.do">정보보기</a></li>
						<li><a href="${conPath }/logout.do">logout</a></li>
					</ul>
				</div>
			</c:if>
			<c:if test="${empty customer and not empty admin}">
				<div class="topmenu">
					<ul>
						<li><a>${admin.aName }님 &nbsp; ▶</a></li>
						<li><a href="${conPath }/allView.do">회원보기</a></li>
						<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
					</ul>
				</div>
			</c:if>
		</div>
		<div id="headmiddle">
			<div class="midmenu">
				<ul>
					<li><a href="#">새소식</a>
						<ol class="submenu">
							<li><a href="${conPath }/list.ao">공지사항</a></li>
							<li><a href="${conPath }/ulist.ao">업데이트</a></li>
							<li><a href="#"></a></li>
						</ol></li>
						<li><a href="#">게임정보</a>
						<ol class="submenu">
							<li><a href="https://game.naver.com/">PC게임</a></li>
							<li><a href="http://www.netmarble.net/mobile">모바일게임</a></li>
							<li><a href="#"></a></li>
						</ol></li>
					<li><a href="#">커뮤니티</a>
						<ol class="submenu">
							<li><a href="${conPath }/list.do">자유게시판</a></li>
							<li><a href="${conPath }/list.so">갤러리</a></li>
							<li><a href="#"></a></li>
						</ol></li>
					<li><a href="#">고객지원</a>
						<ol class="submenu">
							<li><a href="${conPath }/elist.so">오류및버그신고</a></li>
							<li><a href="${conPath }/qlist.ao">QnA</a></li>
							<li><a href="#"></a></li>
						</ol></li>
				</ul>
			</div>
		</div>
		<div id="headbottom">
			<div class="slide">
				<button class="prev" type="button">
					<img src="${conPath }/img/prev.png" alt="" />
				</button>
				<ul>
					<li><img src="${conPath }/img/img1.jpg" alt="" /></li>
					<li><img src="${conPath }/img/img2.jpg" alt="" /></li>
					<li><img src="${conPath }/img/img3.jpg" alt="" /></li>
					<li><img src="${conPath }/img/img4.jpg" alt="" /></li>
					<li><img src="${conPath }/img/img5.jpg" alt="" /></li>
				</ul>
				<button class="next" type="button">
					<img src="${conPath }/img/next.png" alt="">
				</button>
			</div>
		</div>
	</header>
</body>
</html>