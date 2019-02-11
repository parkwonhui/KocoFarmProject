package org.kocofarm.controller.module;

import org.kocofarm.service.module.MeetingRoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/module/rent/meetingroom/*")
public class MeetingRoomController {
	
	private MeetingRoomService service;
	
	@RequestMapping(value="/mroomlist", method=RequestMethod.GET)
	private String mroomlist(Model model){
		model.addAttribute("list", service.getMroomList());
		
	return "module/rent/meetingroom/mroomlist";
	}

}
