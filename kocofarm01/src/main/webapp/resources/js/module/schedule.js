/* 일정 추가, 수정 시 저장 데이터 */
var add_project_id = 0; // 프로젝트 아이디
var add_category_id = 0; // 카테고리 아이디
var add_calender_id = 0; // 일정 아이디
var add_calender_color_value; // color

/* 일정 이동 시 사용되는 변수 */
var drag_before_calender_category_id; // 이동 전 일정의 카테고리 id
var drag_before_calender_id; // 이동 전 일정 id
var drag_before_calender_y; // 이동 전 일정 y
var drag_before_calender_index; // 이동 전 일정 index

/* 카테고리 이동 시 사용되는 변수 */
var drag_category_class; // 이동하는 카테고리의 class
var drag_category_x; // 이동하는 카테고리의 x
var drag_category_projectId; // 이동하는 카테고리의 프로젝트 id
var drag_category_categoryId; // 이동하는 카테고리의 id

/* 프로젝트 관리자 여부 */
var is_project_manager;

/* 권한 제한이 있는 프로젝트인지 아닌지 */
var is_public_project;

// Tag 추가시 저장 데이터

(function($) {

	var elmDrag, replacerSet = $();
	var eventStack = [ 'dragstart', 'dragend', 'selectstart', 'dragover',
			'dragenter', 'drop' ];

	$.fn.dropme = function(options) {
		var userOpt = options.toString();

		options = $.extend({
			linkTo : false
		}, options);

		if (options.contId) {
			//var elmes = '<ul class="sortable">';
			var elmes = '';
			var lnth = options.elem.length;
			var i;
			for (i = 0; i < lnth; i++) {
				elmes += '<li id=' + options.elem[i].id + '>'
						+ options.elem[i].title + '</li>';
			}
			// elmes += '</ul>';
			$('#' + options.contId + ' .dropme').html(elmes);
		}

		return this
				.each(function() {
					var regEx = new RegExp("/^enable|disable|destroy$/");
					if (userOpt.match(regEx)) {

						var itemInOpt = $(this).data('items');

						var items = $(this).children(itemInOpt);

						if (userOpt == 'enable') {
							items.attr('draggable', true);
						} else {
							items.attr('draggable', false);
						}

						if (userOpt == 'destroy') {
							items.add(this).removeData('linkTo items').off(
									JSON.stringify(eventStack));
						}
						return;
					}
					var index, items = $(this).children(options.items);

					var replacer = $('<'
							+ (this.tagName.match(/^ul|ol|div$/i) ? 'li'
									: 'div') + ' class="drop-replacer">');

					$(this).data('items', options.items);

					replacerSet = replacerSet.add(replacer);

					if (options.linkTo) {
						$(options.linkTo).add(this).data('linkTo',
								options.linkTo);
					}

					items
							.attr('draggable', 'true')
							.on(
									eventStack[0],
									function(e) {
										var h = $(this).css("height");
										$(replacerSet).css("height", h); // 이동시킬 때 그림자 높이
										var thisClass = $(this).attr('class');
										if ('calender_info' == thisClass) {
											drag_category_class = thisClass;
											drag_category_x = $(this).children(
													'.this_category_x').val();
											drag_category_categoryId = $(this)
													.children(
															'.this_category_id')
													.val();
										} else {
											// 초기화
											drag_category_class = "";
											drag_category_x = "";
											drag_category_projectId = "";
											drag_category_categoryId = "";
										}

										drag_before_calender_category_id = $(
												this).parent().children(
												".calender_info").children(
												".this_category_id").val();
										drag_before_calender_id = $(this)
												.children(".this_calender_id")
												.val();

										drag_before_calender_y = $(this)
												.children(".this_calender_yPos")
												.val();
										drag_before_calender_index = $(this)
												.parent().eq($(this).index());

										var dataTrnsfr = e.originalEvent.dataTransfer;
										dataTrnsfr.effectAllowed = 'move';
										dataTrnsfr.setData('Text', 'dummy');
										elmDrag = $(this);
										index = (elmDrag).addClass(
												'drop-elmDrag').index();
									})
							.on(
									eventStack[1],
									function() {
										if (0 == is_public_project
												&& 0 == is_project_manager) {
											return;
										}

										// 일정 이동 시
										if ("" == drag_category_class) {
											calenderMoveAjax(this);
										} else {
											categoryMoveAjax(this);
										}

										(elmDrag = $(this)).removeClass(
												'drop-elmDrag').show();
										replacerSet.detach();
										if (index != elmDrag.index()) {
											items.parent().trigger(
													'sortupdate', {
														item : elmDrag
													});
										}
										elmDrag = null;

									})
							.not('a[href], img')
							.on(
									eventStack[2],
									function() {
										if (0 == is_public_project
												&& 0 == is_project_manager) {
											return;
										}

										this.dragDrop && this.dragDrop();
										return false;
									})
							.end()
							.add([ this, replacer ])
							.on(
									'dragover dragenter drop',
									function(event) {
										if (0 == is_public_project
												&& 0 == is_project_manager) {
											return;
										}

										if (!items.is(elmDrag)
												&& options.linkTo !== $(elmDrag)
														.parent()
														.data('linkTo')) {
											return true;
										}
										if (event.type == 'drop') {

											event.stopPropagation();
											replacerSet.filter(':visible')
													.after(elmDrag);
											return false;
										}
										event.preventDefault();
										event.originalEvent.dataTransfer.dropEffect = 'move';
										if (items.is(this)) {
											if (options.replacerSize) {
												replacer.height(elmDrag
														.outerHeight());
											}
											/* 이부분이 이동하는 카테고리의 그림자 보이는 곳 */
											elmDrag.hide();
											$(this)[replacer.index() < $(this)
													.index() ? 'after'
													: 'before'](replacer);
											replacerSet.not(replacer).detach();
										} else if (!replacerSet.is(this)
												&& !$(this).children(
														options.items).length) {
											replacerSet.detach();
											$(this).append(replacer);
										}
										return false;
									});
				});
	};
})(jQuery);

