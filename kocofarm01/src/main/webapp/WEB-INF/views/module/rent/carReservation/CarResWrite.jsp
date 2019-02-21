<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/rent.css" />
<!-- Time Picker 링크 추가 -->
 <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<%--<link href="/resources/jquery/jquery-ui.css?version=1.3" rel="stylesheet" type="text/css" media="screen">${pageContext.servletContext.contextPath}
<script src="/resources/js//jquery-1.8.3.min.js"></script>${pageContext.servletContext.contextPath}
<script src="/resources/jquery/jquery-ui.js?version=1.3"></script>${pageContext.servletContext.contextPath}
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
 --%>
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
					<h2>RentCar Reservation <span id="subTitSpan">차량을 예약 할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > 차량관리 > </li>
						<li class="on" id="subTitLi">예약</li>
					</ul>
				</div>
			</div>
		</div>
	
		<div class="contents_wrap">
			<!-- write -->
			<!-- <form action="" method="post" id = "rentCarWriteForm"> -->
			<form action="" method="post" id = "CarResWriteForm">			
				<input type="hidden" name="mode" id="mode" value="write" />
				
				<div class="contents">
					<!-- 등록 -->
					<table class="contents_tb wr" id="contTb">
						<colgroup>
							<col width="15%">
							<col width="35%">
							<col width="15%">
							<col width="35%">
						</colgroup>
						<tbody id="contentsTbBody">
							<tr>
								<th scope="col">예약자</th>
								<td class="left">
									<select class="form-control" name="resWriter" >
										<c:forEach items="${empList}" var="emp" varStatus="status">
										  <option value="${emp.empId}">${emp.korNm}</option>
										 </c:forEach>
									</select>
								</td>
								<th scope="col">사용자</th>
								<td class="left">
									<select class="form-control" name="resUser" >
										<c:forEach items="${empList}" var="emp" varStatus="status">
										  <option value="${emp.empId}">${emp.korNm}</option>
										 </c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th scope="col">운행시작 일자</th>
								<td class="left">
									<input type="text" name="stDate" id = "stDate" readonly=readonly >
								</td>
								<th scope="col">운행시작 시간</th>
								<td class="left">
									 <input type="text" value = "" name="stTime" id = "stTime" readonly=readonly placeholder= "시간을 선택해주세요." > 						
								</td>
							</tr>
							<tr>
								<th scope="col">운행종료 일자</th>
								<td class="left">
									<input type="text" name="enDate" id = "enDate" readonly=readonly>	
								</td>
								<th scope="col">운행종료 시간</th>
								<td class="left">
									<input type="text" value = "" name="enTime" id = "enTime" readonly=readonly placeholder= "시간을 선택해주세요." >
								</td>
							</tr>
							
							<tr>
								<th scope="col">예약 차량</th>
								<td class="left">
									<input type="text" name="carId" id = "car_id">
									<input type = "button" id = "checkCar" class="btn_b" value="차량 선택">
								</td>
								<th scope="col">사용목적</th>
								<td class="left">
									<input type="text" name="purpose" id = "purpose" >
								</td>
							</tr>							
							<tr>
								<th scope="col">출발지 주소</th>
								<td class="left">																		
									<input type="text" name = "stAddr" id="address" placeholder="주소">
									<input type="button" id = "postcodeBtn"  value="주소 검색"><br>
									<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>										
								</td>													
								<th scope="col">도착지 주소</th>
								<td class="left">																									
									<input type="text" name = "deAddr" id="address2" placeholder="주소">
									<input type="button" id = "postcodeBtn2"  value="주소 검색"><br>
									<div id="map2" style="width:300px;height:300px;margin-top:10px;display:none"></div>	
								</td>								
							</tr>																							
						</tbody>
					</table>
				</div>
			</form>
	
	
			
			<!-- btn -->
			<div class="btn_wrap">
				<div class="flt_r">
					<input type="button" class="list_btn" id="carReslist" value="목록" /> 
					<input type="button" class="write_btn" value="예약신청" id = "carRes" >
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>