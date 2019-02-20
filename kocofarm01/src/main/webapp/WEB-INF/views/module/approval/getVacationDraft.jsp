<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/approval.css" />

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
						<div class="title">
							<h1>휴 가 신 청 서</h1>
						</div>
						
						<div class="draft_wrap">
							<h1 class="txt_c">결재자 정보</h1>
							<div class="inf_wrap_box">
								<table border = 0 height = "100%" width = 100%>
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
										<c:forEach var="ApprEmployee" items="${apprEmp }"  varStatus="vs">
											<tr>
												<td id = 'count'>${vs.count }</td>
												<td>${ApprEmployee.deptNm }</td>
												<td>${ApprEmployee.positionNm }</td>
												<td id = 'position${vs.count }' class = "${ApprEmployee.empId}">${ApprEmployee.empId}</td>
												<td>${ApprEmployee.korNm}</td>
												<c:if  test= "${ApprEmployee.empId eq loginVO.empId }">
													<td>
														<input type="button" class="approveBtn" id ="apprState" value="결재" width ="20px"/>
													</td>
													<td>
														<input type="button" class="returnBtn" id ="apprState" value="반려" width ="20px" />
													</td>
													<td id = 'signImage' class = "getSign" ></td>
												</c:if>
												
											</tr>
										</c:forEach>
									</tbody>
								</table>	
								
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
						
						<input type = "hidden" name="draftId" id="draftId" value ="${draft.draftId}" />
						<input type = "hidden" name="formId" id="formId" value ="4" />
						<input type = "hidden" name = "empId" id="empId" value="${loginVO.empId}" />
						<!-- btn -->
						
						<div class="btn_wrap">
							<div class="flt_r">
								<input type="button" class="list_btn" value="목록" />
								<input type="button" class="vacEdit_btn" value="수정" /> 
								<input type="button" class="vacDel_btn" value="삭제" />
								
							</div>
						</div>			
					</div>
			</div>
		
		</div>
		
		<!-- 댓글 -->
		<!--  댓글  -->
    <div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm"  style="margin-left: 350;">
            <div class="input-group">
<%--                <input type="hidden" name="draftId" value="${DRAFT_COMMENT.draftId}"/> --%>
<%--                <input type="hidden" name="empId" value="${DRAFT_COMMENT.empId }"/> --%>
               <input type="text"  width="70%"   class="form-control" id="commentContents" 
               name="commentContents" placeholder="내용을 입력하세요.">
               <span class="input-group-btn">
                    <input type="button" class="commentInsertBtn" value="등록"/>
               </span>
              </div>
        </form>
    </div>
    
    <div class="container">
        <div class="getCommentList"></div>
    </div>
</div>
 </div>



<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>