package org.kocofarm.controller.module;

import org.kocofarm.domain.meetingRoom.ReservationVO;
import org.kocofarm.service.module.EmpService;
import org.kocofarm.service.module.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
		model.addAttribute("moduleNm", "rent");/*leftbar*/

		return "/module/rent/reservation/reservlist";
	}

	// 등록 폼
	@GetMapping("/reservInsertForm")
	private String reservInsertForm(@RequestParam("mId")int mId,Model model) {
		model.addAttribute("mroom", service.getMroomNm(mId) );
		model.addAttribute("deptList", empService.getDeptList());
		model.addAttribute("empList", empService.getEmpList());		
		model.addAttribute("moduleNm", "rent");/*leftbar*/
		return "/module/rent/reservation/reservInsertForm";
	}

	// 등록
	@PostMapping("/reservInsert")
	private String reservInsert(ReservationVO reserv) {
		service.setReserv(reserv);

		return "redirect:/reservation/reservlist";
	}
	
	//삭제
	@PostMapping("/delReserv")
	private String reservDelete(@RequestParam("delReserv") int[] delReserv, ModelMap modelmap){
		// 삭제할 사용자 ID마다 반복해서 사용자 삭제
	    for (int rvId : delReserv) {
	        boolean deleteCount = service.delReserv(rvId);
	    }

		return "redirect:/reservation/reservlist";
	}
	
	//회의실 번호 확인
		@ResponseBody
		@PostMapping("/idChk")
		public int idChk(@RequestParam("mId")int mId){
			
			ReservationVO idChk = service.getIdChk(mId);
			
			int result = 0;
			
			if(idChk != null){
				result = 1;
			}
			
			return result;
			
		}//end idChk
	
	
	//수정
	
	//취소

}
