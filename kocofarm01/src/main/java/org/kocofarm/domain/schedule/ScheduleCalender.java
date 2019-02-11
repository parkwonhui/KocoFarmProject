package org.kocofarm.domain.schedule;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCalender implements Serializable{

	private int calenderId;
	private String title;
	private String startDt;
	private String endDt;
	private String backgroundColor;
	private int completionPer;
	private int xPos;
	private int yPos;
	private int categoryId;
			
}
