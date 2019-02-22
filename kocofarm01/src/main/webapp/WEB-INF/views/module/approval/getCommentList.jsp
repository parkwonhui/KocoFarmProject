<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>

<style>
.CommentTable {
	width: 700px;
	margin-left: 410;
	border-top: 1px solid #444444;
	border-collapse: collapse
}

.NameNum1, .CommentValue1 {
	border-bottom: 1px solid #444444;
	padding: 10px;
	vertical-align: middle;
}

</style>
<script type="text/javascript">

$(".delBtn").click(function(){
	var draftId = $("#draftId").val();
	var empId = $("#empId").val();
	var commentId = $(this).attr("id");
	console.log($(this).attr("id"));
 	var param = {"draftId" : draftId, "empId" : empId, "commentId" : commentId};
	 $.ajax({
		type : "POST",
		url : "delComment",
		data : param,
		success : function(){
			alert("���ϵ帳�ϴ�. �������� ����� �����Ǿ����ϴ�.");
			getCommentList();
		}
	}); 
});

</script>



<body>
	<table class="CommentTable">
		<c:forEach var="row" items="${list}">
			<tr class="Commentcontainer">
				<th class="NameNum1">�� �� �� ȣ</th>
				<th class="NameNum1">�� �� ��</th>
				<th class="NameNum1">�� �� �� ¥</th>
				<th class="NameNum1">��         ��</th>
				<th class="NameNum1">��         ��</th>
			</tr>
			<tr class="Commentcontainer2">
				<td class="CommentValue1">${row.commentId }</td>
				<td class="CommentValue1">${row.empId}</td>
				<td class="CommentValue1">(${row.commentDt })</td>
				<td class="CommentValue1">${row.commentContents }	</td>        
				<td class="CommentValue1">
				<!-- <input type="button" value="����" class="setUpBtn" style="width: 40%; height: 20; text-align: center; "> -->
				<input type="button" value="����" id="${row.commentId }" class="delBtn" style="width: 70%; height: 20;">
				</td>
			
			</tr>
			
		</c:forEach>
	</table>
</body>
</html>
