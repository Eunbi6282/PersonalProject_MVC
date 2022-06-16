<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "product.ProductDAO" %>
<%@ page import = "product.ProductDTO" %>

<%@ page import = "java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/product_view.css">
<title>Insert title here</title>
	<script language="javaScript">

	function check_onclick(){
		theForm=document.frm1;
		
		if(theForm.amount.value==""){
			alert("장바구니에 담을 상품 개수를 입력해주세요");
			history.back();
		}
		
	}

	</script>

</head>
<body>
	<%@ include file="../MyHomePage/main.jsp" %>
	
		
	<%
		String id = (String)session.getAttribute("id");
		String p_id = (String)session.getAttribute("p_id");
		String pImg = request.getParameter("pImg");
		
		ArrayList<ProductDTO> list = null;
		ProductDAO dao = new ProductDAO();
		list = dao.productView(p_id);
		for (int i = 0; i < list.size(); i++) {
	%>
	<div id="mun_information" class="xans-element- xans-product xans-product-detaildesign">
		<div class="col-auto d-none d-lg-block" style = "float:left; margin-right:10px;">
          <img class="bd-placeholder-img" width="200" height="300px" src="../images/products/<%=list.get(i).getpImg() %>.jpg" role="img" 
          aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice">
        </div>
        
    	<ul style = "float:left;">
		    <li class="mun-detail-list  xans-record-">
		        <div class="mun-detail-title"><span style="font-size:15px;color:#111111;"><%= list.get(i).getCategory() %></span></div>
		        <div class="mun-detail-desc"><span style="font-size:15px;color:#111111;"><%= list.get(i).getPname() %></span></div>
		    </li>
		    <li class="mun-detail-list  xans-record-">
		        <div class="mun-detail-title"><span style="font-size:11px;color:#111111;">상품요약정보</span></div>
		        <div class="mun-detail-desc"><span style="font-size:11px;color:#111111;"><%= list.get(i).getDescription() %></span></div>
		    </li>
		    <li class="mun-detail-list  xans-record-">
		        <div class="mun-detail-title"><span style="font-size:11px;color:#111111;">판매가</span></div>
		        <div class="mun-detail-desc"><span style="font-size:11px;color:#111111;"><strong id="span_product_price_text" style="text-decoration: line-through;"><%= list.get(i).getPrice() %></strong><input id="product_price" name="product_price" value="" type="hidden"></span></div>
		    </li>
		    <li class="mun-detail-list  xans-record-">
		        <div class="mun-detail-title"><span style="font-size:11px;color:#111111;">할인판매가</span></div>
		        <div class="mun-detail-desc"><span><span style="font-size:11px;color:#111111;"><span id="span_product_price_sale"><%= list.get(i).getDownprice() %> </span></span></span></div>
		    </li>
		    <li class="mun-detail-list  xans-record-">
		        <div class="mun-detail-title"><span style="font-size:11px;color:#b89977;">할인 기간</span></div>
		        <div class="mun-detail-desc"><span style="font-size:11px;color:#b89977;"><span style="color: rgb(77, 117, 218); display: none;"><span class="product_promotion_date">남은시간 0일 07:47:52</span> (7,900원 할인)</span><span class="period">2022-06-13 00:00 ~ 2022-06-16 00:00</span></span></div>
		    </li>
   		 </ul>
	</div>
	
	
		<input id="price" name="price" value="<%= list.get(i).getP_id() %>" type="hidden">
		<input id="price" name="price" value="<%= list.get(i).getId() %>" type="hidden">
		
	<%
		}
		
		try {		
	%>	
	
	<form name = "frm1" method = "get" action ="cart_list.do?p_id=<%=p_id %>&id=<%=id %>">
		<span style="font-size:11px;color:#111111;"> 
			개수
			<input type = "number" class = "amount" size = "14px" id = "amount" name = "amount" value = "" onclick = "">
			<input type = "hidden"  id = "p_id" name = "p_id" value = "<%=p_id %>">
			<input type = "hidden"  id = "id" name = "id" value = "<%=id %>">
			<input type = "hidden"  id = "pImg" name = "pImg" value = "<%=pImg %>">
			
		</span> 
		<div>
			<button type = "submit" id = "btnList" onclick = "check_onclick(this)">
				<strong style="font-size:11px;color:#111111;"> + 장바구니</strong>
			</button>
		</div>
	</form>
	
	<%
		}
		catch(Exception e) {}
	
	%>
</body>
</html>