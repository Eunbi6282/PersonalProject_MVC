<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

<script type="text/javascript" src="./resources/js/validation.js"></script>

<title>상품 등록</title>
</head>
<body>

	<jsp:include page="head.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">
				상품 등록
			</h1>
		</div>
	</div>
	
	<div class="container">
		<form name="newProduct" action="product_save.jsp" class="form-horizontal" method="post" enctype="multipart/form-data">
		<input type = "hidden" name = "flag" value = "i"/>
			<div class="form-group row">
				<label class="col-sm-2">상품코드</label>
				<div class="com-sm-3">
					<input type="text" id="p_id" name="p_id" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2">작성자 아이디</label>
				<div class="com-sm-3">
					<input type="text" id="wname" name="wname" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품분류</label>
				<div class="com-sm-3">
					<SELECT name=category size=1>
                        <OPTION value=11>TOP
                        <OPTION value=22>BOTTOM
                        <OPTION value=33>OUTER
                        <OPTION value=44>DRESS
                        <OPTION value=55>SHOES/BAG
                        <OPTION value=66>ACC
                    </SELECT>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품명</label>
				<div class="com-sm-3">
					<input type="text" id="pname" name="pname" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">제조사</label>
				<div class="com-sm-5">
					<input type= "text" id = "sname" name="sname">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">시중가</label>
				<div class="com-sm-3">
					<input type="text" id = "price" name="price" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">판매가</label>
				<div class="com-sm-3">
					<input type="text" id = "dprice" name="dprice" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">입고수량</label>
				<div class="com-sm-3">
					<input type="text" id="stock" name="stock" class="form-control">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-sm-2">상품 이미지</label>
				<div>
					<input type="file" id = "pImg" name="pImg" class="form-control">
				</div>
			</div>

            <div class="form-group row">
				<label class="col-sm-2">상세 내용</label>
				<div class="com-sm-5">
					<textarea id = "description" name="description" cols="50" rows="2" class="form-control"></textarea>
				</div>
			</div>
				
			<div class="form-group row">
				<div class="col-sm-offset-2 col-sm-10">
					<input type="submit" class="btn btn-primary" value="등록" onclick="CheckAddProduct()">
				</div>
			</div>
			
		</form>
	</div>
</body>
</html>