<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<!-- <link rel="stylesheet" type="text/css" href="/resources/css/module/rent.css" /> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />

<div class="cont_wrap">
	<!-- SubTitle Area -->
	<div class="sub_title">
		<div class="sub_title_top">
			<div class="sub_title_inner">
				<h2>
					Reservation List <span>회의실 예약 목록</span>
				</h2>
				<ul class="sub_nav">
					<li>홈 ></li>
					<li class="on">회의실 예약 목록</li>
				</ul>
			</div>
		</div>
	</div>

	<!--회의실 예약 목록 -->
	<div class="contents_wrap">
		<div class="contents">
			<!-- 목록 보기 -->
			<form action="/reservation/delReserv" method="post">
				<table class="contents_tb" id="contTb">
					<colgroup>
						<col width="10%">
						<col width="15%">
						<col width="10%">
						<col width="10%">
						<col width="10%">
						<col width="12.5%">
						<col width="12.5%">
						<col width="10%">
						<col width="10%">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">예약 번호</th>
							<th scope="col">회의실 제목</th>
							<th scope="col">시작 일</th>
							<th scope="col">종료 일</th>
							<th scope="col">부서</th>
							<th scope="col">사용자</th>
							<th scope="col">예약자</th>
							<th scope="col">회의실 번호</th>
							<th scope="col">등록 일</th>
						</tr>
					</thead>
					<tbody id="contentsTbBody">
						<c:choose>
							<c:when test="${not empty list}">
								<c:forEach var="reserv" items="${list}" varStatus="status">
									<tr>
										<td><input type="checkbox" name="delReserv"
											value="${reserv.rvId}" />${reserv.rvId}</td>
										<td>${reserv.mTitle}</td>
										<fmt:parseDate var="dateString" value="${reserv.startDt }"
											pattern="yyyy-MM-dd" />
										<td><fmt:formatDate value="${dateString}"
												pattern="yyyy-MM-dd" /></td>
										<fmt:parseDate var="dateString2" value="${reserv.endDt}"
											pattern="yyyy-MM-dd" />
										<td><fmt:formatDate value="${dateString2}"
												pattern="yyyy-MM-dd" /></td>
										<td>${reserv.deptNm}</td>
										<td>${reserv.rvUserNm}</td>
										<td>${reserv.rvWriterNm}</td>
										<td>${reserv.mId}</td>
										<fmt:parseDate var="dateString3" value="${reserv.regDt}"
											pattern="yyyy-MM-dd" />
										<td><fmt:formatDate value="${dateString3}"
												pattern="yyyy-MM-dd" /></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="9">데이터가 없습니다.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<br /> 
			<div class="btn_wrap">
				<div class="flt_r">
					<c:if test="${loginVO.authority == 99}">
						<input class="auto_wth_btn_b" type="submit" value="삭제" />
					</c:if>
				</div>
			</div>	
			</form>
		</div>
	</div>
	<script type="text/javascript" src="/resources/js/module/rent.js"></script>
	<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false"></jsp:include>