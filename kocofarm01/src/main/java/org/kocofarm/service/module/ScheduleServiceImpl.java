package org.kocofarm.service.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.mapper.module.EmpMapper;
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
	private EmpMapper empMapper;
	
	
	@Override
	public JSONArray getProjectJsonArray(ScheduleProjectSearchVO project, String empId){
		log.info("getAjson:"+project);
		JSONArray jsonArr = new JSONArray();
		
		List<ScheduleProjectVO> projectList = mapper.getProjectList(project);
		log.info("getAjson23123");
		List<ScheduleProjectVO> managerProjectList = mapper.getManagerProjectList(empId);
		log.info("getAjson123123");

		// managerProjectList에서 projectList의 중복되는 값을 제거한 최종 list
		List<ScheduleProjectVO> addList = new ArrayList<ScheduleProjectVO>(); 		
		log.info("getAjso1111111");
		// projectList와 중복되는 프로젝트 제외
		for(ScheduleProjectVO managerListVO : managerProjectList){
			if(true == searchProject(projectList, managerListVO)){
				addList.add(managerListVO);
			}
		}
		
		log.info(projectList);
		log.info(managerProjectList);
		
		projectList.addAll(addList);
		
		log.info("..........getProjectJsonArray:"+projectList);
		jsonArr = JSONArray.fromObject(projectList);
		
		return jsonArr;
	}

	public ScheduleProjectVO getSelectProject(int projectId){
		
		ScheduleProjectVO project = mapper.getSelectProject(projectId);
		return project;
	}


	@Override
	public List<ScheduleCalenderListVO> getProjectCalenderList(int projectId) {
		
		List<ScheduleCalenderListVO> list = mapper.getProjectCalenderList(projectId);
		return list;
	}

	@Override
	public int setCalender(ScheduleCalenderVO calender) {
		
		int re = mapper.setCalender(calender);
		return re;
	}
	
	@Override
	public int setUpCalender(ScheduleCalenderVO calender){
		
		int re = mapper.setUpCalender(calender);
		return re;
	}
	
	@Transactional
	@Override
	public int delCalender(int calenderId){
		
		int re = mapper.delMemberWithCalender(calenderId);
		re = mapper.delCalender(calenderId);
		
		return re;
	}
	
	@Override
	public int setUpCalenderPos(List<ScheduleCalenderMoveVO> calenderMoveList){
		
		int re = 0;
		for(ScheduleCalenderMoveVO calenderMove : calenderMoveList){
			re = mapper.setUpCalenderPos(calenderMove);
			if(0 >= re){
				return -1;
			}
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
		
		int re = mapper.setProject(project);		
		return re;
	}

	@Transactional
	@Override
	public int setUpProject(ScheduleProjectVO project){
		
		int re = mapper.setUpProject(project);
		return re;
	}

	@Transactional
	@Override
	public int delCategory(ScheduleCategoryVO category){
		
		int re = mapper.delMemberWithCategory(category.getCategoryId());
		re = mapper.delCalenderWithCategory(category);
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
		
		int re = mapper.delMemberWithProject(projectId);
		re = mapper.delCalenderWithProject(projectId);
		re = mapper.delCaltegoryWithProject(projectId);
		re = mapper.delProject(projectId);
		
		return re;
	}
	
	@Override
	public List<ScheduleMemberVO> getMember(int calenderId){
		return mapper.getMember(calenderId);
	}
	
	// 중복 체크
	public boolean searchProject(List<ScheduleProjectVO> list, ScheduleProjectVO project){
		for(ScheduleProjectVO vo : list){
			if(project.getProjectId() == vo.getProjectId()){
				return false;
			}
		}		
		
		return true;
	}
}
