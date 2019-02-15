package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.rentCar.RentCarVO;

public interface RentCarMapper {
	
	//목록
	public List<RentCarVO> getRentCarDetailList();	
	//등록
	public void setRentCarDetail(RentCarVO rentCar);
	//조회
	public RentCarVO getRentCarDetail(String carId);
	//삭제
	public int delRentCarDetail(String carId); 	
	//수정
	public int setUpRentCarDetail(RentCarVO rentCar);	
	//페이징
	public List<RentCarVO> getListWithPaging(Criteria cri);
	//글 갯수
	public int getTotalCount(Criteria cri);
	//차량번호 중복확인
	public RentCarVO getcarIdChk(String carID);
	
	
}
