$(function() {
	// 메시지 요청
	var bRequestMessage = true;
	var selectMessageRoomName;

	// message room list
	var addMessageRoomList = function(data) {
		console.log(data);

		if (null == data)
			return;

		$(".inbox_chat").empty();
		var length = data.length;
		for (var i = 0; i < length; ++i) {

			var text = '<div class="chat_list">';
			text += '<input type="hidden" name="messageRoomId" value='
					+ data[i].messageRoomId + ' />';
			text += '<div class="chat_people">';
			text += '<div class="chat_img">';
			text += '<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">';
			text += '</div>';
			text += '<div class="chat_ib">';
			text += '<h5>';
			text += '<p>' + data[i].roomTitle + '</p>';
			if (null != data[i].lastMessageEmpName) {
				text += data[i].lastMessageEmpName
						+ ' <span class="chat_date">'
						+ data[i].lastMessageDateToString + '</span>';
			}
			text += '</h5>';
			if (null != data[i].lastMessageEmpName) {
				text += '<p>' + data[i].lastMessage + '</p>';
			}
			text += '</div>';
			text += '</div>';
			text += '</div>';

			$(".inbox_chat").append(text);
		}
	}

	var addMessageList = function(data) {
		$('.msg_history').empty();

		addMessageRoomTitle();
		
		var nLength = data.length;
		var text = "";
		for (var i = 0; i < nLength; ++i) {
			text += addMessageHtml(data[i]);
		}
		
		$('.msg_history').append(text);

		moveScrollbarDown();
		addWriteMessageBox();
	}

	/* 변경된 데이터만 전달 */
	var addMessage = function(data) {
		console.log(data);
		// 현재 메시지 창을 보고 있지 않다
		if (data.roomId != $('#click-message-room-id').val()) {		
			var massageRoomList = $("input[name=messageRoomId]");
			var length = massageRoomList.length;
			for(var i = 0; i < length; ++i){
				console.log($(massageRoomList[i]).val());
				if($(massageRoomList[i]).val() == data.roomId){
					$(massageRoomList[i]).parent().children('.chat_people').children('.chat_ib').children('h5').append('<span>New</span>');
					return;
				}
			}
		}

		var text = addMessageHtml(data);
		$('.msg_history').append(text);
		moveScrollbarDown();
	}

	// message room 초대할 유저 list
	var addEmpFunction = function addEmpList(data) {
		$("#emp-list").empty();
		var length = data.length;
		var text = '<ul class="add-message-room-emp-list">';
		for (var i = 0; i < length; ++i) {
			text += '<li class="add-message-room-emp">';
			// text += '<img src="/resources/img/comm/'+data[i].empImaSrc+'" >';
			text += '<input type="hidden" name="empId" value=' + data[i].empId
					+ ' />';
			text += '<div class="add-message-chat-img"><img src="https://ptetutorials.com/images/user-profile.png" ></div>';
			text += '<p class="add-message-emp-name">' + data[i].empName
					+ '</p>';
			text += '</li>';
		}
		text += '</ul>';

		$("#emp-list").append(text);
	}
	
	
	function addMessageHtml(data){
		const strEmpId = $('#message-my-emp-id').val();

		console.log(data);
		var text = '';

		// 누군가 나갔다
		if(data.type == 2){
			console.log('1111111111111');
			text += '<div class="message-room-exit-message">'+ data.contents +'</div>';
			
			// 나간 것은 자기자신이다
			if(strEmpId == data.empId){
				$('.message-room-title').empty();
				//$('.mesgs').empty();
				text = '';
			}
			
			
		}else if (strEmpId == data.empId) {
			console.log('222222222222222');

			text += '<div class="outgoing_msg">';
			text += '<div class="sent_msg">';
			text += '<p>' + data.contents + '</p>';
			text += '<span class="time_date">' + data.dateString + '</span>';
			text += '</div>';
			text += '</div>';
		} else {
			console.log('3333333333333333');
			text += '<div class="incoming_msg">';
			text += '<div  class="incoming_msg_img">';
			text += '<img  src="https://ptetutorials.com/images/user-profile.png" alt="sunil">';
			text += '</div>';
			text += '<div class="received_msg">';
			text += '<p>' + data.korNm + '</p>';
			text += '<div  class="received_withd_msg">';
			text += '<p>' + data.contents + '</p>';
			text += '<span  class="time_date">' + data.dateString + '</span>';
			text += '</div>';
			text += '</div>';
			text += '</div>';
			text += '</div>';
		}
		
		return text;
	}
	
	function addWriteMessageBox(){
		$('.input_msg_write').empty();
		var text = "";
		text += '<input type="text" class="write_msg" placeholder="Type a message" />';
		text += '<button class="msg_send_btn" type="button">';
		text += '<i class="fa fa-paper-plane-o" aria-hidden="true"></i>';
		text += '</button>';
		$('.input_msg_write').append(text);
		
	}

	// Context 등록하기
	//requestNewMessage();

	// 메시지 룸 리스트 가져오기
	ajaxRequest("listMessageRoom", null, "get",addMessageRoomList);

	$('#add-message-room-button').click(function() {
		ajaxRequest("empList", null, "get", addEmpFunction);
	});

	$('#add-message-room-request').click(
			function() {
				var empList = $('.add-message-room-emp');
				var messageRoomTitle = $('#add-message-room-title').val();

				var length = empList.length;
				var list = {};
				var index = 0;
				list[index + ""] = messageRoomTitle;
				++index;
				++index;
				var count = 0;
				for (var i = 0; i < length; ++i) {
					if (false == $(empList[i]).hasClass('checked'))
						continue;

					list[index + ""] = $(empList[i]).children(
							"input[name=empId]").val();
					++index;
					++count;
				}

				list["1"] = count;

				// 채팅방 리스트
				$.ajax({
					type : "post",
					data : JSON.stringify(list),
					dataType : "json",
					url : "addMessageRoom",
					contentType : 'application/json',
					success : function(data) {
						requestMessageRoomList();
					},
					error : function(error) {
					}, // error
				});// ajax

			});

	// 메시지 룸 초대 인원 클릭 동적 태그 이벤트 붙여주기
	$(document).on("click", ".add-message-room-emp", function() {
		$(this).toggleClass('checked');
	});

	// 메시지 방 선택 
	$(document).on("click", ".chat_list", function() {
		$(this).toggleClass('checked');
		initSelectMessageRoomColor(this);

		//$(massageRoomList[i]).parent().children('.chat_people').children('.chat_ib').children('h5')
		var roomID = $(this).children("input[name=messageRoomId]").val();
		 $(this).children("input[name=messageRoomId]").val();
		var data = {
			"roomId" : roomID
		};

		var messageRoom = $(this).children('.chat_people').children('.chat_ib').children('h5').children('p').html();
		// 현재 선택한 방 id 저장
		$('#click-message-room-id').val(roomID);
		selectMessageRoomName = messageRoom;
		ajaxRequest("listMessage", data, "get", addMessageList);
	});

	$(document).on("click", ".msg_send_btn", function(){
		var roomId = searchMessageRoomId();
		var text = $('.write_msg').val();
		console.log('roomId:'+roomId);
		var data = {
			"contents" : text,
			"messageRoomId" : roomId
		};
		console.log(data);

		$.ajax({
			type : "post",
			data : data,
			url : "push/sendMessage/",
			success : function(data) {
				//addMessage(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	});
	
	$(document).on("click", ".message-room-emp-list-print", function(){
	});
	
	$(document).on("click", ".message-room-emp-exit", function(){
		var roomId = $('#click-message-room-id').val();
		var data = {"messageRoomId":roomId};
		ajaxRequest("push/delMessagePush", data, "post", requestMessageRoomList);
	});


	function ajaxRequestMessage() {
		bRequestMessage = false;
		console.log('요청걸기!!!');
		$.ajax({
			type : "post",
			url : "push/getMessage",
			contentType : 'json',
			success : function(data) {
				console.log('데이터 받았다!!!!:');
				console.log(data);
				
				bRequestMessage = true;
				addMessage(data);
				return true;
			},
			error : function(error) {
				console.log('걸기 실패');
				return false;
			}, // error
		});// ajax
	}
	
	function ajaxRequest(sendUrl, sendData, type, func) {
		$.ajax({
			type : type,
			data : sendData,
			dataType : "json",
			url : sendUrl,
			success : function(data) {
				console.log(func);
				if(undefined != func){
					
					if(-1 == data){
						alert('[에러] 알 수 없는 에러가 발생했습니다');
						return;
					}
					
					func(data);
				}
			},
			error : function(error) {
			}, // error
		});// ajax
	}
	
	/* 선택한 메시지룸 찾기*/
	function searchMessageRoomId() {
		var list = $('.chat_list');
		var length = list.length;
		for (var i = 0; i < length; ++i) {
			if (true == $(list[i]).hasClass('checked')) {
				return $(list[i]).children("input[name=messageRoomId]").val();
			}
		}
	}

	/* 선택된 메시지 방 외의 다른 메시지방 색상 초기화*/
	function initSelectMessageRoomColor(clickRoom) {
		// 배경색 변경
		var list = $('.chat_list');
		var length = list.length;
		for (var i = 0; i < length; ++i) {
			if (clickRoom == list[i]) {
				continue;
			}

			$(list[i]).removeClass('checked');
		}
	}
	
	/* 스크롤바 맨 아래로 이동 */
	function moveScrollbarDown(){
		if(undefined == $(".msg_history")[0]){
			return;
		}

		$(".msg_history").scrollTop($(".msg_history")[0].scrollHeight);
	}
	
	/* 메시지 룸 타이틀 생성 */
	function addMessageRoomTitle(){
		$('.message-room-title').empty();

		var html = "<div class='message-room-top-title '>"+selectMessageRoomName
		html += '<div class="dropdown">';
		html += "<img src = '/resources/img/message/more.png' class='message-room-top-more' />";
		html += "<div class='dropdown-content'>";
		html += '<div class="message-room-emp-list-print">참여자</div>';
		html += '<div class="message-room-emp-exit">나가기</div>';
		html += '</div>';
		html += '</div>';
		html += '</div>';
		
		$('.message-room-title').append(html);
	}
	
	/* 메시지 룸 리스트 가져오기 */
	function requestMessageRoomList() {

		$.ajax({
			type : "get",
			dataType : "json",
			url : "listMessageRoom",
			success : function(data) {
				
				addMessageRoomList(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	}

	function requestNewMessage() {
		if (true == bRequestMessage) {
			bRequestMessage = ajaxRequestMessage();
		}
	}

	setInterval(requestNewMessage, 1000);

});