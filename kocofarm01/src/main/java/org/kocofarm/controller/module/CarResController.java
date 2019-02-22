package org.kocofarm.controller.module;

import java.util.ArrayList;
import java.util.List;

import org.kocofarm.domain.rentCar.CarAppVO;
import org.kocofarm.domain.rentCar.CarResVO;
import org.kocofarm.service.module.CarResService;
import org.kocofarm.service.module.EmpService;
import org.kocofarm.service.module.RentCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	//EmpService 주입
	@Setter(onMethod_= {@Autowired})
	private EmpService empService;
	
	//차량 예약 등록 페이지
	@GetMapping("/CarResWrite")
	private String setCarRes(Model model){		
		
		model.addAttribute("empList", empService.getEmpList());		
		model.addAttribute("moduleNm", "rent");/*leftbar*/			
		return "/module/rent/carReservation/CarResWrite";
	}	

	//차량 예약 등록
	@PostMapping("/CarResWrite")
	private String setCarRes(Model model,CarResVO carRes){
		
		log.info("CarResWrite : " + carRes);		
		carResService.setCarRes(carRes);		
		model.addAttribute("moduleNm", "rent");/*leftbar*/
		
		return "/module/rent/carReservation/CarResWrite";
	}
	
	//예약 목록
	@GetMapping("/CarResList")
	private String getCarResList(Model model){
		model.addAttribute("list", carResService.getCarResList());
		model.addAttribute("moduleNm", "rent");/*leftbar*/		
		
		return "/module/rent/carReservation/CarResList";
	}
	
	//예약 조회
	@GetMapping("/CarResDetail")
	public String getCarRes( @RequestParam("resId") String resId, Model model){
		
		model.addAttribute("CarReserve", carResService.getCarRes(resId));		
		model.addAttribute("moduleNm", "rent");/*leftbar*/	
		
		return "/module/rent/carReservation/CarResDetail";
		//url주소 넣어주기
	}
	
	
	
	//승인 등록
	@GetMapping("/setCarApp")
	private String setCarApp( CarAppVO carApp,
			 Model model, RedirectAttributes rttr ){
				
		carResService.setCarApp(carApp);
		//carResService.getCarRes(resId);
	
		
		model.addAttribute("moduleNm", "rent");//*leftbar*/			
		//return "redirect:CarResList";
		return "redirect:CarResList";
	}
	

	//승인된 내용 목록
	@GetMapping("/CarAppList")
	public String getCarAppList(Model model){
		log.info("==차량목록==");
		//model.addAttribute("lit", carResService.getCarResList());
		
		model.addAttribute("list", carResService.getCarAppList());
	
		model.addAttribute("moduleNm", "rent");/*leftbar*/	
		
		return "/module/rent/carReservation/CarAppList";
		//return "/module/rent/carReservation/CarResList";
		
	}
	
	

	
}
