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

	<div class="bigPictureWrapper">
		<div class="bigPicture"></div>


	</div>



	<style>
.uploadResult {
	width: 100%;
	background-color: gray;
}

.uploadResult ul {
	display: flex;
	flex-flow: row;
	justify-content: center;
	align-items: center;
}

.uploadResult ul li {
	list-style: none;
	padding: 10px;
}

.uploadResult ul li img {
	width: 100px;
}

.uploadResult ul li span {
	color: white;
}

.bigPictureWrapper {
	position: absolute;
	display: none;
	justify-content: center;
	align-items: center;
	top: 0%;
	width: 100%;
	height: 100%;
	background-color: gray;
	z-index: 100;
	background: rgba(255, 255, 255, 0, 5);
}

.bigPicture {
	position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
}

.bigPicture img {
	width: 600px;
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

function showImage(fileCallPath){
	//alert(fileCallPath)
	
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture")
	.html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'/>")
	.animate({width:'100%', height: '100%'}, 0);
	
	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0%', height: '0%'}, 10);
	setTimeout(() => {
		$(this).hide();
		}, 10);
	});
 
}

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
			//uploadfile 키값에 file[0]을 매핑하여 폼데이터에 추가는 것임
		}
		
		var uploadResult = $(".uploadResult ul");
		
		function showUploadedFile(uploadResultArr){
		//동작 내용까지 쇼 업로드 파일 밑에 넣으면 쇼업 예하가 너무 길어지는데 그게 싫어서 에이젝스로 별도 동작하고 그 데이터 값만 넣어온것 같음
			var str = "";
			
			$(uploadResultArr).each(
					function(i, obj){
						//function() 에서 i 는 인덱스, obj 는 뭐 어떤 데이터 값
						if(!obj.image){
				
						var fileCallPath = encodeURIComponent( obj.filePath +"/"+obj.uuid +"_"+ obj.fileName);
						
						str += "<li><div><a href='/download?fileName="+fileCallPath+"'>"
								+"<img src='/resources/img/doc.png'>"+obj.fileName+"</a>"
								+"<span data-file=\'"+fileCallPath+"\'data-type='file'> x </spqn>" +"<div></li>" 
					}else{
						
						var fileCallPath = encodeURIComponent(obj.filePath+ "/s_" + obj.uuid +"_"+obj.fileName);
						
						var originPath = obj.filePath +"\\" + obj.uuid +"_"+obj.fileName;
						
						originPath = originPath.replace(new RegExp(/\\/g),"/");
						
						
						str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
						"<img src='display?fileName="+fileCallPath+"'></a>"+
						"<span data-file=\'"+fileCallPath+"\' data-type='image'> x</spna>"+
						"<li>";
						
						
						
					}
						
						$('.uploadResult').on("click","span",function(e){
							var targetFile = $(this).data("file");
							var type = $(this).data("type");
							console.log(targetFile);
							console.log(type);
							
							$.ajax({
								url:'/deleteFile',
								data: {"fileName" : targetFile , "type":type},
								dataType : "text",
								type: 'POST',
								success: function(result){
									alert(result);
								}
								
							});
			});

				})
			
			
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