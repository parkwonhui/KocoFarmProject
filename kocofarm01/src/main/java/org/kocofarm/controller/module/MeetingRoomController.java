package org.kocofarm.controller.module;

import org.kocofarm.service.module.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;

@Controller
@RequestMapping("/module/rent/meetingroom/*")
public class MeetingRoomController {
	
	@Setter(onMethod_ = { @Autowired })
	private MeetingRoomService service;
	
	@GetMapping("/mroomlist")
	private String mroomlist(Model model){
		model.addAttribute("list", service.getMroomList());
		
		System.out.println(service.getMroomList());
		
	return "/module/rent/meetingroom/mroomlist";
	}

}
