package org.kocofarm.controller.module;

import org.kocofarm.domain.meetingRoom.ReservationVO;
import org.kocofarm.service.module.EmpService;
import org.kocofarm.service.module.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.Setter;

@Controller
@RequestMapping("/reservation/*")
public class ReservationController {

	@Setter(onMethod_ = { @Autowired })
	private ReservationService service;
	
	@Setter(onMethod_ = { @Autowired })
	private EmpService empService;

	// 목록
	@GetMapping("/reservlist")
	private String reservList(Model model) {
		model.addAttribute("list", service.getReservList());

		return "/module/rent/reservation/reservlist";
	}

	// 등록 폼
	@GetMapping("/reservInsertForm")
	private String reservInsertForm(@RequestParam("mId")int mId,Model model) {
		model.addAttribute("mroom", service.getMroomNm(mId) );
		model.addAttribute("deptList", empService.getDeptList());
		model.addAttribute("empList", empService.getEmpList());

		return "/module/rent/reservation/reservInsertForm";
	}

	// 등록
	@PostMapping("/reservInsert")
	private String reservInsert(ReservationVO reserv) {
		service.setReserv(reserv);

		return "redirect:/reservation/reservlist";
	}
	
	//삭제
	
	//취소

}
