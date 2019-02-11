package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprFormVO {
	private int formId;
	private int sortId;
	private int modeId;
	private String sortName;
	private String modeName;
}
