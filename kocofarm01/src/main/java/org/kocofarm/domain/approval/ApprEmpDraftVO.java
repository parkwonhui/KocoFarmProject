package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprEmpDraftVO {
	private int draftId;
	private String draftName;
	private String modeName;
	private String draftDt;
	private String empId;
	private String korNm;
	private String apprOption;
} 
