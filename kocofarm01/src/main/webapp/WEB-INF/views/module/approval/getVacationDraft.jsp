<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/approval.css" />
<script type="text/javascript">
	var initBody;
	function beforePrint(){
		boxes = document
		
	}
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
			
			<!-- list -->
			<style>
				td {
					text-align: center;
				}
				
				h1 {
					text-align: center;
				}
			</style>
			<div class="contents">
				
				<!-- 기안서 보기 -->
				
				<div class="vacation_wrap">
					<div id = "printForm">
						<div class="title">
							<h1>휴 가 신 청 서</h1>
						</div>
						
						<div class="draft_wrap">
							<h1 class="txt_c">결재자 정보</h1>
							<div class="inf_wrap_box">
								<table>
									<thead>
										<tr>
											<th width = 10%>번호 </th>
											<th width = 10%>부서 </th>
											<th width = 10%>직위</th>
											<th width = 10%>사번</th>
											<th width = 20%>이름</th>
											<th width = 10%>결재</th>
											<th width = 10%>반려</th>
											<th width = 10%>sign</th>
											
										</tr>
									</thead>
									
								
									<tbody>
										<c:forEach var="ApprEmployee" items="${apprEmpList }"  varStatus="vs">
											<tr>
												
												<td id = 'count'>${vs.count }</td>
												<td>${ApprEmployee.deptNm }</td>
												<td>${ApprEmployee.positionNm }</td>
												<td id = 'position${vs.count }' class = "${ApprEmployee.empId}">${ApprEmployee.empId}</td>
												<td>${ApprEmployee.korNm}</td>
												
												<!-- 로그인 된 empId에 해당하는 컬럼 -->
												<c:if  test= "${ApprEmployee.empId eq loginVO.empId }">
													<td>
														<input type="button" class="approveBtn" id ="apprState" value="결재" width ="100%"/>
													</td>
													
													<td>
														<input type="button" class="returnBtn" id ="apprState" value="반려" width ="100%" />
													</td>
													
													<c:if test = "${apprEmp.apprOption eq '미확인' }" >
														<td id = 'signImage' class = "empSign"></td>
													</c:if>
													<c:if test = "${apprEmp.apprOption eq '결재' }" >
														<td id = 'signImage' class = "empSign">
														
														<c:if test =  "${ApprEmployee.empSign eq null }" >
														<input type='image' name = 'tmpSignImage' id = 'empSignImage' value = '${apprEmp.draftSign}' src = '/resources/img/approval/tmpSign/${ApprEmployee.draftSign}' />
														</c:if>
														
														<c:if test =  "${ApprEmployee.empSign ne null }" >
														<input type='image' name = 'tmpSignImage' id = 'empSignImage' value = '${apprEmp.draftSign}' src = '/resources/img/approval/${ApprEmployee.draftSign}' />
														</c:if>
														
														</td>
													</c:if>
													
													<c:if test = "${apprEmp.apprOption eq '반려' }" >
														<td id = 'signImage' class = "empSign">
														반려
														</td>
													</c:if>
												</c:if>
												<!-- 로그인 된 empId에 해당하는 컬럼 끝 -->
												
												<!-- 다른  empId에 해당하는 컬럼 -->
												<c:if  test= "${ApprEmployee.empId ne loginVO.empId }">
													<td>
													</td>
													<td>
													</td>
													<td class = "empSign">
														<c:if test = "${ApprEmployee.draftSign ne null}"> <!-- 결재 정보에 사인값이 널이 아닐 때 -->
															<c:if test =  "${ApprEmployee.empSign eq null }" > <!-- 사용자 사인을 안 갖고 있을때 -->
																<c:if test = "${ApprEmployee.draftSign ne 'return'}">
																	<input type='image' name = 'tmpSignImage' id = 'empSignImage' value = '${ApprEmployee.draftSign}' src = '/resources/img/approval/tmpSign/${ApprEmployee.draftSign}' />
																</c:if>
																
																<c:if test = "${ApprEmployee.draftSign eq 'return'}">
																	반려
																</c:if>	
															</c:if>
																
															<c:if test =  "${ApprEmployee.empSign ne null }" > <!-- 사용자 사인을 갖고 있을때 -->
																
																<c:if test = "${ApprEmployee.draftSign ne 'return'}">
																	<input type='image' name = 'tmpSignImage' id = 'empSignImage' value = '${ApprEmployee.draftSign}' src = '/resources/img/approval/${ApprEmployee.draftSign}' />				
																</c:if>
																
																<c:if test = "${ApprEmployee.draftSign eq 'return'}">
																	반려
																</c:if>
															</c:if>
														</c:if>
													</td>
													
												</c:if>	
											
									
											</tr>
										</c:forEach>
									</tbody>
								</table>
							
								<input type = "hidden" id = "empSign" value = "${loginEmp.empSign}" />
								<input type = "button" class="apprSubmit" value = "저장" />	
							</div>
							<p></p>
							<p></p>
				
						</div>
						<!-- vacation table 시작 -->
						<div class="vac_table">
							<table width = 80% height = 80% border=1 cellpadding=0 cellspacing=0 align="center">

								<tr>
									<td rowspan="2" width = 15%>기안서 정보</td>
									<td width = 15%>기안서 제목</td>
									<td colspan="3">${draft.draftTitle }</td>
									<td>등록 날짜</td>
									<td colspan="3" >${draft.draftDt }</td>
			
			
								</tr>
								<tr>
									<!-- 기안서 정보 -->
									<td>기안서 양식</td>
									<td colspan="3"></td>
									<td>보존년한</td>
									<td colspan="3">${draft.draftYear }</td>
								</tr>
			
								<tr>
									<td rowspan="3">인적 사항</td>
									<td>소속 부서</td>
									<td colspan="7">${empVO.deptNm }</td>
								</tr>
								<tr>
			
									<td>직위</td>
									<td colspan="7">${empVO.positionNm }</td>
								</tr>
								<tr>
			
									<td>성명</td>
									<td colspan="7">${empVO.korNm }</td>
								</tr>
								<tr>
									<td rowspan="4">신청내용</td>
									<td rowspan="3">휴가신청</td>
									<td width = 7%>휴가종류</td>
									<td colspan="6">${vacation.vacationType }</td>
								</tr>
								
								<tr>
									<!-- 신청내용   -->
									<!-- 휴가신청   -->
									<td>휴가 일정</td>
									<td width = 5%>시작 날짜</td>
									<td>${vacation.vacationStartDt}</td>
			
									<td width = 5%>끝 날짜</td>
									<td>${vacation.vacationEndDt}</td>
									<td width = 5%>총 일 수</td>
									<td>${vacation.vacationDays }</td>
								</tr>
			
								<tr>
									<!-- 신청내용   -->
									<!-- 휴가신청   -->
									<td>대체근무자</td>
									<td>ID</td>
									<td>${vacation.replacementId }</td>
									<td>이름</td>
									<td>${replaceEmp.korNm }</td>
									<td>직위</td>
									<td>${replaceEmp.positionNm }</td>
								</tr>
								<tr>
									<!-- 신청내용   -->
									<td>신 청 사 유 <Br>(세부 내용)
									</td>
									<!-- 세부내용기재   -->
									<td colspan="7">${vacation.vacationReason}</td>
			
								</tr>
								<tr height = "400px" > 
									<td colspan = 9>
									<p class="last_msg">위와 같이 휴가를 신청하오니 허가하여 주시기 바랍니다 .</p><br><br><br>
									<p class="sysdate">
										<fmt:parseDate var = "dateString" value ="${draft.draftDt}"  pattern = "yyyy-MM-dd"/>
										<fmt:formatDate value="${dateString }" pattern = "yyyy년  MM월  dd일"/>
									</p><br><br>
									<p class="sign">${empVO.korNm }&nbsp;&nbsp;&nbsp;	(인)</p>
								</tr>

			
							</table>
						</div>
						<!-- vacation table 끝 -->
					</div> <!-- print form 끝 -->
						
						<input type = "hidden" name="draftId" id="draftId" value ="${draft.draftId}" />
						<input type = "hidden" name="formId" id="formId" value ="4" />
						<input type = "hidden" name = "empId" id="empId" value="${loginVO.empId }" />
						<!-- btn -->
						
						<div class="btn_wrap">
							<div class="flt_r">
								<input type="button" class="list_btn" value="목록" />
								<input type="button" class="vacEdit_btn" value="수정" /> 
								<input type="button" class="vacDel_btn" value="삭제" />
								<c:if test="${draft.approveState eq '결재완료' }">
								<INPUT TYPE="button" VALUE="인쇄하기" style="background-color: #000000; font-size: 10pt; color: #ffffff; " onclick="printWin()">
								</c:if>
								
							</div>
						</div>			
					</div>
			</div>
		
		</div>
		
