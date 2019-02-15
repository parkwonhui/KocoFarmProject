package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.CarResVO;

public interface CarResMapper {
	
	//목록
	//@Select("SELECT * FROM CAR_RES")
	public List<CarResVO> getCarResList();

	//예약
	
}
