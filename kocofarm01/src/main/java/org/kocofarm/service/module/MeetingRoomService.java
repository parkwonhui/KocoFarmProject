package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;

public interface MeetingRoomService {
	//public void register(MeetingRoomVO mroom);
	
	//조회
	public MeetingRoomVO get(int mId);
	
	//수정
	public boolean modify(MeetingRoomVO mroom);
	
	//삭제
	public boolean remove(int mId);
	
	//목록
	public List<MeetingRoomVO> getList();
}
