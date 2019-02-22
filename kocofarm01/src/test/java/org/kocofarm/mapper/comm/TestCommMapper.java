package org.kocofarm.mapper.comm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.service.comm.SignInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class TestCommMapper {
	
	@Setter(onMethod_ = @Autowired)
	private CommMapper mapper;
	
	@Setter(onMethod_ = { @Autowired })
	private SignInOutService service;
	
	@Test
	public void testgetLoginUserInfo(){
		
		LoginVO vo = mapper.getLoginUserInfo("EMP_003");
		
		LoginVO vo2 = service.getLoginUserInfo("EMP_008");
		
		log.info(vo);
		
	}
}
