package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessagePushVO;
import org.kocofarm.domain.message.MessageRoomListVO;
import org.kocofarm.domain.message.MessageRoomVO;
import org.kocofarm.domain.message.MessageVO;

public interface MessageMapper {

	public List<MessageRoomListVO> getMessageRoomList(String empId);
	public int setMessageRoom(MessageRoomVO messageRoomVo);
	public int setMessagePush(MessagePushVO MessagePushVo);
	public List<MessageVO> getMessageList(int roomId);
	public int setMessage(MessageVO messageVO);
	public MessagePushVO getEmpMessageRoom(MessagePushVO messagePushVo);
	public List<String> getMessageRoomEmpList(int roomId);
	public int delMessagePush(MessagePushVO messagePushVo);
	public int delMessage(int messageRoomId);
	public int delMessageRoom(int messageRoomId);
	public int getMessageRoomEmpCount(int messageRoomId);
	public List<MessageEmpListVO> getMessageRoomInvite(int messageRoomId);
}
