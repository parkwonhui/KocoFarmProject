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
<style>

.uploadResult{

width:100%;
background-color: gray;
}

.uploadResult ul{

display: flex;
flex-flow: row;
justify-content: center;
align-items: center;
}

.uploadResult ul li{
	list-style:none;
	padding: 10px;
	
}
.uploadResult ul li img{
width: 20px;

}

</style>
	<div class="uploadResult">
	<ul>
	
	</ul>
	</div>


	<button id="uploadBtn">Upload</button>

	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>

<script>	
$(document).ready(function(){ 

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
	
	$("#uploadBtn").on("click",function(e){
		
		var formData = new FormData();
		
		var inputFile = $("input[name='uploadFile']");
		
		var files = inputFile[0].files;
		
		console.log(files);
				
	 	 //add filedate to formdata
		for(var i = 0; i < files.length; i++){
			
			if(!checkExtension(files[i].name,files[i].size)){
				return false;
			}
			
			formData.append("uploadFile", files[i]);
			
		}
		
		var uploadResult = $(".uploadResult ul");
		
		function showUploadedFile(uploadResultArr){
		//동작 내용까지 쇼 업로드 파일 밑에 넣으면 쇼업 예하가 너무 길어지는데 그게 싫어서 에이젝스로 별도 동작하고 그 데이터 값만 넣어온것 같음
			var str = "";
			
			$(uploadResultArr).each(
					function(i, obj){
						//function() 에서 i 는 인덱스, obj 는 뭐 어떤 데이터 값
						if(!obj.image){
				str += "<li><img src='/resources/img/file.png'>"
						+ obj.fileName + "</li>";
				// obj 가 어디서 온것인지?
						// function 도..
						
					} else {
						//str += "<li>" + obj.fileName + "</li>";
						var fileCallPath = encodeURIComponent( obj.filePath +"/s_"+obj.uuid +"_"+ obj.fileName);
						
						str += "<li><img src='/display?fileName="+fileCallPath+"'></li>";
					}
				
			});
			
			uploadResult.append(str);
				
		}
 
		$.ajax({
		url:'/uploadAjaxAction',
		processData: false,
		contentType: false,
		data: formData,
		type: 'POST',
		dataType : "json",
		success: function(result){


			console.log(result);
			
			showUploadedFile(result);
			
			$(".uploadDiv").html(cloneObj.html());
		}
		 
	});
	});
	
});
		 
</script>
</body>
</html>