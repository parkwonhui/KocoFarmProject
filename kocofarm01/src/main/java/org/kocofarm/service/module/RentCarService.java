package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.rentCar.RentCarVO;

public interface RentCarService {

		//목록
		public List<RentCarVO> getRentCarDetailList();	
		//등록
		public void setRentCarDetail(RentCarVO rentCar);
		//조회
		public RentCarVO getRentCarDetail(String carId);
		//삭제
		public boolean delRentCarDetail(String carId); 	
		//수정
		public boolean setUpRentCarDetail(RentCarVO rentCar);
	
		
}
