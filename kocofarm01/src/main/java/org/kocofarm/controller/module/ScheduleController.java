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
		
		// 자신이 팀장인지 정보 전달
		boolean isLeader = service.getDepartment(loginVo.getEmpId());
		session.setAttribute("teamLeader", "1");
		
		// left 메뉴 정보
		model.addAttribute("moduleNm", "schedule");
		return "/module/schedule/list";
	}
	
	@GetMapping("/list")
	private String getProjectListAjax(HttpSession session, HttpServletResponse response, ModelAndView mv){
		log.info("/list..........");
		log.info("/list..........");
		LoginVO loginVo = (LoginVO) session.getAttribute("loginVO");
		// NULL이면 리다이렉션 처리해야함
		if(null == loginVo){
			log.info(null == loginVo);
			return "/main";
		}
		
		try {
			ScheduleProjectSearchVO search = new ScheduleProjectSearchVO(loginVo.getEmpId());
			JSONArray list = service.getProjectJsonArray(search);
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
		System.out.println("list:"+list);
		model.addAttribute("project", list);
		model.addAttribute("moduleNm", "schedule");
		return list;
	}
	*/
	
	@PostMapping("/sendProjectId")
	private ModelAndView getProjectListAjax(@ModelAttribute("project_id") int projectId){
		log.info("/sendProjectId..........");
		log.info("project_id:"+projectId);
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
	private int setCalender(ScheduleCalenderVO calender){
		log.info("/insertCalender..........");
		int re = service.setCalender(calender);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalender")
	public int setUpCalender(ScheduleCalenderVO calender){
		log.info("/editCalender..........");
		int re = service.setUpCalender(calender);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertCategory")
	public int setCategory(ScheduleCategoryVO category){
		log.info("/insertCategory..........");
		log.info("insertCategory:"+category);
		int re = service.setCategory(category);
		return re;
	}

	@ResponseBody
	@PostMapping("/editCategory")
	public int setUpCategory(ScheduleCategoryVO category){
		log.info("/editCategory..........");
		int re = service.setUpCategory(category);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/insertProject")
	public int setProject(HttpSession session, ScheduleProjectVO project){
		if(null == session){
			return -1;
		}

		LoginVO loginVO = (LoginVO)session.getAttribute("loginVO");
		if(null == loginVO){
			return -1;
		}
		
		if(null == loginVO.getEmpId()){
			return -1;
		}
		
		project.setProjectLeader(loginVO.getEmpId());
		log.info("/insertProject..........");
		int re = service.setProject(project);
		
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editProject")
	public int setUpProject(ScheduleProjectVO project){
		log.info("/editProject..........");
		log.info("project정보:"+project);
		int re = service.setUpProject(project);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCalenderPos")
	public int setUpCalenderPos(@RequestBody List<ScheduleCalenderMoveVO> data){
		log.info("/editCalenderPos..........");
		int re = service.setUpCalenderPos(data);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/editCategoryPos")
	public int setCategoryPos(ScheduleCategoryMoveVO category){
		log.info("/editCategoryPos..........");
		int re = service.setMoveCategory(category);
		return re;	
	}
	
	@ResponseBody
	@PostMapping("/delCalender")
	public int delCalender(int calenderId){
		log.info("/delCalender..........");
		int re = service.delCalender(calenderId);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delCategory")
	public int delCategory(ScheduleCategoryVO category){
		log.info("/delCategory..........");
		int re = service.delCategory(category);
		return re;
	}
	
	@ResponseBody
	@PostMapping("/delProject")
	public int delProject(int projectId){
		log.info("/delProject..........");
		log.info("projectId:"+projectId);
		int re = service.delProject(projectId);
		return re;
	}
}
