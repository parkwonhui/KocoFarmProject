package org.kocofarm.controller.module;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.service.module.ApproalServiceTest;
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
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
					"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
@WebAppConfiguration
public class ApprovalControllerTest {

	@Setter(onMethod_ = {@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
/*	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/module/approval/getDraftList"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}*/
	
/*	@Test
	public void testSetDraft() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/module/approval/setDraft")
				.param("draftDt", "2018-02-11")
				.param("draftName","기안")
				.param("draftTitle","기안서")
				.param("draftYear","3")
				.param("formId","1")
				.param("empId","EMP_004")
				.param("approveState","기안중")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	/*@Test
	public void testGetDraft() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/module/approval/getDraft")
				.param("draftId", "165"))
				.andReturn()
				.getModelAndView().getModelMap());
	}*/
	/*public void testDelete() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/approval/delDraft")
				.param("draftId","165")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	@Test
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/approval/getDraftList")
								.param("pageNum", "2")
								.param("amount","5"))
								.andReturn()
								.getModelAndView()
								.getModelMap());
		}
	
}
