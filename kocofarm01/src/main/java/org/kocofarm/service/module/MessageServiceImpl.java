package org.kocofarm.service.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.message.MessageEmpListVO;
import org.kocofarm.domain.message.MessagePushVO;
import org.kocofarm.domain.message.MessageRoomListVO;
import org.kocofarm.domain.message.MessageRoomVO;
import org.kocofarm.domain.message.MessageVO;
import org.kocofarm.mapper.module.EmpMapper;
import org.kocofarm.mapper.module.MessageMapper;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

	private MessageMapper mapper;
	private EmpMapper empMapper;
	
	@Override
	public List<MessageRoomListVO> getMessageRoomList(String empId){
		
		if(null == empId){
			return null;
		}
		
		List<MessageRoomListVO> list = mapper.getMessageRoomList(empId);
		for(MessageRoomListVO vo :list){
			
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			EmpVO emp = empMapper.getEmp(vo.getLastMessageEmpId());
			if(null == emp)
				continue;
			
			String name = emp.getKorNm();
			String dateToString = transFormat.format(vo.getLastMessageDate());

			vo.setLastMessageEmpName(name);
			vo.setLastMessageDateToString(dateToString);

		}
		
		return list;
	}
	
	@Override
	public List<MessageEmpListVO> getMessageEmpList(){
		List<EmpVO> list = empMapper.getEmpList();
		List<MessageEmpListVO> messageEmpVOList = new ArrayList<MessageEmpListVO>();
		
		for(EmpVO emp : list){
			MessageEmpListVO messageEmp = new MessageEmpListVO(emp.getKorNm(), emp.getEmpId(),emp.getEmpImg());
			messageEmpVOList.add(messageEmp);
		}
		
		return messageEmpVOList;
	}
	
	@Transactional
	@Override
	public int setMessageRoom(List<String> empList, String messageRoomTitle){

		int re = 0;
		MessageRoomVO roomVO = new MessageRoomVO();
		roomVO.setRoomTitle(messageRoomTitle);
		re = mapper.setMessageRoom(roomVO);
		if(-1 >= re){
			return -1;
		}	
		
		for(String empId : empList){
			MessagePushVO pushVO = new MessagePushVO();
			pushVO.setEmpId(empId);
			pushVO.setMessageRoomId(roomVO.getMessageRoomId());
			re = mapper.setMessagePush(pushVO);
			if(-1 >= re){
				return -1;
			}
		}
		return re;
		
	}	
	
	@Override
	public List<MessageVO> getMessageList(int roomId){
		if(0 == roomId){
			return null;
		}
		List<MessageVO> list = mapper.getMessageList(roomId);
		
		for(MessageVO vo : list){
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");			
			String dateToString = transFormat.format(vo.getDt());
			vo.setDateString(dateToString);

		}	
		
		return list;
	}

	@Override
	public int setMessage(MessageVO messageVO){
		if(null == messageVO){
			return -1;
		}
		
		return mapper.setMessage(messageVO);
	}
	
	@Override
	public MessagePushVO getEmpMessageRoom(MessagePushVO messagePushVo){
		return mapper.getEmpMessageRoom(messagePushVo);
	}
	
	@Override
	public List<String> getMessageRoomEmpList(int roomId){
		return mapper.getMessageRoomEmpList(roomId);
	}

	@Override
	@Transactional
	public int delMessagePush(MessagePushVO messagePushVo, MessageVO messageVo){
		int messageRoomId = messageVo.getMessageRoomId();
		int re = mapper.setMessage(messageVo);
		re = mapper.delMessagePush(messagePushVo);
		
		int count = mapper.getMessageRoomEmpCount(messageRoomId);
		if(0 == count){
			mapper.delMessage(messageRoomId);
			mapper.delMessageRoom(messageRoomId);
		}
		return re;
	}
	
	@Override
	public MessageRoomListVO getMessageRoom(int messageRoomId){
		return mapper.getMessageRoom(messageRoomId);
	}

	
	public int setMessagePush(MessagePushVO messagePushVo){
		return mapper.setMessagePush(messagePushVo);
	}

	@Override
	public List<MessageEmpListVO> getMessageRoomInvite(int messageRoomId) {
		return mapper.getMessageRoomInvite(messageRoomId);
	}

}
