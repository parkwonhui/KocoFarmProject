package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.RentCarVO;

public interface RentCarMapper {
	
	//๋ชฉ๋ก
	//@Select("select * from RENTCAR_DETAIL")
	public List<RentCarVO> getRentCarDetailList();	
<<<<<<< HEAD
	//๋ฑ๋ก
=======
	/*//?ฑ๋ก?
>>>>>>> refs/remotes/origin/master
	public void setRentCarDetail(RentCarVO rentCar);
	//์กฐํ
	public RentCarVO getRentCarDetail(String carId);
	//?ญ? 
	public int delRentCarDetail(String carId); 	
<<<<<<< HEAD
	//์์ 
	public int setUpRentCarDetail(RentCarVO rentCar);
=======
	//?? 
	public int setUpRentCarDetail(RentCarVO rentCar);*/
>>>>>>> refs/remotes/origin/master
	

}
