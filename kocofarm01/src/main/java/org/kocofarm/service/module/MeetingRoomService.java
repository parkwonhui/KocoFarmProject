package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;

public interface MeetingRoomService {
	
	//등록
	public void setMroom(MeetingRoomVO mroom);
	
	//조회
	public MeetingRoomVO getMroom(int mId);
	
	//수정
	public boolean setUpMroom(MeetingRoomVO mroom);
	
	//삭제
	public boolean delMroom(int mId);
	
	//목록
	public List<MeetingRoomVO> getMroomList();
}
