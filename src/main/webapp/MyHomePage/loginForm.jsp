<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<script src="../js/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="../css/login.css"/>
<script src="login.js"></script>
</head>
<body>
	<div id="status">
		<div class = "wrapper">
			<div class="title"><h1 style="font-size: 21px;">로그인</h1></div>
			<div class = "loginWrap">
				<div class="formBox">
					<div class="formlogin">
						<div>
							<span class="">아이디 입력</span>
							<input id="id" name="id" class="id" type="text">        
						</div>
						<div>
							<span class="">비밀번호 입력</span>
							<input id="pass" name="pass" type="password" placeholder="">
							<span class="show_pw"></span>
						</div>
					</div>
					<div class="option">
						<span class="auto">
							<input type = "checkbox" name = "save_check" value = "Y">
							<label for="use_login_keeping0">
								로그인 상태 유지
							</label>
						</span>
					</div>
					<div class="LoginArea">
						<button id = "login" type="button" class="btnSubmit">로그인</button>
					</div>
					<!--
					<div class="LoginArea">
						<a href="/member/id/find_id.html" class="btnLogin SMS_login_id SMSloginID_btnTD"><b class="SMS_icon"></b>아이디 찾기</a>
						<a href="/member/passwd/find_passwd_info.html" class="btnLogin SMS_login_pw SMSloginPW_btnTD"><b class="SMS_icon"></b>비밀번호 찾기</a>	
						<a href="/member/login.html?noMemberOrder&amp;returnUrl=%2Fmyshop%2Forder%2Flist.html" class="btnLogin SMS_login_id_join SMSjoin_btnTD"><b class="SMS_icon"></b>비회원 주문조회</a>	
					</div>
					-->
					<div class="joinArea">
						<a href="joinForm.jsp" class="btnJoin">회원가입 후 혜택받기</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	
</body>
</html>