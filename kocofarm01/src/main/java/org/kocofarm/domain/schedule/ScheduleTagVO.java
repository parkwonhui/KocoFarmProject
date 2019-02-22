package org.kocofarm.domain.schedule;

import lombok.Data;

@Data

public class ScheduleTagVO {

	private int tag_id;
	private int calender_id;
	private String tag_name;
	private String tag_color;

}
