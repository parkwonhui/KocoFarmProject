package org.kocofarm.service.module;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.rentCar.RentCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	/*@Test
	public void testsetRentCarDetail(){
		
		RentCarVO rentCar = new RentCarVO();
		rentCar.setCarId("사1234");
		rentCar.setModelName("새차-1호");
		rentCar.setCarModel("새차");
		rentCar.setCondition("새차");
		rentCar.setPrice(5000);
		rentCar.setYear(2020);
		rentCar.setOilType("휘발유");
		
		rentCarService.setRentCarDetail(rentCar);
		
		log.info("생성된 게시물 차량번호 : " + rentCar.getCarId());
		
	}*/
	
	
	/*@Test
	public void testgetRentCarDetailList(){
		rentCarService.getRentCarDetailList().forEach(rentCar->log.info(rentCar));
	}*/
	
	/*@Test
	public void testgetRentCarDetail(){
		log.info(rentCarService.getRentCarDetail("사1234"));
	}*/
	
	//수정
	/*@Test
	public void testsetUpRentCarDetail(){
		RentCarVO rentCar = rentCarService.getRentCarDetail("사1234");
		if(rentCar == null){
			return;
		}
		rentCar.setCondition("중고차");
		log.info("setUp result : " + rentCarService.setUpRentCarDetail(rentCar));
	}*/
	
	@Test
	public void testdelRentCarDetail(){
		log.info("delete result : " + rentCarService.delRentCarDetail("사1234"));
	}
	
	
}//RentCarServiceTests
