<%@page import="cart.CartDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
         
   
   <%
      String p_id = (String)request.getParameter("p_id");
      out.println(p_id);
      
      String pname = (String)request.getParameter("pname");
      out.println(pname);
      
      ArrayList<CartDAO> dao = new ArrayList<CartDAO>();
   %>  
   <table>
   	<tr>
   		<td><input type="number" name="amount" style="width:30px; value="<fmt:formatNumber value="${dao.amount}" pattern="#,###,###" />">
                    <!-- 물건의 개수 (amount)를 fmt태그를 사용해서 패턴의 형식에 맞춰서 문자열로 변환함 -->
                    <!--1,000 / 5,000 등등~  -->
                    
            <input type="hidden" name="cart_id" value="${row.cart_id}">
                            
                                
   </td>
   	
   	
   	</tr>
   
   </table>
   
</body>
</html>