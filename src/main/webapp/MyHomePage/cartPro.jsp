<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "cart.CartDAO" %>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="../js/jquery-1.11.0.min.js"></script>

<% request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="cart" class="cart.CartDTO">
    <jsp:setProperty name="cart" property="*" />
</jsp:useBean>

<%
	//p_img 세션 설정
	HttpSession session = req.getSession(true);
	session.setAttribute("pImg", pImg);
	
	
	CartDAO manager = CartDAO.getInstance();
	manager.insert(cart);
	
	
%>