package org.kocofarm.controller.module;

import java.lang.ProcessBuilder.Redirect;

import org.kocofarm.domain.rentCar.RentCarVO;
import org.kocofarm.service.module.RentCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
public class RentCarController {
	
	@Setter(onMethod_={@Autowired})
	private RentCarService rentCarService;		
	
	//left Bar 삽입	
	private void leftBar(){
		
	}
	
	//Rent의 전체목록으로 이동
	@GetMapping("/list")
	private String list(){
		return "/module/rent/list";
	}
	
	//목록
	@GetMapping("/rentCarDetailList")
	private String rentCarDetailList(Model model){
		log.info("rentCarDetailList");
		model.addAttribute("list", rentCarService.getRentCarDetailList());
		model.addAttribute("moduleNm", "rent");
		return "/module/rent/car/rentCarDetailList";
	}
	
	//등록페이지 이동
	@GetMapping("/rentCarDetailWrite")
	public String rentCarDetailWrite(){
		return "/module/rent/car/rentCarDetailWrite";
	}
	
	//등록
	@PostMapping("/rentCarDetailWrite")
	public String rentCarDetailWrite(RentCarVO rentCar, RedirectAttributes rttr){
		
		log.info("rentCarDetailWrite : " + rentCar);
		rentCarService.setRentCarDetail(rentCar);
		rttr.addFlashAttribute("result", rentCar.getCarId());
		
		return "redirect:/rent/car/rentCarDetailList";		
	}
	
	//조회
	@GetMapping("/rentCarDetailView")
	public String rentCarDetailView(@RequestParam("carId") String carId, Model model ){
		
		log.info("rentCarDetailView...");
		model.addAttribute("rentCarDetail", rentCarService.getRentCarDetail(carId));
		return "/module/rent/car/rentCarDetailView";
	}
	
	//수정페이지 이동
	@GetMapping("/rentCarDetailEdit")
	public String rentCarDetailEdit(@RequestParam("carId") String carId, Model model){
		log.info("rentCarDetailEdit...수정페이지");
		log.info("rentCarDetailEdit : " +rentCarService.getRentCarDetail(carId));
		model.addAttribute("rentCarDetail", rentCarService.getRentCarDetail(carId));
		
		return "/module/rent/car/rentCarDetailUpdate";
	}
	
	//수정
	@PostMapping("/rentCarDetailEdit")
	//@GetMapping("/rentCarDetailEdit")
	public String rentCarDetailEdit(RentCarVO rentCar, RedirectAttributes rttr){
		log.info("rentCarDetailEdit...수정처리");		
		log.info("rentCarDetailEdit :" + rentCar);		
		
		if(rentCarService.setUpRentCarDetail(rentCar)){
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/car/rentCarDetailList";
	}
		
	//삭제
	//기존 post로 설정해주었었다
	@GetMapping("/rentCarDetailDel")
	public String rentCarDetailDel(@RequestParam("carId") String carId, RedirectAttributes rttr){
		log.info("rentCarDetailDel.." + carId);
		
		if(rentCarService.delRentCarDetail(carId)){
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/rent/car/rentCarDetailList";
	}

	
	
}
