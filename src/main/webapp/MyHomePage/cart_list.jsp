<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "cart.CartDAO" %>
<%@ page import = "cart.CartDTO" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.io.PrintWriter" %>
<%@ page import = "common.DBConnPool" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="../MyHomePage/main.jsp" %>
	<h2> 장바구니 </h2>
	
	<%
		// id 세션값 가져오기
	
		String id=(String)session.getAttribute("id");
	    if(id==null) { 
			//로그인하지 않은 상태이면 로그인 화면으로 이동
			
	%>
	    	<script>
			alert("로그인 후 이용해주세요");
		 	location.href= "../MyHomePage/loginForm.jsp";
			</script>
			
	<% 
	    }else {
	%>
      <table border="1" width="400px">
                <tr>
                	<th>주문번호</th>
                    <th>상품명 </th>
                    <th>단가</th>
                    <th>수량</th>
                    <th>금액</th>
                    <th>삭제</th>
                </tr>
    <% 
    
		ArrayList<CartDTO> list = null;
		CartDAO dao = new CartDAO();
		list = dao.listCart(id);
		
		for (int i = 0; i < list.size(); i++) {
	%>
                <tr align="center">
                <!-- map에 있는 list출력하기 위해 forEach문을 사용해 row라는 변수에 넣는다. -->
                
                	<td>
                		이미지 자리
                	</td>
                	<td><%=list.get(i).getCart_id() %> </td>
                    <td><%=list.get(i).getPname() %> </td>
                    <td><%=list.get(i).getCart_price() %> </td>
                    <td><%= list.get(i).getAmount() %></td>
                    <td>
                    	<%=list.get(i).getMoney() %>
                    </td>
                    <td><a href="#">[삭제]</a>
							<!-- 삭제 버튼을 누르면 delete.do로 장바구니 개별 id (삭제하길원하는 장바구니 id)를 보내서 삭제한다. -->
                    </td>
                </tr>
             
        <%
		}
        
        %>
            </table>
            <button id="btnUpdate">수정</button>
            <button type="button" id="btnDelete">장바구니 비우기</button>
			<!-- //btnUpdate와 btnDelete id는 위쪽에 있는 자바스크립트가 처리한다. -->
	<%
	
	    }
	%>
</body>
</html>