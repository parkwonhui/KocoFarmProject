package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessageRoomListVO;

public interface MessageService {
	
	public List<MessageRoomListVO> getMessageRoomList(String empId);
	public List<MessageEmpListVO> getMessageEmpList();
	public int setMessageRoom(List<String> empList, String messageRoomTitle);
}
