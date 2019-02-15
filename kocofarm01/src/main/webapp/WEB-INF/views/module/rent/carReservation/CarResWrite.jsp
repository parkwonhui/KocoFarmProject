<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/rent.css" />

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
			<form action="/rent/rentCarDetailWrite" method="post" id = "rentCarWriteForm">			
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
									<input type="text" name="stDate" id = "stDate" >
								</td>
								<th scope="col">운행시작 시간</th>
								<td class="left">
									<input type="text" name="stTime" id = "stTime" >
								</td>
							</tr>
							<tr>
								<th scope="col">운행종료 일자</th>
								<td class="left">
									<input type="text" name="enDate" id = "enDate">	
								</td>
								<th scope="col">운행종료 시간</th>
								<td class="left">
									<input type="text" name="enTime" id = "enTime">
								</td>
							</tr>
							
							<tr>
								<th scope="col">예약 차량</th>
								<td class="left">
									<input type="text" name="carId" id = "car_id">
									<input type = "button" id = "checkCar_Id" class="btn_y" value="차량선택">
								</td>
								<th scope="col">사용목적</th>
								<td class="left">
									<input type="text" name="modelName" id = "modelName" >
								</td>
							</tr>

							<tr>
								<th scope="col">출발지 주소</th>
								<td class="left">
									<input type="text" name="price" id = "price">	
								</td>
								<th scope="col">도착지 주소</th>
								<td class="left">
									<input type="text" name="price" id = "price">	
								</td>								
							</tr>
							
							
							<!-- <tr>
								<th scope="col">차량 번호22</th>
								<td class="left">
									<input type="text" name="carId" id = "car_id">
									<input type = "button" id = "checkCar_Id" class="btn_y" value="중복확인">
								</td>
								<th scope="col">차량 모델 명22</th>
								<td class="left">
									<input type="text" name="modelName" id = "modelName" >
								</td>
							</tr> -->
							
							
							
						</tbody>
					</table>
				</div>
			</form>
			
			<!-- btn -->
			<div class="btn_wrap">
				<div class="flt_r">
					<input type="button" class="list_btn" id="listBtn" value="목록" onclick="location.href = '/rent/rentCarDetailList'" />
<%-- 					 <c:if test="${param.mode eq 'write'}"> --%>
						 <input type="button" class="write_btn" value="등록" id = "enroll" >
					<%-- </c:if>
					<c:if test="${param.mode eq 'edit'}">
						<input type="button" class="edit_btn" id="editProCBtn" value="수정" />
					</c:if>  --%>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>