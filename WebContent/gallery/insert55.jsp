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
		for(int i=0 ; i<55 ; i++){
		GalleryDao dao = new GalleryDao();
		dao.gWrite("kcn1201", "제목"+i, i+"번째 본문", null, "192.168.10.151");
			}
			response.sendRedirect("../list.so");
	%>
</body>
</html>