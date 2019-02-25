<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false"></jsp:include>
<%-- <jsp:include page="/WEB-INF/views/comm/top.jsp" flush="false" ></jsp:include> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<!-- ��������� ����

 
<form action="uploadFormAction" method ="post" enctype="multipart/form-data">

	<input type='file' name='uploadFile' multiple>
	<button>Submit</button>
</form>
 -->


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
	background: #666;
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
	background: #666;
}


</style>

	<div class="cont_wrap">
		<!-- SubTitle Area -->
		<div class="sub_title">
			<div class="sub_title_top">
				<div class="sub_title_inner">
					<h2>FileRoom <span>������ ���ε� �� �� �ֽ��ϴ�.</span></h2>
					<ul class="sub_nav">
						<li>Ȩ > </li>
						<li class="on">���� ���ε�</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Contents Area -->
		<div class="contents_wrap">
		
						<div class="sch_box_wrap">
							<div class="right">
								<select name="schType" id="schType">
									<option value="">��ü</option>
									<option value="title" ${param.schType eq "title" ? "selected='selected'" : ""}>����</option>
								</select>
								<input type="text" name="schWord" id="schWord" value="${param.schWord != null ? param.schWord : ''}" placeholder="�˻�� �Է� �� �ּ���" />
								<input type="button" class="schBtn" id="schBtn" value="�˻�" />
							</div>
						</div>
			<!-- sch_top -->
			<form action="uploadAjaxAction" id="uploadAjaxAction">
				<input type="hidden" name="mode" id="mode" value="" />
				
				<div class="sch_wrap">
				
<!-- 					<p class="tit">�˻�</p> -->
					<div class="sch_toggle_wrap">
					<div class="contents">
				<div class="form-group uploadDiv">

							<input type='file' name='uploadFile' multiple>




							<div class="bigPictureWrapper">
								<div class="bigPicture"></div>


							</div>
					
							

							<!-- ÷������ ���� â  -->
							<!-- <div class="uploadResult">
								<ul>
								</ul>
							</div> -->
							<!-- <button id="uploadBtn">Upload</button> -->

						</div>
						</div>
					</div>
				</div>
			</form>
			<div class="uploadResult">
				<ul>
				</ul>
			</div>
		</div>
	</div>

		
		
	
	<script src="https://code.jquery.com/jquery-3.3.1.js"
		integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
		crossorigin="anonymous"></script>

	<script>
		$(document).ready(	function(e) {
				/* ���ε� ��ư Ŭ���� ÷������ ó���� ���� �⺻ ������ ���� �۾� ?? */
				var formObj = $("form[role='form']");
					$("button[type='submit']").on("click",	function(e) {
									
										console.log("submit clicked");
									var str = "";
									$("uploadResult ul ui").each(
									function(i, obj) {var jobj = $(obj);
										console.dir(jobj);
									str += "<input type='hidden' name='filenName'value='"+ jobj.data("fileName")+ "'>";
									str += "<input type='hidden' name='uuid'value='"+ jobj.data("uuid")	+ "'>";
									str += "<input type='hidden' name='uploadPath'value='"+ jobj.data("uploadPath")	+ "'>";
									str += "<input type='hidden' name='fileType'value='"+ jobj.data("fileType")	+ "'>";
																	//�ٷ� ���ϰ� �����Ѵ�
																	// upload lst �� ���ش�

																});
												formObj.append(str).submit();
												}); 
				
							var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
							var maxSize = 5242880; //5mb

							function checkExtension(fileName, fileSize) {
								if (fileSize >= maxSize) {
									alert("���ϻ����� �ʰ�");
									return false;
								}
								if (regex.test(fileName)) {
									alert("�ش� ������ ������ ���ε� �� �� �����ϴ�");
									return false;

								}
								return true;
							}
							$("input[type='file']")	.change(
								function(e) {
									var formData = new FormData();
									var inputFile = $("input[name='uploadFile']");
									var files = inputFile[0].files;
									//add filedate to formdata
									for (var i = 0; i < files.length; i++) {

										if (!checkExtension(
												files[i].name,
												files[i].size)) {
											return false;
										}
										formData.append(
												"uploadFile",
												files[i]);
										//uploadfile Ű���� file[0]�� �����Ͽ� �������Ϳ� �߰��� ����
									}
							$.ajax({
										url : '/uploadAjaxAction',
										processData : false,
										contentType : false,
										data : formData,
										type : 'POST',
										dataType : "json",
										success : function(
												result) {
											showUploadResult(result);
												console.log(result);
											//$(".uploadDiv").html(cloneObj.html());
											//���ε� ��� ó�� �Լ�
										}

											});//$ajax
								});//input file type change end

					function showUploadResult(uploadResultArr) {
						//���� ������� �� ���ε� ���� �ؿ� ������ ��� ���ϰ� �ʹ� ������µ� �װ� �Ⱦ ���������� ���� �����ϰ� �� ������ ���� �־�°� ����

						if (!uploadResultArr|| uploadResultArr.length == 0) {
							return;
						}
						// ������ �ִ��� ������ ������ �Ǵ�
						var uploadUL = $(".uploadResult ul");

						var str = "";

						$(uploadResultArr).each(
							function(i, obj) {
							//function() ���� i �� �ε���, obj �� �� � ������ ��
								console.log(obj);
								if (obj.fileType) {
								var fileCallPath = encodeURIComponent(obj.uploadPath+ "/s_"+ obj.uuid+ "_"+ obj.fileName);
									console.log("deletefile");
									str += "<li data-path='"+obj.uploadPath+"'";
	 								str +=" data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"'data-type='"+obj.fileType+"'";
	 								str +" ><div>";
									str += "<span> "+ obj.fileName+ "</span>";
									str += "<button type ='button' data-file=\'"+fileCallPath+"\' ";
									str += "data-type='image' class='deleteBtn'><i class='fa fa-times'></i></button><br>";
									str += "<a href = '/download?fileName="+fileCallPath+"'>"+"<img src='/display?fileName="
											+ fileCallPath
											+ "'></a>";
									str += "</div>";
									str + "</li>";

											} else {

									var fileCallPath = encodeURIComponent(obj.uploadPath+ "/"+ obj.uuid+ "_"+ obj.fileName);
									var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
									str += "<li><a href = '/download?fileName="+fileCallPath+"'>"+"<img src='/resources/img/file.png'><br>"+obj.fileName +"</a></li>";
												
									}

								$('.uploadResult').on("click","button",function(e){
									
									console.log("deletefile");
									var targetFile = $(this).data("file");
									var type = $(this).data("type");
									var targetLi = $(this).closest("li");
									//$(this). ~~ �� ���õ� <div>�� ����Ŵ
									
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

				$(".uploadResult ul").append(str);

							}; // showUploadResult
						}); // end ready
	</script>



</body>
<jsp:include page="/WEB-INF/views/comm/bottom.jsp" flush="false" ></jsp:include>
</html>