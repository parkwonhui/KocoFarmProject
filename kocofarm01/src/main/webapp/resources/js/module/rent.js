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
	     
	     $('#rentCarEditForm').empty();
	     
	     $('#rentCarEditForm').append(pageNumTag);
	     $('#rentCarEditForm').append(amountTag);
		
		$("#rentCarEditForm").submit();	
		
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
	

	
	/*차량 등록페이지 - Ajax 호출*/
	$("#checkCar_Id").click(function(){
				
		var query = {carId : $("#car_id").val()};		
		$.ajax({
			url : "/rent/getcarIdChk",
			data : query,
			type : "post",
			dataType:"json",
			success : function(data){				
				if(1 == data){
					alert("이미 존재하는 차량번호 입니다.\n차량번호를 정확히 입력 해 주세요.");
					$("#car_id").val("");
					$("#car_id").focus();
				}else{
					alert("사용 가능한 차량 번호 입니다.");
				}
			}	
		})//ajax
		
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
				console.log("carDetail");
				e.preventDefault();
				actionForm
						.append("<input type='hidden' name='carId' value='"
								+ $(this).attr("href") + "'>");
				actionForm.attr("action", "/rent/rentCarDetailView");
				actionForm.submit();
			});

	
	//검색버튼 클릭
	$("#schBtn").click(function() {
		actionForm.attr("action", "/rent/rentCarDetailList");
		alert("검색버튼 눌렀당")
		
	/*	if (!searchForm.find("option:selected")
				.val()) {
			alert("검색종류를 선택하세요");
			return false;
		}

		if (!searchForm.find(
				"input[name='keyword']").val()) {
			alert("키워드를 입력하세요");
			return false;
		}

		searchForm.find("input[name='pageNum']").val("1");
		e.preventDefault();*/
		actionForm.submit();
	})
	
	
	
	/*==========================차량예약========================*/
	
	
	/*datepicker 사용*/
	 var 
      from = $( "#stDate").datepicker({
	    	  dateFormat: 'yy/mm/dd' //Input Display Format 변경
	          ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	          ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
	          ,changeYear: true //콤보박스에서 년 선택 가능
              ,changeMonth: true //콤보박스에서 월 선택 가능                
              ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
              ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
              ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
              ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
              ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
              ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
              ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
              ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
              ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
              ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
              ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)       
        })
        .on( "change", function() {
          to.datepicker( "option", "minDate", getDate( this ) );
        }),
      to = $( "#enDate" ).datepicker({
    	  dateFormat: 'yy/mm/dd' //Input Display Format 변경
	          ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
	          ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
	          ,changeYear: true //콤보박스에서 년 선택 가능
              ,changeMonth: true //콤보박스에서 월 선택 가능                
              ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
              ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
              ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
              ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
              ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
              ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
              ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
              ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
              ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트
              ,minDate: "-1M" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
              ,maxDate: "+1M" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)     
      })
      .on( "change", function() {
        from.datepicker( "option", "maxDate", getDate( this ) );
      });
    
    $('#stDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
    //To의 초기값을 내일로 설정
    $('#enDate').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
 
    function getDate( element ) {
      var date;
      try {
        date = $.datepicker.parseDate( dateFormat, element.value );
      } catch( error ) {
        date = null;
      }
 
      return date;
    }
	
    /*datepicker 사용 종료*/
    
    
    /*timeFormat: 'p h:mm',
     * minTime: '09:00am',
    maxTime: '11:00pm',*/
    /*timepicker 사용*/    
   $('#stTime').timepicker({
        timeFormat: 'H:mm',
        interval: 60,
        minTime: '09:00',
        maxTime: '23:00',
        dynamic: true,
        dropdown: true,
        scrollbar: true

    });
    
    $('#enTime').timepicker({
    	timeFormat: 'H:mm',
        interval: 60,
        minTime: '09:00',
        maxTime: '23:00',
        dynamic: true,
        dropdown: true,
        scrollbar: true
    });
    /*timepicker 종료*/


    /*지도api*/
    
    /*=======================첫번째 지도=========================*/
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

	//지도를 미리 생성
	var map = new daum.maps.Map(mapContainer, mapOption);
	//주소-좌표 변환 객체를 생성
	var geocoder = new daum.maps.services.Geocoder();
	//마커를 미리 생성
	var marker = new daum.maps.Marker({
	    position: new daum.maps.LatLng(37.537187, 127.005476),
	    map: map
	});
    
    
    
    $("#postcodeBtn").click(function() {
   	new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용
                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
                        // 지도를 보여준다.
                        mapContainer.style.display = "block";
                        map.relayout();
                        // 지도 중심을 변경한다.
                        map.setCenter(coords);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker.setPosition(coords)
                    }
                });
            }
        }).open();
	})
    
	
	  /*=======================두번째 지도=========================*/
	  var mapContainer2 = document.getElementById('map2'), // 지도를 표시할 div
    mapOption2 = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

	//지도를 미리 생성
	var map2 = new daum.maps.Map(mapContainer2, mapOption2);
	//주소-좌표 변환 객체를 생성
	var geocoder2 = new daum.maps.services.Geocoder();
	//마커를 미리 생성
	var marker2 = new daum.maps.Marker({
	    position: new daum.maps.LatLng(37.537187, 127.005476),
	    map2: map2
	});
	
	 $("#postcodeBtn2").click(function() {   	
   	new daum.Postcode({
            oncomplete: function(data) {
                var addr2 = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("address2").value = addr2;
                // 주소로 상세 정보를 검색
                geocoder2.addressSearch(data.address, function(results2, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result2 = results2[0]; //첫번째 결과의 값을 활용
                        // 해당 주소에 대한 좌표를 받아서
                        var coords2 = new daum.maps.LatLng(result2.y, result2.x);
                        // 지도를 보여준다.
                        mapContainer2.style.display = "block";
                        map2.relayout();
                        // 지도 중심을 변경한다.
                        map2.setCenter(coords2);
                        // 마커를 결과값으로 받은 위치로 옮긴다.
                        marker2.setPosition(coords2);
                    }
                });
            }
        }).open();
	})
    
    /*지도api*/
  
   
	/*=============예약등록페이지  >>> 예약 리스트로 이동 ===============*/
	 $("#carReslist").click(function() {
		 $('#CarResWriteForm').attr("action", "/rent/CarResList").attr("method", "get")
			$("#CarResWriteForm").submit();		
			
			
	})
	
	
	
	
	
	$("#checkCar").click(function() {
		alert("클릭햇당")
		
		//예약 가능한 차량 보여주기 
		/*운행시작시간과 운행종료 시간 참고하기*/
		
		//window.open('https://www.google.co.kr/', 'google', 'width=500,height=500');
		window.open('/module/rent/carReservation/a.jsp', 'rentCarDetailList' ,'width=500,height=500,location=no,status=no,scrollbars=yes');
		

	})
	
	//예약신청 버튼 누르면, 등록되도록 설정한다. 	
	$("#carRes").click(function() {
		alert('예약신청이 완료되었습니다. ')		
		
		var mode = $("#mode").val();
		if("write" == mode){
			
			$("#CarResWriteForm").submit()
		
		}
	})
	
	
	//수정버튼 눌렀을떄
	$("#updateForm2").click(function() {		
		
		//$('#carResDetailForm').attr("action", "/rent/rentCarDetailEdit")
		$("#carResDetailForm").submit();		
		return false;
	})
	
	
	
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

