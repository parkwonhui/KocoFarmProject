<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/jsp/comm/top.jsp" flush="false"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="/Kocofarm/css/module/file_Room.css" />



<!-- SubTitle Area -->
<div class="sub_title">

	<div class="sub_title_top">
		<div class="sub_title_inner">
			<h2>
				자료실 <span>파일을 올릴수 있습니다.</span>
			</h2>
			<ul class="sub_nav">
				<li>홈 > 자료실 ></li>
				<li class="on">상세정보</li>
			</ul>
		</div>
	</div>
</div>
<a href="fileUpload.do">파일업로드</a>
<div class="contents_wrap">
	<form action="fileList.do" id="FileForm">
		<input type="hidden" name="mode" id="mode" value="" />

		<div class="sch_wrap">
			<p class="tit">검색</p>
			<div class="sch_slide_btn">
				<img id="slideBtnImg" class="upBtn"
					src="/KocoFarmPro/img/comm/list_up_btn.png" alt="메뉴 접기" />
			</div>
			<div class="sch_toggle_wrap">
				<div class="sch_box_wrap">
					<div class="right">
						<select name="schType" id="schType">
							<option value="">전체</option>
							<option value="title"
								${param.schType eq "title" 	 ? "selected='selected'" : ""}>제목</option>
							<option value="contents"
								${param.schType eq "contents" ? "selected='selected'" : ""}>내용</option>
						</select> <input type="text" name="schWord" id="schWord"
							value="${param.schWord != null ? param.schWord : ''}"
							placeholder="검색어를 입력 해 주세요" /> <input type="button"
							class="schBtn" id="schBtn" value="검색" />
					</div>


				</div>
			</div>

		</div>
	</form>
	
	<!-- Tab -->
	<div class="tab_wrap">
		<ul class="tab_ul">
			<li class="tab_li on" id="fileImgList">썸네일 보기</li>
			<li class="tab_li" id="fileBasicList">목록 보기</li>
		</ul>
	</div>
	
	<!-- 좌측 사이드 메뉴 만들고 파일 바둑판 식으로 배열되기 하고 파일 업로드 기능 부여 -->
	<div class="contents">
		<!-- 목록 보기 -->
		<table class="contents_tb" id="imgList" style="margin-bottom:50px">
			<tbody id="contentsTbBody">
				<%-- <c:forEach var="list" items="${fileList.list}" varStatus="status"> --%>
				<tr>
				<c:set var ="i" value="0"/>
				<c:set var ="j" value="4"/>
				
				<c:forEach var="list" items="${fileList}" varStatus="status">
					<!-- c: forEach var= 이이름으로 담는 것임 -->
					<c:if test="${i%j == 0} ">
						<tr>					
					</c:if>

					<td>
						<c:if test="${list.fileExtention eq null}">
						<img alt="" src="/KocoFarmPro/img/extention_image/pdf.png">
						</c:if>
						<c:if test="${list.fileExtention eq 'jpg'}">
						<img alt="" src="/KocoFarmPro/img/extention_image/jpg.png">
						</c:if>
						<c:if test="${list.fileExtention eq 'doc'}">
						<img alt="" src="/KocoFarmPro/img/extention_image/doc.png">
						</c:if>
						<c:if test="${list.fileExtention eq 'txt'}">
						<img alt="" src="/KocoFarmPro/img/extention_image/txt.png">
						</c:if>
						<c:if test="${list.fileExtention eq 'pdf'}">
						<img alt="" src="/KocoFarmPro/img/extention_image/pdf.png">
						</c:if>
						<div>${list.fileId}</div>
						<div>${list.fileName}</div>
						<div>${list.filePath}</div>
						<div>${list.fileDate}</div>
						<!-- <td>${file.empId}</td> -->
						<div>${list.fileExtention}</div>
						<div>${list.fileSize}</div>
						
					</td>
						<c:if test="${i%j == j-1}">
						</tr>
						</c:if>
					<c:set var="i" value = "${i+1}"/>
				</c:forEach>
				</tr>
			</tbody>
		</table>
		
		
		
		<!-- 목록 보기 -->
		<table class="contents_tb dp_none" id="basicList" style="margin-bottom:50px">
			<colgroup>
			
			</colgroup>
			<thead>
				<tr>
					<th scope="col">NO</th>
					<th scope="col">제목</th>
					<th scope="col">경로</th>
					<th scope="col">업로드 날짜</th>
					<!-- <th scope="col">등록자</th> -->
					<th scope="col">확장자</th>
					<th scope="col">크기</th>
				</tr>
			</thead>
			<tbody id="contentsTbBody">
				<%-- <c:forEach var="list" items="${fileList.list}" varStatus="status"> --%>
				<c:forEach var="list" items="${fileList}" varStatus="status">
					<!-- c: forEach var= 이이름으로 담는 것임 -->
					<tr>
						<td>${list.fileId}</td>
						<td>${list.fileName}</td>
						<td>${list.filePath}</td>
						<td>${list.fileDate}</td>
						<!-- <td>${file.empId}</td> -->
						<td>${list.fileExtention}</td>
						<td>${list.fileSize}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
	</div>
</div>

<script>
$(function(){
	$(".tab_li").click(function(){
		var id= $(this).attr("id");
		
		if("fileImgList" == id){
			$("#imgList").show();
			$("#basicList").hide();
		}else{
			$("#imgList").hide();
			$("#basicList").show();
		}
		
	});
});

</script>

<script type="text/javascript" src="/Kocofarm/js/module/fileRoom.js"></script>
<jsp:include page="/jsp/comm/bottom.jsp" flush="false"></jsp:include>