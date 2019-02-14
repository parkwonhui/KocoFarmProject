package org.kocofarm.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleMemberVO {
	int memberIndex;
	int completeCount;
	int progressCount;
	String empId;
	int calenderId;
	int projectId;
}
