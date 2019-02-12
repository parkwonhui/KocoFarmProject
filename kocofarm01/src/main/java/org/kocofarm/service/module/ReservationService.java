package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.domain.meetingRoom.ReservationVO;

public interface ReservationService {
	
	//등록
	public void setReserv(ReservationVO reserv);
	
	//조회
	public ReservationVO getReserv(int rvId);
	
	//수정
	public boolean setUpReserv(ReservationVO reserv);
	
	//삭제
	public boolean delReserv(int rvId);
	
	//목록
	public List<ReservationVO> getReservList();
}
