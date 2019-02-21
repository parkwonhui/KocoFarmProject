package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.CarResVO;

public interface CarResMapper {
	
	//목록
	public List<CarResVO> getCarResList();
	//등록
	public void setCarRes(CarResVO carResVo);
	//차량 예약 중복확인--관리자의 승인이 난 후에 중복확인을 할 수 있다. 
	/*public */
	
	//조회
	public CarResVO getCarRes(String resId);
	
}
