<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "logon.LogonDAO" %>
<%@ page import = "logon.LogonDTO" %>



<% request.setCharacterEncoding("utf-8");%>
<%-- 7~9라인: 수정된 정보를 가지고 데이터저장빈객체를 생성--%>
<jsp:useBean id="member" class="logon.LogonDTO">
    <jsp:setProperty name="member" property="*" />
</jsp:useBean>

<%  
	LogonDAO manager = LogonDAO.getInstance();
  //회원정보 수정 처리 메소드 호출 후 수정 상황값 반환
  int check = manager.updateMember(member);
    
  out.println(check);
 %>