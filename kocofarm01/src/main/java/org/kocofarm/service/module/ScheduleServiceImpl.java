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
	final static private class CHECK_VALUE{
		final static int PROJECT_TITLE_LENGHT = 50;			// 프로젝트 길이
		final static int CALENDER_DATE_LENGHT = 10;			// 시작,종료 날짜 String 길이
		final static int CALENDER_COMPLETION_PER_MIN = 0;	// 일정 완료상황 최소 값
		final static int CALENDER_COMPLETION_PER_MAX = 100;	// 일정 완료상황 최대 값
	}
	
	final static public class ERROR_CODE{
		final static int UNKNOWN_ERROR = -1;
		final static int PROJECT_NAME_LENGHT_FAIL = 1000;
		final static int CATEGORY_NAME_LENGHT_FAIL = 1001;
		final static int CALENDER_TOO_MANY_TEXT = 1002;
		final static int CALENDER_START_DT_WRONG = 1003;
		final static int CALENDER_END_DT_WRONG = 1004;
		final static int CALENDER_COMPLETION_PERCENT_WRONG = 1005;
		final static int AUTH_FAIL = 1006;				// 접근할 수 없는 권한
	};
	
	private ScheduleMapper mapper;
	private EmpMapper empMapper;
	
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
		if(null == calender){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
			
		initCalender(calender);
		int re = mapper.setCalender(calender);
		return re;
	}
	
	@Override
	public int setUpCalender(ScheduleCalenderVO calender){
		if(null == calender){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
		
		initCalender(calender);
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
				return ERROR_CODE.UNKNOWN_ERROR;
			}
		}
		return re;
	}
	
	@Override
	public int setCategory(ScheduleCategoryVO category){
		if(null == category){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		String categoryName = category.getCategoryName();
		if(null == categoryName){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		if(categoryName.length() > 5){
			return ERROR_CODE.CATEGORY_NAME_LENGHT_FAIL;
		}
		
		int re = mapper.setCategory(category);
		return re;
	}
	
	@Override
	public int setUpCategory(ScheduleCategoryVO category){
		if(null == category)
			return ERROR_CODE.UNKNOWN_ERROR;
		
		String categoryName = category.getCategoryName();
		if(null == categoryName){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		if(categoryName.length() > 5 || categoryName.length() <= 0){
			return ERROR_CODE.CATEGORY_NAME_LENGHT_FAIL;
		}
		
		int re = mapper.setUpCategory(category);
		return re;
	}
	
	@Override	
	public int setProject(ScheduleProjectVO project){
		if(null == project){
			log.info("null == project");
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		String title = project.getTitle();
		if(null == title){
			log.info("null이다");
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		if(title.length() > CHECK_VALUE.PROJECT_TITLE_LENGHT){
			return ERROR_CODE.PROJECT_NAME_LENGHT_FAIL;
		}
		
		project.setProjectStartDt("");
		project.setProjectEndDt("");
		int re = mapper.setProject(project);
		int projectId = (int) project.getProjectId();
		
		ScheduleMemberVO member = new ScheduleMemberVO();
		member.setProjectId(projectId);
		member.setEmpId(project.getProjectLeader());
		
		return re;
	}

	@Transactional
	@Override
	public int setUpProject(ScheduleProjectVO project){
		if(null == project){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		String title = project.getTitle();
		if(null == title){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
	
		if(title.length() > CHECK_VALUE.PROJECT_TITLE_LENGHT || title.length() <= 0){
			return ERROR_CODE.PROJECT_NAME_LENGHT_FAIL;
		}
		
		int re = mapper.setUpProject(project);
		return re;
	}

	@Transactional
	@Override
	public int delCategory(ScheduleCategoryVO category){
		if(null == category){
			return ERROR_CODE.UNKNOWN_ERROR;
		}

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
		if(null == calender){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		String title = calender.getTitle();
		if(null == title){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		System.out.println("title.length() :"+title.length() );
		if(title.length() > CHECK_VALUE.PROJECT_TITLE_LENGHT || title.length() <= 0){
			return ERROR_CODE.CALENDER_TOO_MANY_TEXT;
		}
		
		// calender 날짜 체크
		String startDt = calender.getStartDt();
		if(null == startDt){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		if(startDt.length() >= 1){
			if(CHECK_VALUE.CALENDER_DATE_LENGHT != startDt.length() || false == dataCheck(startDt))
				return ERROR_CODE.CALENDER_START_DT_WRONG;
		}
		
		String endDt = calender.getStartDt();
		if(null == endDt){
			return ERROR_CODE.UNKNOWN_ERROR;
		}

		if(startDt.length() >= 1){
			if(CHECK_VALUE.CALENDER_DATE_LENGHT != endDt.length() || false == dataCheck(endDt))
				return ERROR_CODE.CALENDER_END_DT_WRONG;
		}
		
		int completionPer = calender.getCompletionPer();
		if(CHECK_VALUE.CALENDER_COMPLETION_PER_MIN > completionPer || completionPer > CHECK_VALUE.CALENDER_COMPLETION_PER_MAX)
			return ERROR_CODE.CALENDER_COMPLETION_PERCENT_WRONG;
		
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
