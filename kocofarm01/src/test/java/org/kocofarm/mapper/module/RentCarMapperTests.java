package org.kocofarm.mapper.module;

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
public class RentCarMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private RentCarMapper rentCarMapper;
	
	/*@Test
	public void testgetRentCarDetailList(){
		rentCarMapper.getRentCarDetailList().forEach(rentCar->log.info(rentCar));
	}*/
	
	/*@Test
	public void testsetRentCarDetail(){
		
		RentCarVO rentCar = new RentCarVO();
		rentCar.setCarId("새1234");
		rentCar.setModelName("새차-1호");
		rentCar.setCarModel("새차");
		rentCar.setCondition("중고차");
		rentCar.setPrice(100);
		rentCar.setYear(2019);
		rentCar.setOilType("경유");
		
		rentCarMapper.setRentCarDetail(rentCar);
	}*/
	
	/*@Test
	public void testgetRentCarDetail(){
		RentCarVO rentCar = rentCarMapper.getRentCarDetail("새1234");
		log.info(rentCar);
	}*/
	
	/*@Test
	public void testdelRentCarDetail(){
		log.info("DELETE COUNT : " + rentCarMapper.delRentCarDetail("사1234"));
	}*/
	
	/*@Test
	public void testsetUpRentCarDetail(){
		RentCarVO rentCar = new RentCarVO();
		rentCar.setCarId("새1234");
		rentCar.setModelName("수정차-1호");
		rentCar.setCarModel("수정차");
		rentCar.setCondition("신차");
		rentCar.setPrice(500);
		rentCar.setYear(2020);
		rentCar.setOilType("경유");
		
		int count = rentCarMapper.setUpRentCarDetail(rentCar);
		log.info("UPDATE COUNT : " + count);
	}*/

}
