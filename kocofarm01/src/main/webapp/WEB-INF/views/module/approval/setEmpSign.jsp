<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="/resources/css/module/approval.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
		integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="crossorigin="anonymous"></script>

<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<link rel="stylesheet" type="text/css" href="/resources/css/module/approval.css" />

<div class="cont_wrap">
	<!-- SubTitle Area -->
	<div class="sub_title">
		<div class="sub_title_top">
			<div class="sub_title_inner">
				<h2>
					Approval <span>결재 관련 내용을 확인할 수 있습니다.</span>
				</h2>
				<ul class="sub_nav">
					<li>홈 ></li>
					<li class="on">문서 결재</li>
				</ul>
			</div>
		</div>
	</div>

	<!-- Contents Area -->
	<div class="contents_wrap">
		<!-- basic draft input  -->
		<div class="draft_wrap">
			<h1 class="txt_c">기본 정보</h1>
			<div class="inf_wrap_box_sign">
				<p class="name">
					<b>이름</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${emp.korNm }
				</p>
				<p class="position">
					<b>직위</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${emp.positionNm}
				</p>
				<p class="dep">
					<b>부서</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${emp.deptNm }
				</p>
				<p class = "empSign">
					<b>사인</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
					<c:if test = "${emp.empSign  ne null}" >
						<input type='image' class="singImage" id ='signImage' src ='/resources/img/approval/${emp.empSign}' />								
						<input type = "button" class="register" id ="uploadSignBtn" value ="수정하기" />
					</c:if>
					<c:if test = "${emp.empSign eq null}" >
						<input type = "button" class="register" id ="uploadSignBtn" value ="등록하기" />
					</c:if>
					<input type = "file" class="fileNm" name = "uploadFile" />
				
				</p>
			</div>
			<p></p>
			<p></p>

		</div>
	</div>
	
</div>
<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false"></jsp:include>

