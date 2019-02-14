package org.kocofarm.service.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.emp.DepartmentsVO;
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
	
/*	@Override
	public List<ScheduleProjectVO> getProjectList(ScheduleProjectSearchVO search) {
		List<ScheduleProjectVO> list = mapper.getProjectList(search);
		return list;
	}*/

	@Override
	public JSONArray getProjectJsonArray(ScheduleProjectSearchVO project, String empId){
		JSONArray jsonArr = new JSONArray();
		
		List<ScheduleProjectVO> projectList = mapper.getProjectList(project);
		List<ScheduleProjectVO> managerProjectList = mapper.getManagerProjectList(empId);

		// managerProjectList에서 projectList의 중복되는 값을 제거한 최종 list
		List<ScheduleProjectVO> addList = new ArrayList<ScheduleProjectVO>(); 		
		
		// projectList와 중복되는 프로젝트 제외
		for(ScheduleProjectVO managerListVO : managerProjectList){
			if(true == searchProject(projectList, managerListVO)){
				addList.add(managerListVO);
			}
		}
		
		projectList.addAll(addList);
		
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
		if(null == calender)
			return -1;
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
			
		initCalender(calender);
		int re = mapper.setCalender(calender);
		return re;
	}
	
	@Override
	public int setUpCalender(ScheduleCalenderVO calender){
		if(null == calender)
			return -1;
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
		
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
		if(null == category)
			return -1;
		
		String categoryName = category.getCategoryName();
		if(null == categoryName)
			return -1;
		
		if(categoryName.length() > 5){
			return 1001;
		}
		
		int re = mapper.setCategory(category);
		return re;
	}
	
	@Override
	public int setUpCategory(ScheduleCategoryVO category){
		if(null == category)
			return -1;
		
		String categoryName = category.getCategoryName();
		if(null == categoryName)
			return -1;
		
		if(categoryName.length() > 5 || categoryName.length() <= 0){
			return 1001;
		}
		
		int re = mapper.setUpCategory(category);
		return re;
	}
	
	@Override	
	public int setProject(ScheduleProjectVO project){
		if(null == project){
			log.info("null == project");
			return -1;
		}
		
		String title = project.getTitle();
		if(null == title){
			log.info("null이다");
			return -1;
		}
		
		if(title.length() > 5){
			log.info("5보다 크다!!!!");
			return 1000;
		}
		
		project.setProjectStartDt("");
		project.setProjectEndDt("");
		int re = mapper.setProject(project);
		int projectId = (int) project.getProjectId();
		
		ScheduleMemberVO member = new ScheduleMemberVO();
		member.setProjectId(projectId);
		member.setEmpId(project.getProjectLeader());
		mapper.setMember(member);
		
		return re;
	}

	@Transactional
	@Override
	public int setUpProject(ScheduleProjectVO project){
		if(null == project)
			return -1;
		
		String title = project.getTitle();
		if(null == title)
			return -1;
	
		if(title.length() > 5 || title.length() <= 0){
			return 1000;
		}
		
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
	
	public int checkCalenderInfo(ScheduleCalenderVO calender){
		if(null == calender)
			return -1;
		
		String title = calender.getTitle();
		if(null == title)
			return -1;
		
		System.out.println("title.length() :"+title.length() );
		if(title.length() > 10 || title.length() <= 0){
			return 1002;
		}
		
		// calender 날짜 체크
		String startDt = calender.getStartDt();
		if(null == startDt){
			return -1;
		}
		
		if(startDt.length() >= 1){
			if(10 != startDt.length() || false == dataCheck(startDt))
				return 1003;
		}
		
		String endDt = calender.getStartDt();
		if(null == endDt)
			return -1;

		if(startDt.length() >= 1){
			if(10 != endDt.length() || false == dataCheck(endDt))
				return 1004;
		}
		
		int completionPer = calender.getCompletionPer();
		if(0 > completionPer || completionPer > 100)
			return 1005;
		
		return 1;
	}
	
	public boolean dataCheck(String date){
		Pattern pattern =  Pattern.compile("^((19|20)\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$");
		Matcher matcher = pattern.matcher(date); 

		if(matcher.find() == false)
			return false;
		
		return true;
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
