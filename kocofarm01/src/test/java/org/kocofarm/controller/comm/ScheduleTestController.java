package org.kocofarm.controller.comm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	private WebApplicationContext context;
	private MockMvc dumyMvc;
	
	@Before
	public void setup(){
		this.dumyMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testList() throws Exception{
		log.info(dumyMvc.perform(MockMvcRequestBuilders.get("/schedule/"))
		.andReturn()
		.getModelAndView()
		.getModelMap());		
	}

}
