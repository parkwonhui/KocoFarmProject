<%@page import="org.kocofarm.service.module.EmpService"%>
<%@page import="org.kocofarm.controller.module.EmpController"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet"href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" type="text/css"	href="/resources/css/module/approval.css" />
<link rel="stylesheet" type="text/css"href="/resources/css/comm/common.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/module/approval.js"></script>
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion({
			header : "> div > h3"
		}).sortable({
			axis : "y",
			handle : "h3",
			stop : function(event, ui) {
				// IE doesn't register the blur when sorting
				// so trigger focusout handlers to remove .ui-state-focus
				ui.item.children("h3").triggerHandler("focusout");

				// Refresh accordion to handle new order
				$(this).accordion("refresh");
			}
		});
	});

	/* 클로저 사용해서 부모 페이지 값 전달 */
	/* window.onload = function(){
		var links = document.getElementsByTagName('a');

  		for(var i =0; i<links.length; i++){
  			(function(tmp){
  				links[tmp].onclick=function(){
  					if(tmp%4 == 0){
  					window.opener.document.setVac.replacementId.value = links[tmp].innerHTML;
  					window.opener.document.setVac.korNm.value =  links[tmp+1].innerHTML; 
  					window.opener.document.setVac.positionNm.value =  links[tmp+2].innerHTML; 
  					window.opener.document.setVac.cellPhone.value =  links[tmp+3].innerHTML; 
  					self.close();
  					}
  				}
  			})(i);
  		}
	}	
	 */
</script>


</head>
<body>
	<center>
		<h2>대체 근무자</h2>
	</center>
	<div class="total_wrap">
		<div class="header_wrap">
			<!-- th 정보 테이블 시작 -->
			<!-- 			<table id="header" border="1" width=100%>
				<tr>
					<th>사원목록</th>
					
				</tr>
			</table> -->
			<!-- th 정보 테이블 끝-->
		</div>

		




	</div>
</body>
</html>


