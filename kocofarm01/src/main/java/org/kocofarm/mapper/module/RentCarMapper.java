package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.RentCarVO;

public interface RentCarMapper {
	
	//๋ชฉ๋ก
	//@Select("select * from RENTCAR_DETAIL")
	public List<RentCarVO> getRentCarDetailList();	
	/*//?ฑ๋ก?
	public void setRentCarDetail(RentCarVO rentCar);
	//์กฐํ
	public RentCarVO getRentCarDetail(String carId);
	//?ญ? 
	public int delRentCarDetail(String carId); 	
	//?? 
	public int setUpRentCarDetail(RentCarVO rentCar);*/
	

}
