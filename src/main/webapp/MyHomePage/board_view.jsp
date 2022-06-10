<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="head.jsp" flush="false"/>
	
	<h2> 상세보기</h2>
	
	<table border = "1" width = "90%">
		<colgroup>
			<col width = "15%" /> <col width = "35%" />
			<col width = "15%"/> <col width = "*"/>
		</colgroup>
	
	<!--  게시글 정보 출력 -->
	<tr>
		<td>번호</td> <td> ${dto.num } </td>
		<td>작성자</td><td> ${dto.name} </td>
	</tr>
	
	<tr>
		<td>작성일</td> <td> ${dto.postdate } </td>
		<td>조회수</td><td> ${dto.visitcount} </td>
	</tr>
	
	<tr>
		<td> 내용</td>
		<td colspan = "3" height = "100"> ${dto.content } </td>
	</tr>
	<!-- 첨부파일 -->
	<tr>
		<td> 첨부파일</td>
		<td>
			<c:if test="${not empty dto.ofile }"> <!-- 첨부파일이 존재할 때 -->
				${dto.ofile }
				<a href = "../mvc_board/download.do?ofile=${dto.ofile}&sfile=${dto.sfile}&idx=${dto.idx}">
					[다운로드]
				</a>
			</c:if>
		</td>
		<td>
			다운로드 수
		</td>
		<td>
			${dto.downcount }
		</td>
	</tr>
	<!-- 하단 메뉴 버튼 -->
	<tr> <!-- td 4개 합침 -->
		<td colspan = "4" align = "center">
		<!-- param.idx => 넘어오는 idx값 -->
			<button type = "button" onclick = "location.href='../MyHomePage/pass.do?mode=edit&idx=${param.idx}';"> 수정하기</button>
			<button type = "button" onclick = "location.href='../MyHomePage/pass.do?mode=delete&idx=${param.idx}';"> 삭제하기 </button>
			<button type = "button" onclick = "location.href='../MyHomePage/board_list.do';"> 목록 바로가기</button>
		</td>
	</tr>
	</table>

</body>
</html>