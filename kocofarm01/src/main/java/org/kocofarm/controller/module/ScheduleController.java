package org.kocofarm.controller.module;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.service.module.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;
@Log4j
@Controller
@RequestMapping("/schedule/*")
@AllArgsConstructor
public class ScheduleController {
	final static private class CHECK_VALUE{
		final static int PROJECT_TITLE_LENGHT = 50;			// 프로젝트 길이
		final static int CATEGORY_TITLE_LENGHT = 50;		// 카테고리 길이
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
	
	private ScheduleService service;
	
	@GetMapping("/")
	private String getProjectList(HttpSession session, Model model){
		LoginVO loginVo = (LoginVO) session.getAttribute("loginVO");
		if(null == loginVo){
			return "/main";
		}
		
		log.info("loginVo.getMangerId():"+loginVo.getManagerId());
		// left 메뉴 정보
		model.addAttribute("moduleNm", "schedule");
		return "/module/schedule/list";
	}
	
	@GetMapping("/list")
	private String getProjectListAjax(HttpSession session, HttpServletResponse response, ModelAndView mv){
		log.info("/list..........여기들어왓니??");
		LoginVO loginVo = (LoginVO) session.getAttribute("loginVO");
		if(null == loginVo){
			log.info(null == loginVo);
			return "/main";
		}
	
		String empId = loginVo.getEmpId();
		log.info("empId:"+empId);
		
		try {
			ScheduleProjectSearchVO search = new ScheduleProjectSearchVO(loginVo.getEmpId());
			JSONArray list = service.getProjectJsonArray(search, empId);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(list);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
/*	@ResponseBody
	@GetMapping("/getProjectListSearch")
	private List<ScheduleProjectVO> getProjectListSearch(HttpSession session, Model model){
		log.info("/getProjectListSearch..........");
		LoginVO loginVo = (LoginVO) session.getAttribute("loginVO");
		// NULL이면 리다이렉션 처리해야함
		if(null == loginVo){
			return null;
		}
		
		ScheduleProjectSearchVO search = new ScheduleProjectSearchVO(loginVo.getEmpId());
		List<ScheduleProjectVO> list = service.getProjectList(search);
		model.addAttribute("project", list);
		model.addAttribute("moduleNm", "schedule");
		return list;
	}
	*/
	
	@PostMapping("/sendProjectId")
	private ModelAndView getProjectListAjax(@ModelAttribute("project_id") int projectId, HttpSession session){
		log.info("/sendProjectId..........");
		log.info("project_id:"+projectId);
		
		session.setAttribute("selectProjectId", projectId);		

		ModelAndView mv = new ModelAndView();
		mv.addObject("projectId", projectId);
		mv.addObject("moduleNm", "schedule");
		mv.setViewName("/module/schedule/project");
		return mv;
	}
		
	@PostMapping("/listCalender")
	private String getProjectCalenderList(int projectId, Model model){
		log.info("/listCalender.............");
		log.info("listCalender projectId:"+projectId);
		List<ScheduleCalenderListVO> list = service.getProjectCalenderList(projectId);
		log.info("list.............."+list);
		model.addAttribute("calenderList", list);
		return "/module/schedule/calenderListJsonParse";
	}
	
	@ResponseBody
	@PostMapping("/insertCalender")
	private int setCalender(HttpSession session, ScheduleCalenderVO calender){
		if(false == isProjectManager(session)){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/insertCalender..........");
		
		if(null == calender){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
			
		initCalender(calender);
		
		int re = service.setCalender(calender);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalender")
	public int setUpCalender(HttpSession session, ScheduleCalenderVO calender){
		// 팀장이거나 해당 캘린더의 작업자인지 확인
		
		log.info("/editCalender..........");
		
		if(null == calender){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		boolean isMember = isMember(calender.getCalenderId(), loginVO);
		boolean isProjectManager = (null == isManager(session)) ? false : true;
		if(false == isMember && false == isProjectManager){
			return ERROR_CODE.AUTH_FAIL;
		}
		
		int result = checkCalenderInfo(calender);
		if(1 != result)
			return result;
		
		initCalender(calender);
		
		int re = service.setUpCalender(calender);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertCategory")
	public int setCategory(HttpSession session, ScheduleCategoryVO category){
		if(false == isProjectManager(session)){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/insertCategory..........");
		log.info("insertCategory:"+category);
		if(null == category){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		String categoryName = category.getCategoryName();
		if(null == categoryName){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		if(categoryName.length() > ERROR_CODE.CATEGORY_NAME_LENGHT_FAIL){
			return ERROR_CODE.CATEGORY_NAME_LENGHT_FAIL;
		}
				
		int re = service.setCategory(category);
		return re;
	}

	@ResponseBody
	@PostMapping("/editCategory")
	public int setUpCategory(HttpSession session, ScheduleCategoryVO category){
		if(false == isProjectManager(session)){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/editCategory..........");
		
		if(null == category)
			return ERROR_CODE.UNKNOWN_ERROR;
		
		String categoryName = category.getCategoryName();
		if(null == categoryName){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		if(categoryName.length() > 5 || categoryName.length() <= 0){
			return ERROR_CODE.CATEGORY_NAME_LENGHT_FAIL;
		}
		
		int re = service.setUpCategory(category);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertProject")
	public int setProject(HttpSession session, ScheduleProjectVO project){
		LoginVO loginVO = isManager(session);
		if(null == loginVO){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		project.setProjectLeader(loginVO.getEmpId());
		log.info("/insertProject..........");
		
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
		
		int re = service.setProject(project);
		
		int projectId = (int) project.getProjectId();
		
		ScheduleMemberVO member = new ScheduleMemberVO();
		member.setProjectId(projectId);
		member.setEmpId(project.getProjectLeader());
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editProject")
	public int setUpProject(HttpSession session, ScheduleProjectVO project){
		LoginVO loginVO = isManager(session);
		if(null == loginVO){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/editProject..........");
		
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
		
		int re = service.setUpProject(project);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalenderPos")
	public int setUpCalenderPos(HttpSession session, @RequestBody List<ScheduleCalenderMoveVO> data){
		if(false == isProjectManager(session)){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/editCalenderPos..........");
		int re = service.setUpCalenderPos(data);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCategoryPos")
	public int setCategoryPos(HttpSession session, ScheduleCategoryMoveVO category){
		if(false == isProjectManager(session)){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/editCategoryPos..........");
		int re = service.setMoveCategory(category);
		return re;	
	}
	
	@ResponseBody
	@PostMapping("/delCalender")
	public int delCalender(HttpSession session, int calenderId){
		if(false == isProjectManager(session)){
			return ERROR_CODE.AUTH_FAIL;
		}
		
		log.info("/delCalender..........");
		int re = service.delCalender(calenderId);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delCategory")
	public int delCategory(HttpSession session, ScheduleCategoryVO category){
		if(false == isProjectManager(session)){
			return ERROR_CODE.AUTH_FAIL;
		}
		
		if(null == category){
			return ERROR_CODE.UNKNOWN_ERROR;
		}
		
		log.info("/delCategory..........");
		int re = service.delCategory(category);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delProject")
	public int delProject(HttpSession session, int projectId){
		LoginVO loginVO = isManager(session);
		if(null == loginVO){
			return ERROR_CODE.AUTH_FAIL;
		}
		
		log.info("/delProject..........");
		int re = service.delProject(projectId);
		return re;
	}
	
	// 팀장 여부 체크
	public LoginVO isManager(HttpSession session){
		if(null == session){
			return null;
		}

		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return null;
		}
		
		String empId = loginVO.getEmpId();
		if(null == empId || true == empId.isEmpty()){
			return null;
		}
		
		String managerId = loginVO.getManagerId();
		if(null == managerId || true == managerId.isEmpty()){
			return null; 
		}
		
		return loginVO;
	}
	
	// 현재 접속 중인 프로젝트의 팀장인지 체크
	public boolean isProjectManager(HttpSession session){
		if(null == session){
			return false;
		}
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return false;
		}
		
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

		if(matcher.find() == false){
			return false;
		}
		
		return true;
	}
}
