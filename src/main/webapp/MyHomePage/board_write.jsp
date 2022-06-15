<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>

	<script type ="text/javascript">
		function validateForm (form) {
			if(form.name.value == "" || form.name.value.length == 0) {
				alert("작성자를 입력하세요");
				form.name.value.focus();
				return false;
			}
			if(form.title.value == "" || form.title.value.length == 0){
				alert("제목을 입력하세요");
				form.title.value.focus();
				return false;
			}
			if(form.content.value == "" || form.content.value.length == 0) {
				alert("내용을 입력하세요");
				form.content.focus();
				return false;
			}
			if(form.pass.value == "" || form.pass.value.length == 0) {
				alert("비밀번호를 입력하세요");
				form.pass.focus();
				return false;
			}
		}
	</script>
</head>
<body>
	
	<jsp:include page="head.jsp" flush="false"/>
	
	<%
		// 로그인한 사람만 글을 쓸 수 있도록
		String id = null;
		if(session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}
	%>
	
	<%
	
		if(id == null) {
	%>
			<script>
			alert("로그인 후 이용해주세요");
		 	location.href= "../MyHomePage/loginForm.jsp";
			</script>
			
	<% 	
		}else {
	
	%>

	<form name = "writeForm" method = "post" enctype = "multipart/form-data" action = "../MyHomePage/board_write.do" onsubmit = "return validateForm(this);" style = "margin-left:320px">
		<table border = "1" width = "70%" >
			<tr>
				<td> 작성자 : </td>
				<td>
					<input type = "text" name = "name" style = "width :150px;">
				</td>
			</tr>
			<tr>
				<td> 제목 : </td>
				<td>
					<input type = "text" name = "title" style = "width :90%;">
				</td>
			</tr>
			<tr>
				<td> 내용 : </td>
				<td>
					<textarea name = "content" style = "width :90%; height:180px"></textarea>
				</td>
			</tr>
			<tr>
				<td> 첨부파일 : </td>
				<td>
					<input type = "file" name = "ofile" />
				</td>
			</tr>
			<tr>
				<td> 비밀번호 : </td>
				<td>
					<input type = "password" name = "pass" style = "width:100px"/>
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<button type = "submit"> 작성완료 </button>
					<button type = "button" onclick = "location.href='../MyHomePage/board_list.do';">
						목록 바로가기
					</button>
				</td>
			</tr>
		</table>
	</form>
	
	<%
		}
	%>
</body>
</html>