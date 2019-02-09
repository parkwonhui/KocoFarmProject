package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleCalender;
import org.kocofarm.domain.schedule.ScheduleCalenderList;
import org.kocofarm.domain.schedule.ScheduleCalenderMove;
import org.kocofarm.domain.schedule.ScheduleCategory;
import org.kocofarm.domain.schedule.ScheduleCategoryMove;
import org.kocofarm.domain.schedule.ScheduleProject;
import org.springframework.ui.Model;

public interface ScheduleService {
	public List<ScheduleProject> getProjectList();
	public List<ScheduleCalenderList> getProjectCalenderList(int projectId);
	public int setCalender(ScheduleCalender calender);
	public int setUpCalender(ScheduleCalender scheduleCalender);
	public int delCalender(int calenderId);
	public int setUpCalenderPos(List<ScheduleCalenderMove> calenderMoveList);
	public int setCategory(ScheduleCategory category);
	public int setUpCategory(ScheduleCategory category);
	public int setProject(ScheduleProject project);
	public int setUpProject(ScheduleProject project);
	public int delCategory(ScheduleCategory category);
	public int setMoveCategory(ScheduleCategoryMove category);
	public int delProject(int projectId);

}
