package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.CarAppVO;
import org.kocofarm.domain.rentCar.CarResVO;

public interface CarResMapper {
	
	//예약 신청한 목록(전체예약목록)
	public List<CarResVO> getCarResList();
	//예약 등록
	public void setCarRes(CarResVO carResVo);
	//차량 예약 중복확인--관리자의 승인이 난 후에 중복확인을 할 수 있다. 
	/*public */
	//예약신청 내용 조회
	public CarResVO getCarRes(String resId);
	
	
	
	/*============예약결제============*/
	
	/*승인여부 등록 */
	public void setCarApp(CarAppVO carAppVo);	
	/*승인목록 (승인한것들 목록)*/
	public List<CarAppVO> getCarAppList();
	
	/*결제상세내역보기
	public CarAppVO getCarApp(String appId);*/
	
	/*결제내역불러오기*/

}
