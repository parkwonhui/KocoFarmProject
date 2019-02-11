package org.kocofarm.domain.schedule;


import java.io.Serializable;

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
public class ScheduleCalenderListVO implements Serializable {

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
	
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
