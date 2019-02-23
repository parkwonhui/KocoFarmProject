<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<!-- Time Picker 링크 추가 -->
 <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
 <!-- Time Picker 링크 추가 종료 -->

<!-- 다음주소API js라이브러리 추가-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script> 
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=556d645dceea630218b9f008bb8bb894&libraries=services"></script>
<!-- 다음주소API js라이브러리 추가 종료-->
    
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<style>
/* 여기에 스타일 넣기 */ 

.btn_wrap button.edit_btn{width:80px;height:40px;line-height:40px;background:#ffce0b;border-radius:3px;font-size:14px;font-weight:bold;color:#fff;cursor:pointer;text-indent:0;border:1px solid #ddd}

.btn_wrap button.del_btn{width:80px;height:40px;line-height:40px;background:#ED1B00;border-radius:3px;font-size:14px;font-weight:bold;color:#fff;cursor:pointer;text-indent:0;border:1px solid #ddd}
.btn_wrap button.write_btn{width:80px;height:40px;line-height:40px;background:#010372;border-radius:3px;font-size:14px;font-weight:bold;color:#fff;cursor:pointer;text-indent:0;border:1px solid #ddd}


</style>


	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>RentCar Reservation <span>예약된 정보를 확인할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > 차량관리 > </li>
						<li class="on">상세정보</li>						
					</ul>					
				</div>
			</div>
		</div>
		

		<!-- Contents Area -->
		<div class="contents_wrap">
			<!-- view -->
			<form action="" id ="carResDetailForm" method = "get">
				 <input type = "hidden" name = "mode" id = "mode" value = "${param.mode}" /> 
				<input type = "hidden" name = "carId" id = "car_id" value = "${CarReserve.carId}" />
				<input type="hidden" id="resId" value="${CarReserve.resId }" />
				
			<%-- 	<input type = "hidden" name  = "pageNum" value = "${cri.pageNum}">
  				<input type = "hidden" name  = "amount" value = "${cri.amount}">  --%>

				<div class="contents">
					<!-- 상세 정보 -->
					<table class="contents_tb vw" id="carResDetail">
						<colgroup>
							 <!--<col width="20%">
							<col width="*"> -->
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tbody>						
							<tr>
								<th scope="col">예약번호</th>
								<td class="left">${CarReserve.resId }</td>																															
								</td> 
							</tr>
							<tr>
								<th scope="col">예약자</th>
								<td class="left">${CarReserve.resWriter}</td>
								
								<th scope="col">사용자</th>
								<td class="left">${CarReserve.resUser }</td>
							
							</tr>
							
							<tr>
								<th scope="col">예약차량</th>
								<td class="left">${CarReserve.carId }</td>
								
								<th scope="col">사용목적</th>
								<td class="left">${CarReserve.purpose }</td>
							</tr>
							
							<tr>
								<th scope="col">운행시작 날짜</th>
								<fmt:parseDate var = "dateString" value="${CarReserve.stDate}" pattern="yyyy-MM-dd" />
								<td><fmt:formatDate value="${dateString}" pattern="yyyy-MM-dd" /></td>
								
								
								<th scope="col">운행시작 시간</th>
								<td class="left">${CarReserve.stTime}</td>
							</tr>
							
							<tr>
								<th scope="col">운행종료 날짜</th>
								<fmt:parseDate var = "dateString2" value="${CarReserve.enDate}" pattern="yyyy-MM-dd" />
								<td><fmt:formatDate value="${dateString2}" pattern="yyyy-MM-dd" /></td>
								
								<th scope="col">운행종료 시간</th>
								<td class="left">${CarReserve.enTime}</td>
							</tr>						
							<tr>
								<th scope="col">출발지 주소</th>
								<td class="left">${CarReserve.stAddr }</td>
								
								<th scope="col">도착지 주소</th>
								<td class="left">${CarReserve.deAddr }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<!-- btn -->
			<div class="btn_wrap">
				<div class="flt_r"><!-- onclick="location.href = '/rent/CarResList'" -->
					<input type="button" class="list_btn" id="carReslist2" value="목록"  />					
					
						<!-- 관리자 권한주기 -->
				 <c:if test="${loginVO.authority == 99}">
					<button name = "acceptBtn"   class ="write_btn"  value = "Y">승인</button>
					<button name = "acceptBtn" class ="del_btn" value = "N">반려</button>
				</c:if>	 
									 	
				</div>
			</div>
		
			
			
			
		</div>	
	</div>
	
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>