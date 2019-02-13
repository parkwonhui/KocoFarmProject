package org.kocofarm.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rent/*")
public class RentCarController {
	
	@GetMapping("/list")
	public String list(){
		
		return "/module/rent/list";
	}

}
