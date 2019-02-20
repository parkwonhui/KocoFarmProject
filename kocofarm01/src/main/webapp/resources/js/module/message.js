$(function() {
	// 메시지 요청
	var bRequestMessage = true;

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

		const strEmpId = $('#message-my-emp-id').val();
		var nLength = data.length;

		for (var i = 0; i < nLength; ++i) {

			var text = '';
			if (strEmpId == data[i].empId) {
				text += '<div class="outgoing_msg">';
				text += '<div class="sent_msg">';
				text += '<p>' + data[i].contents + '</p>';
				text += '<span class="time_date">' + data[i].dateString
						+ '</span>';
				text += '</div>';
				text += '</div>';
			} else {
				text += '<div class="incoming_msg">';
				text += '<div  class="incoming_msg_img">';
				text += '<img  src="https://ptetutorials.com/images/user-profile.png" alt="sunil">';
				text += '</div>';
				text += '<div class="received_msg">';
				text += '<p>' + data[i].korNm + '</p>';
				text += '<div  class="received_withd_msg">';
				text += '<p>' + data[i].contents + '</p>';
				text += '<span  class="time_date">' + data[i].dateString
						+ '</span>';
				text += '</div>';
				text += '</div>';
				text += '</div>';
				text += '</div>';
			}

			$('.msg_history').append(text);
		}
	}
	
	/* 변경된 데이터만 전달 */
	var addMessage = function(data){

		// 현재 메시지 창을 보고 있지 않다
		if(data.roomId != $('#click-message-room-id').val()){
			return;
		}
		
		console.log(data);
		console.log(data.empId);
		const strEmpId = $('#message-my-emp-id').val();
	
		var text = '';
		if (strEmpId == data.empId) {
			text += '<div class="outgoing_msg">';
			text += '<div class="sent_msg">';
			text += '<p>' + data.contents + '</p>';
			text += '<span class="time_date">' + data.dateString	+ '</span>';
			text += '</div>';
			text += '</div>';
		} else {
			text += '<div class="incoming_msg">';
			text += '<div  class="incoming_msg_img">';
			text += '<img  src="https://ptetutorials.com/images/user-profile.png" alt="sunil">';
			text += '</div>';
			text += '<div class="received_msg">';
			text += '<p>' + data.korNm + '</p>';
			text += '<div  class="received_withd_msg">';
			text += '<p>' + data.contents + '</p>';
			text += '<span  class="time_date">' + data.dateString
					+ '</span>';
			text += '</div>';
			text += '</div>';
			text += '</div>';
			text += '</div>';
		}

		$('.msg_history').append(text);
		
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

	// Context 등록하기
	requestNewMessage();
	
	// 메시지 룸 리스트 가져오기
	ajaxGetRequest("listMessageRoom", null, addMessageRoomList);

	$('#add-message-room-button').click(function() {
		ajaxGetRequest("empList", null, addEmpFunction);
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

		var roomID = $(this).children("input[name=messageRoomId]").val();
		var data = {
			"roomId" : roomID
		};
		
		// 현재 선택한 방 id 저장
		$('#click-message-room-id').val(roomID);

		requestMessageList(data);
	});
	
	// 메시지 보내기
	$('.msg_send_btn').click(function(){
	
		var roomId = searchMessageRoomId();
		var text = $('.write_msg').val();
		var data = {"contents": text, "messageRoomId":roomId};
		
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
	
	/* 선택한 메시지룸 찾기*/
	function searchMessageRoomId(){
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

	function ajaxGetRequest(sendUrl, sendData, func) {

		$.ajax({
			type : "get",
			data : sendData,
			dataType : "json",
			url : sendUrl,
			success : function(data) {
				func(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	}
	
	function ajaxRequest(sendUrl, sendData) {
		$.ajax({
			type : "post",
			data : sendData,
			dataType : "json",
			url : sendUrl,
			success : function(data) {
				requestMessageRoomList(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	}

	/* 메시지 룸 리스트 가져오기 */
	function ajaxNoFunction(url, sendData, func) {
		var str =  "가나다라라라라라";
		$.ajax({
			type : "post",
			data : str,
			url : url,
			success : function(data) {
				console.log(data);
				//if(undefined == func){
				//	func(data);
				//}
			},
			error : function(error) {
			}, // error
		});// ajax
	}
	
	function ajaxRequestMessage(){
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
				
				
			/*	var contentElement = document.getElementById("content");
				contentElement.innerHTML += data;*/
				return true;
			},
			error : function(error) {
				console.log('걸기 실패');
				return false;
			}, // error
		});// ajax
	}

	
	/* 메시지 룸 리스트 가져오기 */
	function requestMessageRoomList() {

		$.ajax({
			type : "get",
			dataType : "json",
			url : "listMessageRoom",
			success : function(data) {
				addMessage(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	}

	/* 메시지 리스트 가져오기 */
	function requestMessageList(data) {
		$.ajax({
			type : "get",
			data : data,
			dataType : "json",
			url : "listMessage",
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