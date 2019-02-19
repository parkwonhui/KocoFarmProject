$(function() {
		var no = 1;
		/* datePicker */
		$("#Startdatepicker, #Enddatepicker, #expenceDt").datepicker({
			showMonthAfterYear : true,
			showButtonPanel : true,
			changeMonth : true,
			changeYear : true,
			nextText : '다음 달',
			prevText : '이전 달',
			currentText : '오늘 일',
			closeText : '닫기',
			dateFormat : 'yy-mm-dd',
			dayNames : ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'],
			dayNamesMin : ['일','월','화','수','목','금','토'],
			monthNames : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			monthNamesShort : ['1','2','3','4','5','6','7','8','9','10','11','12'],
		});
		
		/* ---------------------휴가 신청서 휴가일 계산 버튼 기능 --------------------- */
		
		$("#dayBtn").click(function(){
			var startDate =$("#Startdatepicker").val();
			var endDate = $("#Enddatepicker").val();
			
			sDate = new Date(startDate);
			eDate = new Date(endDate);
			
			dateResult = (eDate - sDate)/1000/60/60/24;  
			$('#vacationDays').val(dateResult); 
			
			return false;
		});
		
		/* ---------------------지출결의서 sum계산 버튼 기능 --------------------- */
		
		$("#sumBtn").click(function(){
			var sum = 0;
			var commSum = 0;
			$(".expencePrice").each(function(index,item){
				sum += parseInt($(this).val());
			});
			
			$(".expenceId").each(function(index,item){
				var radioValue = null;
				radioValue = $("input[name='commissionType"+$(this).val()+"']:checked").val();

				for(var i = $(this).val(); i<=$(this).val(); i++){
					if(radioValue == 'no'){
						$('#commPrice'+i).val(0);
						$('#commPrice'+i).attr('readonly', true);
					}else if(radioValue == 'yes'){
						var a = parseInt($("#expencePrice"+i).val());
						$('#commPrice'+i).val(a * 0.1);
					}	
				}
			})
			$(".commPrice").each(function(index,item){
				commSum += parseInt($(this).val());
			});
			
			$("#sumPrice").val(sum+commSum)
			return false;
		});
		
		/* ---------------------기안서 버튼 기능 --------------------- */
		
		$(".list_btn").click(function(){
			location.href="listDraft.do";
			return false;
		});
		
		$(".vacEdit_btn").click(function(){
			location.href="/approval/getSetUpVacPage?draftId="+$("#draftId").val();
			return false;
		});
		
		$(".vacDel_btn").click(function(){
			location.href="/approval/delVacDraft?draftId="+$("#draftId").val();
			return false;
		});
		
		$(".expEdit_btn").click(function(){
			location.href="/approval/getSetUpExpPage?draftId="+$("#draftId").val();
			return false;
		});
		
		$(".expDel_btn").click(function(){
			location.href="/approval/delExpDraft?draftId="+$("#draftId").val();
			return false;
		});
		
		$(".approveBtn").click(function(){
			alert('승인 하시겠습니까?');
			/*$("#signImage").val("<input type = 'text' value =' 바보' readonly='readonly' >");*/
			var a = document.getElementById('signImage');
			a.innerHTML = "<input type='image' src = '/resources/img/approval/searchBt.png'>";
			console.log(a.innerHTML);
			
		/*	
			var b = document.getElementById('position1');
			console.log(b.innerHTML);
			*/
			
			
			//location.href="/approval/setUpApprState?draftId="+$("#draftId").val()+"&apprState="+1;
		});

		$(".returnBtn").click(function(){
			alert('반려 하시겠습니까?');
			var a = document.getElementById('signImage');
			a.innerHTML = "반려";
			console.log(a.innerHTML);
			//location.href="/approval/setUpApprState?draftId="+$("#draftId").val()+"&apprState="+0;
			
		});
		

	
});	
/*
	  alert(empId);
	  
	  location.href = "/approval/getEmpDraftList?empId="+empId;
	}
*/
	function getApprDraftList(empId) {
     alert(empId);
     
     location.href = "/approval/getEmpDraftList?empId="+empId;
   }

$(".commentInsertBtn").click(function(){
	var commentContents = $("commentContents").val();
	var draftId = "${draft_comment.draftId}";
	var empId = "${draft_comment.empId}";
	var param = {"commentContents" : commentContents, "draftId" : draftId, "empId" : empId};
		$.ajax({
			type : "POST",
			url : "setComment",
			data : param,
			success : function(){
				alert("댓글이 등록되었습니다.");
				}
		});
});