function categoryMoveAjax(moveCategory) {
	// 카테고리 이동 시
	var categoryList = $(moveCategory).parent().children('.calender_info');
	var size = $(moveCategory).parent().children('.calender_info').length;
	var x = 0;
	var categoryId = 0;

	$.each(categoryList, function(index, category) {
		var tempCategoryId = $(category).children('.this_category_id').val();
		// 이동한 카테고리 정보 저장
		if (tempCategoryId != drag_category_categoryId) {
			categoryId = tempCategoryId;
			x = $(category).children('.this_category_x').val();
		}
	});

	// ajax 요청
	var data = {
		"projectId" : add_project_id,
		"moveCategoryId" : drag_category_categoryId,
		"moveCategoryX" : drag_category_x,
		"oriCategoryId" : categoryId,
		"oriCategoryX" : x
	};
	var url = "editCategoryPos";

	ajaxRequest(url, data);
}

// this를 moveCategory로 받음
function calenderMoveAjax(moveCalender) {
	var drag_after_calender_category_id = 0;// 이동 후 일정의 카테고리 id
	var drag_after_calender_id = 0; // 이동 후 일정 id
	var drag_after_calender_y = 0; // 이동 후 일정 y
	var drag_after_calender_index = 0; // 이동 후 일정 index
	var isSameCategory = 0; // 이동전 후 비교해서 같은 카테고리인지

	$(moveCalender).children(".this_category_id").val(
			drag_after_calender_category_id);
	drag_after_calender_index = $(moveCalender).index();
	// 카테고리 id 갱신
	drag_after_calender_category_id = $(moveCalender).parent().children(
			".calender_info").children(".this_category_id").val()
	if (drag_before_calender_category_id == drag_after_calender_category_id)
		isSameCategory = 1;
	var data = "";
	if (1 == isSameCategory) {
		var size = $(moveCalender).parent().children().length - 1; // 인덱스는 길이보다 1작다
		var y = 0;
		data += "[";
		for (var index = size; index >= 1; --index) {
			var calenderId = $(moveCalender).parent().children().eq(index)
					.children(".this_calender_id").val();
			if (undefined == calenderId)
				continue;

			data += '{ "categoryId":';
			data += drag_before_calender_category_id + ', "calenderId":';
			data += calenderId + ', "yPos":';
			data += y + '}';
			data += ",";
			$(moveCalender).parent().children().eq(index).children(
					".this_calender_yPos").val(y++);
		}
		data = data.substr(0, data.length - 1);
		data += "]";
	} else {
		// y값 갱신
		//이동한 곳 밑에 노드가 있으면 y값 구하고 1 더해줌. 아니면 0넣어줌
		if (null == $(moveCalender).parent().children().eq(
				$(moveCalender).index() + 1)) {
			drag_after_calender_y = $(moveCalender).parent().children().eq(
					$(moveCalender).index() + 1)
					.children(".this_calender_yPos") + 1;
		}

		// 이동한 곳 처리. y값 전체 재설정
		var size = $(moveCalender).parent().children().length - 1; // 인덱스는 길이보다 1작다
		var otherY = 0;
		data += "[";
		for (var index = size; index >= 1; --index) {
			var calenderId = $(moveCalender).parent().children().eq(index)
					.children(".this_calender_id").val();
			if (undefined == calenderId)
				continue;
			data += '{ "categoryId":';
			data += drag_after_calender_category_id + ', "calenderId":';
			data += calenderId + ', "yPos":';
			data += otherY + '}';
			data += ",";
			$(moveCalender).parent().children().eq(index).children(
					".this_calender_yPos").val(otherY++);
		}

		var list = $(".this_category_id").filter(
				"input[value=" + drag_before_calender_category_id + "]")
				.parent().parent(); //이동하기 전 원래 있던 카테고리
		var beforeSize = $(list).children().length - 1; // 인덱스는 길이보다 1작다
		var beforeY = 0;
		for (var index = beforeSize; index >= 1; --index) {
			var calenderId = $(".this_category_id").filter(
					"input[value=" + drag_before_calender_category_id + "]")
					.parent().parent().children().eq(index).children(
							".this_calender_id").val();
			if (undefined == calenderId)
				continue;

			data += '{"categoryId":';
			data += drag_before_calender_category_id + ', "calenderId":';
			data += calenderId + ', "yPos":';
			data += beforeY + '}';
			data += ",";

			$(list).parent().children().eq(index).children(
					".this_calender_yPos").val(beforeY++);
		}
		data = data.substr(0, data.length - 1);
		data += "]";
	}

	var url = "editCalenderPos";
	$.ajax({
		type : "POST",
		data : data,
		dataType : "json",
		url : url,
		contentType : 'application/json',
		success : function(data) {
			calenderList();
		},
		error : function(error) {
		}, // error
	});// ajax
}

