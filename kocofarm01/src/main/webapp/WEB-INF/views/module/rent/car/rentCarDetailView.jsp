<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>


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
			<form action="" id ="rentCarDetailForm" >
				<input type = "hidden" name = "mode" id = "mode" value = "${param.mode}}" /> 
				<input type = "hidden" name = "car_id" id = "car_id" value = "${rentCarDetail.carId}" />
				
				<div class="contents">
					<!-- 상세 정보 -->
					<table class="contents_tb vw" id="rentCarDetailView">
						<colgroup>
							<col width="20%">
							<col width="*">
						</colgroup>
						<tbody>
							<tr>
								<th scope="col">차량번호</th>
								<td class="left">${rentCarDetail.carId }</td>
							</tr>
							<tr>
								<th scope="col">차량 모델 명</th>
								<td class="left">${rentCarDetail.modelName}</td>
							</tr>
							<tr>
								<th scope="col">차종</th>
								<td class="left">${rentCarDetail.carModel }</td>
							</tr>
							<tr>
								<th scope="col">구입 조건</th>
								<td class="left">${rentCarDetail.condition }</td>
							</tr>
							<tr>
								<th scope="col">가격</th>
								<td class="left">${rentCarDetail.price } 만원</td>
							</tr>
							<tr>
								<th scope="col">연식</th>
								<td class="left">${rentCarDetail.year} 년</td>
							</tr>
							<tr>
								<th scope="col">유종</th>
								<td class="left">${rentCarDetail.oilType }</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			
			<!-- btn -->
			<div class="btn_wrap">
				<div class="flt_r">
					<input type="button" class="auto_wth_btn_y" value="수정" id = "updateForm">
					<input type="button" class="auto_wth_btn_r" value="삭제" id = "delete">
				</div>
			</div>
		</div>
	</div>
	
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>