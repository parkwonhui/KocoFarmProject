<%@page import="org.kocofarm.service.module.EmpService"%>
<%@page import="org.kocofarm.controller.module.EmpController"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<head>
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
				ui.item.children("h3").triggerHandler("focusout");
				// Refresh accordion to handle new order
				$(this).accordion("refresh");
			}
		});
	});
	/* 클로저 사용해서 부모 페이지 값 전달 */
	 window.onload = function(){
		var aLinks = document.getElementsByTagName('a');
		var imgLinks = document.getElementsByTagName('img');
		console.log(imgLinks);
		
  		for(var i =0; i<aLinks.length-6; i++){
  			(function(tmp){
  				aLinks[tmp].onclick=function(){
  					if(tmp%3 == 0){
  						if($("#first").val() == ""){
  							$("#first").val(aLinks[tmp+1].innerHTML);
  							$("#firstId").val(aLinks[tmp].innerHTML);
  						}else if($("#second").val() == ""){
  							$("#second").val(aLinks[tmp+1].innerHTML);
  							$("#secondId").val(aLinks[tmp].innerHTML);
  						}else if($("#third").val() == ""){
  							$("#third").val(aLinks[tmp+1].innerHTML);
  							$("#thirdId").val(aLinks[tmp].innerHTML);
  						}else if($("#fourth").val() == ""){
  							$("#fourth").val(aLinks[tmp+1].innerHTML);
  							$("#fourthId").val(aLinks[tmp].innerHTML);
  						}else if($("#fifth").val() == ""){
  							$("#fifth").val(aLinks[tmp+1].innerHTML);
  							$("#fifthId").val(aLinks[tmp].innerHTML);
  						}else {
  							alert("최대 5명까지 입력이 가능합니다.")
  						}
  					}
  				}
  			})(i);
  		
		}
  		
  		/* x버튼 누르면 데이터 당겨지는 js */
  		for(var j =0; j<imgLinks.length; j++){
  			(function(tmp2){
  				imgLinks[tmp2].onclick=function(){
	  					if($("#first").val() != "" && imgLinks[tmp2].id == "firstBn"){
	  						$("#first").val($("#second").val());
	  						$("#second").val($("#third").val());
	  						$("#third").val($("#fourth").val());
	  						$("#fourth").val($("#fifth").val());
	  						$("#fifth").val("");
	  						
	  						$("#firstId").val($("#secondId").val());
	  						$("#secondId").val($("#thirdId").val());
	  						$("#thirdId").val($("#fourthId").val());
	  						$("#fourthId").val($("#fifthId").val());
	  						$("#fifthId").val("");
	  					}else if($("#second").val() != "" && imgLinks[tmp2].id == "secondBn"){
	  						$("#second").val($("#third").val());
	  						$("#third").val($("#fourth").val());
	  						$("#fourth").val($("#fifth").val());
	  						$("#fifth").val("");
	  						
	  						$("#secondId").val($("#thirdId").val());
	  						$("#thirdId").val($("#fourthId").val());
	  						$("#fourthId").val($("#fifthId").val());
	  						$("#fifthId").val("");
	  					}else if($("#third").val() != "" && imgLinks[tmp2].id == "thirdBn"){
	  						$("#third").val($("#fourth").val());
	  						$("#fourth").val($("#fifth").val());
	  						$("#fifth").val("");
	  						
	  						$("#thirdId").val($("#fourthId").val());
	  						$("#fourthId").val($("#fifthId").val());
	  						$("#fifthId").val("");
	  					}else if($("#fourth").val() != "" && imgLinks[tmp2].id == "fourthBn"){
	  						$("#fourth").val($("#fifth").val());
	  						$("#fifth").val("");
	  						
	  						$("#fourthId").val($("#fifthId").val());
	  						$("#fifthId").val("");
	  					}else if($("#fifth").val() != "" && imgLinks[tmp2].id == "fifthBn"){
	  						$("#fifth").val("");
	  						$("#fifthId").val("");
	  					}
  				}
  			})(j);
  		}
  		$(".submitApprBtn").click(function(){
				if($("#first").val() == ""){
					alert("결재자를 입력하세요.")
				}else if($("#first").val() != "" && $("#second").val() == ""){
		               window.opener.document.setEmpId.empNameList.value = $("#first").val();
		               window.opener.document.setEmpId.empIdList.value = $("#firstId").val();
		               self.close();
		        }else if($("#second").val() != "" && $("#third").val() == ""){
		               window.opener.document.setEmpId.empNameList.value = $("#first").val()+","+$("#second").val();
		               window.opener.document.setEmpId.empIdList.value = $("#firstId").val()+","+$("#secondId").val();
		               self.close();
		        }else if($("#third").val() != "" && $("#fourth").val() == ""){
		               window.opener.document.setEmpId.empNameList.value = $("#first").val()+","
		               +$("#second").val()+","+$("#third").val();
		               window.opener.document.setEmpId.empIdList.value = $("#firstId").val()+","
		               +$("#secondId").val()+","+$("#thirdId").val();
		               self.close();
		        }else if($("#fourth").val() != "" && $("#fifth").val() == ""){
		               window.opener.document.setEmpId.empNameList.value = $("#first").val()+","
		               +$("#second").val()+","+$("#third").val()+","+$("#fourth").val();
		               window.opener.document.setEmpId.empIdList.value = $("#firstId").val()+","
		               +$("#secondId").val()+","+$("#thirdId").val()+","+$("#fourthId").val();
		               self.close();
		        }else if($("#fifth").val() != ""){
		               window.opener.document.setEmpId.empNameList.value = $("#first").val()+","
		               +$("#second").val()+","+$("#third").val()+","+$("#fourth").val()+","+$("#fifth").val();
		               window.opener.document.setEmpId.empIdList.value = $("#firstId").val()+","
		               +$("#secondId").val()+","+$("#thirdId").val()+","+$("#fourthId").val()+","+$("#fifthId").val();
		               self.close();
		        }
	
			});
	}
  		
  		
	 
