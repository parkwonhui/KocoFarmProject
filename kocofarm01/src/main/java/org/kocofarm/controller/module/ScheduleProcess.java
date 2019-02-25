package org.kocofarm.controller.module;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.kocofarm.controller.comm.ScheduleEnum;
import org.kocofarm.controller.comm.ScheduleEnum.ERROR;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.domain.schedule.ScheduleTagVO;
import org.kocofarm.service.module.ScheduleService;
import org.kocofarm.service.module.ScheduleServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
public class ScheduleProcess {
	
	protected ScheduleService service;
	protected ScheduleProjectVO projectVO;
	protected ScheduleCalenderVO calenderVO;	
	protected ScheduleTagVO tagVO;
	
	
	public ScheduleProcess(){}
	public ScheduleProcess(ScheduleService service, ScheduleProjectVO projectVO){
		this.service = service;
		this.projectVO = projectVO;
	}

	public int setCalender(HttpSession session, ScheduleCalenderVO calender){
		
		if(null == calender){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
			
		initCalender(calender);
		
		int re = service.setCalender(calender);
		return re;
	}

	public int setUpCalender(HttpSession session, ScheduleCalenderVO calender){
	
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
		
		initCalender(calender);
		
		int re = service.setUpCalender(calender);
		return re;
	}
	
	public int setCategory(HttpSession session, ScheduleCategoryVO category){
		
		if(null == category){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String categoryName = category.getCategoryName();
		if(null == categoryName){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(categoryName.length() > ScheduleEnum.CHECK.CATEGORY_TITLE_LENGHT){
			return ScheduleEnum.ERROR.CATEGORY_NAME_LENGHT_FAIL;
		}
				
		int re = service.setCategory(category);
		return re;
	}

	
	public int setUpCategory(HttpSession session, ScheduleCategoryVO category){
		
		if(null == category)
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		
		String categoryName = category.getCategoryName();
		if(null == categoryName){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(categoryName.length() > ScheduleEnum.CHECK.CATEGORY_TITLE_LENGHT || categoryName.length() <= 0){
			return ScheduleEnum.ERROR.CATEGORY_NAME_LENGHT_FAIL;
		}
		
		int re = service.setUpCategory(category);
		return re;
	}
	
	public int setUpCalenderPos(HttpSession session, @RequestBody List<ScheduleCalenderMoveVO> data){
		
		int re = service.setUpCalenderPos(data);
		
		return re;
	}
	
	public int setCategoryPos(HttpSession session, ScheduleCategoryMoveVO category){
		
		int re = service.setMoveCategory(category);
		
		return re;	
	}
	
	public int delCalender(HttpSession session, int calenderId){

		int re = service.delCalender(calenderId);
	
		return re;
	}

	public int delCategory(HttpSession session, ScheduleCategoryVO category){
				
		if(null == category){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = service.delCategory(category);
		
		return re;
	}
	
	public int setTag(HttpSession session, ScheduleTagVO tag){
		
		if(null == tag){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String tagName = tag.getTagName();
		if(null == tagName){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(tagName.length() > ScheduleEnum.CHECK.CATEGORY_TITLE_LENGHT){
			return ScheduleEnum.ERROR.CATEGORY_NAME_LENGHT_FAIL;
		}
		
		String tagColor = tag.getTagColor();
		if(tagColor.length() > ScheduleEnum.CHECK.TAG_TITLE_LENGTH){
			return ScheduleEnum.ERROR.TAG_NAME_LENGTH_FAIL;
		}
		if(tagColor.length() > ScheduleEnum.CHECK.TAG_COLOR_LENGTH){
			return ScheduleEnum.ERROR.TAG_COLOR_LENGTH_FAIL;
		}
		
		int re = service.setTag(tag);
		return re;
	}
	
	public int setUpTag(HttpSession session, ScheduleTagVO tag){
		
		if(null == tag){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String tagName = tag.getTagName();
		if(null == tagName){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(tagName.length() > ScheduleEnum.CHECK.CATEGORY_TITLE_LENGHT){
			return ScheduleEnum.ERROR.CATEGORY_NAME_LENGHT_FAIL;
		}
		
		String tagColor = tag.getTagColor();
		if(tagColor.length() > ScheduleEnum.CHECK.TAG_TITLE_LENGTH){
			return ScheduleEnum.ERROR.TAG_NAME_LENGTH_FAIL;
		}
		if(tagColor.length() > ScheduleEnum.CHECK.TAG_COLOR_LENGTH){
			return ScheduleEnum.ERROR.TAG_COLOR_LENGTH_FAIL;
		}
		
		int re = service.setUpTag(tag);
		return re;
	}
	
	public int delTag(HttpSession session, int tag_id){
		
		int re = service.delTag(tag_id);
		return re;
	}
	
	/*public int getCalenderMember(HttpSession session, int calenderId){
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return ERROR.UNKNOWN_ERROR;
		}
		
	
		
		
	}*/
	
	// 현재 접속 중인 프로젝트의 팀장인지 체크
	public boolean isProjectManager(HttpSession session, ScheduleProjectVO projectVO){
		
		String managerId = projectVO.getProjectLeader();
		if(null == managerId){
			return false;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return false;
		}
		
	 	String empId = loginVO.getEmpId();
	 	if(null == empId){
	 		return false;
	 	}
	 	
	 	if(false == empId.equals(managerId)){
	 		return false;
	 	}
	 	
	 	return true;
	}
	
	// 해당 일정의 작업자인지 체크
	public boolean isMember(int calenderId, LoginVO loginVO){
		String empId = loginVO.getEmpId();
		if(null == empId){
			return false;
		}
		
		List<ScheduleMemberVO> list = service.getMember(calenderId);
		for(ScheduleMemberVO member : list){
			if(true == member.getEmpId().equals(empId)){
				return true;
			}
		}
		
		return false;
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
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String title = calender.getTitle();
		if(null == title){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(title.length() > ScheduleEnum.CHECK.PROJECT_TITLE_LENGHT || title.length() <= 0){
			return ScheduleEnum.ERROR.CALENDER_TOO_MANY_TEXT;
		}
		
		// calender 날짜 체크
		String startDt = calender.getStartDt();
		if(null == startDt){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(startDt.length() >= 1){
			if(ScheduleEnum.CHECK.CALENDER_DATE_LENGHT != startDt.length() || false == dataCheck(startDt))
				return ScheduleEnum.ERROR.CALENDER_START_DT_WRONG;
		}
		
		String endDt = calender.getStartDt();
		if(null == endDt){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}

		if(startDt.length() >= 1){
			if(ScheduleEnum.CHECK.CALENDER_DATE_LENGHT != endDt.length() || false == dataCheck(endDt))
				return ScheduleEnum.ERROR.CALENDER_END_DT_WRONG;
		}
		
		int completionPer = calender.getCompletionPer();
		if(ScheduleEnum.CHECK.CALENDER_COMPLETION_PER_MIN > completionPer || completionPer > ScheduleEnum.CHECK.CALENDER_COMPLETION_PER_MAX)
			return ScheduleEnum.ERROR.CALENDER_COMPLETION_PERCENT_WRONG;
		
		return 1;
	}
	
	public boolean dataCheck(String date){
		Pattern pattern =  Pattern.compile("^((19|20)\\d\\d)?([- /.])?(0[1-9]|1[012])([- /.])?(0[1-9]|[12][0-9]|3[01])$");
		Matcher matcher = pattern.matcher(date); 

		if(matcher.find() == false){
			return false;
		}
		
		return true;
	}
}
