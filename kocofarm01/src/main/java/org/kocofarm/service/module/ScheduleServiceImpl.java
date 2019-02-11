package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Log4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	private ScheduleMapper mapper;
	
	@Override
	public List<ScheduleProjectVO> getProjectList() {
		List<ScheduleProjectVO> list = mapper.getProjectList();
		return list;
	}

	@Override
	public JSONArray getProjectJsonArray(){
		JSONArray jsonArr = new JSONArray();
		List<ScheduleProjectVO> projectList = mapper.getProjectList();
		log.info("..........getProjectJsonArray:"+projectList);
		jsonArr = JSONArray.fromObject(projectList);
		return jsonArr;
	}


	@Override
	public List<ScheduleCalenderListVO> getProjectCalenderList(int projectId) {
		List<ScheduleCalenderListVO> list = mapper.getProjectCalenderList(projectId);
		return list;
	}

	@Override
	public int setCalender(ScheduleCalenderVO calender) {
		initCalender(calender);
		int re = mapper.setCalender(calender);
		return re;
	}
	
	@Override
	public int setUpCalender(ScheduleCalenderVO calender){
		initCalender(calender);
		int re = mapper.setUpCalender(calender);
		return re;
	}
	
	@Override
	public int delCalender(int calenderId){
		int re = mapper.delCalender(calenderId);
		return re;
	}
	
	@Override
	public int setUpCalenderPos(List<ScheduleCalenderMoveVO> calenderMoveList){
		int re = 0;
		for(ScheduleCalenderMoveVO calenderMove : calenderMoveList){
			re = mapper.setUpCalenderPos(calenderMove);
			if(0 >= re)
				return -1;
		}
		return re;
	}
	
	@Override
	public int setCategory(ScheduleCategoryVO category){
		int re = mapper.setCategory(category);
		return re;
	}
	
	@Override
	public int setUpCategory(ScheduleCategoryVO category){
		int re = mapper.setUpCategory(category);
		return re;
	}
	
	@Override	
	public int setProject(ScheduleProjectVO project){
		project.setProjectLeader("");
		project.setProjectStartDt("");
		project.setProjectEndDt("");
		int re = mapper.setProject(project);
		return re;
	}

	@Override
	public int setUpProject(ScheduleProjectVO project){
		int re = mapper.setUpProject(project);
		return re;
	}

	@Transactional
	@Override
	public int delCategory(ScheduleCategoryVO category){
		int re = mapper.delCalenderWithCategory(category);
		re = mapper.delCategory(category.getCategoryId());
		return re;
	}
	
	@Transactional
	@Override
	public int setMoveCategory(ScheduleCategoryMoveVO category){
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
	
	public ScheduleCalenderVO initCalender(ScheduleCalenderVO calender){
		if(null == calender.getBackgroundColor()){
			calender.setBackgroundColor("");
		}else if(null == calender.getStartDt()){
			calender.setStartDt("");
		}else if(null == calender.getEndDt()){
			calender.setEndDt("");
		}
		
		return calender;
	}
}
