package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.mapper.module.MeetingRoomMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
public class MeetingRoomServiceImpl implements MeetingRoomService{

	private MeetingRoomMapper mapper;

	//등록
	@Override
	public void setMroom(MeetingRoomVO mroom){
		
	 	mapper.setMroom(mroom);
	}

	//조회
	@Override
	public MeetingRoomVO getMroom(int mId) {

		return mapper.getMroom(mId);
	}

	//수정
	@Override
	public boolean setUpMroom(MeetingRoomVO mroom) {

		return mapper.setUpMroom(mroom) == 1;
	}

	//삭제
	@Override
	public boolean delMroom(int mId) {

		return mapper.delMroom(mId) == -1;
	}

	//목록
	@Override
	public List<MeetingRoomVO> getMroomList() {

		return mapper.getMroomList();
	}

	//회의실 번호 확인
	@Override
	public MeetingRoomVO getIdChk(int mId) {
		
		return mapper.getIdChk(mId);
	}
	
	
	
}
