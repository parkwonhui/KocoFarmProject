<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<style>
/* 여기에 스타일 넣기 */ 
</style>


	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>RentCar <span>등록된 차량 정보를 확인할 수 있습니다.</span></h2>
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
				<input type = "hidden" name  = "pageNum" value = "${cri.pageNum}">
  				<input type = "hidden" name  = "amount" value = "${cri.amount}"> 

				<div class="contents">
					<!-- 상세 정보 -->
					<table class="contents_tb vw" id="rentCarDetailView">
						<colgroup>
							<!-- <col width="20%">
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
								
								<th scope="col">승인여부</th>
								<td class="left"></td>
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
				<div class="flt_r">
					<input type="button" class="list_btn" id="listBtn" value="목록"  />
					<input type="button" class="auto_wth_btn_y" value="  수정    " id = "updateForm2">	
					<input type="button" class="auto_wth_btn_g"  value="  결제    " id = "carResApp">			
				</div>
			</div>
		</div>	
	</div>
	
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>