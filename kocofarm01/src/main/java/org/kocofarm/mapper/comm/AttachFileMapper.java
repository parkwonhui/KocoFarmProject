package org.kocofarm.mapper.comm;

import java.util.List;

import org.kocofarm.domain.comm.AttachFileVO;
import org.kocofarm.domain.comm.Criteria;

public interface AttachFileMapper {
	
	public void setFile(AttachFileVO vo);

	public String delFile(String uuid);
	
	public List<AttachFileVO> getFileList(Criteria cri);
	

}
