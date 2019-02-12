package org.kocofarm.service.module;

import static org.junit.Assert.assertNotNull;

import java.util.concurrent.Delayed;

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
public class MroomServiceTests {

		@Setter(onMethod_ = { @Autowired })
		private MeetingRoomService service;
		
		@Test
		public void testExist(){
			log.info(service);
			assertNotNull(service);
		}
		
		@Test
		public void testsetMroom(){
		MeetingRoomVO mroom = new MeetingRoomVO();
		mroom.setmId(1);
		mroom.setmName("회의실1");
		mroom.setpNum(3);
		
		service.setMroom(mroom);
		log.info(mroom.getmId());
		
		}
		
		//목록
		/*@Test
		public void testGetMroomList(){
			
			service.getMroomList().forEach(meetingroom -> log.info(meetingroom));
		}*/
		
		
		/*//조회
		@Test
		public void testGetMroomList(){
			
			log.info(service.getMroom(3));
		}*/
		
		/*@Test
		public void testDelete(){
			log.info(service.remove(1));
		}*/
		
		/*@Test
		public void testUpate(){
			MeetingRoomVO mroom = service.get(3);
			
			if(mroom == null){
				return;
			}//end if
			
			mroom.setMName("회의실7");
			log.info(service.modify(mroom));
		}*/
}
