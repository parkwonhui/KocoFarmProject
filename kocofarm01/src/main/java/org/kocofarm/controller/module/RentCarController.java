package org.kocofarm.controller.module;

import java.lang.ProcessBuilder.Redirect;

import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.comm.PageDTO;
import org.kocofarm.domain.rentCar.RentCarVO;
import org.kocofarm.service.module.RentCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
public class RentCarController {
	
	@Setter(onMethod_={@Autowired})
	private RentCarService rentCarService;		
	
	
	
	//목록
	@GetMapping("/rentCarDetailList")

	private String rentCarDetailList(Criteria cri, Model model){
		
		//log.info("rentCarDetailList : " + cri);		
		model.addAttribute("list", rentCarService.getRentCarDetailList(cri));
		
		model.addAttribute("moduleNm", "rent");/*leftbar*/
		
		int total = rentCarService.getTotal(cri);
		//log.info("total : " + total);		
		model.addAttribute("pageMaker", new PageDTO(cri, total));		


		return "/module/rent/car/rentCarDetailList";
	}
	
	//등록페이지 이동
	@GetMapping("/rentCarDetailWrite")
	public String rentCarDetailWrite(Model model){
		model.addAttribute("moduleNm", "rent");/*leftbar*/
		return "/module/rent/car/rentCarDetailWrite";
	}
	
	//등록
	@PostMapping("/rentCarDetailWrite")
	public String rentCarDetailWrite(RentCarVO rentCar, RedirectAttributes rttr){
		
		log.info("rentCarDetailWrite : " + rentCar);
		rentCarService.setRentCarDetail(rentCar);
		rttr.addFlashAttribute("result", rentCar.getCarId());
		
		
		/*return "redirect:/rent/car/rentCarDetailList";	*/	
		return "redirect:rentCarDetailList";
	}
	
	//조회
	@GetMapping("/rentCarDetailView")
	public String rentCarDetailView(@RequestParam("carId") String carId, 
									@ModelAttribute("cri") Criteria cri ,Model model ){
		
		log.info("rentCarDetailView...");
		model.addAttribute("rentCarDetail", rentCarService.getRentCarDetail(carId));
		
		return "/module/rent/car/rentCarDetailView";
	}
	
	//수정페이지 이동
	@GetMapping("/rentCarDetailEdit")
	public String rentCarDetailEdit(@RequestParam("carId") String carId,
									@ModelAttribute("cri") Criteria cri, Model model){
		
		log.info("rentCarDetailEdit...수정페이지");
		log.info("rentCarDetailEdit : " +rentCarService.getRentCarDetail(carId));
		
		model.addAttribute("rentCarDetail", rentCarService.getRentCarDetail(carId));
		
		return "/module/rent/car/rentCarDetailUpdate";
	}
	
	//수정
	@PostMapping("/rentCarDetailEdit")
	//@GetMapping("/rentCarDetailEdit")
	public String rentCarDetailEdit(RentCarVO rentCar,
									@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
		log.info("rentCarDetailEdit...수정처리");		
		log.info("rentCarDetailEdit :" + rentCar);		
		
		if(rentCarService.setUpRentCarDetail(rentCar)){
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:rentCarDetailList";
	}
		
	//삭제
	//기존 post로 설정해주었었다
	@GetMapping("/rentCarDetailDel")
	public String rentCarDetailDel(@RequestParam("carId") String carId, 
								@ModelAttribute("cri") Criteria cri, RedirectAttributes rttr){
		log.info("rentCarDetailDel.." + carId);
		
		if(rentCarService.delRentCarDetail(carId)){
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
/*		return "redirect:/rent/car/rentCarDetailList";*/
		return "redirect:rentCarDetailList";
	}

	
	
}
