package org.kocofarm.domain.schedule;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ScheduleCalenderMove implements Serializable{
	private int 	categoryId;
	private int 	calenderId;
	private int 	yPos;				// 일정의 Y좌표
}
