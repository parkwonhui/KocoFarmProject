package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;

public interface ScheduleMapper {
	public List<ScheduleProjectVO> getProjectList(ScheduleProjectSearchVO project);
	public List<ScheduleProjectVO> getManagerProjectList(String empId);		// 자신이 manager인 프로젝트 검색
	public ScheduleProjectVO		getSelectProject(int projectId);		// 프로젝트 id로 자신이 보고있는 화면의 project 정보를 가져온다
	public List<ScheduleCalenderListVO> getProjectCalenderList(int projectId);
	public int setCalender(ScheduleCalenderVO scheduleCalender);
	public int setUpCalender(ScheduleCalenderVO scheduleCalender);
	public int delCalender(int calenderId);
	public int setUpCalenderPos(ScheduleCalenderMoveVO calenderMove);
	public int setCategory(ScheduleCategoryVO category);
	public int setUpCategory(ScheduleCategoryVO category);
	public int setProject(ScheduleProjectVO project);
	public int setUpProject(ScheduleProjectVO project);
	public int delCategory(int categoryId);
	public int delCalenderWithCategory(ScheduleCategoryVO category);		// 카테고리 삭제 시 하위 일정 삭제
	public int setMoveCategoryPosX(ScheduleCategoryMoveVO category);
	public int setOriCategoryPosX(ScheduleCategoryMoveVO category);
	public int delCalenderWithProject(int projectId);						// 프로젝트 삭제 시 하위 일정, 카테고리 삭제
	public int delCaltegoryWithProject(int projectId);
	public int delProject(int projectId);
	public int setMember(ScheduleMemberVO member);
	public int getMember(int projectId);
	public int delMemberWithCalender(int calenderId);						// 일정 id로 멤버삭제
	public int delMemberWithCategory(int categoryId);						// 카테고리 id로 멤버삭제
	public int delMemberWithProject(int projectId);							// 프로젝트 id로 멤버삭제
	


}
