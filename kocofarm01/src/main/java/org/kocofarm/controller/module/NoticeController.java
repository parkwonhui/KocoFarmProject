package org.kocofarm.controller.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/notice/*")
@AllArgsConstructor
public class NoticeController {
	
	@GetMapping("/list")
	public String mainPage(){
		return "/module/notice/list";
	}
}
