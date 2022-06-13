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
	<aside id="left">
        <ul>
            <li><a href="top_view.jsp" target="iframe1">TOP</a></li>
            <li><a href="./clothing.html" target="iframe1">BOTTOM</a></li>
            <li><a href="./music.html" target="iframe1">OUTER</a></li>
            <li><a href="./movie.html" target="iframe1">DRESS</a></li>
            <li><a href="./computer.html" target="iframe1">SHOES/BAG</a></li>
            <li><a href="./computer.html" target="iframe1">ACC</a></li>
        </ul>
    </aside>
	
</body>
</html>