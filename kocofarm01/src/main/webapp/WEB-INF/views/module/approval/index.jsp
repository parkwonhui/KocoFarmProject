<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!doctype html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<title> 사인 저장하기 </title>

	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	
	<script  type="text/javascript" src="${pageContext.request.contextPath}/resources/js/module/apprSignApp.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/module/apprSignPad.js" ></script>
	 
	<link rel="stylesheet" href="/resources/css/module/approval.css">
</head>

<body>
	<div id="signature-pad" class="m-signature-pad">
		<div class="m-signature-pad--body">
			<canvas></canvas>
		</div>
		<div class="m-signature-pad--footer">
			<div class="description">사인해 주세요~</div>
			<button type="button" class="button clear" data-action="clear">지우기</button>
			<button type="button" class="button save" data-action="save">저장</button>
		</div>
	</div>
    
    <script>
    window.onload = function(){
		var canvas = $("#signature-pad canvas")[0];
		var sign = new SignaturePad(canvas, {
			minWidth: 1,
			maxWidth: 5,
			penColor: "rgb(0, 0, 0)"
		});
		
		$("[data-action]").on("click", function(){
			if ( $(this).data("action")=="clear" ){
				sign.clear();
			}
			else if ( $(this).data("action")=="save" ){
				if (sign.isEmpty()) {
					alert("사인해 주세요!!");
				} else {
					$.ajax({
						url : "/approval/save",
						method : "post",
						dataType : "json",
						data : {
							sign : sign.toDataURL()
						},
						success : function(r){
							alert("저장완료 : " + r.filename);
							$(opener.document).find('#signImage').empty();
							$(opener.document).find('#signImage').append("<input type='image' name = 'tmpSignImage' value = '"+r.filename+"' id = 'tmpSignImage' src = '/resources/img/approval/tmpSign/"+r.filename+"' />");
							self.close();
						},
						error : function(res){
							console.log(res);
						}
					});
				}
			}
		});
    

		
		function resizeCanvas(){
			var canvas = $("#signature-pad canvas")[0];
	
			var ratio =  Math.max(window.devicePixelRatio || 1, 1);
			canvas.width = canvas.offsetWidth * ratio;
			canvas.height = canvas.offsetHeight * ratio;
			canvas.getContext("2d").scale(ratio, ratio);
		}
	    
	    $(window).on("resize", function(){
			resizeCanvas();
		});

		resizeCanvas();
    }
    </script>
</body>
</html>
