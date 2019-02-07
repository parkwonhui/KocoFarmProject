package org.kocofarm.mapper.module;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MroomMapperTests {
	
	@Setter(onMethod_ =  @Autowired )
	private MeetingRoomMapper mapper;
	
	/*@Test
	public void testGetList(){
		mapper.getList().forEach(meetingroom -> log.info(meetingroom));
	}*/

	/*@Test
	public void testInsert(){
		MeetingRoomVO mroom = new MeetingRoomVO();
		mroom.setMId(3);
		mroom.setMName("회의실3");
		mroom.setPNum(9);
		
		mapper.insert(mroom);
		
		log.info(mroom);
	}*/
	
	/*@Test
	public void testInsertSelectKey(){
		MeetingRoomVO mroom = new MeetingRoomVO();
		mroom.setMId(4);
		mroom.setMName("회의실4");
		mroom.setPNum(11);
		
		mapper.insertSelectKey(mroom);
		
		log.info(mroom);
	}*/
	
	/*@Test
	public void testRead(){
		MeetingRoomVO mroom = mapper.read(3);
		
		log.info(mroom);
	}*/
	
	/*@Test
	public void testDelete(){
		log.info("DELETE COUNT: " + mapper.delete(2));
	}*/
	
	/*@Test
	public void testUpdate(){
		MeetingRoomVO mroom = new MeetingRoomVO();
		mroom.setMId(3);
		mroom.setMName("회의실77");
		mroom.setPNum(5);
		
		int count = mapper.update(mroom);
		log.info(mroom);
	}*/
	
}
