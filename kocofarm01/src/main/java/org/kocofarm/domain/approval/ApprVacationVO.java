package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprVacationVO {

	private int vacationId;
	private int draftId;
	private int formId;
	private String vacationStartDt;
	private String vacationEndDt;
	private String vacationType;
	private int vacationDays;
	private String vacationReason;
	private String replacementId;
}
