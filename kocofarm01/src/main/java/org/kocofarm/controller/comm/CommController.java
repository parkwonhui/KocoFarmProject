package org.kocofarm.controller.comm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommController {

	@GetMapping("/main")
	public String main(){
		return "main";
	}
	
	
}
