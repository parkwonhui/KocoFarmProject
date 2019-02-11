package org.kocofarm.mapper.module;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.domain.meetingRoom.ReservationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReservationMapperTests {
	
	@Setter(onMethod_ = { @Autowired })
	private ReservationMapper mapper;
	
	//회의실 예약 목록
	/*@Test
	public void testGetReservList(){
		mapper.getReservList().forEach(reservation -> log.info(reservation));
	}*/
	
	//예약 등록
	/*	@Test
	public void testsetReserv(){
		
		String inDate   = new java.text.SimpleDateFormat("yyyyMMdd").format(new java.util.Date());

		ReservationVO reserv = new ReservationVO();
		EmpVO emp = new EmpVO();
		MeetingRoomVO mroom = new MeetingRoomVO();
		reserv.setMTitle("영업팀 회의");
		reserv.setStartDt(inDate);
		reserv.setEndDt(inDate);
		reserv.setRvUser(emp.getDeptNm());
		reserv.setRvWriter(emp.getDeptNm());
		reserv.setMId(3);
		
		mapper.setReserv(reserv);
		
		log.info(reserv);
	}*/
	
	//예약 삭제
	/*@Test
	public void testDelReserv(){
		mapper.delReserv(104);
	}*/
	
	//예약 수정

	
	

}
