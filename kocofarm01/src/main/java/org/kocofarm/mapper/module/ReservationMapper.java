package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.ReservationVO;

public interface ReservationMapper {

	//@Select("select m_id AS mId from meetingroom where m_id > 0")
	public List<ReservationVO> getList();
	
	//등록
	public void insert(ReservationVO reserv);
	
	/*//키를 찾아서 등록
	public void insertSelectKey(MeetingRoomVO mroom);*/
	
	//조회
	public ReservationVO read(int rvId);
	
	/*//삭제
	public int delete(int rvId);
	//수정
	public int update(ReservationVO reserv);*/
}
