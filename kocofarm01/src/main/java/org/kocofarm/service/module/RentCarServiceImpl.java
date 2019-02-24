package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.rentCar.RentCarVO;
import org.kocofarm.mapper.module.RentCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class RentCarServiceImpl implements RentCarService {

	@Setter(onMethod_= {@Autowired})
	private RentCarMapper rentCarMapper;	
	
	//목록
	@Override
	public List<RentCarVO> getRentCarDetailList(Criteria cri) {
		return rentCarMapper.getListWithPaging(cri);
	}

	//등록
	@Override
	public void setRentCarDetail(RentCarVO rentCar) {
		rentCarMapper.setRentCarDetail(rentCar);

	}

	//조회
	@Override
	public RentCarVO getRentCarDetail(String carId) {
		return rentCarMapper.getRentCarDetail(carId);
	}

	//삭제
	@Override
	public boolean delRentCarDetail(String carId) {
		return rentCarMapper.delRentCarDetail(carId)==-1;
	}

	//수정
	@Override
	public boolean setUpRentCarDetail(RentCarVO rentCar) {
		return rentCarMapper.setUpRentCarDetail(rentCar)==1;
	}

	@Override
	public int getTotal(Criteria cri){
		return rentCarMapper.getTotalCount(cri);
	}
	
	@Override
	public RentCarVO getcarIdChk(String carID){
		return rentCarMapper.getcarIdChk(carID);
	}
	
}
