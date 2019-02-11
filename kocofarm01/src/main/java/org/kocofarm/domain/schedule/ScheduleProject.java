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
