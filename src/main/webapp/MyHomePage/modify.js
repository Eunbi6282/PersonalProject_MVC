var status = true;

$(document).ready(function(){
	
	// modify_chForm.jsp페이지의 정보 수정 버튼을 클릭하면 자동실행 => 비밀번호 확인페이지
	// 입력한 비밀번호를 가지고 memberCheck.jsp 페이지 호출
	$("#modify").click(function(){   //modify_chForm.jsp에서 [정보수정]클릭
		var query = {pass:$("#pass").val()};
		
		$.ajax({
			type: "post",
			url: "memberCheck.jsp",
			data: query,
			succes: function(data) {
				if(data == 1) { //비밀번호 맞음
				   alert("값 잘 넘오옴");
					location.href='.99/modifyForm.jsp';
				} else {
					alert("비밀번호가 맞지 않습니다.");
		    	  	 $("#pass").val("");
		    	  	 $("#pass").focus();
				}
			}
		});
	});
	
	// modifyForm.jsp 페이지의 [수정]버튼 클릭시 자동수행
	// 수정폼에 입력한 값을 가지고 modifyPro.jsp 실행
	
	
});