<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon.LogonDAO" %>
<%@ page import = "logon.LogonDTO" %>

<meta name="viewport" content="width=device-width,initial-scale=1.0"/>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="modify.js"></script>

<% request.setCharacterEncoding("UTF-8"); %>

<% String id = (String) session.getAttribute("id");
	String pass = request.getParameter("pass");
	
	LogonDAO manager = LogonDAO.getInstance();
	LogonDTO m = manager.getMember(id, pass);
	
	try{
%>
	<jsp:include page="head.jsp" flush="false"/>
	
	<div id="regForm" class="box" align = "center">
	   <ul>
	      <li><p class="center">회원 정보 수정
	      <li><label for="id">아이디</label>
	          <input id="id" name="id" type="email" size="20" 
	           maxlength="50" value="<%=id%>" readonly disabled>
	      <li><label for="passwd">비밀번호</label>
	          <input id="pass" name="pass" type="password" 
	           size="20" placeholder="6~16자 숫자/문자" maxlength="16">
	      <li><label for="name">이름</label>
	          <input id="name" name="name" type="text" 
	           size="20" maxlength="10" value="<%=m.getName()%>">
	      <li><label for="address">주소</label>
	          <input id="address" name="address" type="text" 
	           size="30" maxlength="50" value="<%=m.getAddress()%>">
	      <li><label for="tel">전화번호</label>
	          <input id="tel" name="tel" type="tel" 
	           size="20" maxlength="20" value="<%=m.getTel()%>">
	      <li class="label2"><button id="modifyProcess">수정</button>
	          <button id="cancle">취소</button>
	   </ul>
	</div>
<% } catch(Exception e) {} %>