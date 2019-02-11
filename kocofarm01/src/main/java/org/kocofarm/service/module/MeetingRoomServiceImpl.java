package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.mapper.module.MeetingRoomMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MeetingRoomServiceImpl implements MeetingRoomService{

	private MeetingRoomMapper mapper;

	/*@Override
	public void register(MeetingRoomVO mroom) {
		log.info("register" + mroom);
		
		mapper.insert(mroom);
		
	}*/

	//조회
	@Override
	public MeetingRoomVO getMroomList(int mId) {
		log.info("get" + mId);
		
		return mapper.getMroom(mId);
	}

	//수정
	@Override
	public boolean setUpMroom(MeetingRoomVO mroom) {
		log.info("modify" + mroom);
		
		return mapper.setUpMroom(mroom) == 1;
	}

	//삭제
	@Override
	public boolean delMroom(int mId) {
		log.info("remove" + mId);
		
		return mapper.delMroom(mId) == -1;
	}

	//목록
	@Override
	public List<MeetingRoomVO> getMroomList() {
		log.info("getList");
		
		return mapper.getMroomList();
	}
	
	
	
}
