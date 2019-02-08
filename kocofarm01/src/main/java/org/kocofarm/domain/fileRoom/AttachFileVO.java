package org.kocofarm.domain.fileRoom;

import lombok.Data;

@Data
public class AttachFileVO {

	private String File_name;
	private String File_path;
	private String uuid;
	private String File_icon_image;
	private boolean image;
	
}