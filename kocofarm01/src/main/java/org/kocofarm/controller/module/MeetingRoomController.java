package org.kocofarm.controller.module;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.service.module.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;

@Controller
@RequestMapping("/meetingroom/*")
public class MeetingRoomController {

	@Setter(onMethod_ = { @Autowired })
	private MeetingRoomService service;

	//목록
	@GetMapping("/mroomlist")
	private String mroomList(Model model) {
		model.addAttribute("list", service.getMroomList());
		model.addAttribute("moduleNm", "rent");
		
		return "/module/rent/meetingroom/mroomlist";
	}

	//조회
	@GetMapping("/mroomdetail")
	private String mroomDetail(@RequestParam("mId") int mId, Model model) {
		model.addAttribute("mroom", service.getMroom(mId));

		return "/module/rent/meetingroom/mroomdetail";
	}

	//등록 폼
	@GetMapping("/mroomInsertForm")
	private String mroomInsertForm() {

		return "/module/rent/meetingroom/mroomInsertForm";
	}

	//등록
	@PostMapping("/mroomInsert")
	private String mroomInsert(@ModelAttribute("mroom") MeetingRoomVO mroom) {
		
		service.setMroom(mroom);

		return "redirect:/meetingroom/mroomlist";
	}

	//삭제
	@GetMapping("/delMroom")
	private String mroomDelete(@RequestParam("mId") int mId, RedirectAttributes rttr) {
		if (service.delMroom(mId)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/meetingroom/mroomlist";
	}

	//수정폼
	@GetMapping("/mroomupdateForm")
	private String mroomUpdataForm(@RequestParam("mId") int mId, Model model) {
		model.addAttribute("mroom", service.getMroom(mId));

		return "/module/rent/meetingroom/mroomupdateForm";
	}

	//수정
	@PostMapping("/mroomUpdate")
	private String mroomUpdate(MeetingRoomVO mroom, RedirectAttributes rttr) {
		if (service.setUpMroom(mroom)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/meetingroom/mroomlist";
	}
	
	//회의실 번호 확인
	@ResponseBody
	@PostMapping("/idChk")
	public int idChk(@RequestParam("mId")int mId){
		MeetingRoomVO idChk = service.getIdChk(mId);
		
		int result = 0;
		
		if(idChk != null){
			result = 1;
		}
		
		return result;
		
	}//end idChk

}