/* 드래그 창 */
$('.dropme').dropme('enable');
$('.exclude').dropme({
	items : ':not(.disabled)'
});
$('.connected').dropme({
	linkTo : '.connected'
});

var _gaq = _gaq || [];
_gaq.push([ '_setAccount', 'UA-36251023-1' ]);
_gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
_gaq.push([ '_trackPageview' ]);

(function() {
	var ga = document.createElement('script');
	ga.type = 'text/javascript';
	ga.async = true;
	ga.src = ('https:' == document.location.protocol ? 'https://ssl'
			: 'http://www')
			+ '.google-analytics.com/ga.js';
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(ga, s);
})();

// 일정 추가 버튼 '+', 수정, 삭제 눌렀을 때
function calenderButtonClick(projectId, categoryId, calenderId) {

	add_project_id = projectId;
	add_category_id = categoryId;
	add_calender_id = calenderId;
}
/////////////////////////////////////////////////////////////////////
function addDynamicHtml(data) {
	$(".con").empty();

	var html = "";
	var categoryId = -1; // 카테고리 아이디 저장

	var projectId = 0;
	for (var i = 0; i < data.length; i++) {
		// 카테고리 id가 다르면 새로운 카테고리생성
		if (categoryId != data[i].categoryId) {
			if (0 != i) {
				add_project_id = data[i].projectId;
				projectId = data[i].projectId;
				html += '</ul>';
			}

			html += '<ul class="connected li1">';
			html += '<li class="calender_info">';
			html += '<input class="category-name-input" type="text" readonly="true" value="'
					+ data[i].categoryName + '"></input>';

			// 매니저이거나 public project 일 때
			if (1 == is_project_manager || 1 == is_public_project) {
				html += '<img src="/resources/img/schedule/dustbin.png" class="category-delete-btn" data-toggle="modal" data-target="#categoryDeleteModal"/>';
				html += '<button type="button" class="btn  btn-primary calenderWriteBtn btn-block" data-toggle="modal" data-target="#calenderAddModal">새 일정 추가</button>';
			} else {
				html += '<button type="button" class="btn  btn-primary calenderWriteBtn btn-block" data-toggle="modal" data-target="#calenderAddModal" disabled>새 일정 추가</button>';
			}

			html += '<input type="hidden" class="this_project_id" value='
					+ data[i].projectId + ' />';
			html += '<input type="hidden" class="this_category_id" value='
					+ data[i].categoryId + ' />';
			html += '<input type="hidden" class="this_category_x" value='
					+ data[i].xPos + ' />';
			html += '</li>';
			categoryId = data[i].categoryId;
		}

		// 캘린더 id가 0이 아니라면
		if (0 != data[i].calenderId) {
			html += '<li class="calender_detail">';

			if ("" != data[i].backgroundColor) {
				html += '<div class="calender-detail-color" style="background-color:#'
						+ data[i].backgroundColor + '";>' + '&nbsp;' + '</div>';
			}

			html += '<div><img src="/resources/img/schedule/settings.png" class="calender-modify-btn" data-toggle="modal" data-target="#calenderModify"/></div>';
			html += '<div class="calender_detail_title ">' + data[i].title
					+ '</div>';
			html += '<input type="hidden" class="this_calender_id" value='
					+ data[i].calenderId + ' />';
			html += '<input type="hidden" class="this_calender_yPos" value='
					+ data[i].yPos + ' />';
			var startDt = data[i].startDt;
			var endDt = data[i].endDt;
			if ("" != startDt || "" != endDt) {
				html += '<span class="calender_detail_startDt">'
						+ startDt.substring(0, 10) + '</span><span> - </span>';
				html += '<span class="calender_detail_endDt">'
						+ endDt.substring(0, 10) + '</span>';
			}
			html += "<div>";
			//여기에 태그 DIV 만들어야 함
			var memberListLength = data[i].memberList.length;
			if (0 < memberListLength) {
				for (var k = 0; k < memberListLength; ++k) {
					html += '<div class="calender-member-info">'+data[i].memberList[k].korNm+'</div>';
					html += ' ';
				}
			}

			html += "</div>";

			
			
			var tagName = data[i].tagName;
			var tagColor = data[i].tagColor;
			
			html += '<input type="hidden" class="calender_detail_completionPer" value="'
					+ data[i].completionPer + '"></input>';
			
			html += "<div id='list-tag-bar"+data[i].calenderId+"'>";
			html += getTagList(data[i].calenderId);
			html += "</div>";
			
			
			html += '<div class="calender-progress-bar">';
			html += '<div style=" height: 20px; background-color: #01A9DB; border-radius:10px; width:'
					+ data[i].completionPer
					+ '%" >'
					+ data[i].completionPer
					+ '</div>'
			html += '</div>';

			/*	html += '<div id = list-tag-bar></div>';
			html += '<div class="calender_tag">';
			html += '<div style=" height: 20px; background-color:'+ data[i].tag_color +'; border-radius:10px">'
					+ data[i].tag_name + '</div>';
			html += '<div class="schedule-tag-list"></div>';
			html += '</div>';
			html += '</li>';

			if("" != data[i].fileName){
				$("input[name='tagColor']").change(function() {
					var tagColor = $("input[name='tagColor']").val(); 
					
				});				
			}
*/
		/*	for (var t = 0; t < data.tagDataList; t++) {
				var tagListLength = data[i].tagDataList[t].length;
				*/
			/*
					html += '<div class="calender_tag">';
					html += '<div style=" height: 65px; background-color:'
							+ "#ffff"
							+ ' ; border-radius:10px> + data[i].tag_name + </div>'
					html += '</div>';
					html += '</li>';
*/
				
			}
	//	}

	}//for문

	// 동적으로 생성된 element 이벤트 붙이기
	html += '</ul>';

	// 새  카테고리
	if (1 == is_project_manager || 1 == is_public_project) {
		html += '<ul class="connected li1">';
		html += '<li class="calender_info">';
		html += '<div><input class="add-category-name-input" type="text"></input><div>';
		html += '<button type="button" class="btn btn-light btn-block add-category-button">새 카테고리 추가</button>';
		html += '<input type="hidden" class="this_project_id" value='
				+ projectId + ' />';
		html += '</li>';
		html += '</ul>';
	}

	$(".con").append(html);

	$('.dropme').dropme('enable');
	$('.exclude').dropme({
		items : ':not(.disabled)'
	});
	$('.connected').dropme({
		linkTo : '.connected'
	});

	// 일정 추가 버튼 클릭
	$(document).on("click", ".calenderWriteBtn", function() {
		// eq로 찾는거 자꾸 위치가 바뀌므로 좋지 않다
		var par = $(this).parent(); // calender_info
		var project_id = par.children(".this_project_id").val();
		var category_id = par.children(".this_category_id").val();
		$("#editCalenderCompletionPerRang").val(0);
		calenderButtonClick(project_id, category_id, 0);
	});

	// 세팅 버튼에 이벤트 추가
	$(document).on(
			"click",
			".calender-modify-btn",
			function() {
				var par = $(this).parent().parent(); //calender_detail
				var parpar = par.parent().children('.calender_info');

				var project_id = parpar.children(".this_project_id").val();
				var category_id = parpar.children(".this_category_id").val();
				var calender_id = par.children('.this_calender_id').val();

				// 정보 추출
				var color = $(par).children(".calender-detail-color").css(
						"background-color");
				var title = $(par).children(".calender_detail_title").html();
				var startDt = $(par).children(".calender_detail_startDt")
						.html();
				var endDt = $(par).children(".calender_detail_endDt").html();
				var completionPer = $(par).children(
						".calender_detail_completionPer").val();
				
				var tagName = $("input[name=tagname]").val();
				var tagColor = $("input[name=tagname]").val();
				// 태그 데이털르 어떻게 넣는가

				var val = $("#calenderModify").children(".modal-dialog")
						.children(".modal-content").children(".modal-body");
				add_calender_color_value = hexc(color);

				$("#edit-tag-bar").empty();
				$(".tag-name-select").val("ex)Important");
				$(".tag-color-select").val("");
				
				// 값 넣기
				
				val.children("input[name=write]").val(title);
				val.children("input[name=editDatepickerStart]").val(startDt);
				val.children("input[name=editDatepickerEnd]").val(endDt);
				$("#editCalenderCompletionPerRang").val(completionPer);
				$("#editCalenderCompletionPerVal").val(completionPer);
				/*$("#tag_bar").html(tagName);*/
				calenderButtonClick(project_id, category_id, calender_id);
			});

	/* 카테고리 값 체인지 이벤트 */
	$('.category-name-input').on("dblclick", function() {
		if (0 == is_public_project && 0 == is_project_manager) {
			return;
		}
		$(this).attr("readonly", false);
	});

	$('.category-name-input').on("focusout", function() {
		if (0 == is_public_project && 0 == is_project_manager) {
			return;
		}

		$(this).attr("readonly", true);

		var par = $(this).parent();
		var categoryId = par.children('.this_category_id').val();
		var categoryName = par.children('.category-name-input').val();

		var data = {
			"categoryId" : categoryId,
			"categoryName" : categoryName
		};
		var url = "editCategory";
		ajaxRequest(url, data);
	});

	$('.add-category-button').on("click", function() {
		var text = $('.add-category-name-input').val();
		if ("" == text) {
			alert("카테고리 이름이 없습니다");
			return;
		}

		var xPos = $('.category-name-input').length + 1;
		var data = {
			"projectId" : add_project_id,
			"xPos" : xPos,
			"categoryName" : text
		};
		var url = "insertCategory";

		ajaxRequest(url, data);
	});

	// 카테고리 삭제 버튼
	$('.category-delete-btn').on("click", function() {
		var parent = $(this).parent();
		add_category_id = parent.children(".this_category_id").val();
		add_project_id = parent.children(".this_project_id").val();
	});
	
	// 태그 관련 리스트 호출
//	getTagList($("#calendarId").val());
	
}// end addDynamicHtml
////////////////////////////////////////////////////////////////////


