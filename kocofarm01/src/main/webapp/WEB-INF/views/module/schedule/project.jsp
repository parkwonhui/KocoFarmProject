
<%@ page import="org.kocofarm.domain.schedule.ScheduleCategoryVO"%>
<%@ page import="org.kocofarm.domain.schedule.ScheduleCalenderVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.lang.Integer"%>
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
		/* 일정 추가 진행상황% 슬라이더  */
		var calenderCompletionSlider = document.getElementById("calenderCompletionPerRang");
		var calenderCompletionOutput = document.getElementById("calenderCompletionPerVal");

		calenderCompletionSlider.oninput = function() {
			calenderCompletionOutput.value = this.value;
		}
		/* 일정 추가 진행상황% textbox */
		$('#calenderCompletionPerVal').change(function(){
			var value = $(calenderCompletionOutput).val() ;
			if(false == isNaN(value)){
				if(0 > value) value = 0;
				if(100 < value) value = 100;
				$(calenderCompletionSlider).val($(this).val());
				$(calenderCompletionOutput).val($(this).val());
			}else{
				$(calenderCompletionSlider).val(0);
				$(calenderCompletionOutput).val(0);
			}
		});


		/* 일정 수정 진행상황% 슬라이더  */
		var editCalenderCompletionSlider = document.getElementById("editCalenderCompletionPerRang");
		var editCalenderCompletionOutput = document.getElementById("editCalenderCompletionPerVal");

		editCalenderCompletionSlider.oninput = function() {
			editCalenderCompletionOutput.value = this.value;
		}

		/* 일정 수정 진행상황% textbox */
		$('#editCalenderCompletionPerVal').change(function(){
			var value = $(editCalenderCompletionOutput).val() ;
			if(false == isNaN(value)){
				if(0 > value) value = 0;
				if(100 < value) value = 100;
				
				$(editCalenderCompletionSlider).val($(this).val());
				$(editCalenderCompletionOutput).val($(this).val());
				
			}else{
				$(editCalenderCompletionSlider).val(0);
				$(editCalenderCompletionOutput).val(0);
			}
		});
			
		var projectId = "${projectId}";
		add_project_id = projectId;
		
		is_project_manager = "${bProjectManager}";
		is_public_project = "${bPublic}";
		
		$.ajax({
			url : 'listCalender',
			data : {
				"projectId" : projectId
			},
			type:"POST",
			dataType : 'json',
			success : function(data) {

				addDynamicHtml(data);
			}// success function

		});// ajax
	});
</script>

	<div class="con">
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
					<p>캘린더 칼라</p>
					<div>
					 	<input type="button" class="tag-important btn"/>
					  	<input type="button" class="tag-approve btn" />
					   	<input type="button" class="tag-quickly btn" />
					   	<input type="button" class="tag-request btn" />
					</div>
					<div>
						<p>진행상황</p>
						<div class="add-calender-slidecontainer">
						  <input type="range" min="0" max="100" value="0" class="add-calender-slider" id="calenderCompletionPerRang">
  						  <input type="text" name="addCompletionPer" id="calenderCompletionPerVal" value="0"></input>
						</div>
					</div>
					<div>
						<div style = "height: 20px; display:inline-block;" id= "add-tag_bar" ></div>
						<div id = "add-tag_bar2"></div>
						<button class="btn btn-warning" data-target="#select-tag-popup" data-toggle="modal" name="tag" id="add-tag">태그 추가</button>
					</div>
					<div class="modal fade" id="add-tag-popup" >
					 <div class="modal-dialog">
					   <div class="modal-content">
					     <!-- header -->
					     <div class="tag-select-header">
					       <!-- 닫기(x) 버튼 -->
					       <button type="button" class="close" data-dismiss="modal">×</button>
					       <!-- header title -->
					       <h4 class="tag-select-title">Tag선택</h4>
					     </div>
					     <!-- body -->
					     <div class="modal-body">
					          Tag name
					         <div><input type="text" value="" onclick="this.value=''" name= "addtagName" class="tag-name-select" id = "tag-name-select"></div>
					          
					          Tag color
					         <div><input type="color"  value="#ffff" class="tag-color-select" name= "addtagColor" id="tag-color-select"></div>
					         
					     </div>
					     <!-- Footer -->
					      <div class="tag-select-footer">
					        <input type="button" class="btn btn-tag-add" id="tag-add" value="add"></input>
					        <input type="button" class="btn btn-tag-close" id="tag-close" value="Close">
					      </div>
					    </div>
					  </div>
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
						<div style = "height: 20px; display:inline-block;" id="edit-tag-bar">
						
						</div>
						<div id = "edit-tag_bar2"></div>
						<button class="btn btn-warning" data-target="#select-tag-popup" data-toggle="modal" name="tag" id="edit-tag">태그 추가</button>
					</div>
					<div class="modal fade" id="select-tag-popup" >
					 <div class="modal-dialog">
					   <div class="modal-content">
					     <!-- header -->
					     <div class="tag-select-header">
					       <!-- 닫기(x) 버튼 -->
					       <button type="button" class="close" data-dismiss="modal">×</button>
					       <!-- header title -->
					       <h4 class="tag-select-title">Tag선택</h4>
					     </div>
					     <!-- body -->
					     <div class="modal-body">
					          Tag name
					         <div><input type="text" value="ex)Important" onclick="this.value=''" name= "tagName" class="tag-name-select" id = "tag-name-select"></div>
					          
					          Tag color
					         <div><input type="color"  value="#ffff" class="tag-color-select" name= "tagColor" id="tag-color-select"></div>
					         
					     </div>
					     <!-- Footer -->
					      <div class="tag-select-footer">
					        <input type="button" class="btn btn-tag-edit" id="tag-edit" value="Edit"></input>
					        <input type="button" class="btn btn-tag-close" id="tag-close" value="Close">
					      </div>
					    </div>
					  </div>
					</div>

					<div><button class="btn btn-secondary" name="worker_list" id="edit-calender-emp" data-target="#edit-calender-emp-list" data-toggle="modal">작업자 선택</button></div>
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
	
	<!-- 카테고리 삭제. 하위 일정도 같이 삭제됨 -->
	<div class="modal fade" id="categoryDeleteModal" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">카테고리 삭제</h4>
				</div>
				<div class="modal-body">
					<p>카테고리 하위 일정이 전부 삭제됩니다.</p>
					<p>정말 삭제하시겠습니까?</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal" id="project-delete-project-button">삭제</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 카테고리 삭제. 하위 일정도 같이 삭제됨 -->
	<div class="modal fade" id="edit-calender-emp-list" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">일정 작업자 추가</h4>
				</div>
				<div class="modal-body">
					<p>일정 작업자를 선택해주세요</p>
					<div class="add-calender-emp-lists">
					<ul id="no-claender-emp-list" class="claender-emp-list">
					<li>a</li>
					<li>b</li>
					<li>c</li>
					</ul>			
					<ul id="current-claender-emp-list" class="claender-emp-list">
					<li>1</li>
					<li>2</li>
					<li>3</li>
					</ul>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal" id="edit-calender-emp-list-button">추가</button>
				</div>
			</div>
		</div>
	</div>
	
	<script src="/resources/js/module/schedule.js"></script>