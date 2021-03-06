package org.kocofarm.service.module;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.rentCar.RentCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class RentCarServiceTests {

	@Setter(onMethod_= {@Autowired})
	private RentCarService rentCarService;
	
	/*@Test
	public void testExist(){
		log.info(rentCarService);
		assertNotNull(rentCarService);
	}*/
	
/*	@Test
	public void testsetRentCarDetail(){
		
		RentCarVO rentCar = new RentCarVO();
		rentCar.setCarId("휴1234");
		rentCar.setModelName("레이-5호");
		rentCar.setCarModel("레이");
		rentCar.setCondition("신차");
		rentCar.setPrice(5000);
		rentCar.setYear(2019);
		rentCar.setOilType("휘발유");
		
		rentCarService.setRentCarDetail(rentCar);
		
		log.info("생성된 게시물 차량번호 : " + rentCar.getCarId());
		
	}*/
	
	
	@Test
	public void testgetRentCarDetailList(){
		//rentCarService.getRentCarDetailList().forEach(rentCar->log.info(rentCar));
		rentCarService.getRentCarDetailList
		(new Criteria(2,10)).forEach(rentCar->log.info(rentCar));
	}
	
	/*@Test
	public void testgetRentCarDetail(){
		log.info(rentCarService.getRentCarDetail("사1234"));
	}*/
	
	//수정
	/*@Test
	public void testsetUpRentCarDetail(){
		RentCarVO rentCar = rentCarService.getRentCarDetail("호1234");
		if(rentCar == null){
			return;
		}
		rentCar.setCondition("새차새차");
		log.info("setUp result : " + rentCarService.setUpRentCarDetail(rentCar));
	}*/
	
	/*@Test
	public void testdelRentCarDetail(){
		log.info("delete result : " + rentCarService.delRentCarDetail("사1234"));
	}*/
	
	
	
	
}//RentCarServiceTests
