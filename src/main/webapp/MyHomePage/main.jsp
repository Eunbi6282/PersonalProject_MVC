<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/laneige01.css">
<link rel="stylesheet" href="../css/category.css">
<title>Main Page</title>
  <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">  -->
</head>
<body>
	
	<jsp:include page="head.jsp" flush="false"/>
	<% 
	 	String uid = "";
	 	uid = (String)session.getAttribute("id");
	 %>
	 
	 <%= uid %> 님 환영합니다.
	
	
</body>
</html>