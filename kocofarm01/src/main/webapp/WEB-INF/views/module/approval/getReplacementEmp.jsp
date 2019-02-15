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

	window.onload = function(){
		var links = document.getElementsByTagName('a');

		/* 클로저,,, */
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
	
</script>


</head>
<body>
	<center>
		<h2>대체 근무자</h2>
	</center>
	<div class="total_wrap">

		<div id="accordion">
			<c:forEach var="DepartmentsVO" items="${depList }">
				<div class="group">
					<h3>${DepartmentsVO.deptNm}</h3>
					<div class="wrap ui-accordion-content ui-corner-bottom ui-helper-reset ui-widget-content ui-accordion-content-active" id="ui-id-2" aria-labelledby="ui-id-1" role="tabpanel" aria-hidden="false" style="display: block;height: 120px;padding-left: 2px;padding-top: 2px;padding-right: 2px;padding-bottom: 2px;">
						<table class="empTable">
							<thead>
								<tr>
									<th width=150px>사번</th>
									<th width=150px>이름</th>
									<th width=150px>직책</th>
									<th width=150px>번호</th>
								</tr>
							</thead>

							<c:forEach var="EmpVO" items="${empList }">
								<c:if test="${DepartmentsVO.deptNm eq EmpVO.deptNm }">
									<tr>
										<td width=150px><a href="#">${EmpVO.empId}</a></td>
										<td width=150px><a href="#">${EmpVO.korNm}</a></td>
										<td width=150px><a href="#">${EmpVO.positionNm}</a></td>
										<td width=150px><a href="#">${EmpVO.cellPhone}</a></td>
									</tr>
								</c:if>
							</c:forEach>
						</table>
					</div>
				</div>

			</c:forEach>
		</div>




	</div>
</body>
</html>


