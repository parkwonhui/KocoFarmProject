$(function(){
	
	
	//차량등록
	$("#enroll").click(function() {
		if(confirm("등록하시겠습니까?")){
			//여기에 유효성 겁사
			chkReq();										
		}
		return false;
	})
	
	//수정버튼 눌렀을 떄
	$("#updateForm").click(function() {		
		$('#rentCarDetailForm').attr("action", "/rent/rentCarDetailEdit")
		$("#rentCarDetailForm").submit();		
		return false;
	})
	
	
	//상세보기에서 - 목록버튼 눌렀을 때 기존페이지로 이동한다. 
	$("#listBtn").click(function() {		
		$('#rentCarDetailForm').attr("action", "/rent/rentCarDetailList")
		$("#rentCarDetailForm").submit();		
			
	})
	
	//내용수정(update.jsp)에서 - 목록버튼 눌렀을떄 =>1페이지로 이동돼ㅠ
	$("#listBtn2").click(function() {		
		$('#rentCarEditForm').attr("action", "/rent/rentCarDetailList").attr("method", "get")
		 var pageNumTag = $("input[name = 'pageNum']").clone();
	     var amountTag = $("input[name = 'amount']").clone();
	   /*  var keywordTag = $("input[name = 'keyword']").clone();
	     var typeTag = $("input[name = 'type']").clone();*/
	     
	     $('#rentCarEditForm').empty();
	     
	     $('#rentCarEditForm').append(pageNumTag);
	     $('#rentCarEditForm').append(amountTag);
	    /* $('#rentCarEditForm').append(keywordTag);
	     $('#rentCarEditForm').append(typeTag);*/
		
		$("#rentCarEditForm").submit()		
		//기존 : location.href = "/rent/rentCarDetailList";		
	}) 
	

	//수정내용 등록
	$("#Upenroll").click(function() {
		if(confirm("수정완료 하시겠습니까?")){
			chkReq2();
		}
		return false;
	})
	
	//삭제
	$("#delete").click(function() {
		if(confirm("삭제하시겠습니까?")){
			/*페이지 이동*/
			/*location.href = "/rent/rentCarDetailDel?carId=" +$("#car_id").val()*/
			//location.href = "/rent/rentCarDetailDel?carId="+$("#car_id").val();	
			$('#rentCarEditForm').attr("action", "/rent/rentCarDetailDel").attr("method", "get")
			$("#rentCarEditForm").submit()
		}	
	})
	
	
	
	/*
	//차량번호 - 중복확인(ajax 이용)
	$("#checkCar_Id").click(function() {
		$.ajax({
			type : "POST",
			url : '/resources/rentCarDetailWriteForm.do',
			dataType : "json",
			error : function(){
				alert("통신실패");
			},
			success : function() {
				alert('통신성공')
			}
		})
		
	})*/
	
	/*Ajax 호출*/
	$("#checkCar_Id").click(function(){
		var ajaxMode = "getCarId";
		$.ajax({
			"url" : "getCarAjaxData.do",
			"data" : {
					"id" : $("#car_id").val(),
					"ajaxMode" : ajaxMode
			},
			dataType : "json",
			success : function(data){
				
				if(1 == data[0]){
					alert("이미 존재하는 차량번호 입니다.\n정확한 차량번호를 입력 해 주세요.");
					$("#car_id").val("");
					$("#car_id").focus();
				}else{
					alert("사용 가능한 차량 번호 입니다.");
				}
			}	
		})

		
	})
	
	
	
	//페이징처리
	var actionForm = $("#actionForm");
	$(".paginate_button a").on(
			"click",
			function(e) {

				e.preventDefault();
				console.log('click');
				actionForm.find("input[name='pageNum']").val($(this).attr("href"));				
				actionForm.submit();
			});
	
	//페이징처리 - 이벤트
	$(".move")
	.on(
			"click",
			function(e) {
				e.preventDefault();
				actionForm
						.append("<input type='hidden' name='carId' value='"
								+ $(this).attr("href") + "'>");
				actionForm.attr("action", "/rent/rentCarDetailView");
				actionForm.submit();
			});


});/*전체function 괄호*/





//등록 필수 값 체크
function chkReq(){
	if($("#car_id").val() == null || $("#car_id").val() == "" ){
		alert("차량번호를 입력해주세요.");
		$("#car_id").focus();
		return false;
	}
	
	//차량번호 유효성 검사
	if($("#car_id").val() != '^[가-힣\s]+$')
	
	if($("#modelName").val() == null || $("#modelName").val() == "" ){
		alert("차량모델명을 입력해주세요.");
		$("#modelName").focus();
		return false;
	}
	
	
	if($('input[name = "carModel"]:checked').val() == null || $('input[name = "carModel"]:checked').val() == "" ){
		alert("차종을 선택해주세요.");
		$("#carModel").focus();
		return false;
	}
	
	
	if($('input[name = "condition"]:checked').val() == null || $('input[name = "condition"]:checked').val() == "" ){
		alert("구입조건을 선택해주세요.");
		$("#condition").focus();
		return false;
	}
	
	if($("#price").val() == null || $("#price").val() == "" ){
		alert("가격을 입력해주세요.");
		$("#price").focus();
		return false;
	}
	
	if($("#year").val() == null || $("#year").val() == "" ){
		alert("연식을 입력해주세요.");
		$("#year").focus();
		return false;
	}
	
	if($('input[name = "oilType"]:checked').val() == null || $('input[name = "oilType"]:checked').val() == "" ){
		alert("유종을 선택해주세요.");
		$("#oil_Type").focus();
		return false;
	}
	

	/*여기 주석처리*/
	var mode = $("#mode").val();
	if("write" == mode){
		$('#rentCarWriteForm').attr("action", "/rent/rentCarDetailWrite")
		$("#rentCarWriteForm").submit()
	
	}
	
	
}

//수정완료 버튼 후, 필수값 체크
function chkReq2(){
	
	
	
	if($("#modelName").val() == null || $("#modelName").val() == "" ){
		alert("차량모델명을 입력해주세요.");
		$("#modelName").focus();
		return false;
	}
	
	
	if($('input[name = "carModel"]:checked').val() == null || $('input[name = "carModel"]:checked').val() == "" ){
		alert("차종을 선택해주세요.");
		$("#carModel").focus();
		return false;
	}
	
	
	if($('input[name = "condition"]:checked').val() == null || $('input[name = "condition"]:checked').val() == "" ){
		alert("구입조건을 선택해주세요.");
		$("#condition").focus();
		return false;
	}
	
	if($("#price").val() == null || $("#price").val() == "" ){
		alert("가격을 입력해주세요.");
		$("#price").focus();
		return false;
	}
	
	if($("#year").val() == null || $("#year").val() == "" ){
		alert("연식을 입력해주세요.");
		$("#year").focus();
		return false;
	}
	
	if($('input[name = "oilType"]:checked').val() == null || $('input[name = "oilType"]:checked').val() == "" ){
		alert("유종을 선택해주세요.");
		$("#oil_Type").focus();
		return false;
	}	
	
	var mode = $("#mode").val();	
	if (mode == "edit" ) { 	
		$('#rentCarEditForm').attr("action", "/rent/rentCarDetailEdit");		
		$("#rentCarEditForm").submit()
	}
}

