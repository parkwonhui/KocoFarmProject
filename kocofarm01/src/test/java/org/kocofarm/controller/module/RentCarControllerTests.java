package org.kocofarm.controller.module;


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
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Log4j
public class RentCarControllerTests {
	
	@Setter(onMethod_ ={@Autowired})
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup(){
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	//목록
	@Test
	public void testRentCarDetailList() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/rent/rentCarDetailList"))
				.andReturn()
				.getModelAndView()
				.getModelMap());		
	}
	
	//등록
	/*@Test
	public void testRentCarDetailWrite() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/rent/rentCarDetailWrite")
				.param("carId", "하1234")
				.param("modelName","미니쿠페-3호차")
				.param("carModel","미니쿠페")
				.param("condition", "신차")
				.param("price", "4000")
				.param("year", "2020")
				.param("oilType", "휘발유")
				).andReturn().getModelAndView().getViewName();
		
				log.info(resultPage);		
	}*/
	
	//조회
	/*@Test
	public void testRentCarDetailView() throws Exception{
		log.info(mockMvc.perform(MockMvcRequestBuilders
				.get("/rent/rentCarDetailView")
				.param("carId", "호1234"))
				.andReturn().getModelAndView().getModelMap()
				);
	}*/
	
	//수정
	/*@Test
	public void testRentCarDetailEdit()throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/rent/rentCarDetailEdit")
				.param("carId", "호1234")
				.param("modelName","새차-1호")
				.param("carModel","새차")
				.param("condition", "새차")
				.param("price", "1000")
				.param("year", "2020")
				.param("oilType", "휘발유"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	//삭제
	/*@Test
	public void testRentCarDetailDel() throws Exception{
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/rent/rentCarDetailDel")
				.param("carId", "사1234")
				).andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/
	
	/*@Test
	public void testListPaging() throws Exception{
		log.info(
				mockMvc.perform(
						MockMvcRequestBuilders.get("/rent/rentCarDetailList")
						.param("pageNum", "1")
						.param("amount", "10"))
						.andReturn().getModelAndView().getModelMap()				
				);
	}*/
	
	
	
}//RentCarControllerTests
