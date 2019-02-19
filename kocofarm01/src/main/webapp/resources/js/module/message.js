$(function() {

	// message room list
	var addMessageRoomListFunction = function addMessageRoomList(data) {
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

	// message room 초대할 유저 list
	var addEmpFunction = function addEmpList(data) {
		console.log('addEmpFunction');
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

	ajaxGetRequest("listMessageRoom", null, addMessageRoomListFunction);

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
				console.log(empList[0]);
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
		var data = {"roomId" : roomID };
				
		requestMessageList(data);
	});

	/* 선택된 메시지 방 외의 다른 메시지방 색상 초기화*/
	function initSelectMessageRoomColor(clickRoom){
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
				console.log(data);
				func(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	}

	function ajaxRequest(sendUrl, sendData) {
		console.log(sendUrl);
		
		console.log(sendData);
		$.ajax({
			type : "post",
			data : sendData,
			dataType : "json",
			url : sendUrl,
			success : function(data) {
				console.log(data);
				requestMessageRoomList(data);
			},
			error : function(error) {
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
				addMessageRoomListFunction(data);
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
				console.log(data);
				//addMessageRoomListFunction(data);
			},
			error : function(error) {
			}, // error
		});// ajax
	}
});