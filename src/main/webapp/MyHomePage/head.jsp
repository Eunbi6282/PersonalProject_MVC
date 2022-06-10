<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"/>
<link rel="stylesheet" href="../css/common.css">
<link rel="stylesheet" href="../css/laneige01.css">
<link rel="stylesheet" href="../css/category.css">
<title>Main Page</title>
  <!--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">  -->
</head>
<body>
 <%
	//로그인 한사람이면 userID라는 변수에 해당 아이디가 담기고 그렇지 않으면 null값
		String id = null;
		if (session.getAttribute("id") != null) {
			id = (String) session.getAttribute("id");
		}
  %>
  
  <%
	//로그인 안된경우
	if(id == null) {
  %>
     <div class="top_right" id = "main_auth">
         <a href="joinForm.jsp">
             <span class="title">회원가입</span>
         </a>
         <a href="loginForm.jsp">
             <span class="title">로그인</span>
         </a>  
    </div>
   <%
		//로그인 된경우
	}else {
	%>
		<div class="top_right">
	         <a href = "logout.jsp">
	             <span class="title" id = "logout">로그아웃</span>
	         </a>
	         <a href="modify.jsp">
	             <span class="title">회원정보수정</span>
	         </a>  
	    </div>
  	<%
		}
	%>
    <div class="_3aNsjos9K5">
		<h1 class = "bekkwdip3W"><a href="main.jsp">TTEI</a></h1>
		<div class="top_left">
			<div class="top_left">
				<a href = "/" >
					<img class = "cart" src="../images/icon_cart.png" alt="장바구니">
				</a>
			</div>
		</div>
		<div class="_1YWP8VnWTb">
			<div class="_1L46Yq05VR">
				<input type="text" title="검색어 입력" placeholder="검색어를 입력해보세요" value="" class="_38DpYZHzTy">
				<button class="_1H_iy_tmiI N=a:GNB.search">
					<span class="Y4Wg6LWa6z">
						<span class="blind">검색하기</span>
					</span>
				</button>
			</div>
		</div>
	</div>

	<nav>
        <ul>
            <li><a href="main.jsp">Home</a></li>
            <li><a href="index.html">About Us</a></li>
            <li><a href="index.html">News</a></li>
            <li><a href="index.html">My Account</a></li>
            <li><a href="board_list.do">BOARD</a></li>
        </ul>
    </nav>
    
</body>
</html>