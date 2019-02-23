package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.rentCar.CarAppVO;
import org.kocofarm.domain.rentCar.CarResVO;

public interface CarResService {

		//목록
		public List<CarResVO> getCarResList();
		//등록
		public void setCarRes(CarResVO carResVo);
		//조회
		public CarResVO getCarRes(String resId);
		
		/*===============예약결제===============*/ 
		
		//승인여부 등록 
		public void setCarApp(CarAppVO carAppVo);			
		
		//전체 승인목록 불러오기(승인한것들 목록)
		public List<CarAppVO> getCarAppList();
		
		/*결제상세내역보기 - 승인번호 불러오기*/
		public CarAppVO getCarApp(String appId);
}
