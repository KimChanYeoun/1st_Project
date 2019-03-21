<%@page import="com.tj.ex.dao.NoticeDao"%>
<%@page import="com.tj.ex.dao.BoardDao"%>
<%@page import="com.tj.ex.dao.GalleryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for (int i = 0; i < 55; i++) {
			NoticeDao dao = new NoticeDao();
			dao.nwrite("admin", "제목" + i, i + "번째 본문");
		}
		response.sendRedirect("../list.ao");
	%>
</body>
</html>