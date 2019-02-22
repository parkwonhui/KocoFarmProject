package org.kocofarm.mapper.comm;

import java.util.List;

import org.kocofarm.domain.comm.AttachFileVO;

public interface AttachFileMapper {
	
	public void setFile(AttachFileVO vo);

	public void delFile(String uuid);
	
	public List<AttachFileVO> getFileList(long bno);
	

}
