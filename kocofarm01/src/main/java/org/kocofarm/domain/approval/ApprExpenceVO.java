package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprExpenceVO {
	private int expenceId;
	private int draftId;
	private int formId;
	private String expenceDt;
	private String expenceType;
	private String sumPrice;
	private int contId;
	
}
