package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.rentCar.CarAppVO;
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
		return carResMapper.getCarResList();
	}

	//등록
	@Override
	public void setCarRes(CarResVO carResVo) {
		carResMapper.setCarRes(carResVo);
	}

	//조회
	@Override
	public CarResVO getCarRes(String resId) {
		return carResMapper.getCarRes(resId);
	}
	
	
	/*승인여부 등록 */
	@Override
	public void setCarApp(CarAppVO carAppVo){
		carResMapper.setCarApp(carAppVo);
	}	
	
	/*전체 승인목록 불러오기(승인한것들 목록)*/
	@Override
	public List<CarAppVO> getCarAppList(){
		return carResMapper.getCarAppList();
	}
	
	@Override
	public CarAppVO getCarApp(String appId){
		return carResMapper.getCarApp(appId);
	}
	
	
	
}
