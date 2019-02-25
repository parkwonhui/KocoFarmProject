package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.comm.AttachFileVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.mapper.comm.AttachFileMapper;
import org.kocofarm.mapper.module.RentCarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class FileServiceImpl implements FileService {

	@Setter(onMethod_= {@Autowired})
	private AttachFileMapper attachFileMapper;
	
	@Override
	public void setFile(AttachFileVO fileVO) {
		log.info(fileVO);
		 attachFileMapper.setFile(fileVO);
		log.info(fileVO);

		
	}

	@Override
	public String delFile(String uuid) {
		
		return attachFileMapper.delFile(uuid);
	}

	@Override
	public List<AttachFileVO> setFileList(Criteria cri) {
		
		return attachFileMapper.getFileList(cri);
	}

}
