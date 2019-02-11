package org.kocofarm.domain.schedule;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCalenderList implements Serializable {

	private int 	categoryId;
	private String 	categoryName;
	private int 	projectId;
	private int 	calenderId;
	private String 	title;
	private String 	startDt;
	private String 	endDt;
	private String 	backgroundColor;
	private int 	completionPer;
	private int 	xPos;				// 일정의 X좌표
	private int 	yPos;				// 일정의 Y좌표
}
