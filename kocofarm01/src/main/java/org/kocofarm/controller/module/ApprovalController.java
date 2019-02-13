package org.kocofarm.controller.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprVacationVO;
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
@RequestMapping("/approval/*")
@AllArgsConstructor
public class ApprovalController {
	private ApprovalService service;
	/*전체 기안서 리스트 가져오기*/
	@GetMapping("/getDraftList")
	public String getDraftList(Model model){
		model.addAttribute("moduleNm", "approval");//leftbar띄우기
		model.addAttribute("draftList", service.getDraftList());
		return "module/approval/getDraftList";
	}
	/*전체 양식 리스트 가져오기*/
	@GetMapping("/getFormList")
	public String getFormList(Model model){
		model.addAttribute("moduleNm", "approval");//leftbar띄우기
		model.addAttribute("formList",service.getFormList()); 
		return "/module/approval/getFormList";
	}
	
	//이 두 부분은 expence랑 vacation Test 다 해보고 수정할게요.
	@PostMapping("/setDraft")
	public String setDraft(ApprDraftVO draft, RedirectAttributes rttr){
		service.setDraft(draft);
		rttr.addFlashAttribute("draftNo",service.getDraftNo());
		rttr.addAttribute("moduleNm", "approval");
		return "redirect:/module/approval/getDraftList";
	}
	
	/*특정 기안서 가져오기*/
	@GetMapping("/getDraft")
	public String getDraft(@RequestParam("draftId") int draftId, Model model){
		model.addAttribute("moduleNm", "approval"); //leftbar띄우기
		model.addAttribute("draft",service.getDraft(draftId));
		
		int formId = service.getDraft(draftId).getFormId();
		if(formId == 2){
			model.addAttribute("expence",service.getExpence(draftId));
			int expenceId = service.getExpence(draftId).getExpenceId();
			model.addAttribute("expenceCont",service.getExpenceCont(expenceId));
			return  "/module/approval/getExpenceDraft";
		}else if(formId == 4){
			model.addAttribute("vacation", service.getVacation(draftId));
			return "module/approval/getVacationDraft";
		}else{
			return "module/approval/defaultDraft";
		}
	}
	

	/*기안서 양식 가져오기 */
	@GetMapping("/getForm")
	public String getForm(@RequestParam("formId") int formId, Model model){
		model.addAttribute("moduleNm", "approval");
		
		if(formId == 2){
			return  "module/approval/setExpenceDraft";
		}else if(formId == 4){
			return "module/approval/setVacationDraft";
		}else{
			return "module/approval/defaultDraft";
		}
	}

	/* 기본 기안서 업데이트 */
	@PostMapping("/setUpDraft")
	public String setUpDraft(ApprDraftVO draft, RedirectAttributes rttr){
		log.info("setUpDraft : " + draft);
		
		if(service.setUpDraft(draft)){
			rttr.addFlashAttribute("result" , "success");
		}	
		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 정보 수정 */
	@GetMapping("/setUpVacation")
	public String setUpVacation(@RequestParam("draftId") int draftId, Model model) throws Exception{
		model.addAttribute("moduleNm", "approval"); //leftbar띄우기
		model.addAttribute("draftId",service.getDraft(draftId));
		ApprVacationVO vacation = service.getVacation(draftId);
		String SDt = vacation.getVacationStartDt();
		String EDt = vacation.getVacationEndDt();

		model.addAttribute("vacation",service.setUpVacation(vacation));
		return "redirect:/approval/setUpVacation";
	}
}
