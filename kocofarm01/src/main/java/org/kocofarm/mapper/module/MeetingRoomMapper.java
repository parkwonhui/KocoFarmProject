package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;

public interface MeetingRoomMapper {

	//@Select("select m_id AS mId from meetingroom where m_id > 0")
	public List<MeetingRoomVO> getMroomList();
	
	//등록
	public void setMroom(MeetingRoomVO mroom);
	
	/*//키를 찾아서 등록
	public void insertSelectKey(MeetingRoomVO mroom);*/
	
	//조회
	public MeetingRoomVO getMroom(int mId);
	//삭제
	public int delMroom(int mId);
	//수정
	public int setUpMroom(MeetingRoomVO mroom);
}
