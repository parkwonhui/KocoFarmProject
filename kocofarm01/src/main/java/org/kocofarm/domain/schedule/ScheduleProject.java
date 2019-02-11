package org.kocofarm.domain.schedule;


import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleProject implements Serializable{

	private long projectId;
	private String title;
	private String projectLeader;
	private String projectStartDt;
	private String projectEndDt;
	private int projectRegDt;
	private int projectCompletion;
	private boolean publicProject;
}
