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
		
		
		$("#dayBtn").click(function(){
			var startDate =$("#Startdatepicker").val();
			var endDate = $("#Enddatepicker").val();
			
			sDate = new Date(startDate);
			eDate = new Date(endDate);
			
			dateResult = (eDate - sDate)/1000/60/60/24;  
			$('#vacationDays').val(dateResult); 
			
			return false;
		});
		
		$("#sumBtn").click(function(){
			var sum = 0;
			var commSum = 0;
			$(".expencePrice").each(function(index,item){
				sum += parseInt($(this).val());
			});
			
			$(".expenceId").each(function(index,item){
				var radioValue = null;
				radioValue = $("input[name='vacationType"+$(this).val()+"']:checked").val();

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
		
		
		$(".list_btn").click(function(){
			location.href="listDraft.do";
			return false;
		});
		
		$(".vacEdit_btn").click(function(){
			location.href="UpdateForm.do?draftId="+$("#draftId").val();
			return false;
		});
		
		$(".vacDel_btn").click(function(){
			location.href="DeleteAction.do?draftId="+$("#draftId").val();
			return false;
		});
		
		$(".expEdit_btn").click(function(){
			location.href="";
			return false;
		});
		
		$(".expDel_btn").click(function(){
			location.href="";
			return false;
		});
		
		
});	

