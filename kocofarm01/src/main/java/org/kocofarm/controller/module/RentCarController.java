package org.kocofarm.controller.module;

import java.lang.ProcessBuilder.Redirect;

import org.kocofarm.domain.rentCar.RentCarVO;
import org.kocofarm.service.module.RentCarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/rent/*") /*기존 : /module/rent/*/
@AllArgsConstructor
@Log4j
public class RentCarController {
	
	private RentCarService rentCarService;
	
	//Rent의 전체목록으로 이동
	@GetMapping("/list")
	private String list(){
		return "/module/rent/list";
	}

	/*@GetMapping("/list")
	public String list(Model model){
		
	}*/
	
	//목록
	/*@GetMapping("/getlist")
	private String getRentCarDetailList(Model model){
		log.info("list");
		model.addAttribute("getlist",rentCarService.getRentCarDetailList());
		return "module/rent/car/rentCarDetailList";
	}*/
	
	//목록
	@GetMapping("/rentCarDetailList")
	private String rentCarDetailList(Model model){
		//log.info("rentCarDetailList");
		model.addAttribute("list", rentCarService.getRentCarDetailList());
		return "module/rent/car/rentCarDetailList";
	}
	
	//등록
	@PostMapping("/rentCarDetailWrite")
	public String rentCarDetailWrite(RentCarVO rentCar, RedirectAttributes rttr){
		
		//log.info("rentCarDetailWrite : " + rentCar);
		rentCarService.setRentCarDetail(rentCar);
		rttr.addFlashAttribute("result", rentCar.getCarId());
		
		return "redirect:/module/rent/car/rentCarDetailList";		
	}
	
	//조회
	@GetMapping("/rentCarDetailView")
	public String rentCarDetailView(@RequestParam("carId") String carId, Model model ){
		
		//log.info("rentCarDetailView");
		model.addAttribute("rentCar", rentCarService.getRentCarDetail(carId));
		return "module/rent/car/rentCarDetailView";
	}
	
	//수정
	@PostMapping("/rentCarDetailEdit")
	public String rentCarDetailEdit(RentCarVO rentCar, RedirectAttributes rttr){
		//log.info("rentCarDetailEdit :" + rentCar);
		
		if(rentCarService.setUpRentCarDetail(rentCar)){
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/module/rent/car/rentCarDetailList";
	}
	
	
	//삭제
	@PostMapping("/rentCarDetailDel")
	public String rentCarDetailDel(@RequestParam("carId") String carId, RedirectAttributes rttr){
		log.info("rentCarDetailDel.." + carId);
		
		if(rentCarService.delRentCarDetail(carId)){
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/module/rent/car/rentCarDetailList";
	}
	
	//left Bar 삽입
	
	private void leftBar(){
		
	}
	
	
}
