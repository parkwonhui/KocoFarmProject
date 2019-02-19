package org.kocofarm.controller.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.message.MessagePushVO;
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
	@Test
	public void getRoom(){
		log.info(mapper.getMessageRoomList("EMP_007"));
	}
}
