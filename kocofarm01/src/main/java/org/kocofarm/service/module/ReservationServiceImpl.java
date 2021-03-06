package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.domain.meetingRoom.ReservationVO;
import org.kocofarm.mapper.module.EmpMapper;
import org.kocofarm.mapper.module.ReservationMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{

	private ReservationMapper mapper;

	//예약 등록
	@Override
	public void setReserv(ReservationVO reserv) {
		mapper.setReserv(reserv);
	}

	//조회
	@Override
	public ReservationVO getReserv(int rvId) {
		
		return mapper.getReserv(rvId);
	}

	//수정
	@Override
	public boolean setUpReserv(ReservationVO reserv) {
		
		return mapper.setUpReserv(reserv) == 1;
	}

	//삭제
	@Override
	public boolean delReserv(int rvId) {
		
		return mapper.delReserv(rvId) == -1;
	}

	//목록
	@Override
	public List<ReservationVO> getReservList() {
		
		return mapper.getReservList();
	}

	//회의실 이름
	@Override
	public MeetingRoomVO getMroomNm(int mId) {
		
		return mapper.getMroomNm(mId);
	}

	//회의실 번호 확인
	@Override
	public ReservationVO getIdChk(int mId) {
		
		return mapper.getIdChk(mId);
	}
	
}
