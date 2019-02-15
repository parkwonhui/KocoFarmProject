package org.kocofarm.mapper.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.rentCar.CarResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CarResMapperTests {

	@Setter(onMethod_= {@Autowired})
	private CarResMapper carResMapper;
	
	/*@Test
	public void testGetCarResList(){
		carResMapper.getCarResList().forEach(CarRes ->log.info(CarRes));
	}*/
	
	/*public void testsetCarRes(){
		CarResVO carResVo = new CarResVO();
		carResVo.setCarId("가1234");
		carResVo.setResWriter("정현");
		carResVo.setResUser("아람");
		
		
	}*/
}
