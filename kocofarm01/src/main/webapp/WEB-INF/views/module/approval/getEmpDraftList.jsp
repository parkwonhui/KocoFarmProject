<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/approval.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/comm/comm.css" />


	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>Approval <span>결재 관련 내용을 확인할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > </li>
						<li class="on">문서 결재 ></li>
						<li class="on">기안서 전체보기</li>
					</ul>
				</div>
			</div>
		</div>
		<form id = 'actionForm' action = "/approval/getDraftList" method = 'get'>
			<input type = 'hidden' name = 'pageNum' value = '${pageMaker.cri.pageNum }'>
			<input type = 'hidden' name = 'amount' value = '${pageMaker.cri.amount }'>
		</form>
		<!-- Contents Area -->
		<div class="contents_wrap">
			<!-- sch_top -->
			<div class="sch_wrap">
				<p class="tit">검색</p>
				<div class="sch_slide_btn">
					<img id="slideBtnImg" class="upBtn" src="/resources/img/comm/list_up_btn.png" alt="메뉴 접기" />
				</div>
				<div class="sch_toggle_wrap">
					<div class="sch_box_wrap">
						<div class="right">
							<select name="schType" id="schType">
								<option value="">전체</option>
								<option value="title">제목</option>
								<option value="contents">내용</option>
							</select>
							<input type="text" name="schWord" id="schWord" placeholder="검색어를 입력 해 주세요" />
							<input type="button" class="schBtn" id="schBtn" value="검색" />
						</div>
					</div>
				</div>
			</div>
		
			<!-- list -->
			<div class="contents">
				 <div class = "tab_menu">
				 	<input id = "tab1" type = "radio" name = "tabs" checked>
				 	<label for  = "tab1"> 전체 보기</label>
				 	
				 	<input id = "tab2" type = "radio" name = "tabs">
				 	<label for  = "tab2"> 기안 중</label>
				 	
				 	<input id = "tab3" type = "radio" name = "tabs" >
				 	<label for  = "tab3"> 결재 중</label>
				 	
				 	<input id = "tab4" type = "radio" name = "tabs" >
				 	<label for  = "tab4"> 반려 </label>
				 	
				 	<input id = "tab5" type = "radio" name = "tabs" >
				 	<label for  = "tab5"> 결재 완료</label>
				 
				 	<br><br>
				 	<table class="contents_tb" id="contTb">
							<tr>
								<th width = 10%>기안서 번호</th>
								<th width = 30%>기안서 제목</th>
								<th width = 15%>기안서 양식</th>
								<th width = 15%>등록 날짜</th>
								<th width = 15%>기안자</th>
								<th width = 15%>결재 상태 </th>
							</tr>
					</table>
				 	<section id = "content1">
				 		<table class="contents_tb" id="contTb">

							<c:forEach var="ApprEmpDraftVO" items="${perDraftList }">
								<tr>
									<td width = 10%>${ApprEmpDraftVO.draftId }</td>
									<td width = 30%><a href = "/approval/getDraft?draftId=${ApprEmpDraftVO.draftId }">${ApprEmpDraftVO.draftName }</a></td>
									<td width = 15%>${ApprEmpDraftVO.modeName }</td>
									<td width = 15%>${ApprEmpDraftVO.draftDt }</td>
									<td width = 15%>${ApprEmpDraftVO.korNm }</td>
									<td width = 15%>${ApprEmpDraftVO.apprOption }</td>
									</tr>
							</c:forEach>
						</table>
				 	</section>
				 
					<!-- pagination start -->
					<div class = 'pull-right '>
						<ul class = "pagination">
							<c:if test = "${pageMaker.prev }">
								<li class = "paginate_button previous"><a href = "${pageMaker.startPage - 1 }">Previous</a></li>
							</c:if>
							
							<c:forEach var = "num" begin ="${pageMaker.startPage }" end = "${pageMaker.endPage }">
								<li class = "paginate_button ${pageMaker.cri.pageNum == num ? "active" : ""}"><a href="${num }">${num }</a></li>
							</c:forEach>
						
							<c:if test = "${pageMaker.next }">
								<li class = "paginate_button next"><a href="${pageMaker.endPage + 1 }">Next</a></li>
							</c:if>
						</ul>
					</div>
					<!-- pagination end -->
				 </div>

			
			</div>
		
		</div>

	</div>
<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<script type="text/javascript" src="/resources/js/comm/comm.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>

