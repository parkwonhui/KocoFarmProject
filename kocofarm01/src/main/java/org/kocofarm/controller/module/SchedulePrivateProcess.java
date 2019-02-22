package org.kocofarm.controller.module;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.kocofarm.controller.comm.ScheduleEnum;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.domain.schedule.ScheduleTagVO;
import org.kocofarm.service.module.ScheduleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
public class SchedulePrivateProcess extends ScheduleProcess {
	public SchedulePrivateProcess(){}
	public SchedulePrivateProcess(ScheduleService service, ScheduleProjectVO vo){
		super(service, vo);
	}
	
	public int setCalender(HttpSession session, ScheduleCalenderVO calender){
		
		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setCalender(session, calender);
		return re;
	}
	
	public int setUpCalender(HttpSession session, ScheduleCalenderVO calender){
		
		// 팀장이거나 해당 캘린더의 작업자인지 확인
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		boolean isMember = isMember(calender.getCalenderId(), loginVO);
		boolean isProjectManager = isProjectManager(session, projectVO);
		if(false == isMember && false == isProjectManager){
			return ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		int re = super.setUpCalender(session, calender);
		return re;
	}
	
	public int setTag(HttpSession session, ScheduleTagVO tag){
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		int calenderId =tag.getCalender_id();
		boolean isMember = isMember(calenderId, loginVO); 
		if(false == isMember && false == isProjectManager(session, projectVO)){
			//해당 캘린더에 속해있는 사람인지 확인해야함
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setTag(session, tag);
		return re;
	}
	
	public int setUpTag(HttpSession session, ScheduleTagVO tag){
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		int calenderId =tag.getCalender_id();
		boolean isMember = isMember(calenderId, loginVO); 
		if(false == isMember && false == isProjectManager(session, projectVO)){

			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setUpTag(session, tag);
		return re;
	}
	
	public int delTag(HttpSession session, ScheduleTagVO tag){
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		int calenderId =tag.getCalender_id();
		
		boolean isMember = isMember(calenderId, loginVO);
		if(false == isMember && false == isProjectManager(session, projectVO)){

			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		int tagId = tag.getTag_id();
		int re = super.delTag(session, tagId);
		return re;
	}
	
	public int setCategory(HttpSession session, ScheduleCategoryVO category){
		
		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setCategory(session, category);
		return re;
	}
	
	public int setUpCategory(HttpSession session, ScheduleCategoryVO category){
	
		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setUpCategory(session, category);
		return re;
	}
	
	public int setUpCalenderPos(HttpSession session, @RequestBody List<ScheduleCalenderMoveVO> data){
		
		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setUpCalenderPos(session, data);
		return re;
	}
	
	public int setCategoryPos(HttpSession session, ScheduleCategoryMoveVO category){
		
		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int re = super.setCategoryPos(session, category);
		return re;
	}
	
	public int delCalender(HttpSession session, int calenderId){
		
		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		int re = super.delCalender(session, calenderId);
		return re;
	}
	
	public int delCategory(HttpSession session, ScheduleCategoryVO category){

		if(false == isProjectManager(session, projectVO)){
			return ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		int re = super.delCategory(session, category);
		return re;
	}	
}
