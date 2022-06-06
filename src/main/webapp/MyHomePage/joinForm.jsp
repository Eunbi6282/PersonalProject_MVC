<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <title>회원가입 페이지</title>

    <link rel="stylesheet" href="../css/join.css"/>
    <script src="../js/jquery-1.11.0.min.js"></script>
	<script src="join.js"></script>

</head>

<body>
    <div class="wrapper">
        <div class="title"><h1 style="font-size: 21px;">회원가입</h1></div>
        <div class="id">
            <input id="id" name = "id" type="text" style = "width: 80%" placeholder="아이디를 입력해 주세요. 예)6~16자 숫자/문자" autofocus>
            <button id="checkId" style = "height: 30px">ID중복확인</button>
        </div>
        <div class="email">
            <input id="email" name = "email" type="text" placeholder="이메일을 입력해주세요 예)example@kings.com" >
        </div>
        <div class="name">
            <input id="name" name = "name" type="text" placeholder="이름을 입력해 주세요.">
        </div>
        <div class="pass">
            <input id="pass" name = "pass" type="password" placeholder="비밀번호를 입력해 주세요. <6~16자 숫자/문자>">
        </div>
        <div class="passwordCheck">
            <input id="passwordCheck" name = "passwordCheck" type="password" placeholder="비밀번호를 다시 입력해 주세요.">
        </div>
        <div class="address">
            <input id="address" name = "address" type="text" placeholder="주소를 입력해 주세요. 예)서울 특별시 00구 00동 00-00">
        </div>
        <div class="tel">
            <input id="tel" name="tel" type="text" placeholder="전화번호를 입력해 주세요. 예)010-0000-0000">  
        </div>
        <div class="line">
            <hr>
        </div>
        <div class="signUp">
            <button id="signUpButton">가입하기</button>
            	<hr><hr><hr>
            <button id="cancle">취소</button>
        </div>
    </div>
</body>

</html>