<!-- 댓글달기 -->
<!-- 댓글 부분 -->
    <div id="comment" align="center" >
    <!-- 댓글 목록 -->    
   	<div id = "ListComment" style="width: 54%; text-align: left;">
   		<c:forEach var="item" items="${draftList }" varStatus="status">
   		<table style="border: 1px solid #A4A4A4; border-radius : 3px; width : 100%; margin-top: 5px; font-size:12px;">
   			<tr style="height : 15px; background-color : #F6E3CE; ">
   				<td>
   					작성자 &nbsp; ${item.empId }
   				</td>
   				<td style="text-align: right !important ;">
   					작성일 &nbsp; ${item.commentDt }
   				</td>
   			</tr>
   			<tr>
   				<td style="width:15%;">
   					댓글내용
   				</td>
   				<td >
   					<div style=" height: 40px; overflow:auto;">${item.commentContents }</div>
   				</td>
   			</tr>
   		</table>
   		</c:forEach>
   	</div>
	<div id="commentPaging">
	
	
	
	
	</div>   	
	<!-- 로그인 했을 경우만 댓글 작성가능 -->
            
		<!--  댓글작성창 -->
		<form id="commentForm" style="margin-top : 10px;">
			<div class=re_writer style="width:54%;">
					<input type="hidden" name="draftId" id="draftId" value="${draft.draftId}" />
					<textarea id="commentContents" name="commentContents" style="height: 60; width: 90%; vertical-align: middle; border : 1px solid #A4A4A4; border-radius:3px; resize:none;"></textarea>
					<input type="button" id="setCommentBtn" value="댓글등록" style="vertical-align: middle; width:76px;height: 60px; line-height: 33px; border : 1px solid #A4A4A4; border-radius:3px;">
			</div>
			<div style="padding-left: 800;">
			
			</div>
		</form>
		<!-- 댓글목록들 -->
	
