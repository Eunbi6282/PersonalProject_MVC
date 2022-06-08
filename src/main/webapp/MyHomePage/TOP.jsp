<%@ page import="javax.security.auth.callback.ConfirmationCallback" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "product.ProductDAO1" %>
<%@ page import = "product.ProductDTO" %>
<%@ page import = "common.DBConnPool" %>
<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
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
        <h4>카테고리</h4>
        <ul>
            <li><a href="TOP.jsp" target="iframe1">TOP</a></li>
            <li><a href="./clothing.html" target="iframe1">BOTTOM</a></li>
            <li><a href="./music.html" target="iframe1">OUTER</a></li>
            <li><a href="./movie.html" target="iframe1">DRESS</a></li>
            <li><a href="./computer.html" target="iframe1">SHOES/BAG</a></li>
            <li><a href="./computer.html" target="iframe1">ACC</a></li>
        </ul>
    </aside>
	<div class = "main">
		  <div class="row mb-2">
		   <%
                ProductDAO1 ppDAO1 = new ProductDAO1();
                ArrayList<ProductDTO> list1 = ppDAO1.getList();
                for (int i = 0; i < list1.size(); i++) {
             %>
		    <div class="col-md-6">
		   
		      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
		        <div class="col p-4 d-flex flex-column position-static">
		          <strong class="d-inline-block mb-2 text-primary">상품이름 <%=list1.get(i).getPname() %></strong>
		          <h3 class="mb-0">Featured post</h3>
		          <div class="mb-1 text-muted">Nov 12</div>
		          <p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
		          <a href="#" class="stretched-link">Continue reading</a>
		        </div>
		        <div class="col-auto d-none d-lg-block">
		          <img class="bd-placeholder-img" width="200" height="250" src="./image/product" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
		
		        </div>
		      </div>
		    </div>
		    <%
                 }
		    %>
		  </div>
	</div>
</body>
</html>