$('#tag-edit').click(function(){
	var tagName = $("input[name=tagName]").val();
	var tagColor = $("input[name=tagColor]").val();
	$('#list-tag-bar').append('<div style=" height: 20px; margin:3px; padding-left: 10px;  padding-right: 10px; color : white; background-color:'+ tagColor +'; border-radius:10px; display:inline-block">'
			+ tagName + '</div>');
	$('#edit-tag-bar').append('<div style=" height: 20px; margin:3px; padding-left: 10px;  padding-right: 10px; color : white; background-color:'+ tagColor +'; border-radius:10px; display:inline-block">'
			+ tagName + '</div>');
	document.getElementById("edit-tag").style["margin-top"] = "10px";
	$(".tag-name-select").val("ex)Important");
	$(".tag-color-select").val("");
	$('#select-tag-popup').modal('toggle');
	
	
	//태그창만 끄기		
});

$("#edit-tag").click(function(){
	var tagName = $("input[name=tagName]").val();
	var tagColor = $("input[name=tagColor]").val();
	
	$('.schedule-tag-list').append('<div>'+ '<button 태그이름 태그 색상>' +'</div>');
	
});

///
function ajaxRequest(sendUrl, sendData) {
	$("#calendarId").val(sendData.calenderId);
	
	$.ajax({
		type : "POST",
		data : sendData,
		dataType : "json",
		url : sendUrl,
		success : function(data) {
			
			if (1001 == data) {
				alert('[실패] 카테고리 이름을 확인해주세요(1~50자)');

			} else if (1002 == data) {
				alert('[실패] 일정의 글자수가 너무 많거나 없습니다(1~100자)');

			} else if (1003 == data) {
				alert('[실패] 시작날짜가 잘못되었습니다');

			} else if (1004 == data) {
				alert('[실패] 종료날짜가 잘못되었습니다');

			} else if (1005 == data) {
				alert('[실패] 완료상황은 0과 100 사이 값만 가능합니다');

			} else if (1006 == data) {
				alert('[실패] 권한이 없습니다');

			} else if (1007 == data) {
				alert('[실패] 태그 이름을 확인해주세요(1~50자)');

			} else if (1008 == data) {
				alert('[실패] 색 이름을 확인해주세요(1~50자)');

			} else if (-1 == data) {
				alert('[실패] 알 수 없는 에러');

			} else {
				calenderList();
			}
		},
		error : function(error) {
		}, // error
	});// ajax
}

