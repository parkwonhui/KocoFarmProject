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
				alert("파일사이즈 초과");
				return false;
				
			}
			
			if(regex.test(fileName)){
				alert("해당 종류의 파일을 업로드 할 수 없습니다");
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
				
				$(".uploadDiv").html(cloneObj.html());//다시 처음 상태창으로 돌아오게 하는 것임 그야말로 그대로 ctrl + c+v
				
			}
		}); //$.ajax
		
	});
/* }); */
</script>
</body>
</html>