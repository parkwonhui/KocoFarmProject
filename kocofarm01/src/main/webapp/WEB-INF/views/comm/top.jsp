<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="/resources/css/comm/common.css" />
<link rel="stylesheet" type="text/css" href="/resources/css/comm/top.css" />
<script type="text/javascript" src="/resources/js/comm/jquery.js"></script>
<script type="text/javascript" src="/resources/js/comm/comm.js"></script>
<!-- datePicker -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<div id="wrap">
	<div class="top_wrap">
		<div class="top">
			<div class="gnb_wrap">
				<div class="gnb">
					<nav class="gnb_inner">
						<div class="logo">
							<a href="/main"><img class="logo_img" /></a>
						</div>
						<div class="gnb_top">
							<ul class="gnb_top_ul">
								<c:choose>
									<c:when test="${not empty loginVO}">
										<li>${loginVO.korNm}님 환영 합니다.</li>
										<li><a href="/signIn/out">로그아웃</a></li>
									</c:when>
									<c:otherwise>
										<li>방문을 환영 합니다.</li>
										<li><a href="/signIn/page">로그인</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</div>
						
						<ul class="gnb">
							<li class="top_menu_li"><a href="">공지사항</a></li>
							<li class="top_menu_li"><a href="">인사 관리</a></li>
							<li class="top_menu_li"><a href="/approval/getEmpDraftList">문서 결재</a></li>
							<li class="top_menu_li"><a href="/rent/rentCarDetailList">대여 관리</a></li>
							<li class="top_menu_li"><a href="/schedule/">일정 관리</a></li>
						</ul><!-- gnb E -->
						
					</nav><!-- gnb_inner E -->
				</div>
			</div>
		</div>
	</div>
	<!-- left_menu -->
	<jsp:include page="/WEB-INF/views/comm/left.jsp" flush="false" ></jsp:include>