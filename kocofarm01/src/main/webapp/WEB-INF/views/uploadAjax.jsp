<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<h3>Upload with Ajax</h3>

	<div class='uploadDiv'>
		<input type="file" name="uploadFile" multiple>
	</div>
	
	<div class="uploadResult">
	<ul>
	
	</ul>
	
	</div>
	

	<button id="uploadBtn">Upload</button>

	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>


	<script>
	
	var uploadResult = $(".uploadResult ul");
	
	function showUploadedFile(uploadResultArr) {
		
		var str ="";
		
		$(uploadResultArr).each(function(i, obj) {
			
			str += "<li>" + obj.fileName + "</li>";
		});
			
		uploadResult.append(str);
		
	}
	
	

		
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		var maxSize = 5242880; //5mb
		
		function checkExtension(fileName, fileSize){
			
			if(fileSize >= maxSize){
				alert("���ϻ����� �ʰ�");
				return false;
				
			}
			
			if(regex.test(fileName)){
				alert("�ش� ������ ������ ���ε� �� �� �����ϴ�");
				return false;
				
			}
			return true;
		}
		
		var cloneObj = $(".uploadDiv").clone();
		
$(document).ready( 
		
	$("#uploadBtn").on("click",function(e){
		
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
		
		//add filedata to formdata
		for(var i = 0; i < files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			
			
			formData.append("uploadFile", files[i]);
		}
		
		$.ajax({
			url: '/uploadAjaxAction',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			datatype : 'json',
			success: function(result) {
				
				console.log(result);
				
				showUploadedFile(result);
				
				$(".uploadDiv").html(cloneObj.html());//�ٽ� ó�� ����â���� ���ƿ��� �ϴ� ���� �׾߸��� �״�� ctrl + c+v
				
			}
		}); //$.ajax
		
	});
/* }); */
</script>
</body>
</html>