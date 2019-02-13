package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.domain.meetingRoom.ReservationVO;

public interface ReservationMapper {

	//조회
	public List<ReservationVO> getReservList();
	
	//등록
	public void setReserv(ReservationVO reserv);
	
	//회의실 이름 불러오기
	public MeetingRoomVO getMroomNm(int mId); 
	
	//삭제
	public int delReserv(int rvId);

	//조회
	public ReservationVO getReserv(int rvId);
	
	//수정
	public int setUpReserv(ReservationVO reserv);
}
