package org.kocofarm.controller.module;


import org.kocofarm.service.module.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;

@Controller
@RequestMapping("/reservation/*")
public class ReservationController {
	
	@Setter(onMethod_ = { @Autowired })
	private ReservationService service;

	//등록 폼
	@GetMapping("/reservInsertForm")
	private String mroomInsertForm() {

		return "/module/rent/reservation/reservInsertForm";
	}



}
