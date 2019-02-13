package org.kocofarm.service.comm;

import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.mapper.comm.CommMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class SignInOutServiceImpl implements SignInOutService{
	private CommMapper commMapper;
	
	@Override
	public LoginVO getLoginUserInfo(String userId) {
		
		LoginVO loginVO = commMapper.getLoginUserInfo(userId);
		
		return loginVO;
	}

}
