<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

	<script>
		function validateForm(form){
			if(form.id.value == "") {
				alert("비밀번호를 입력하세요");
				form.id.focus();
				return false;
			} 
		}
	</script>
<meta charset="UTF-8">
<title> 비밀번호 검증 </title>
</head>
<body>
	
	<jsp:include page="head.jsp" flush="false"/>
	<h2> 비밀번호를 입력하세요 </h2>
	
	<form name ="WriteForm" method = "post" action = "../MyHomePage/pass.do" onsubmit = "return validateForm(this);">
		<input type = "hidden" name = "num" value = "${param.num}" />
		<input type = "hidden" name = "mode" value = "${param.mode }" />
		
		<table border = "1" width = "90%">
			<tr>
				<td> 비밀번호 : </td>
				<td>
					<input type = "password" name = "pass" style = "width : 100px;">
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<button type = "submit">검증하기</button>
					<button type = "reset">Reset</button>
					<button type = "button" onclick = "location href = '../MyHomePage/board_list.do';">목록</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>