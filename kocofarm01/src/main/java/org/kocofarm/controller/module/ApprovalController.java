package org.kocofarm.controller.module;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.service.module.ApprovalService;
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
@Log4j
@RequestMapping("/module/approval/*")
@AllArgsConstructor
public class ApprovalController {
	private ApprovalService service;
	
	@GetMapping("/getDraftList")
	public String getDraftList(Model model){
		model.addAttribute("draftList", service.getDraftList());
		return "module/approval/getDraftList";
	}
	@GetMapping("/getFormList")
	public String getFormList(Model model){
		model.addAttribute("formList",service.getFormList());
		return "/module/approval/getFormList";
	}
	
	//이 두 부분은 expence랑 vacation Test 다 해보고 수정할게요.
	@PostMapping("/setDraft")
	public String setDraft(ApprDraftVO draft, RedirectAttributes rttr){
		service.setDraft(draft);
		rttr.addFlashAttribute("draftNo",service.getDraftNo());
		return "redirect:/module/approval/getDraftList";
	}
	
	@GetMapping("/getDraft")
	public String getDraft(@RequestParam("draftId") int draftId, Model model){
		model.addAttribute("draft",service.getDraft(draftId));
		return "/module/approval/getDraft";
	}
}
