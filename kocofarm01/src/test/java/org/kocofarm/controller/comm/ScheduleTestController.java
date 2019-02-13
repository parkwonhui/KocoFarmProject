package org.kocofarm.controller.comm;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.mapper.module.EmpMapper;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.kocofarm.service.module.ScheduleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@Setter(onMethod_ = {@Autowired})
	private EmpMapper empMapper;
	
	
	@Before
	public void setup(){
	}
	
/*	@Test
	public void testList() throws Exception{
		List<ScheduleProject> list = mapper.getProjectList();	
		log.info(list);
	}
*/
/*	@Test
	public void testGetProjectCalenderList(){
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(1);
		log.info(list);
	}
*/	
	
/*	@Test
	public void testSetCalender(){
		ScheduleCalender calender = new ScheduleCalender(101, "타이틀", "2019-02-01", "2019-02-01", "", 10, 10, 10, 61);
		mapper.setCalender(calender);
		
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(1);
		log.info(list);
			
	}
	*/
	
	/*@Test
	public void testSetUpCalender(){
		ScheduleCalender calender = new ScheduleCalender(154, "타이틀1212121", "2019-02-01", "2019-02-01", "", 10, 10, 10, 61);
		mapper.setUpCalender(calender);
		
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(1);
		log.info(list);
			
	}*/
	
/*	@Test
	public void testDelCalender(){
		mapper.delCalender(153);
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(1);
		log.info(list);
	
	}
	*/
	/*@Test
	public void testSetUpCalenderPos(){
		List<ScheduleCalenderMove> calenderMoveList = new ArrayList<ScheduleCalenderMove>();
		ScheduleCalenderMove calenderMove2 = new ScheduleCalenderMove(62,154, 7);
		calenderMoveList.add(calenderMove2);
		calenderMoveList.add(calenderMove2);
		calenderMoveList.add(calenderMove2);
		calenderMoveList.add(calenderMove2);
		calenderMoveList.add(calenderMove2);
		calenderMoveList.add(calenderMove2);
		ScheduleCalenderMove calenderMove3 = new ScheduleCalenderMove(61,154, 7);
		calenderMoveList.add(calenderMove3);
		mapper.setUpCalenderPos(calenderMove3);
		for(ScheduleCalenderMove calenderMove : calenderMoveList){
			mapper.setUpCalenderPos(calenderMove);
		}
		
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(1);
		log.info(list);
	}*/
	
/*	@Test
	public void setCategory(){
		ScheduleCategory category = new ScheduleCategory(100, "새로운 카테고리", 1, 5);
		mapper.setCategory(category);
		
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(1);
		log.info(list);
	}*/
	
/*	@Test
	public void setUpCategory(){
		ScheduleCategory category = new ScheduleCategory(66, "새로운 카테고리2", 1, 2);
		mapper.setUpCategory(category);
	}*/
/*		
	@Test
	public void setProject(){
		ScheduleProjectVO project = new ScheduleProjectVO(5,"졸려","", "",0,0,false, "EMP_002");
		mapper.setProject(project);
		
		mapper.getProjectList(null);
	}*/
	
/*	@Test
	public void setUpProject(){
		ScheduleProject project = new ScheduleProject(101,"졸려BBBBB222BB","2019-03-22","2019-05-22", "",80,0,true);
		mapper.setUpProject(project);
		
		List<ScheduleProject> list = mapper.getProjectList();
		log.info(list);
	}*/
/*	
	@Test
	public void delCategory(){
		mapper.delCategory(66);
	}	*/
	
/*	@Test
	public void getProjectEmp(){
		ScheduleProjectSearchVO search = new ScheduleProjectSearchVO("EMP_006");
		mapper.getProjectList(search);
	}*/
	
/*	@Test
	public void test(){
		empMapper.getDeptListEmp("EMP_006");
	}
*/
	@Test
	public void setProject(){
		//ScheduleProjectVO project = new ScheduleProjectVO(5,"졸려","EMP_002","", "",0,0,false);
		//mapper.setProject(project);
		mapper.setMember(new ScheduleMemberVO(1000, 0, 0, "EMP_002", 142, 2));
		//mapper.getProjectList(null);
	}

}
