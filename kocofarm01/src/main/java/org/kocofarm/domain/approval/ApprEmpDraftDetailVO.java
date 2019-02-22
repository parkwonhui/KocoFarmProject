package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprEmpDraftDetailVO {
	private int draftId;
	private String apprDt;
	private String empId;
	private String apprOption;
	private int apprLineId;
	private String draftSign;
	
}
