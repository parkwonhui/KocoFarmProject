<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/resources/css/module/rent.css" />

<div class="cont_wrap">
	<!-- SubTitle Area -->
	<div class="sub_title">
		<div class="sub_title_top">
			<div class="sub_title_inner">
				<h2>
					MeetingRoom <span>회의실을 등록할 수 있습니다.</span>
				</h2>
				<ul class="sub_nav">
					<li>홈 ></li>
					<li class="on">회의실 등록</li>
				</ul>
			</div>
		</div>
	</div>

	<div class="contents_wrap">
		<div class="contents">
			<!-- 등록 -->
			<form action="/meetingroom/mroomInsert" method="post">
				<table class="contents_tb wr" id="contTb">
					<colgroup>
						<col width="15%">
					</colgroup>
					<tbody id="contentsTbBody">
						<tr>
							<th scope="col">회의실 번호</th>
							<td class="left"><input type="text" name="mId" id="mId"
								placeholder="회의실 번호를 확인해주세요." />
								<button type="button" id="idChk">회의실 번호 확인</button>
							<p class="result">
								<span class="msg"></span>
							</p>
						</tr>
						<tr>
							<th scope="col">회의실 이름</th>
							<td class="left"><input type="text" name="mName"
								placeholder="+회의실번호를 입력해주세요." /></td>
						</tr>
						<tr>
							<th scope="col">인원</th>
							<td class="left"><input type="text" name="pNum" /></td>
						</tr>
					</tbody>
				</table>
				<div class="btn_wrap">
					<div class="flt_r">
						<input type="submit" class="auto_wth_btn_b" id="submit"
							disabled="disabled" value="등록">
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<script>

$(function(){
	$("#idChk").click(function(){
		var query = {mId : $("#mId").val()};
		
		$.ajax({
			url: "/meetingroom/idChk",
			type: "post",
			data: query,
			dataType: "json",
			success: function(data) {
				if(data == 1){
					$(".result .msg").text("회의실 번호 중복");
					$(".result .msg").attr("style", "color:#f00");
					alert("중복된 회의실 입니다. 다시 입력해주세요.");
					
					$("#submit").attr("disabled", "disabled");	
				}else{
					$(".result .msg").text("등록 가능합니다.");
					$(".result .msg").attr("style", "color:#00f");
					
					$("#submit").removeAttr("disabled");
				}	
			}
		}); //end ajax
	})//end idChk
	
	
})
</script>
<script type="text/javascript" src="/resources/js/module/rent.js"></script>
<script src="/resources/js/comm/jquery.js"></script>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false"></jsp:include>