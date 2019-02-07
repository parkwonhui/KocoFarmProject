<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/jsp/comm/top.jsp" flush="false"></jsp:include>
<!-- <link rel="stylesheet" type="text/css" href="/KocoFarmPro/css/module/fileRoom.css" /> -->
	<!-- SubTitle Area -->
	<div class="sub_title">
		<div class="sub_title_top">
			<div class="sub_title_inner">
				<h2>
					자료실 <span id="subTitSpan">파일을 올릴 수 있습니다.</span>
				</h2>
				<ul class="sub_nav">
					<li>홈 > 자료실 ></li>
					<li class="on" id="subTitLi">등록</li>
				</ul>
			</div>
	</div>
	<!-- Contents Area -->
	<div class="contents_wrap">
		<!-- write -->
		<form action="insertFile.do" id="fileForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="empId" value="${loginVO.empId}" />
			
			<div class="contents">
				<!-- 등록 -->
				<table class="contents_tb wr" id="contTb">
					<colgroup>
						<col width="20%">
						<col width="*">
					</colgroup>
					<tbody id="contentsTbBody">
						<tr>
							<th scope="col">파일</th>
							<td class="left">
								<input type="file" name="fileName">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
		
			<!-- btn -->
			<div class="btn_wrap">
				<div class="flt_r">
					<input type="submit" class="auto_wth_btn_b" value="업로드" />
				</div>
			</div>
		
		</form>
	</div>
</div>
<!-- <script type="text/javascript" src="/KocoFarmPro/js/module/fileRoom.js"></script> -->
<jsp:include page="/jsp/comm/bottom.jsp" flush="false"></jsp:include>