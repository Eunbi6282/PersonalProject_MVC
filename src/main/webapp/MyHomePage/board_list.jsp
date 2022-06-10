<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style>
		#boardForm {
		    font-family: 'Marcellus','Noto Sans KR',Arial,Helvetica,san-serif;
		    font-size: 14px;
		    
		}
		
		
	
	
	
	</style>


<link rel="stylesheet" href="../css/board_list.css">
</head>
<body>
	<jsp:include page="head.jsp" flush="false"/>
	
<div id = "boardForm" style = "margin-left:100px">
	<form method = "get">
		<table border = "1" width = "90%">
			<tr>
				<td align = "center">
					<select name = "searchField">
						<option value = "title"> 제목 </option>	<!--  제목을 선택하면 제목이 searchFiled 변수의 값으로 들어감 -->
						<option value = "name"> 작성자 </option>
					</select>
					<input type = "text" name = "searchWord" />
					<input type = "submit" value = "검색하기" />
				</td>
			</tr>
		</table>
	</form>
	
	<!--  목록 테이블 -->
	<table border = "1" width = "90%">
		<tr>
			<th width = "10%"> 번호 </th>
			<th width = "15%"> 작성자 </th>
			<th width = "*"> 제목 </th>
			<th width = "10%"> 조회수</th>
			<th width = "15%"> 작성일</th>
			<th width = "8%"> 첨부 </th>
		</tr>
		
		<c:choose>
			<c:when test = "${empty boardLists}">	<!-- boardLists값이 비어있을 때 -->
				<tr>
					<td colspan = "6" align = "center">
					등록된 게시물이 없습니다.
					</td>
				</tr>
			</c:when>
			<c:otherwise>	<!-- boardLists값이 있을 때 => 게시물이 존재할 때 -->
				<c:forEach items="${boardLists }" var="row" varStatus="loop">  
				<!-- req.setAttribute("boardLists", boardLists);로 변수 넘겨줌 -->
					<tr align = "center">
						<td>	<!-- 번호 출력 -->
							${map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}
						</td>
						<td align = "left" > <!-- 링크 걸면서 제목 출력 -->
							<a href = "../MyHomePage/view.do?idx=${row.id }"> ${row.title }</a>
						</td>
						<td>	<!-- 작성자 -->
							${row.name }
						</td>
						<td> 	<!-- 조회수 -->
							${row.visitcount}
						</td>
						<td>	<!-- 작성일 -->
							${row.postdate }
						</td>
						<td>	<!-- 첨부파일 -->
							<c:if test ="${not empty row.ofile}">
								<a href ="../MyHomePage/download.do?ofile=${row.ofile}&sfile=${row.sfile}&idx =${row.idx}"> [Down]</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</table>	
	
	<!-- 하단 메뉴(바로가기, 글쓰기) -->
    <table border="1" width="90%">
        <tr align="center">
            <td>
                ${ map.pagingImg }
            </td>
            <td width="100"><button type="button"
                onclick="location.href='../MyHomePaged/write.do';">글쓰기</button>
            </td>
        </tr>
    </table>
  </div>
</body>
</html>