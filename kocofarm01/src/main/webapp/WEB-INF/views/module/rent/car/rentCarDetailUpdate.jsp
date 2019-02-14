<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>

	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>RentCar <span id="subTitSpan">차량 정보를 수정 할 수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > 차량관리 > </li>
						<li class="on" id="subTitLi">수정</li>
					</ul>
				</div>
			</div>
		</div>
	
		<div class="contents_wrap">
			<!-- write --><!-- action에 /rent/rentCarDetailUpdate  넣어봄,id = "rentCarEditForm" 삭제함 -->
			<form id = "rentCarEditForm" action="" method = "post" >
				<input type="hidden" name="mode" id="mode" value="edit" />
				<input type="hidden" id = "car_id" name="carId" value="${rentCarDetail.carId }"/>
				
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
								<th scope="col">차량 모델 명</th>
								<td class="left">
									<input type = "text" name = "modelName" id="modelName" value='<c:out value="${rentCarDetail.modelName}"/>'>
								</td>
								<th scope="col">차종</th>
								<td class="left">
									<input type = "radio" name="carModel" value = "싼타페">싼타페
							 		<input type = "radio" name="carModel" value = "레이">레이
							 		<input type = "radio" name="carModel" value = "SM3">SM3
				 					<input type = "radio" name="carModel" value = "k3">k3
								</td>
							</tr>
							<tr>
								<th scope="col">구입 조건</th>
								<td class="left">									
									<input type = "radio" name="condition" value="신차" >신차
									<input type = "radio" name="condition" value="중고차">중고차	
								</td>
								<th scope="col">가격</th>
								<td class="left">
									<input type = "text" name = "price" id="price"  value="${rentCarDetail.price }">	
								</td>
							</tr>
							<tr>
								<th scope="col">연식</th>
								<td class="left">
									<input type = "text" name = "year" id = "year" value="${rentCarDetail.year }">	
								</td>
								<th scope="col">유종</th>
								<td class="left">									
									<input type = "radio" name="oilType" value="휘발유">휘발유
									<input type = "radio" name="oilType" value="경유">경유
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<!-- btn -->
			<div class="btn_wrap">
				<div class="flt_r">
					<input type="button" class="list_btn" id="listBtn" value="목록"  />
					 <input type="button" class="auto_wth_btn_y" value="수정완료" id = "Upenroll">				
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript" src="/resources/js/module/rent.js"></script>	
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>