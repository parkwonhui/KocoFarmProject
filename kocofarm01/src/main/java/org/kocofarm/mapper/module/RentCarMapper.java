package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.kocofarm.domain.rentCar.RentCarVO;

public interface RentCarMapper {
	
	//목록
	//@Select("select * from RENTCAR_DETAIL")
	public List<RentCarVO> getRentCarDetailList();	
<<<<<<< HEAD
	//등록
=======
	/*//?���?
>>>>>>> refs/remotes/origin/master
	public void setRentCarDetail(RentCarVO rentCar);
	//조회
	public RentCarVO getRentCarDetail(String carId);
	//?��?��
	public int delRentCarDetail(String carId); 	
<<<<<<< HEAD
	//수정
	public int setUpRentCarDetail(RentCarVO rentCar);
=======
	//?��?��
	public int setUpRentCarDetail(RentCarVO rentCar);*/
>>>>>>> refs/remotes/origin/master
	

}
