package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.rentCar.CarResVO;
import org.kocofarm.mapper.module.CarResMapper;
import org.kocofarm.mapper.module.RentCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Log4j
@Service
@AllArgsConstructor
public class CarResServiceImpl implements CarResService {

	@Setter(onMethod_= {@Autowired})
	private CarResMapper carResMapper;
	
	//목록
	@Override
	public List<CarResVO> getCarResList() {
		log.info("getCarResList.......");
		return carResMapper.getCarResList();
	}

	//등록
	@Override
	public void setCarRes(CarResVO carResVo) {
		log.info("setCarRes.......");
		carResMapper.setCarRes(carResVo);
	}

}
