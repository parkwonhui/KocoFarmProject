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
	public MeetingRoomVO get(int mId) {
		log.info("get" + mId);
		
		return mapper.read(mId);
	}

	//수정
	@Override
	public boolean modify(MeetingRoomVO mroom) {
		log.info("modify" + mroom);
		
		return mapper.update(mroom) == 1;
	}

	@Override
	public boolean remove(int mId) {
		log.info("remove" + mId);
		
		return mapper.delete(mId) == -1;
	}

	//목록
	@Override
	public List<MeetingRoomVO> getList() {
		log.info("getList");
		
		return mapper.getList();
	}
	
	
	
}
