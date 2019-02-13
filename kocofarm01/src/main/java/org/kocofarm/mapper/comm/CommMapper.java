package org.kocofarm.mapper.comm;

import org.kocofarm.domain.comm.LoginVO;

public interface CommMapper {
	public LoginVO getLoginUserInfo(String userId);
}
