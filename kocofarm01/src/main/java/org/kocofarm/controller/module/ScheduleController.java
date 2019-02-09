package org.kocofarm.controller.module;

import java.util.List;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
@Log4j
@Controller
@RequestMapping("/schedule/*")
@AllArgsConstructor
public class ScheduleController {
	private ScheduleService service;
	
	@GetMapping("")
	private String getProjectList(Model model){
		List<ScheduleProject> list = service.getProjectList();
		model.addAttribute("project", list);
		return "/module/schedule/list.jsp";
	}
	
	@GetMapping("/listCalender")
	private String getProjectCalenderList(int projectId, Model model){
		List<ScheduleCalenderList> list = service.getProjectCalenderList(projectId);
		model.addAttribute("calenderList", list);
		return "/module/schedule/calenderListJsonParse.jsp";
	}
	
	@PostMapping("/insertCalender")
	private String setCalender(ScheduleCalender calender){
		service.setCalender(calender);
		return "/module/schedule/calenderListJsonParse.jsp";		
	}
	
	
	@PostMapping("/editCalender")
	public int setUpCalender(ScheduleCalender calender){
		int re = service.setUpCalender(calender);
		return re;
	}
	
	@PostMapping("/editCalenderPos")
	public int setUpCalenderPos(List<ScheduleCalenderMove> calenderMoveList){
		int re = service.setUpCalenderPos(calenderMoveList);
		return re;
	}
	
	@PostMapping("/insertCategory")
	public int setCategory(ScheduleCategory category){
		int re = service.setCategory(category);
		return re;
	}
	
	@PostMapping("/editCategory")
	public int setUpCategory(ScheduleCategory category){
		int re = service.setUpCategory(category);
		return re;
	}
	
	@PostMapping("/insertProject")
	public int setProject(ScheduleProject project){
		int re = service.setProject(project);
		return re;
	}
	
	@PostMapping("/editProject")
	public int setUpProject(ScheduleProject project){
		int re = service.setUpProject(project);
		return re;
	}

	@PostMapping("/delCategory")
	public int delCategory(ScheduleCategory category){
		int re = service.delCategory(category);
		return re;
	}
	
	@PostMapping("/editCategoryPos")
	public int setCategoryPos(ScheduleCategoryMove category){
		int re = service.setMoveCategory(category);
		return re;	
	}
	
	@PostMapping("/delProject")
	public int delProject(int projectId){
		int re = service.delProject(projectId);
		return re;
	}
	
	

}
