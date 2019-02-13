<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/rent.css" />

	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>MeetingRoom <span>등록된 회의을 수정할  수 있습니다.</span></h2>
					<ul class="sub_nav">
						<li>홈 > </li>
						<li class="on">회의실 수정</li>
					</ul>
				</div>
			</div>
		</div>
		
		<div class="contents_wrap">
			<div class="contents">
				<!-- 수정 -->
				<form action="/meetingroom/mroomUpdate" method="post">
					<table class="contents_tb wr" id="contTb">
						<colgroup>
							<col width="15%">
						</colgroup>
						<tbody id="contentsTbBody">
							<tr>
								<th scope="col">회의실 이름</th>
								<td class="left">
									<input type="text" name="mName" value="${mroom.mName}">
								</td>
							</tr>
							<tr>
								<th scope="col">인원</th>
								<td class="left">
									<input type="text" name="pNum" value="${mroom.pNum}">
								</td>
							</tr>
						</tbody>
					</table>
					<div class="btn_wrap">
						<div class="flt_r">
							<input class="auto_wth_btn_b" type="submit" value="수정">
						</div>
					</div>
				</form>
			</div>
		</div>
		
	</div>
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>