function calenderList() {
	$.ajax({
		type : "POST",
		data : {
			"projectId" : add_project_id
		},
		dataType : "json",
		url : "listCalender",
		success : function(data) {
			addDynamicHtml(data);
		},
		error : function(error) {
		}, // error
	});// ajax
}

// 카테고리 삭제 모달 버튼
$('#project-delete-project-button').click(function() {
	var url = "delCategory";
	var data = {
		projectId : add_project_id,
		categoryId : add_category_id
	};

	ajaxRequest(url, data);

});

//일정 추가 버튼 눌렀을 때
$('#calender_add').click(function() {

	var write = $("input[name=write]").val();
	var color = add_calender_color_value;
	var completion_per = $("input[name=addCompletionPer]").val();
	var tagName = $("input[name=tagname]").val();
	var tagColor = $("input[name=tag]").val();
	var category = $("input[value=" + add_category_id + "]").parent(); // 선택한 카테고리 클릭
	var y = category.parent().children().last().index() + 1; // 마지막 자식 노드 인덱스, 카테고리 때문에 1더함
	var startDt = $("input[name=addDatepickerStart]").val();
	var endDt = $("input[name=addDatepickerEnd]").val();
	var tagName = $("input[name=tagName]").val();
	var tagColor = $("input[name=tagColor]").val();
	
	if (null == write || "" == write) {
		alert("일정 정보가 없습니다");
		return;
	}

	if ("" == completion_per) {
		completion_per = 0;
	}

	if ("" == color) {
		color = "";
	}

	$('#calenderAddModal').modal('toggle');
	clearAddDlg($(this).parent());

	var data = {
		projectId : add_project_id,
		categoryId : add_category_id,
		title : write,
		backgroundColor : color,
		completionPer : completion_per,
		tagName : tagName,
		tagColor : tagColor,
		yPos : y,
		startDt : startDt,
		endDt : endDt,
		
	};
	
	/*이전에 태그 바를 만드는  동작을 실행한다.
	그 동작은 태그 제작 후 완료. 버튼을 누르면 실해된다
	그리고 저장 완료를 하면 태그의 네임과 컬러가 CAL ID 와 함께 저장된다.
	
	var index = 0;
	var list = $('.schedule-tag-list').chiledere();
	var size = list.length;
	++index;
	for(var i = 0; i < size; ++i){
		data.list[index] = $(list[i]).val()
	}
	리스트 객체를 만든다 그리고 태그 제작 완료 버튼을 모달창에 생서된 태그 바(칠드런들)를 리스트에 넣는다
	그리고 리스트의 1번인덱스에는 LIST 의 벨류값()을 받는다
	2번 인덱스에는 BACKCOLOR를 넣는다.
	*/
	
	

	
	var url = "insertCalender";

	ajaxRequest(url, data);

});//click
/*
$("input[name='tagName']").change(function() {
	var tagName = $("input[name='tagName']").val(); 

	var div = document.createElement('div');
	console.log(div);
	div.
	div.innerHTML = document.getElementById('tag_bar').innerHTML;
	
	document.getElementById('tag_bar').appendChild(div);
});
*/


