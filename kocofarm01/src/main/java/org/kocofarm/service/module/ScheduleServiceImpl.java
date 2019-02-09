package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleCalender;
import org.kocofarm.domain.schedule.ScheduleCalenderList;
import org.kocofarm.domain.schedule.ScheduleCalenderMove;
import org.kocofarm.domain.schedule.ScheduleCategory;
import org.kocofarm.domain.schedule.ScheduleCategoryMove;
import org.kocofarm.domain.schedule.ScheduleProject;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	private ScheduleMapper mapper;
	
	@Override
	public List<ScheduleProject> getProjectList() {
		List<ScheduleProject> list = mapper.getProjectList();
		return list;
	}

	@Override
	public List<ScheduleCalenderList> getProjectCalenderList(int projectId) {
		List<ScheduleCalenderList> list = mapper.getProjectCalenderList(projectId);
		return list;
	}

	@Override
	public int setCalender(ScheduleCalender calender) {
		int re = mapper.setCalender(calender);
		return re;
	}
	
	@Override
	public int setUpCalender(ScheduleCalender calender){
		int re = mapper.setUpCalender(calender);
		return re;
	}
	
	@Override
	public int delCalender(int calenderId){
		int re = mapper.delCalender(calenderId);
		return re;
	}
	
	@Override
	public int setUpCalenderPos(List<ScheduleCalenderMove> calenderMoveList){
		int size = calenderMoveList.size();
		int re = 0;
		for(ScheduleCalenderMove calenderMove : calenderMoveList){
			re = mapper.setUpCalenderPos(calenderMove);
			if(0 >= re)
				return -1;
		}
		return re;
	}
	
	@Override
	public int setCategory(ScheduleCategory category){
		int re = mapper.setCategory(category);
		return re;
	}
	
	@Override
	public int setUpCategory(ScheduleCategory category){
		int re = mapper.setUpCategory(category);
		return re;
	}
	
	@Override	
	public int setProject(ScheduleProject project){
		int re = mapper.setProject(project);
		return re;
	}

	@Override
	public int setUpProject(ScheduleProject project){
		int re = mapper.setUpProject(project);
		return re;
	}

	@Transactional
	@Override
	public int delCategory(ScheduleCategory category){
		int re = mapper.delCalenderWithCategory(category);
		re = mapper.delCategory(category.getCategoryId());
		return re;
	}
	
	@Transactional
	@Override
	public int setMoveCategory(ScheduleCategoryMove category){
		int re = mapper.setMoveCategoryPosX(category);
		re = mapper.setOriCategoryPosX(category);
		return re;
	}
		
	@Transactional
	@Override
	public int delProject(int projectId){
		int re = mapper.delCalenderWithProject(projectId);
		re = mapper.delCaltegoryWithProject(projectId);
		re = mapper.delProject(projectId);
		return re;
	}
}
