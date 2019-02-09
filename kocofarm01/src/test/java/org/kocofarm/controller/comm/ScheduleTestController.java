package org.kocofarm.controller.comm;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.schedule.ScheduleProject;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.kocofarm.service.module.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ScheduleTestController {
	@Setter(onMethod_ = {@Autowired})
	private ScheduleMapper mapper;
	
	@Before
	public void setup(){
	}
	
	@Test
	public void testList() throws Exception{
		List<ScheduleProject> list = mapper.getProjectList();	
		log.info(list);
	}

}
