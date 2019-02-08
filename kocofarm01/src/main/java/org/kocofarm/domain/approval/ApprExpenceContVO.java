package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprExpenceContVO {

	private int expenceId;
	private int contSeq;
	private String customerName;
	private String expencePrice;
	private String commissionOption;
	private String commissionPrice;
}
