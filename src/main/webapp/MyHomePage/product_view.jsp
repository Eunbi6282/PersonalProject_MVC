<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "product.ProductDAO" %>
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
	
		String p_id = (String)session.getAttribute("p_id");
		
		ArrayList<ProductDTO> list = null;
		ProductDAO dao = new ProductDAO();
		list = dao.productView(p_id);
		
		for (int i = 0; i < list.size(); i++) {
	%>
		
		<input type = "hidden" id = "p_id" value = "<%= list.get(i).getP_id() %>">
		<input type = "hidden" id = "id" value = "<%= list.get(i).getId() %>">
		<input type = "hidden" id = "sname" value = "<%= list.get(i).getSname() %>">
		
		<%= list.get(i).getCategory() %>
		<%= list.get(i).getPname() %>
		
		<%= list.get(i).getPrice() %>
		<%= list.get(i).getDownprice() %>
		<%= list.get(i).getDescription() %>
		<%= list.get(i).getpImg() %>
		<input type = "number" class = "amount" value = "<%= list.get(i).getAmount() %>">
		
		
	
	<%
		}
	
	%>	
		<div class="top_left">
			<a href = "cart_list.do" >
				<img class = "cart" src="../images/icon_cart.png" alt="장바구니">
			</a>
		</div>
</body>
</html>