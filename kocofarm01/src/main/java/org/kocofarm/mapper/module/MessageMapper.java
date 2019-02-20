package org.kocofarm.mapper.module;

import java.util.List;

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
}