$('#calender_edit').click(function() {
	
	var par = $(this).parent(); // modal-body
	var write = par.children("input[name=write]").val();
	var color = add_calender_color_value;
	var completion_per = $("#editCalenderCompletionPerVal").val();
	var startDt = par.children("input[name=editDatepickerStart]").val();
	var endDt = par.children("input[name=editDatepickerEnd]").val();
	var tagName = $("#edit-tag-bar div").text();
	var tagColor = $("#edit-tag-bar div").css("background-color");
	
	var data = {
		projectId : add_project_id,
		categoryId : add_category_id,
		calenderId : add_calender_id,
		title : write,
		backgroundColor : color,
		completionPer : completion_per,
		tagName : tagName,
		tagColor : tagColor,
		startDt : startDt,
		endDt : endDt

	};
	
	var url = "editCalender";
	ajaxRequest(url, data);
	
	$('#tag-name-select').val('ex)Important');
	$('#calenderModify').modal('toggle');

	
	//태그 이름 refresh
}

);

// 일정 삭제
$('#calender_del').click(function() {
	if (0 == is_public_project && 0 == is_project_manager) {
		alert('[에러] 권한이 없습니다');
		return;
	}

	$('#calenderModify').modal('toggle');
	var data = {
		calenderId : add_calender_id
	};
	var url = "delCalender";

	ajaxRequest(url, data);
});

