package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessagePushVO;
import org.kocofarm.domain.message.MessageRoomListVO;
import org.kocofarm.domain.message.MessageVO;

public interface MessageService {
	
	public List<MessageRoomListVO> getMessageRoomList(String empId);
	public List<MessageEmpListVO> getMessageEmpList();
	public int setMessageRoom(List<String> empList, String messageRoomTitle);
	public List<MessageVO> getMessageList(int roomId);
	public int setMessage(MessageVO messageVO);
	public MessagePushVO getEmpMessageRoom(MessagePushVO messagePushVo);
	public List<String> getMessageRoomEmpList(int roomId);
	public int delMessagePush(MessagePushVO messagePushVo);

}
