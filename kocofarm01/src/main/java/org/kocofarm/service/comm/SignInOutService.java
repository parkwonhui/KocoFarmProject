package org.kocofarm.service.comm;

import org.kocofarm.domain.comm.LoginVO;

public interface SignInOutService {
	public LoginVO getLoginUserInfo(String userId);
}
