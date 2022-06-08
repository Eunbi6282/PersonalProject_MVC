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
	<div class = "main">
		  <div class="row mb-2" style = "margin-right:100px; height:400px">
		   <%
                ProductDAO1 ppDAO1 = new ProductDAO1();
                ArrayList<ProductDTO> list1 = ppDAO1.getList();
                for (int i = 0; i < list1.size(); i++) {
             %>
		    <div class="col-md-6">
		   
		      <div class="row g-0 border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
		        <div class="col p-4 d-flex flex-column position-static">
		          <strong class="d-inline-block mb-2 text-primary"><%=list1.get(i).getCategory() %></strong>
		          <h3 class="mb-0"><%=list1.get(i).getPname() %></h3>
		          <div class="mb-1 text-muted"><%=list1.get(i).getPrice() %></div>
		          <div class="mb-1 text-muted"><strong><%=list1.get(i).getDownprice() %></strong></div>
		          <p class="card-text mb-auto"><%=list1.get(i).getDescription() %></p>
		          <a href="#" class="stretched-link">+상세보기</a>
		        </div>
		        <div class="col-auto d-none d-lg-block">
		          <img class="bd-placeholder-img" width="200" height="300px" src="./images/products/<%=list1.get(i).getpImg() %>.jpg" role="img" 
		          aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
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