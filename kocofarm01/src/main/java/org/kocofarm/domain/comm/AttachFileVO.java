package org.kocofarm.domain.comm;

import lombok.Data;

@Data
public class AttachFileVO {
	private String uudi;
	private String uploadPath;
	private String fileName;
	private boolean fileType;
	
	private Long bno;
	

}
