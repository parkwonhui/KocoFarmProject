package org.kocofarm.controller.module;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.controller.comm.ScheduleEnum;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.domain.schedule.ScheduleTagVO;
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
	
	private ScheduleService service;
	
	@GetMapping("/")
	private String getProjectList(HttpSession session, Model model){
		log.info("/..........");
		
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
		log.info("/list..........");
		
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
	
	/*@ResponseBody
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
	}*/
	
	
	@PostMapping("/project")
	private ModelAndView getProjectListAjax(@ModelAttribute("project_id") int projectId, HttpSession session){
		log.info("/project..........");
		
		ModelAndView mv = new ModelAndView();
		
		session.setAttribute("selectProjectId", projectId);		
	
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			mv.setViewName("/main");
			return mv;
		}
		
		boolean isProjectManager = isProjectManager(session, projectVO);
		int projectManagerVal = (true == isProjectManager) ? 1 : 0;
		
		// public이면 화면에서 전부 그려준다
		String bPublic = projectVO.getPublicUse();
		log.info("bPublic:"+bPublic);
		mv.addObject("bPublic", bPublic);
		mv.addObject("bProjectManager", projectManagerVal);
		mv.addObject("projectId", projectId);
		mv.addObject("moduleNm", "schedule");
		mv.setViewName("/module/schedule/project");
		
		return mv;
	}
		
	@PostMapping("/listCalender")
	private String getProjectCalenderList(HttpSession session, int projectId, Model model){
		log.info("/listCalender.............");
		
		List<ScheduleCalenderListVO> list = service.getProjectCalenderList(projectId);
		if(null == list){
			return "";// error url
		}
		
		model.addAttribute("calenderList", list);	
		
		return "/module/schedule/calenderListJsonParse";
	}
	
	@PostMapping("/listTag")
	private String getTagList(HttpSession session, int calenderId, Model model){
		
		if(null == session){
			
			return null;
		}
		
		List<ScheduleTagVO> list = service.getTagList(calenderId);
		if(list == null){ return "";}
		
		model.addAttribute("tagVOList", list);
		
		return "/module/schedule/calenderTagListJson";

	}
	
	
	
	
	@ResponseBody
	@PostMapping("/insertCalender")
	private int setCalender(HttpSession session, ScheduleCalenderVO calender){
		log.info("/insertCalender..........");

		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String bPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(bPublic, service, projectVO); 
		int re = process.setCalender(session, calender);
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertTag")
	private int setTag(HttpSession session, ScheduleTagVO tag){
		log.info("/inserttag..........");
		log.info(session);
		if(null == session){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int scheduleId = (int)session.getAttribute("selectedCalenderId");
		int re = service.setTag(tag);
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalender")
	public int setUpCalender(HttpSession session, ScheduleCalenderVO calender){
		// 팀장이거나 해당 캘린더의 작업자인지 확인		
		log.info("/editCalender..........");
		
		if(null == calender){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String bPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(bPublic, service, projectVO); 
		int re =process.setUpCalender(session, calender);
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertCategory")
	public int setCategory(HttpSession session, ScheduleCategoryVO category){
		log.info("/insertCategory..........");
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String strPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(strPublic, service, projectVO);
		int re = process.setCategory(session, category);
		
		return re;
	}

	@ResponseBody
	@PostMapping("/editCategory")
	public int setUpCategory(HttpSession session, ScheduleCategoryVO category){
		log.info("/editCategory..........");
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String strPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(strPublic, service, projectVO);
		int re = process.setUpCategory(session, category);
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertProject")
	public int setProject(HttpSession session, ScheduleProjectVO project){
		log.info("/insertProject..........");
		
		if(false == isManager(session)){
			return 	ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		project.setProjectLeader(loginVO.getEmpId());
		
		String title = project.getTitle();
		if(null == title){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(title.length() > ScheduleEnum.CHECK.PROJECT_TITLE_LENGHT){
			return ScheduleEnum.ERROR.PROJECT_NAME_LENGHT_FAIL;
		}
		
		project.setProjectStartDt("");
		project.setProjectEndDt("");
		log.info(project);
		int re = service.setProject(project);
		
		ScheduleMemberVO member = new ScheduleMemberVO();
		member.setEmpId(project.getProjectLeader());
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editProject")
	public int setUpProject(HttpSession session, ScheduleProjectVO project){
		log.info("/editProject..........");
		
		if(false == isManager(session)){
			return ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		log.info("/editProject..........");
		
		if(null == project){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String title = project.getTitle();
		if(null == title){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
	
		if(title.length() > ScheduleEnum.CHECK.PROJECT_TITLE_LENGHT || title.length() <= 0){
			return ScheduleEnum.ERROR.PROJECT_NAME_LENGHT_FAIL;
		}
		
		int re = service.setUpProject(project);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalenderPos")
	public int setUpCalenderPos(HttpSession session, @RequestBody List<ScheduleCalenderMoveVO> data){
		log.info("/editCalenderPos..........");
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String strPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(strPublic, service, projectVO);
		int re = process.setUpCalenderPos(session, data);		
	
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCategoryPos")
	public int setCategoryPos(HttpSession session, ScheduleCategoryMoveVO category){
		log.info("/editCategoryPos..........");
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String strPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(strPublic, service,  projectVO);
		int re = process.setCategoryPos(session, category);
		
		return re;	
	}
	
	@ResponseBody
	@PostMapping("/delCalender")
	public int delCalender(HttpSession session, int calenderId){
		log.info("/delCalender..........");
		
		if(null == session){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		String strPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(strPublic, service,  projectVO);
		int re = process.delCalender(session, calenderId);
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delCategory")
	public int delCategory(HttpSession session, ScheduleCategoryVO category){
		log.info("/delCategory..........");
		
		int projectId = (int)session.getAttribute("selectProjectId");
		ScheduleProjectVO projectVO = service.getSelectProject(projectId);	
		if(null == projectVO){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}		

		String strPublic = projectVO.getPublicUse();
		ScheduleProcess process = getScheduleProcess(strPublic, service,  projectVO);
		int re = process.delCategory(session, category);
	
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delProject")
	public int delProject(HttpSession session, int projectId){
		log.info("/delProject..........");
		
		if(null == session){
			return ScheduleEnum.ERROR.UNKNOWN_ERROR;
		}
		
		if(false == isManager(session)){
			return ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return ScheduleEnum.ERROR.AUTH_FAIL;
		}
		
		log.info("/delProject..........");
		int re = service.delProject(projectId);
		return re;
	}
		
	// 팀장 여부 체크
	public boolean isManager(HttpSession session){

		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return false;
		}
		
		String empId = loginVO.getEmpId();
		if(null == empId || true == empId.isEmpty()){
			return false;
		}
		
		String managerId = loginVO.getManagerId();
		if(null == managerId || true == managerId.isEmpty()){
			return false; 
		}
		
		if(false == empId.equals(managerId)){
			return false; 
		}
		
		return true;
	}
	
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
		
	public ScheduleProcess getScheduleProcess(String publicFlag, ScheduleService service, ScheduleProjectVO projectVO){
		if(true == publicFlag.equals("1")){
			return new ScheduleProcess(service, projectVO);
		}else{
			return new SchedulePrivateProcess(service, projectVO);
		}		
	}
}
