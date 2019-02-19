package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprEmpVO {
	private int apprlineId;
	private String apprDt;
	private int apprOption;
	private int draftId;
	private String empId;
}
