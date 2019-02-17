$(function(){
	
	// message room list
	var addMessageRoomListFunction = function addMessageRoomList(data){
		if(null == data)
			return;
		
		$(".inbox_chat").empty();
		var length = data.length;
		for(var i = 0; i < length; ++i){
			
			var text = '<div class="chat_list">';
			text += '<div class="chat_people">';
			text += '<div class="chat_img">';
			text += '<img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">';
			text += '</div>';
			text += '<div class="chat_ib">';
			text += '<h5>';
			text += '<p>'+ data[i].roomTitle + '</p>';
			if(null != data[i].lastMessageEmpName){
				text += data[i].lastMessageEmpName+' <span class="chat_date">'+data[i].lastMessageDateToString+'</span>';
			}
			text += '</h5>';
			if(null != data[i].lastMessageEmpName){
				text += '<p>'+ data[i].lastMessage +'</p>';
			}
			text += '</div>';
			text += '</div>';
			text += '</div>';

			$(".inbox_chat").append(text);
		}	
	}
	
	// message room 초대할 유저 list
	var addEmpFunction = function addEmpList(data){
		console.log('addEmpFunction');
		$("#emp-list").empty();
		var length = data.length;
		var text ='<ul class="add-message-room-emp-list">';
		for(var i = 0; i < length; ++i){
			text += '<li class="add-message-room-emp">';
			//text += '<img src="/resources/img/comm/'+data[i].empImaSrc+'" >';
			text += '<input type="hidden" name="empId" value='+data[i].empId+' />';
			text += '<div class="add-message-chat-img"><img src="https://ptetutorials.com/images/user-profile.png" ></div>';
			text += '<p class="add-message-emp-name">'+data[i].empName+'</p>';
			text += '</li>';
		}
		text += '</ul>';
		
		$("#emp-list").append(text);
	}

	ajaxRequest("list", null, "get", addMessageRoomListFunction);
	

	
	$('#add-message-room-button').click(function(){
		ajaxRequest("empList", null, "get", addEmpFunction);
	});
	
	$('#add-message-room-request').click(function(){
		var empList = $('.add-message-room-emp');
		var messageRoomTitle = $('#add-message-room-title').val();
		
		var length = empList.length;
		var list = {};
		var index = 0;
		list[index+""] = messageRoomTitle;
		++index;
		++index;
		var count = 0;
		console.log(empList[0]);
		for(var i = 0; i < length; ++i){
			if(false ==$(empList[i]).hasClass('checked'))
				continue;

			list[index+""] = $(empList[i]).children("input[name=empId]").val();
			++index;
			++count;
		}
		
		list["1"] = count; 

		// 채팅방 리스트
		$.ajax({
		    type:"post",
		    data : JSON.stringify(list),
		    dataType:"json",
		    url:"addMessageRoom",
		    contentType : 'application/json',
		    success: function(data) {
		    	requestMessageRoomList();
		    },
		    error : function(error) {
		    },	// error
		  });// ajax
		
	});
	
	//  메시지 룸 초대 인원 클릭 동적 태그 이벤트 붙여주기
	$(document).on("click", ".add-message-room-emp", function(){
		$(this).toggleClass('checked');
	});
	
	// 메시지 방 선택 시 배경색 변경
	$(document).on("click", ".chat_list", function(){
		console.log('click!!!');
		$(this).toggleClass('checked');
		
		var list = $('.chat_list');
		var length = list.length;
		
		for(var i = 0; i < length; ++i){
			if(this == list[i]){
				continue;
			}
			
			$(list[i]).removeClass('checked');
		}
	});
	
	function ajaxRequest(sendUrl, sendData, type, func){
		
		$.ajax({
		    type:type,
		    data : sendData,
		    dataType:"json",
		    url:sendUrl,
		    success: function(data) {
		    	console.log(data);
		    	func(data);
		    },
		    error : function(error) {
		    },	// error
		  });// ajax
	}
	
	function requestMessageRoomList(){
		
		$.ajax({
		    type:"get",
		    dataType:"json",
		    url:"list",
		    success: function(data) {
		    	addMessageRoomListFunction(data);
		    },
		    error : function(error) {
		    },	// error
		  });// ajax
	}
	
	
});