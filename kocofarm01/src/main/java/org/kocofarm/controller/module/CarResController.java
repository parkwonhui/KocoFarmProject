package org.kocofarm.controller.module;

import org.kocofarm.service.module.CarResService;
import org.kocofarm.service.module.EmpService;
import org.kocofarm.service.module.RentCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/rent/*") /*기존 : /module/rent/*/
@AllArgsConstructor
@Log4j
public class CarResController {
	
	@Setter(onMethod_= {@Autowired})
	private CarResService carResService;
	
	//EmpSerive 주입
	@Setter(onMethod_= {@Autowired})
	private EmpService empService;
	
	//차량 예약 등록 화면
	@GetMapping("/CarResWrite")
	private String setCarRes(Model model){		
		
		model.addAttribute("empList", empService.getEmpList());		
		model.addAttribute("moduleNm", "rent");/*leftbar*/		
		
		return "/module/rent/carReservation/CarResWrite";
	}

	
	
	
	
	
}
