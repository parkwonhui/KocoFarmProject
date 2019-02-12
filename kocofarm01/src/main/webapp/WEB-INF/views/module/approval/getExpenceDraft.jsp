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


<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/resources/css/module/approval.css" />

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
				<!-- expence table 시작 -->
				<div class="vac_table">
					<table width=54% height=80% border=1 cellpadding=0 cellspacing=0
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
							<td></td>
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
				
				<!-- btn -->
					<div class="btn_wrap">
						<div class="flt_r">
							<input type="button" class="list_btn" value="목록" />
							<input type="button" class="expEdit_btn" value="수정" />
							<input type="button" class="expDel_btn" value="삭제" />
						</div>
					</div>					
					

			</div>


		</div>

<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false"></jsp:include>