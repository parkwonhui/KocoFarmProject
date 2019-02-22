/*$(function(){
	$(".top_menu_li").click(function(){
		session.removeAttribute("search");
	});
});*/

$(function() {

		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click",function(e){
			
			e.preventDefault();
			console.log('click');
			actionForm.find("input[name = 'pageNum']").val($(this).attr("href"));
			actionForm.submit();
		});

});	