/* 일정 추가 후 데이터 초기화 */
function clearAddDlg(this_parent) {
	$("input[name=write]").val("");
	add_calender_color_value = "ffffff";
	$("input[name=addCompletionPer]").val(0);
	$("input[name=addDatepickerStart]").val("");
	$("input[name=addDatepickerEnd]").val("");
	$("input[name=tagName]").val("");
	$("input[name=tagColor]").val("");
}

/* 배경 rgb값을 16진수로 convert */
function hexc(colorval) {
	if (undefined == colorval) {
		return;
	}

	var parts = colorval.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	delete (parts[0]);
	for (var i = 1; i <= 3; ++i) {
		parts[i] = parseInt(parts[i]).toString(16);
		if (parts[i].length == 1)
			parts[i] = '0' + parts[i];
	}

	color = parts.join('');
	return color;
}

/* 일정 컬러색 가져오기 */
$(document).on("click",
		".tag-important, .tag-approve, .tag-quickly, .tag-request", function() {
			var color = $(this).css('background-color');
			add_calender_color_value = hexc(color);
		});

/* 작업자 추가 버튼 클릭 */
$("#edit-calender-emp").click(function(){
	
	$.ajax({
		type : "POST",
		data : { "calenderId": add_calender_id	},
		dataType : "json",
		url : "getCalenderInviteMember",
		success : function(data) {
			if(undefined == data)
				return;
			
			addCalenderInviteMember(data);
		},
		error : function(error) {
		}, // error
	});// ajax
});

