package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.domain.schedule.ScheduleTagVO;
import org.springframework.ui.Model;

import net.sf.json.JSONArray;

public interface ScheduleService {
	public JSONArray getProjectJsonArray(ScheduleProjectSearchVO search, String empId);
	public ScheduleProjectVO getSelectProject(int projectId);	
	public List<ScheduleCalenderListVO> getProjectCalenderList(int projectId);
	public int setCalender(ScheduleCalenderVO calender);
	public int setUpCalender(ScheduleCalenderVO scheduleCalender);
	public int delCalender(int calenderId);
	public int setUpCalenderPos(List<ScheduleCalenderMoveVO> calenderMoveList);
	public int setCategory(ScheduleCategoryVO category);
	public int setUpCategory(ScheduleCategoryVO category);
	public int setProject(ScheduleProjectVO project);
	public int setUpProject(ScheduleProjectVO project);
	public int delCategory(ScheduleCategoryVO category);
	public int setMoveCategory(ScheduleCategoryMoveVO category);
	public int delProject(int projectId);
	public List<ScheduleMemberVO> getMember(int calenderId);
	public int setTag(ScheduleTagVO tag);
	public int setUpTag(ScheduleTagVO tag);
	public int delTag(int tagId);
}
