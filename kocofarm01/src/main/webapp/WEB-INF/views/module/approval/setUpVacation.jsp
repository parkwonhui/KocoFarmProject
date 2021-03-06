<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="/resources/css/common.css" /> 
<link rel="stylesheet" type="text/css" href="/resources/css/approval.css" /> 
 <link rel="stylesheet" href="/code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css"> -->
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
 <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/approval.css" />
<script type="text/javascript">

</script>
	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>Approval <span>결재 관련 내용을 확인할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > </li>
						<li class="on">문서 결재</li>
					</ul>
				</div>
			</div>
		</div>
	
		<!-- Contents Area -->
		<div class="contents_wrap">
			<!-- basic draft input  -->
			<div class="draft_wrap">
				<h1 class="txt_c">기본 정보</h1>
				<div class ="inf_wrap_box">
					<p class="name"><b>이름</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${emp.korNm }</p>
					<p class="position"><b>직위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${emp.positionNm}</p>
					<p class="dep"><b>부서</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${emp.deptNm }</p>

				</div>
				
				<p></p>
				<p></p>
			</div>
			<form action="/approval/setUpVacation?draftId=${draft.draftId }" method="post">
				<div class = "appr_wrap_box">
					<p class="ApprEmp">결재자<input type='text' name="empNameList" value = "${apprNm }" onclick = "window.open('/approval/searchApprovalEmp','결재자 검색','resizable=no width=610 height=620');return false">
					<p class="ApprEmpId">결재자Id<input type='text' name="empIdList" value = "${apprId}"/></p>
				</div>
			<!-- list -->
			<div class="contents">
				<!-- 기안서 보기 -->
				
					<div class="vacation_wrap">
						<div class="title">
							<h1 class="txt_c">휴 가 신 청 서</h1>
						</div>
						
						<!-- 입력 폼 시작 -->
						<div class="input_wrap">
							<div class = "input_draft">
							<h2 class="txt_c">기안서 정보</h2>
							
							 <table width = 80% class="draft" border = "0" >
							 	<colgroup>
							 		<col width="25%" />
							 		<col width="*" />
							 	</colgroup>
							 	<tr>
							 		<th class="inputText" scope="col"> 기안서 제목 </th>
							 		<td><input type="text" name="draftTitle" value = "${draft.draftTitle }"> </td>
							 	</tr>
							 	<tr>
							 		<th class="inputText" scope="col"> 보존 년한 </th>
							 		<td> <input type="text" name="draftYear" value = "${draft.draftYear }"> </td>
							 	</tr>
							 </table>
							 
							 <h2 class="txt_c">휴가 신청 내용</h2>
							 <table class ="draft">
								 <colgroup>
							 		<col width="20%" />
							 		<col width="20%" />
							 		<col width="20%" />
							 		<col width="20%" />
							 	</colgroup>
							 	<tr>
							 		<th class="inputText">휴가 종류</th>
							 		<td colspan="3">
								 		<input type="radio" name = "vacationType" checked value="병가">병가
										<input type="radio" name = "vacationType" value="오전반차"> 오전 반차
										<input type="radio" name = "vacationType" value="오후반차"> 오후 반차
										<input type="radio"	name = "vacationType" value="연차"> 연차
									</td>
							 	</tr>
							 	
							 	<tr>
							 		<th class="inputText">시작 날짜</th>
							 		<td><input type="text" id="Startdatepicker" value = "${vacation.vacationStartDt}" name = "vacationStartDt" class="date" readonly=readonly /> </td>
							 		<th class="inputText">종료 날짜</th>
							 		<td><input type="text" id="Enddatepicker" value = "${vacation.vacationEndDt}"name = "vacationEndDt" class="date" readonly=readonly /></td>
							 	</tr>
							 	<tr>
							 		<th class="inputText">총 일수 </th>
							 		<td><input type="button" class="days_btn" id="dayBtn" value="계산" />
							 		<td colspan="2"><input type="text" id ="vacationDays" name="vacationDays" value = "${vacation.vacationDays }"></td>
							 	</tr>
							 	
							 	<tr>
							 		<th colspan="4" class="inputText txt_c" >신청 사유<span class="sub_txt"> (세부 내용을 기재 해 주세요.)</span></th>
							 	</tr>
							 	
							 	<tr>
							 		<td colspan="4" class="inputText txt_c">
							 			<textarea rows="6" cols="120" name="vacationReason" class="wth100p" >${vacation.vacationReason }</textarea>
									</td>
							 	</tr>
							 	</table>
							 	
							 	<h2 class="txt_c">대체 근무자 정보</h2>
							 	<table class ="draft">
								 	<colgroup>
								 		<col width="20%" />
								 		<col width="*%" />
								 	</colgroup>
								 	<tr>
								 		<th class="inputText" scope="col">사번 </th>
								 		<td><input type='text' name="replacementId" value = "${vacation.replacementId }" onclick = "window.open('/approval/searchReplaceEmp','사원 검색','resizable=no width=700 height=700'); return false">

								 	</tr>
								 	
								 	<tr>
								 		<th class="inputText" scope="col">이름</th>
								 		<td><input type='text' name="korNm" value = "${replacement.korNm }" ></td>
								 	</tr>
								 	
								 	<tr>
								 		<th class="inputText" scope="col">직책</th>
								 		<td><input type='text' name="positionNm" value = "${replacement.deptNm }" ></td>
								 	</tr>
								 	
								 	<tr>
								 		<th class="inputText" scope="col">핸드폰 번호</th>
								 		<td><input type='text' name="cellPhone" value = "${replacement.cellPhone }" ></td>
								 	</tr>
							 	</table>
							
							</div><!-- input_draft -->
						
						</div><!-- input_wrap -->
						<!-- vacation table 끝 -->
			
						<div class= flt_r>
						<br><br>
							<input type="hidden" name="formId" value="4"/>
							
							<input type="submit" class="submitBtn" value="수정하기">
							<br><br>
						</div>
					</div>
					</div>
				</form>
			</div>
		
		</div>
	

<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>

