package org.kocofarm.controller.module;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.rentCar.CarAppVO;
import org.kocofarm.domain.rentCar.CarResVO;
import org.kocofarm.service.comm.SignInOutService;
import org.kocofarm.service.module.CarResService;
import org.kocofarm.service.module.EmpService;
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

@AllArgsConstructor
@Log4j
@Controller
@RequestMapping("/rent/*") 
public class CarResController {
	
	@Setter(onMethod_= {@Autowired})
	private CarResService carResService;
	
	//EmpService 주입
	@Setter(onMethod_= {@Autowired})
	private EmpService empService;
	
	//SignInOutService 주입
	@Setter(onMethod_= {@Autowired})
	private EmpService eService;
	
	
	//차량 예약 등록 페이지
	@GetMapping("/CarResWrite")
	private String setCarRes(Model model){		
		
		model.addAttribute("empList", empService.getEmpList());		
		model.addAttribute("moduleNm", "rent");/*leftbar*/			
		return "/module/rent/carReservation/CarResWrite";
	}	

	//차량 예약 등록
	@PostMapping("/CarResWrite")
	private String setCarRes(Model model,CarResVO carRes, RedirectAttributes rttr){
		
		carResService.setCarRes(carRes);		
		model.addAttribute("moduleNm", "rent");/*leftbar*/
		
		return "redirect:CarResList";
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
			 Model model, RedirectAttributes rttr  ){
		
		carResService.setCarApp(carApp);
		//carResService.getCarRes(resId);
	
		model.addAttribute("moduleNm", "rent");//*leftbar*/			
		//return "redirect:CarResList";
		return "redirect:CarResList";
	}
	

	//승인된 내용 목록
	@GetMapping("/CarAppList")
	public String getCarAppList(Model model,HttpSession session){
		
		model.addAttribute("list", carResService.getCarAppList());	
		model.addAttribute("moduleNm", "rent");/*leftbar*/	
			
		
		/*LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		model.addAttribute("loginEmp",eService.getEmp(empId));
		*/	
		
		/*String resId = carResService.getC
		System.out.println("#empId2 : " + carResService.getCarRes(resId).getResWriter());
		*/
		//model.addAttribute("rWriter", carResService.getCarRes(resId));
		
		
		
		return "/module/rent/carReservation/CarAppList";
		//return "/module/rent/carReservation/CarResList";
		
	}
	
	

	
}
