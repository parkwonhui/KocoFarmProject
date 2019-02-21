package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.rentCar.CarResVO;

public interface CarResService {

		//목록
		public List<CarResVO> getCarResList();
		//등록
		public void setCarRes(CarResVO carResVo);
		//조회
		public CarResVO getCarRes(String resId);
}
