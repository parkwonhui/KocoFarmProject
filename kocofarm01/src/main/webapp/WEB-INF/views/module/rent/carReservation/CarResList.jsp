<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/rent.css" />
<!-- Time Picker 링크 추가 -->
 <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
 <!-- Time Picker 링크 추가 종료 -->

<!-- 다음주소API js라이브러리 추가-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=556d645dceea630218b9f008bb8bb894&libraries=services"></script>
<!-- 다음주소API js라이브러리 추가 종료-->

	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>RentCar Reservation<span>차량 예약 신청 목록을 확인할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > </li>
						<li class="on">차량 예약 목록</li>
					</ul>
				</div>
			</div>
		</div>
	
		<!-- Contents Area -->
		<div class="contents_wrap">
		<!-- 검색 -->				
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
					
					</colgroup>
					<thead>
						<tr>
							<th scope="col">예약번호</th>			
							<th scope="col">예약자</th>
							<th scope="col">사용자</th>
							<th scope="col">차량번호</th>
							<th scope="col">사용목적</th>
							<th scope="col">등록일자</th>
							
						</tr>
					</thead>
					<tbody id="contentsTbBody">
						<c:forEach var="CarReserve" items="${list}"> 
						<!-- RentCarDetailService에서 RentCarDetailListModel 중에서 list만 불러오는 것이다. -->
							<tr>
								<td>${CarReserve.resId }</td>
								<td><a class = "move2" href = "/rent/CarResDetail?resId=${CarReserve.resId }">
									${CarReserve.resWriter}</a>								
								</td>					
								<%-- <td>${CarReserve.resWriter }</td> --%>
								<td>${CarReserve.resUser }</td>
								<td>${CarReserve.carId}</td>
								<td>${CarReserve.purpose}</td>								
																					
								<td>
									<fmt:parseDate var ="dateString" value="${CarReserve.regDt }"	pattern="yyyy-MM-dd"/>
									<fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
						</c:forEach> 
					</tbody>
				</table>
				
				
					<!-- 페이징처리 -->	
		<%-- <div class="paging_div">
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
		</div> --%>
		
		<form id='actionForm' action="/rent/rentCarDetailList" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
				 <input type = "hidden" name = "type" value = "${pageMaker.cri.type}">
				<input type = "hidden" name = "keyword" value = "${pageMaker.cri.keyword}">
				
		</form>								
	</div>
			
			
	
		
	
	
		<!-- btn -->
		<div class="btn_wrap">
			<div class="flt_r">
				<!-- 차량등록 권한 부여 - 관리자만 -->
				<%-- <c:if test="${loginVO.authority == 99}"> --%>
				<!-- <a href="/rent/rentCarDetailWrite">
					<input type="button" class="auto_wth_btn_b" value="차량등록" id = writeBtn />
				</a> -->
				<%-- </c:if> --%>
				
				<input type="button" class="auto_wth_btn_b" value="승인목록" id = "carAppBtn" />
			</div>
		</div>	
		
		
		
		
				
		</div>
		
		
		
		
	</div>
<script>


</script>
<script type="text/javascript" src="/resources/js/module/rent.js"></script>

<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>