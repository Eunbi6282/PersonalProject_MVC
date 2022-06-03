<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <title>회원가입 페이지</title>

    <link rel="stylesheet" href="../css/join.css"/>
    <script src="signup.js"></script>

</head>

<body>
	<form>
    <div class="wrapper">
        <div class="title"><h1 style="font-size: 21px;">회원가입</h1></div>
        <div class="id">
            <input id="id" name = "id" type="text" placeholder="아이디를 입력해 주세요." autofocus>
            <button id="checkId">ID중복확인</button>
        </div>
        <div class="email">
            <input id="email" name = "email" type="text" placeholder="이메일을 입력해주세요 예)example@kings.com" >
        </div>
        <div class="name">
            <input id="name" name = "name" type="text" placeholder="이름을 입력해 주세요.">
        </div>
        <div class="password">
            <input id="password" name = "password" type="password" placeholder="비밀번호를 입력해 주세요.">
        </div>
        <div class="passwordCheck">
            <input id="passwordCheck" name = "password" type="password" placeholder="비밀번호를 다시 입력해 주세요.">
        </div>
        <div class="address">
            <input id="address" type="text" name = "address" placeholder="주소를 입력해 주세요. 예)서울 특별시 00구 00동 00-00">
        </div>
        <div class="tel">
            <input id="tel" type="tel" name="tel">  
        </div>
        <div class="line">
            <hr>
        </div>
        <div class="signUp">
            <button id="signUpButton" disabled onclick="signUpCheck()">가입하기</button>
        </div>
    </div>
   </form>

</body>

</html>