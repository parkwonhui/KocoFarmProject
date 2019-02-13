package org.kocofarm.domain.fileRoom;

import lombok.Data;

@Data
public class AttachFileVO {

	private String FileName;
	private String FilePath;
	private String uuid;
	private boolean image;
	
}