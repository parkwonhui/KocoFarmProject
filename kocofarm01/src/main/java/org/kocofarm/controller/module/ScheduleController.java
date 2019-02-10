package org.kocofarm.controller.module;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kocofarm.domain.schedule.ScheduleCalender;
import org.kocofarm.domain.schedule.ScheduleCalenderList;
import org.kocofarm.domain.schedule.ScheduleCalenderMove;
import org.kocofarm.domain.schedule.ScheduleCategory;
import org.kocofarm.domain.schedule.ScheduleCategoryMove;
import org.kocofarm.domain.schedule.ScheduleProject;
import org.kocofarm.service.module.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private String getProjectList(Model model){
		List<ScheduleProject> list = service.getProjectList();
		model.addAttribute("project", list);
		return "/module/schedule/list";
	}
	
	@GetMapping("/list")
	private String getProjectListAjax(HttpServletResponse response){
		// ControllerAdvice로 처리 안되나??
		try {
			JSONArray list = service.getProjectJsonArray();
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@PostMapping("/sendProjectId")
	private ModelAndView getProjectListAjax(@ModelAttribute("project_id") int projectId){
		log.info("project_id:"+projectId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("projectId", projectId);
		mv.setViewName("/module/schedule/project");
		return mv;
	}
		
	@PostMapping("/listCalender")
	private String getProjectCalenderList(int projectId, Model model){
		log.info("listCalender projectId:"+projectId);
		List<ScheduleCalenderList> list = service.getProjectCalenderList(projectId);
		model.addAttribute("calenderList", list);
		return "/module/schedule/calenderListJsonParse";
	}
	
	@PostMapping("/insertCalender")
	private String setCalender(ScheduleCalender calender){
		service.setCalender(calender);
		return "/module/schedule/project";
	}
	
	
	@PostMapping("/editCalender")
	public String setUpCalender(ScheduleCalender calender){
		int re = service.setUpCalender(calender);
		return "/module/schedule/project";
	}
	
	@PostMapping("/insertCategory")
	public String setCategory(ScheduleCategory category){
		log.info("insertCategory:"+category);
		int re = service.setCategory(category);
		return "/module/schedule/project";
	}
	
	@PostMapping("/editCategory")
	public String setUpCategory(ScheduleCategory category){
		int re = service.setUpCategory(category);
		return "/module/schedule/project";
	}
	
	@PostMapping("/insertProject")
	public String setProject(ScheduleProject project){
		log.info("project!!!!!"+project);
		int re = service.setProject(project);
		return "/module/schedule/list";
	}
	
	@PostMapping("/editProject")
	public String setUpProject(ScheduleProject project){
		log.info("project정보:"+project);
		int re = service.setUpProject(project);
		return "/module/schedule/list";
	}
	
	@PostMapping("/editCalenderPos")
	public String setUpCalenderPos(@RequestBody List<ScheduleCalenderMove> data){
		
		int re = service.setUpCalenderPos(data);
		return "/module/schedule/project";
	}
	
	
	@PostMapping("/editCategoryPos")
	public String setCategoryPos(ScheduleCategoryMove category){
		int re = service.setMoveCategory(category);
		return "/module/schedule/project";	
	}
	
	@PostMapping("/delCalender")
	public String delCalender(int calenderId){
		int re = service.delCalender(calenderId);
		return "/module/schedule/project";
	}
	
	@PostMapping("/delCategory")
	public String delCategory(ScheduleCategory category){
		int re = service.delCategory(category);
		return "/module/schedule/project";
	}
	
	@PostMapping("/delProject")
	public String delProject(int projectId){
		log.info("projectId:"+projectId);
		int re = service.delProject(projectId);
		return "/module/schedule/list";
	}
	
	

}
