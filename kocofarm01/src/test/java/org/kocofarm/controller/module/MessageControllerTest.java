package org.kocofarm.controller.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.message.MessagePushVO;
import org.kocofarm.domain.message.MessageVO;
import org.kocofarm.mapper.module.EmpMapper;
import org.kocofarm.mapper.module.MessageMapper;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class MessageControllerTest {
	@Setter(onMethod_ = {@Autowired})
	private MessageMapper mapper;
	
	@Setter(onMethod_ = {@Autowired})
	private EmpMapper empMapper;
	
	/*@Test
	public void getMember(){
		MessagePushVO pushVO = new MessagePushVO();
		pushVO.setEmpId("EMP_006");
		pushVO.setMessageRoomId(22);
		log.info(mapper.setMessagePush(pushVO));
	}*/
/*	@Test
	public void getRoom(){
		log.info(mapper.getMessageRoomList("EMP_007"));
	}
*/
/*	@Test
	public void getMessageList(){
		log.info(mapper.getMessageList(61));
	}*/
/*	@Test
	public void setMessage(){
		MessageVO vo = new MessageVO();
		vo.setMessageRoomId(61);
		vo.setEmpId("EMP_007");
		vo.setContents("저도 있어요ㅎㅎ");
		vo.setDateString("20190220160139");
		mapper.setMessage(vo);
	}
	*/
/*	@Test
	public void getMessageRoomEmpList(){
		log.info(mapper.getMessageRoomEmpList(61));
	}
*/
	
/*	@Test
	public void getEmpMessageRoom(){
		MessagePushVO vo = new MessagePushVO();
		vo.setEmpId("EMP_006");
		vo.setMessageRoomId(21);
		log.info(mapper.getEmpMessageRoom(vo));
	}
*/
/*	@Test
	public void getMessageRoomList(){
		log.info(mapper.getMessageRoomList("EMP_006"));
	}
*/
/*	@Test
	public void delMessagePush(){
		MessagePushVO vo = new MessagePushVO();
		vo.setEmpId("EMP_007");
		vo.setMessageRoomId(21);

		log.info(mapper.delMessagePush(vo));
	}
*/
	@Test
	public void delMessagePush(){
		// 회색??
		//mapper.delMessage(225);
		//mapper.delMessageRoom(225);
	}
/*	
	@Test
	public void delMessagePush(){
		mapper.getMessageRoomEmpCount(143);
	}
	*/
}
