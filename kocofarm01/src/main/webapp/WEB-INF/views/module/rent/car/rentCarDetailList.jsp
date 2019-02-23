<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/rent.css" />

	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>RentCar Detail <span>등록된 차량 정보를 확인할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > </li>
						<li class="on">차량관리</li>
					</ul>
				</div>
			</div>
		</div>
	
		<!-- Contents Area -->
		<div class="contents_wrap">
			<form id = "searchForm" action="" method="GET">
				<div class="sch_wrap">
					<p class="tit">검색</p>
					
					<div class="sch_slide_btn">
						<img id="slideBtnImg" class="upBtn" src="/resources/img/comm/list_up_btn.png" alt="메뉴 접기" />
					</div>
					
					<div class="sch_toggle_wrap">
						<div class="sch_box_wrap">
							<div class="right">
								<select name="schType" id="schType">
								 <option value="" 
										<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>전체</option>
								<option  value="I"
										<c:out value="${pageMaker.cri.type eq 'I'?'selected':''}"/>>차량번호</option>
								<option  value="M"
										<c:out value="${pageMaker.cri.type eq 'M'?'selected':''}"/>>차량모델명</option>
								 								
								<%-- <option value="" 
										value="${pageMaker.cri.type == null?'selected':''}">전체</option>
								<option  value="I"
										value="${pageMaker.cri.type eq 'I'?'selected':''}">차량번호</option>
								<option  value="M"
										value="${pageMaker.cri.type eq 'M'?'selected':''}">차량모델명</option> --%>
							
								
								</select>
								
							<%--  value='<c:out value="${pageMaker.cri.keyword}" --%>
								<%-- <input type="text" name="keyword" id="schWord"
								value = "${pageMaker.cri.keyword}" placeholder="검색어를 입력 해 주세요" />
								<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
								<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
								<input type = "hidden" name = "type" value = "${pageMaker.cri.type}"> --%>
								<%-- <input type = "hidden" name = "keyword" value = "${pageMaker.cri.keyword}">		 --%>										
								
								<!-- 여기 추가해봄 -->
								<input type='text' name='keyword'
									value='<c:out value="${pageMaker.cri.keyword}"/>' />
								
								<input 	type='hidden' name='pageNum'
									value='<c:out value="${pageMaker.cri.pageNum}"/>' />
								
								<input 	type='hidden' name='amount'
									value='<c:out value="${pageMaker.cri.amount}"/>' />
									
							<%-- 	<input 	type='hidden' name='type'
									value='<c:out value="${pageMaker.cri.type}"/>' /> --%>
								
								<input type="button" class="schBtn" id="schBtn" value="검색" />		
												
							</div>
						</div>
					</div>
				</div>
			</form>			
			
			<!-- list -->
			<div class="contents">
				<!-- 목록 보기 -->
				<table class="contents_tb" id="contTb">
					<colgroup>
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">차량번호</th>			
							<th scope="col">차량모델명</th>
							<th scope="col">차종</th>
							<th scope="col">구입조건</th>
							<th scope="col">가격</th>
							<th scope="col">연식</th>
							<th scope="col">유종</th>
							<th scope="col">등록일자</th>
							<th scope="col">수정일자</th>
						</tr>
					</thead>
					<tbody id="contentsTbBody">
						<c:forEach var="RCdetail" items="${list}"> 
						<!-- RentCarDetailService에서 RentCarDetailListModel 중에서 list만 불러오는 것이다. -->
							<tr>
								<td>${RCdetail.carId }</td> 								
								<td><a class = "move" href = '<c:out value="${RCdetail.carId}"/>'>
									<c:out value="${RCdetail.modelName }"/></a>
								</td>								
								<td>${RCdetail.carModel }</td>
								<td>${RCdetail.condition }</td>
								<td>${RCdetail.price }</td>
								<td>${RCdetail.year}</td>
								<td>${RCdetail.oilType }</td>
								<td>
									<fmt:parseDate var ="dateString" value="${RCdetail.regDt }"	pattern="yyyy-MM-dd"/>
									<fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd"/>
								</td>
								<td>
									<fmt:parseDate var ="dateString" value="${RCdetail.upDt }"	pattern="yyyy-MM-dd"/>
									<fmt:formatDate value="${dateString }" pattern="yyyy-MM-dd"/>
								</td> 
							</tr>
						</c:forEach> 
					</tbody>
				</table>				
			</div>
			
			
		<!-- 페이징처리 -->	
		<div class="paging_div">
			<ul class = "pagination">			
			<c:if test="${pageMaker.prev}">
			   <li class="paginate_button previous"><a href="${pageMaker.startPage-1 }">이전</a></li>
            </c:if>

			<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			   <li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
			    
			    <a href="${num}">${num}</a></li>
			</c:forEach>

			 <c:if test="${pageMaker.next}">
			     <li class="paginate_button next"><a href="#">다음</a></li>
			</c:if>
			</ul>			
		</div>
		
		<form id='actionForm' action="/rent/rentCarDetailList" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
				 <input type = "hidden" name = "type" value = "${pageMaker.cri.type}">
				<input type = "hidden" name = "keyword" value = "${pageMaker.cri.keyword}">
				
		</form>
		
	
	
		<!-- btn -->
		<div class="btn_wrap">
			<div class="flt_r">
				<c:if test="${loginVO.authority == 99}">
				<a href="/rent/rentCarDetailWrite">
					<input type="button" class="auto_wth_btn_b" value="차량등록" id = writeBtn />
				</a>
				</c:if>
			</div>
		</div>	
				
		</div>
	</div>

<script type="text/javascript" src="/resources/js/module/rent.js"></script>

<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>