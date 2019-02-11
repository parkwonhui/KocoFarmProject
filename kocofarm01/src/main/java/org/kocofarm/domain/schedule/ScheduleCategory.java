package org.kocofarm.domain.schedule;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleCategory implements Serializable {
	private int categoryId;
	private String categoryName;
	private int projectId;
	private int xPos;		
}
