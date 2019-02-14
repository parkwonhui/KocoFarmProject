package org.kocofarm.controller.module;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
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
		log.info("/list..........");
		LoginVO loginVo = (LoginVO) session.getAttribute("loginVO");
		if(null == loginVo){
			log.info(null == loginVo);
			return "/main";
		}
	
		String empId = loginVo.getEmpId();
		
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
			return -1;
		}
		
		log.info("/insertCalender..........");
		int re = service.setCalender(calender);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalender")
	public int setUpCalender(HttpSession session, ScheduleCalenderVO calender){
		// 팀장이거나 해당 캘린더의 작업자인지 확인
		
		log.info("/editCalender..........");
		int re = service.setUpCalender(calender);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertCategory")
	public int setCategory(HttpSession session, ScheduleCategoryVO category){
		if(false == isProjectManager(session)){
			return -1;
		}
		
		log.info("/insertCategory..........");
		log.info("insertCategory:"+category);
		int re = service.setCategory(category);
		return re;
	}

	@ResponseBody
	@PostMapping("/editCategory")
	public int setUpCategory(HttpSession session, ScheduleCategoryVO category){
		if(false == isProjectManager(session)){
			return -1;
		}
		
		log.info("/editCategory..........");
		int re = service.setUpCategory(category);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertProject")
	public int setProject(HttpSession session, ScheduleProjectVO project){
		LoginVO loginVO = isManager(session);
		if(null == loginVO){
			return -1;
		}
		
		project.setProjectLeader(loginVO.getEmpId());
		log.info("/insertProject..........");
		int re = service.setProject(project);		
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editProject")
	public int setUpProject(HttpSession session, ScheduleProjectVO project){
		LoginVO loginVO = isManager(session);
		if(null == loginVO){
			return -1;
		}
		
		log.info("/editProject..........");
		int re = service.setUpProject(project);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalenderPos")
	public int setUpCalenderPos(HttpSession session, @RequestBody List<ScheduleCalenderMoveVO> data){
		if(false == isProjectManager(session)){
			return -1;
		}
		
		log.info("/editCalenderPos..........");
		int re = service.setUpCalenderPos(data);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCategoryPos")
	public int setCategoryPos(HttpSession session, ScheduleCategoryMoveVO category){
		if(false == isProjectManager(session)){
			return -1;
		}
		
		log.info("/editCategoryPos..........");
		int re = service.setMoveCategory(category);
		return re;	
	}
	
	@ResponseBody
	@PostMapping("/delCalender")
	public int delCalender(HttpSession session, int calenderId){
		if(false == isProjectManager(session)){
			return -1;
		}
		
		log.info("/delCalender..........");
		int re = service.delCalender(calenderId);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delCategory")
	public int delCategory(HttpSession session, ScheduleCategoryVO category){
		if(false == isProjectManager(session)){
			return -1;
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
			return -1;
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
}
