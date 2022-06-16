<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="../js/jquery-1.11.0.min.js"></script>
<script src="modify.js"></script>
</head>
<body>

	<jsp:include page="head.jsp" flush="false"/>
		<form>
			<div id = "status" align = "center">
				<ul>
					<li>
						<label for = "pass">비밀번호</label>
						<input id = "pass" name = "pass" type = "password" size = "20" placehoder = "6~16자 숫자/문자" maxlength = "16">
					</li>
					<li class="label2">
		            <button type = "submit" id="modify">정보수정</button>
		            <button id="delete">탈퇴</button>
		            </li>
				</ul>
			</div>
		</form>
</body>
</html>
