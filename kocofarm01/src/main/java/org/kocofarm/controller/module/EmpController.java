package org.kocofarm.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/employees/*")
@AllArgsConstructor
public class EmpController {
	
	@GetMapping("/list")
	public String mainPage(){
		return "/module/employees/list";
	}
}
