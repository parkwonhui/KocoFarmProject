package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.RentCarVO;

public interface RentCarMapper {
	
	//Î™©Î°ù
	//@Select("select * from RENTCAR_DETAIL")
	public List<RentCarVO> getRentCarDetailList();	
	/*//?ì±Î°?
	public void setRentCarDetail(RentCarVO rentCar);
	//Ï°∞Ìöå
	public RentCarVO getRentCarDetail(String carId);
	//?Ç≠?†ú
	public int delRentCarDetail(String carId); 	
	//?àò?†ï
	public int setUpRentCarDetail(RentCarVO rentCar);*/
	

}
