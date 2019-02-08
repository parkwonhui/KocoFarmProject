package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprDraftVO {

	private int draftId;
	private String draftName;
	private String draftDt;
	private String draftTitle;
	private String empId;
	private int draftYear;
	private int formId;
	private String approveState;
	
}
