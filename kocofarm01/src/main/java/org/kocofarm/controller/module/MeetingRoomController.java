package org.kocofarm.controller.module;

import org.kocofarm.domain.meetingRoom.MeetingRoomVO;
import org.kocofarm.service.module.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.Setter;

@Controller
@RequestMapping("/meetingroom/*")
public class MeetingRoomController {

	@Setter(onMethod_ = { @Autowired })
	private MeetingRoomService service;

	@GetMapping("/mroomlist")
	private String mroomList(Model model) {
		model.addAttribute("list", service.getMroomList());

		return "/module/rent/meetingroom/mroomlist";
	}

	@GetMapping("/mroomdetail")
	private String mroomDetail(@RequestParam("mId") int mId, Model model) {
		model.addAttribute("mroom", service.getMroom(mId));

		return "/module/rent/meetingroom/mroomdetail";
	}

	@GetMapping("/mroomInsertForm")
	private String mroomInsertForm() {

		return "/module/rent/meetingroom/mroomInsertForm";
	}

	@PostMapping("/mroomInsert")
	private String mroomInsert(MeetingRoomVO mroom) {
		service.setMroom(mroom);

		return "redirect:/module/rent/meetingroom/mroomlist";
	}

	@GetMapping("/delMroom")
	private String mroomDelete(@RequestParam("mId") int mId, RedirectAttributes rttr) {
		if (service.delMroom(mId)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/module/rent/meetingroom/mroomlist";
	}

	@GetMapping("/mroomupdateForm")
	private String mroomUpdataForm(@RequestParam("mId") int mId, Model model) {
		model.addAttribute("mroom", service.getMroom(mId));

		return "/module/rent/meetingroom/mroomupdateForm";
	}

	@PostMapping("/mroomUpdate")
	private String mroomUpdate(MeetingRoomVO mroom, RedirectAttributes rttr) {
		if (service.setUpMroom(mroom)) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/module/rent/meetingroom/mroomlist";
	}

}
