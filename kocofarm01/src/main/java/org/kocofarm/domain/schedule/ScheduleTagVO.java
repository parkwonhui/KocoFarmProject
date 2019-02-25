package org.kocofarm.domain.schedule;

import lombok.Data;

@Data

public class ScheduleTagVO {

	private int tagId;
	private int calenderId;
	private String tagName;
	private String tagColor;

}
