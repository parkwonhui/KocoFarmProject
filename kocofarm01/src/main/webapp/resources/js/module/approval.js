
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
			$('#vacationDays').val(dateResult+1); 
			
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
			location.href="/approval/getDraftList";
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
		13900
		$(".expDel_btn").click(function(){
			location.href="/approval/delExpDraft?draftId="+$("#draftId").val();
			return false;
		});
		
		/* ---------------------기안서 결재/반려 ---------------------- */
		$(".approveBtn").click(function(){
			alert('승인 하시겠습니까?');
			/*$("#signImage").val("<input type = 'text' value =' 바보' readonly='readonly' >");*/
			var a = document.getElementById('signImage');
			if( $("#empSign").val() != ''){
				a.innerHTML = " <input type='image' id = 'tmpSignImage' name = 'tmpSignImage' value='"+$("#empSign").val()+"' src = '/resources/upload/temp/"+$("#empSign").val()+"' />";
				console.log(a.innerHTML);
			}else if ($("#empSign").val() == ''){
				var sign = confirm('사인이 없습니다. 등록하시겠습니까? ')
				if(sign){
					location.href = "/approval/setEmpSign";
				}else{
					window.open('/approval/test','결재자 검색','resizable=no width=600 height=600');
					}
			}
	
		});

		$(".returnBtn").click(function(){
			alert('반려 하시겠습니까?');
			var a = document.getElementById('signImage');

			console.log(a.innerHtml);
			a.innerHTML = "반려";
			console.log(a.innerHTML);
			
		});
		
		
		$(".apprSubmit").click(function(){
			alert('저장 하시겠습니까?');
			var a = document.getElementById('signImage').innerHTML;
			if(a == ''){ //선택 안 했을 시
				alert('결재 여부를 선택하세요.')
			}else if(a == '반려'){ //반려 선택 했을 시
				location.href="/approval/setUpApprState?draftId="+$("#draftId").val()+"&apprState="+0;
				
			}else{ //결재 -> sign img 들어가 있을 시
				location.href="/approval/setUpApprState?draftId="+$("#draftId").val()+"&apprState="+1+"&tmpSignImage="+$("#tmpSignImage").val();
												
			}
		});
		
		$("#uploadSignBtn").on("click",function(e){
			
			var formData = new FormData();
			var inputFile = $("input[name ='uploadFile']");
			var files = inputFile[0].files;
			
			console.log(files);
			
			for(var i = 0 ; i<files.length; i++){
				formData.append("uploadFile", files[i]);
			}
			
			$.ajax({
				url :'/approval/uploadSign',
					processData : false,
					contentType : false,
					data : formData,
					type :'POST',
					success : function(result){
						alert("Uploaded");
						location.href = "/approval/setEmpSign";
					}
				
			}); //$.ajax끝
		});

function ieExecWB( intOLEcmd, intOLEparam )
{
// 웹 브라우저 컨트롤 생성
var WebBrowser = '<OBJECT ID="WebBrowser1" WIDTH=0 HEIGHT=0 CLASSID="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></OBJECT>';
 
// 웹 페이지에 객체 삽입
document.body.insertAdjacentHTML('beforeEnd', WebBrowser);
 
// if intOLEparam이 정의되어 있지 않으면 디폴트 값 설정
if ( ( ! intOLEparam ) || ( intOLEparam < -1 )  || (intOLEparam > 1 ) )
        intOLEparam = 1;
 
// ExexWB 메쏘드 실행
WebBrowser1.ExecWB( intOLEcmd, intOLEparam );
 
// 객체 해제
WebBrowser1.outerHTML = "";
}
 
$(".commentInsertBtn").click(function(){
	var commentContents = $("#commentContents").val();
	var draftId = $("#draftId").val();
	var empId = $("#empId").val();
	
	var param = {"commentContents" : commentContents, "draftId" : draftId, "empId" : empId};
	
		$.ajax({
			type : "POST",
			url : "setComment",
			data : param,
			success : function(){
				alert("댓글이 등록되었습니다.");
				$("#commentContents").val("");
				getCommentList();
				}
		});
});


$(function(){
	getCommentList();
});


function getCommentList(){
	var commentContents = $("#commentContents").val();
	var draftId = $("#draftId").val();
	var empId = $("#empId").val();
	console.log(draftId, empId);
	
	var param = {"commentContents" : commentContents, "draftId" : draftId, "empId" : empId};
	$.ajax({
		type : "GET",
		url : "getCommentList",
		data : param,
		success : function (result){
			$("#getCommentList").html(result);
		}
	});
}

var actionForm = $("#actionForm");
$("#schBtn").on("click",function(e){
	if(!actionForm.find("option:selected").val()){
		alert("검색 종류를 선택해주세요.");
		return false;
	}
	
	if(!actionForm.find("input[name='keyword']").val()){
		alert("검색어를 입력해주세요. ");
		return false;
	}
	actionForm.find("input[name='pageNum']").val("1");
	
	$("#actionForm").submit();
	
//	e.preventDefault();
	
//	actionForm.submit();
})