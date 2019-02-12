package org.kocofarm.controller.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comm/*")
public class CommController {

	@GetMapping("/test")
	public String test(){
	
		return "/comm/test";
	}
	
	
}
