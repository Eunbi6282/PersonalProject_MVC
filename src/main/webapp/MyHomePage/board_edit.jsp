<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
</head>
<body>
	<h2> 수정하기 </h2>
	 <!-- 폼에서 파일을 업로드 할 떄 => 2가지 규칙
	 	1. method가 반드시 post방식이어야 함
	 	2. enctype = multipart/form-data로 설정이 되어야 한다.
	 		form의 모든 변수느 request객체가 아닌 라이브러리에서 지정한 메서드에서 변수의 값을 받는다. 
	  -->
	  
	  <form name = "writeForm" method = "post" enctype = "multipart/form-data" action = "../MyHomePage/board_edit.do" onsubmit = "return validateForm(this);">
	  	<!-- 넘어온 변수값을 다음 페이지로 전송하기 위한 hidden -->
	  	<input type = "hidden" name = "num" value = "${dto.num }">
	  	<input type = "hidden" name = "prevOfile" value = "${dto.ofile }">
	  	<input type = "hidden" name = "prevSfile" value = "${dto.sfile }">
	  	
	  	<table border = "1" width = "90%">
	  		<tr>
				<td> 작성자</td>
				<td>
					<input type = "text" name = "name" style = "width:150px;" value = "${dto.name }" readonly/> <!--  dto의 name컬럼을 가져와서 찍어준다. -->
				</td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td>
					<input type = "text" name = "title" style = "width:90%;" value = "${dto.title }" />
				</td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td>
					<textarea name = "content" style ="width: 90%; height: 100px;"> ${dto.content } </textarea>
				</td>
			</tr>
			<tr>
				<td> 첨부파일 </td>
				<td>
					<input type = "file" name = "ofile" />
				</td>
			</tr>
			<tr>
				<td colspan = "2" align = "center">
					<button type = "submit"> 작성 완료</button>
					<button type = "reset"> RESET </button>
					<button type = "button" onClick = "location.href = '../MyHomePage/board_list.do';"> 목록 바로가기 </button>
				</td>
			</tr>
	  	</table>
	  </form>
</body>
</html>