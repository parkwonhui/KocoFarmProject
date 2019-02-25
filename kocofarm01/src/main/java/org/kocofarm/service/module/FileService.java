package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.comm.AttachFileVO;
import org.kocofarm.domain.comm.Criteria;

public interface FileService {

	
	public void setFile(AttachFileVO fileVO);
	
	public String delFile(String uuid);
	
	public List<AttachFileVO> setFileList(Criteria cri);
}
