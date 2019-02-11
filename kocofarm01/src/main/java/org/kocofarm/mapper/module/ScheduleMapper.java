package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleCalender;
import org.kocofarm.domain.schedule.ScheduleCalenderList;
import org.kocofarm.domain.schedule.ScheduleCalenderMove;
import org.kocofarm.domain.schedule.ScheduleCategory;
import org.kocofarm.domain.schedule.ScheduleCategoryMove;
import org.kocofarm.domain.schedule.ScheduleProject;

public interface ScheduleMapper {
	public List<ScheduleProject> getProjectList();
	public List<ScheduleCalenderList> getProjectCalenderList(int projectId);
	public int setCalender(ScheduleCalender scheduleCalender);
	public int setUpCalender(ScheduleCalender scheduleCalender);
	public int delCalender(int calenderId);
	public int setUpCalenderPos(ScheduleCalenderMove calenderMove);
	public int setCategory(ScheduleCategory category);
	public int setUpCategory(ScheduleCategory category);
	public int setProject(ScheduleProject project);
	public int setUpProject(ScheduleProject project);
	public int delCategory(int categoryId);
	public int delCalenderWithCategory(ScheduleCategory category);		// 카테고리 삭제 시 하위 일정 삭제
	public int setMoveCategoryPosX(ScheduleCategoryMove category);
	public int setOriCategoryPosX(ScheduleCategoryMove category);
	public int delCalenderWithProject(int projectId);					// 프로젝트 삭제 시 하위 일정, 카테고리 삭제
	public int delCaltegoryWithProject(int projectId);
	public int delProject(int projectId);
}
