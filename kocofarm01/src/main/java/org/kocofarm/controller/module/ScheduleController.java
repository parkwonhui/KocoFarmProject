package org.kocofarm.controller.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleProject;
import org.kocofarm.service.module.ScheduleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		log.info("list"+list);
		return "/module/schedule/list.jsp";
	}
}