</script>


</head>
<body>
	<center>
		<h2>결재자 등록</h2>
	</center>
	<div class="total_wrap">
	
		<div class="header_wrap">
		<table>
			<tr>
				<td>
					<div class = "accordion_table" id="accordion">
						<c:forEach var="DepartmentsVO" items="${depList }">
							<div class="group">
								<h3 style="width:300px">${DepartmentsVO.deptNm}</h3>
								<div class="wrap ui-accordion-content ui-corner-bottom ui-helper-reset ui-widget-content ui-accordion-content-active" id="ui-id-2" aria-labelledby="ui-id-1" role="tabpanel" aria-hidden="false" style="display: block;padding: 2px;width:300px;">
									<table class="empTable" style="float:left;">
										<thead>
											<tr>
												<th width=150px>사번</th>
												<th width=150px>이름</th>
												<th width=150px>직책</th>
											</tr>
										</thead>
			
										<c:forEach var="EmpVO" items="${empList }">
											<c:if test="${DepartmentsVO.deptNm eq EmpVO.deptNm }">
												<tr>
													<td width=150px><a href="#">${EmpVO.empId}</a></td>
													<td width=150px><a href="#">${EmpVO.korNm}</a></td>
													<td width=150px><a href="#">${EmpVO.positionNm}</a></td>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</div>
							</div>
			
						</c:forEach>
					</div>
				</td> 	
				<td>
					<table width = 200px height=500px class = "approveTable" style="float:right; margin-left:60px;">
						<thead>
							<tr>
								<th colspan="3">결재자 리스트</th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<td width = 20> 1 . </td>
								<td> <input type="text" id = "first" name = "firstEmpNm" value = "">
									 <input type="hidden" id = "firstId" name = "firstEmp" value = ""></td>
								<td> <a href = "#"><img id="firstBn" src="/resources/img/approval/setNullBt.png" class="setNullBt" /></a></td>
								</tr>
							
							<tr>
								<td width = 20> 2 . </td>
								<td> <input type="text" id = "second" name = "secondEmpNm" value = "">
									 <input type="hidden" id = "secondId" name = "secondEmp" value = ""></td>
								<td> <a href = "#"><img id="secondBn" src="/resources/img/approval/setNullBt.png" class="setNullBt" /></a></td>
							</tr>
							
							<tr>
								<td width = 20> 3 . </td>
								<td> <input type="text" id = "third" name = "thirdEmpNm" value = "">
									 <input type="hidden" id = "thirdId" name = "thirdEmp" value = ""></td>
								<td> <a href = "#"><img id="thirdBn" src="/resources/img/approval/setNullBt.png" class="setNullBt" /></a></td>
							
							</tr>
							
							<tr>
								<td width = 20> 4 . </td>
								<td> <input type="text" id = "fourth" name = "fourththEmpNm" value = "" >
									 <input type="hidden" id = "fourthId" name = "fourthEmp" value = ""></td>
								<td> <a href = "#"><img id="fourthBn" src="/resources/img/approval/setNullBt.png" class="setNullBt" /></a></td>
							</tr>
							
							<tr>
								<td width = 20> 5 . </td>
								<td> <input type="text"id = "fifth"  name = "fifthEmpNm" value = "">
									 <input type="hidden" id = "fifthId" name = "fifthEmp" value = ""></td>
								<td> <a href = "#"><img id="fifthBn" src="/resources/img/approval/setNullBt.png" class="setNullBt" /></a></td>
							</tr>
						</tbody>
					</table>
				
				</td>
			</tr>
			
			<tr>
				<td colspan = "2"><input type="button" class="submitApprBtn" value="입력"></td>
			</tr>
		</table>
			
			

	</div>
</body>
</html>
