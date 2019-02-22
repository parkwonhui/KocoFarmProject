<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	
<%-- <jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- 폼방식으로 구현

 
<form action="uploadFormAction" method ="post" enctype="multipart/form-data">

	<input type='file' name='uploadFile' multiple>
	<button>Submit</button>
</form>
 -->

	<!-- 새로 추가하는 부분  -->
	<div class="upload row">
		<div class="col-lg-12">
			<div class="defualt">

				<div class="pannel-heading">
					<!-- "pnnel-head end"  -->
					<div class="pannel-body">
						<div class="form-group uploadDiv">

							<input type='file' name='uploadFile' multiple>




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
inline-
.bigPicture img {
	width: 600px;
}

.deleteBtn {
    display: inline-block;
    position: relative;
    margin: 0 20px 0 7px;
    padding: 0;
    width: 4px;
    height: 20px;
    background: #000;
    transform: rotate(45deg);
}
.deleteBtn:before {
    display: block;
    content: "";
    position: absolute;
    top: 50%;
    left: -8px;
    width: 20px;
    height: 4px;
    margin-top: -2px;
    background: #000;
}


/* 
.deleteBtn{
	  background-color: orange;

        display: inline-block;

        border: none;

        outline: none;

        border-radius: 20px;

        box-shadow: 0 9px #B0B0B0;

    }

.deleteBtn:active {

        background-color: #FF8C00;

        box-shadow: 0 5px #808080;

        transform: translateY(4px);

}
 */
</style>

							<!-- 첨부파일 띄우는 창  -->
							<div class="uploadResult">
								<ul>
								</ul>
							</div>
							<!-- <button id="uploadBtn">Upload</button> -->

						</div>
						<!-- form-group-uploadDiv -->
					</div>
					<!-- end pannel-body -->

				</div>
				<!-- end pannel head -->


			</div>
			<!--end default -->


		</div>
		<!--end col lg 12  -->



	</div>
	<!-- end upload row -->
	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>

	<script>
		$(document).ready(function(e) {
			
			/* 업로드 버튼 클릭시 첨부파일 처리를 위해 기본 동작을 막는 작업 ?? */
			var formObj = $("form[role='form']");

			$("button[type='submit']").on("click", function(e) {
				//submit 타입 버튼
				e.preventDefault();

				console.log("submit clicked");
				
				var str ="";
				
				$("uploadResult ul ui").each(function(i, obj){
					
					var jobj = $(obj);
					
					console.dir(jobj);
					
				str += "<input type='hidden' name='attractList["+i+"].filenName'value='"+jobj.data("fileName")+"'>";		
				str += "<input type='hidden' name='attractList["+i+"].uuid'value='"+jobj.data("uuid")+"'>";		
				str += "<input type='hidden' name='attractList["+i+"].uploadPath'value='"+jobj.data("uploadPath")+"'>";		
				str += "<input type='hidden' name='attractList["+i+"].fileType'value='"+jobj.data("fileType")+"'>";		
					
					
				});
				formObj.append(str).submit();

			}); //end $("button[type='submit']").on("click", function(e)
			
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
		
			$("input[type='file']").change(function(e) {

				var formData = new FormData();

				var inputFile = $("input[name='uploadFile']");

				var files = inputFile[0].files;

				//add filedate to formdata
				for (var i = 0; i < files.length; i++) {

					if (!checkExtension(files[i].name, files[i].size)) {
						return false;
					}

					formData.append("uploadFile", files[i]);
					//uploadfile 키값에 file[0]을 매핑하여 폼데이터에 추가는 것임
				}

				$.ajax({
					url : '/uploadAjaxAction',
					processData : false,
					contentType : false,
					data : formData,
					type : 'POST',
					dataType : "json",
					success : function(result) {
						showUploadResult(result);

						//$(".uploadDiv").html(cloneObj.html());
						//업로드 결과 처리 함수
					}

				});//$ajax
			});//input file type change end
			
			function showUploadResult(uploadResultArr){
				//동작 내용까지 쇼 업로드 파일 밑에 넣으면 쇼업 예하가 너무 길어지는데 그게 싫어서 에이젝스로 별도 동작하고 그 데이터 값만 넣어온것 같음
				
				if(!uploadResultArr || uploadResultArr.length == 0){ return; }
				
				// 파일이 있는지 없는지 유무를 판단
				var uploadUL = $(".uploadResult ul");
				
				var str = "";
				
				$(uploadResultArr).each(function(i, obj){
						//function() 에서 i 는 인덱스, obj 는 뭐 어떤 데이터 값
						
						console.log(obj);
						
					 if(obj.fileType){
						 var fileCallPath = encodeURIComponent(obj.uploadPath + obj.uuid +"_"+obj.fileName);
						 console.log("deletefile");
						 	str += "<li data-path='"+obj.uploadPath+"'";
						 	str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"'data-type='"+obj.fileType+"'"
						 	str +" ><div>";
							str += "<span> "+ obj.fileName +"</span>";
							str += "<button type ='button' data-file=\'"+fileCallPath+"\' "
							str += "data-type='image' class='deleteBtn'><i class='fa fa-times'></i></button><br>";
							str += "<img src='/display?fileName="+fileCallPath+"'>";
							str += "</div>";
							str + "</li>";
								
						
						
					}else{
						
						var fileCallPath = encodeURIComponent( obj.filePath +"/"+obj.uuid +"_"+ obj.fileName);
						var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
							
						str += "<li";
						str += " data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"'data-filename='"+obj.fileName+"'data-type='"+obj.fileType+"'><div>";
						str += "<span>" +obj.fileName +"</span>";
						str += "<button type = 'button' data-file=\'"+fileCallPath+"\' data-type='file' "
						str += "class='deleteBtn'><i class='fa fa-times'></i></button><br>";
						str += "<a><img src='/resources/img/doc.png'></a>";
						str += "</div>";
						str + "</li>"; 	
						}
							
					$('.uploadResult').on("click","button",function(e){
						
						console.log("deletefile");
						var targetFile = $(this).data("file");
						var type = $(this).data("type");
						var targetLi = $(this).closest("li");
						//$(this). ~~ 는 선택된 <div>를 가리킴
						
						console.log(targetFile);
						console.log(type);
						
						
						$.ajax({
							url:'/deleteFile',
							data: {fileName: targetFile , type:type},
							dataType : "text",
							type: 'POST',
							success: function(result){
								alert(result);
							
								targetLi.remove();
							}
							
						});
					});
						
						
						
				});
								
			

			$(".uploadResult").append(str);

			}; // showUploadResult
						
		}); // end ready
		

		/* 	
			
			
			 
			
			
		 */
	</script>



</body>
</html>