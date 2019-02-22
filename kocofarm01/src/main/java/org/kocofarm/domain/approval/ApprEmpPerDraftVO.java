package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprEmpPerDraftVO {
	private String deptNm;
	private String korNm;
	private String positionNm;
	private String empId;
	private String empSign;
	private String draftSign;
}
