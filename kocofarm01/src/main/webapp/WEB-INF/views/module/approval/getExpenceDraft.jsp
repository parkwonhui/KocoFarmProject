<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"href="/resources/css/common.css" />
<link rel="stylesheet" type="text/css"href="/resources/css/approval.css" />
<link rel="stylesheet"href="/code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
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
 
</script>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/resources/css/module/approval.css" />
<HTML>
<div class="cont_wrap">
	<!-- SubTitle Area -->
	<div class="sub_title">
		<div class="sub_title_top">
			<div class="sub_title_inner">
				<h2>
					Approval <span>결재 관련 내용을 확인할 수 있습니다.</span>
				</h2>
				<ul class="sub_nav">
					<li>홈 ></li>
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
			<div class="expence_wrap">
				<div class="title" align="center">
					<h1>지 출 결 의 서</h1>
				</div>
				
				<div class="draft_wrap">
							<h1 class="txt_c">결재자 정보</h1>
							<div class="inf_wrap_box">
								<table width = 100%>
									<thead>
										<tr>
											<th width = 10%>번호 </th>
											<th width = 10%>부서 </th>
											<th width = 10%>직위</th>
											<th width = 15%>사번</th>
											<th width = 20%>이름</th>
											<th width = 15%>결재</th>
											<th width = 15%>반려</th>
											<th width = 5%>sign</th>
											
										</tr>
									</thead>
									
								
									<tbody>
										<c:forEach var="ApprEmployee" items="${apprEmpList }"  varStatus="vs">
											<tr height = 20px>
												
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
														<input type='image' name = 'tmpSignImage' id = 'empSignImage' value = '${apprEmp.draftSign}' src = '/resources/upload/temp/${ApprEmployee.draftSign}' />
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
														<c:if test = "${ApprEmployee.draftSign ne null}">
															<c:if test =  "${ApprEmployee.empSign eq null }" >
															<input type='image' name = 'tmpSignImage' id = 'empSignImage' value = '${ApprEmployee.draftSign}' src = '/resources/img/approval/tmpSign/${ApprEmployee.draftSign}' />
															</c:if>
		
															<c:if test =  "${ApprEmployee.empSign ne null }" >
																
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
				<!-- expence table 시작 -->
				<div class="vac_table">
					<table width=80% height=80% border=1 cellpadding=0 cellspacing=0
						align="center" name="expence">
						<tr>
							<td rowspan="2">기안서 정보</td>
							<td>기안서 제목</td>
							<td>${draft.draftTitle }</td>
							<td>등록날짜</td>
							<td>
							<fmt:parseDate var = "dateString" value ="${draft.draftDt }"  pattern = "yyyy-MM-dd"/>
							<fmt:formatDate value="${dateString }" pattern = "yyyy-MM-dd"/></td>
							
						</tr>

						<tr>
							<!--기안서 정보-->
							<td>기안서 양식</td>
							<td>지출 명세서</td>
							<td>보존년한</td>
							<td>${draft.draftYear }
						</tr>

						<tr>
							<td rowspan="3">인적 사항</td>
							<td>소속부서</td>
							<td colspan="3">${empVO.deptNm }</td>

						</tr>

						<tr>
							<!-- 인적 사항 -->
							<td>직위</td>
							<td colspan="3">${empVO.positionNm }</td>
						</tr>

						<tr>
							<!-- 인적 사항 -->
							<td>성명</td>
							<td colspan="3">${empVO.korNm }</td>
						</tr>

						<tr>
							<td>결제 정보</td>
							<td>결제종류</td>
							<td>${expence.expenceType }</td>
							<td>결제 날짜</td>
							<td>
								<fmt:parseDate var = "dateString" value ="${expence.expenceDt }"  pattern = "yyyy-MM-dd"/>
								<fmt:formatDate value="${dateString }" pattern = "yyyy년  MM월  dd일"/></td>
						</tr>

						<tr>
							<td colspan="5">지출내역</td>
						</tr>

						<tr>
							<td>번호</td>
							<td>거래처 이름</td>
							<td>결재 금액</td>
							<td>수수료 여부</td>
							<td>수수료 금액</td>
						</tr>
						<c:forEach var="ApprExpenceCont" items="${expenceCont }">
							<tr>
								
								<td>${ApprExpenceCont.contSeq }</td>
								<td>${ApprExpenceCont.customerName }</td>
								<td>${ApprExpenceCont.expencePrice }</td>
								<td>${ApprExpenceCont.commissionOption }</td>
								<td>${ApprExpenceCont.commissionPrice }</td>
							</tr>
						</c:forEach>


						<tr>
							<td colspan="3">합계</td>
							<!-- 합계  -->
							<!-- 합계  -->
							<td colspan="2">${expence.sumPrice }</td>

						</tr>

						<tr>
							<td colspan="5" height=400px><br>
							<br> 위 금액을 영수(청구)합니다<br>
							<br>
								<p class="sysdate">
									<fmt:parseDate var="dateString" value="${draft.draftDt}"
										pattern="yyyy-MM-dd" />
									<fmt:formatDate value="${dateString }"
										pattern="yyyy년  MM월  dd일" />
								</p>
								<br>
							<br>
								<p class="sign">영수자 : ${empVO.korNm }&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp; (인)</p></td>

						</tr>
					</table>
				</div>
				<!-- expence table 끝 -->

				<input type = "hidden" name="draftId" id="draftId" value ="${draft.draftId}" />
				<input type = "hidden" name="formId" id="formId" value ="2" />
						
						
									
				<!-- btn -->
				
					<div class="btn_wrap">
						<div class="flt_r">
							<input type="button" class="list_btn" value="목록" />
								<c:if test = "${empVO.empId eq loginVO.empId} ">
									<c:if test="${draft.approveState eq '기안중' }">
										<input type="button" class="vacEdit_btn" value="수정" /> 
										<input type="button" class="vacDel_btn" value="삭제" />
									</c:if>
								</c:if>
								
								<c:if test="${draft.approveState eq '결재완료' }">
								<input type="button" VALUE="인쇄하기" style="background-color: #000000; font-size: 10pt; color: #ffffff; " onclick="printWin()">
								</c:if>
						</div>
					</div>					
					
						<!--  댓글  -->
		    <div class="container">
		        <form name="commentInsertForm"  >
		            <div class="input-group">
		                 <input type="hidden" name="draftId" value="${DRAFT_COMMENT.draftId}"/>
		               <input type="text"  width="70%"   class="form-control" id="commentContents" 
		               name="commentContents" placeholder="내용을 입력하세요.">
		               <span class="input-group-btn">
		                    <input type="button" class="commentInsertBtn" value="등록"/>
		               </span>
		              </div>
		        </form>
		    </div>
		    
		    <div class="container">
		        <div id = "getCommentList"></div>
		    </div>
		</div>
</div>

</HTML>
<script type="text/javascript" src="/resources/js/module/approval.js"></script>

<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false"></jsp:include>