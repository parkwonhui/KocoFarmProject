
<%@ page import="org.kocofarm.domain.schedule.ScheduleCategory"%>
<%@ page import="org.kocofarm.domain.schedule.ScheduleCalender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.Integer"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var jb = jQuery.noConflict();
$( function() {
	 jb.datepicker.setDefaults({
	        dateFormat: 'yy-mm-dd',
	        prevText: '이전 달',
	        nextText: '다음 달',
	        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        showMonthAfterYear: true,
	        yearSuffix: '년'
	    });
	 
	 jb( ".addDatepickerStart" ).datepicker( "option", "dateFormat", 'yy-mm-dd' );
	 jb( ".addDatepickerEnd" ).datepicker( "option", "dateFormat", 'yy-mm-dd' );
	 jb( ".editDatepickerStart" ).datepicker( "option", "dateFormat", 'yy-mm-dd' );
	 jb( ".editDatepickerEnd" ).datepicker( "option", "dateFormat", 'yy-mm-dd' );

	  jb( "#addDatepickerStart").datepicker({
		  dateFormat: "yy-mm-dd"
	  });
	  jb( "#addDatepickerEnd").datepicker({
		  dateFormat: "yy-mm-dd"
	  });
	  jb( "#editDatepickerStart").datepicker({
		  dateFormat: "yy-mm-dd"
	  });
	  jb( "#editDatepickerEnd").datepicker({
		  dateFormat: "yy-mm-dd"
	  });
});
</script>

<link href="/resources/css/module/schedule.css" rel="stylesheet"
	type="text/css">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/css/bootstrap.min.css">
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function() {		  
		var projectId = "${projectId}";
		$.ajax({
			url : 'listCalender.do',
			data : {
				"projectId" : projectId
			},
			dataType : 'json',
			success : function(data) {

				addDynamicHtml(data);
			}// success function

		});// ajax
	});
</script>
</head>
<body> 

	<div class="con">
		<!-- 동적으로 엘레먼트가 생성된다 -->
	</div>

	<!-- 일정 추가 -->
	<div class="modal fade" id="calenderAddModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">일정추가</h4>
				</div>				
				<div class="modal-body">
					<label>새 일정 추가</label> <input type="text" name="write"></input>
					<div>
						<div>시작날짜 <input type="text" name="addDatepickerStart" id="addDatepickerStart"/></div>
						<div>종료날짜 <input type="text" name="addDatepickerEnd" id="addDatepickerEnd"/></div>	
					</div>
					<div>
						<label>캘린더 칼라</label>
					</div>
					<div>
					 	<input type="button" class="tag-important btn btn-xs" value="red"/>
					  	<input type="button" class="tag-approve btn btn-xs" value="green" />
					   	<input type="button" class="tag-quickly btn btn-xs" value="blue" />
					   	<input type="button" class="tag-request btn btn-xs" value="yellow" />
					</div>
					<div>
						<label>진행상황</label>
						<div class="add-calender-slidecontainer">
						  <input type="range" min="1" max="100" value="50" class="add-calender-slider" id="calenderCompletionPerRang">
  						  <input type="text" name="addCompletionPer" id="calenderCompletionPerVal"></input>
						</div>
					</div>
					<div>
						<button class="btn btn-warning" name="tag">태그 선택</button>
					</div>
					<div>
						<button class="btn btn-secondary" name="worker_list">작업자
							선택</button>
					</div>
					<button class="btn btn-primary" id="calender_add">일정 추가</Button>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- 일정 수정 -->
	<div class="modal fade" id="calenderModify" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">일정수정</h4>
				</div>
				<div class="modal-body">
					<label>일정 내용</label> <input type="text" name="write"></input>
					<div></div>
						시작날짜 <input type="text" name="editDatepickerStart" id="editDatepickerStart"/>
					<div></div>
						종료날짜 <input type="text" name="editDatepickerEnd" id="editDatepickerEnd"/>	
					<div>
						<label>캘린더 칼라</label>
					</div>
					<div>
					 	<input type="button" class="tag-important btn btn-xs" value="red"/>
					  	<input type="button" class="tag-approve btn btn-xs" value="green" />
					   	<input type="button" class="tag-quickly btn btn-xs" value="blue" />
					   	<input type="button" class="tag-request btn btn-xs" value="yellow" />
					</div>
						<label>진행상황</label>
						<div class="edit-calender-slidecontainer">
						  <input type="range" min="1" max="100" value="50" class="edit-calender-slider" id="editCalenderCompletionPerRang">
  						  <input type="text" name="editCompletionPer" id="editCalenderCompletionPerVal"></input>
						</div>
					<div>
						<button class="btn btn-warning" name="tag">태그 선택</button>
					</div>
					<div><button class="btn btn-secondary" name="worker_list">작업자 선택</button></div>
					<button class="btn btn-primary" id="calender_edit">일정 수정</Button>
					<button type="button" class="btn btn-danger" id="calender_del">일정 삭제</button>
				</div><!-- myBody -->
				<!-- body -->
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div><!-- body -->	
			</div><!-- modal-content -->
		</div><!-- modal-dialog -->
	</div>
	<script src="/resources/js/module/schedule.js"></script>
</body>
</html>
