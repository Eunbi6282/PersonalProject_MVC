<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "product.ProductDAO" %>
<%@ page import = "product.ProductDTO" %>
<%@ page import = "product.ProductDTO" %>
<%@ page import = "product.ProductDTO" %>

<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	

</head>
<body>
	<%@ include file="../MyHomePage/main.jsp" %>
	
		
	<%
		String id = (String)session.getAttribute("id");
		String p_id = (String)session.getAttribute("p_id");
		
		ArrayList<ProductDTO> list = null;
		ProductDAO dao = new ProductDAO();
		list = dao.productView(p_id);
		for (int i = 0; i < list.size(); i++) {
	%>
		<input id="price" name="price" value="<%= list.get(i).getP_id() %>" type="hidden">
		<input id="price" name="price" value="<%= list.get(i).getId() %>" type="hidden">
		
		<input id="price" name="price" value="<% %>" type="hidden">
		<div class="col-auto d-none d-lg-block">
          <img class="bd-placeholder-img" width="200" height="300px" src="../images/products/<%=list.get(i).getpImg() %>.jpg" role="img" 
          aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice">
          <input type = "hidden"  id = "pimg" name = "p_img" value = "<%=list.get(i).getpImg() %>">
        </div>
		
		<%= list.get(i).getCategory() %>
		<%= list.get(i).getPname() %>
		<span style="font-size:11px;color:#111111;">
			<strong id="span_product_price_text" style="text-decoration: line-through;"><%= list.get(i).getPrice() %></strong>
			<input id="price" name="price" value="" type="hidden">
		</span>
		
		<%= list.get(i).getDownprice() %>
		<%= list.get(i).getDescription() %>
		<%= list.get(i).getpImg() %>
		
		
		
	
		
	<%
		}
	
		
	%>	
	
	<form method = "get" action ="cart_list.do?p_id=<%=p_id %>&id=<%=id %>">
		<span> 개수
			<input type = "number" class = "amount" id = "amount" name = "amount" value = "">
			<input type = "hidden"  id = "p_id" name = "p_id" value = "<%=p_id %>">
			<input type = "hidden"  id = "id" name = "id" value = "<%=id %>">
			
		</span> 
		<div>
			<button type = "submit" id = "btnList" >
			장바구니담기
			</button>
		</div>
	</form>
</body>
</html>