</div>
<script>
	$(function() {
			 $("#setCommentBtn").click(function(){
					var draftId = $("#draftId").val();
					var commentContents = $("#commentContents").val();
					commentContents = commentContents.replace(/(?:\r\n|\r|\n)/g, '<br />');
					var commentId = 1;
					var data = {
							draftId : draftId,
							commentContents : commentContents,
							commentId : commentId
					}
					
					$.ajax({
						url : "insertComment.do",
						type : "GET",
						data : data,
						success : function(){
							var today = new Date();
							var dd = today.getDate();
							var mm = (today.getMonth()+1).toString();
							if (mm.length < 2) {
								mm = '0' + mm;
							}
							var yyyy = today.getFullYear();
							var hour = today.getHours();
							var min = today.getMinutes();
							var sec = today.getSeconds();
							
							var date = yyyy+'-'+mm+'-'+dd+' '+hour+':'+min+':'+sec;
							var html = '<table style="border: 1px solid #A4A4A4; width : 100%; font-size:12px; margin-top:5px; border-radius:5px;">';
								html += '<tr style="height:15px;  background-color : #F6E3CE; "><td>작성자 &nbsp; '+commentId+'</td><td style="text-align:right !important ;"> 작성일 &nbsp; '+ date + '</td></tr>';
								html += '<tr><td style="width:15%;">댓글내용</td><td><div style=" height: 40px; overflow:auto;">'+commentContents+'</div></td></tr></table>';
								
							$('#ListComment').append(html);
							if ($('#ListComment table').length > 5) {
								$('#ListComment').children()[0].remove();
								 $("#commentContents").val("");
							}
						}
					});
			}); 
		});
		
</script>

<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>