function addCalenderInviteMember(data){
	$("#no-claender-emp-list").empty();
	$("#current-claender-emp-list").empty();
	
	var length = data.length;
	var notMemberList = "";
	var MemberList = "";

	for(var i = 0; i < length; ++i){
		if(1 == data[i].isMember){
			MemberList += addCalenderInviteMemberList(data[i], "current-claender-emp-list");
		}else{
			notMemberList += addCalenderInviteMemberList(data[i], "no-claender-emp-list");
		}
	}
	
	$("#no-claender-emp-list").append(notMemberList);
	$("#current-claender-emp-list").append(MemberList);
}

function addCalenderInviteMemberList(data, id){
	var text="";
	if("current-claender-emp-list" == id){
		text += '<li class="invite-message-room-emp checked">';
	}else{		
		text += '<li class="invite-message-room-emp">';
	}
	// text += '<img src="/resources/img/comm/'+data[i].empImg+'" >';
	text += '<input type="hidden" name="empId" value=' + data.empId	+ ' />';
	text += '<div class="calender-member-invite-list-img"><img src="/resources/img/message/user-profile.png" ></div>';
	text += '<p class="calender-member-invite-list-name">' + data.korNm + '</div></p>';
	text += '</li>';
	
	return text;
}

$(document).on("click", ".invite-message-room-emp", function() {
	$(this).toggleClass('checked');
	
	var parent = $(this).parent();
	$(parent).children("input[value="+$(this).empId + "]").remove();
	
	if(true == $(this).hasClass('checked')){
		$("#current-claender-emp-list").append($(this));		
		
	}else{
		$("#no-claender-emp-list").append($(this));		
	}
	
});


/*$('#edit-calender-emp-list-button').click(){
	var empList = $('#current-claender-emp-list');
	var length = $("#current-claender-emp-list").length;
	var list = {};
	var index = 0;
	list[index + ""] = calenderId;
	++index;
	++index;
	var count = 0;
	for (var i = 0; i < length; ++i) {

		list[index + ""] = $(empList[i]).children(
				"input[name=empId]").val();
		++index;
		++count;
	}

	list["1"] = count;

	
}
*/


// 태그 리스트 출력
function getTagList(calId){
	$.ajax({
			url : "getTagList"
		,	data : {
				"calendarId" : calId
			}
		,	success : function(data){
				var tagListTxt = "";
				
				$(data).each(function(i, obj){
					tagListTxt += "<div style='background-color:"+obj.tagColor+";border-radius:5px;display:inline-block;margin-left:3px'>"
					tagListTxt += "<span style='padding:10px'>"+obj.tagName+"</span></div>";
				});
				
				$("#list-tag-bar"+calId).html(tagListTxt);
			}
	});
	
}
