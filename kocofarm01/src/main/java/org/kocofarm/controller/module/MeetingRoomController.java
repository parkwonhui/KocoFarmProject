package org.kocofarm.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/module/rent/meetingroom/*")
public class MeetingRoomController {
	
	@GetMapping("/mroomlist")
	private void mroomlist(){
		
	}

}
