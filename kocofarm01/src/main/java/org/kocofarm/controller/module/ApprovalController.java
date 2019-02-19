package org.kocofarm.controller.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kocofarm.domain.approval.ApprCommentVO;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.comm.PageDTO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.service.module.ApprovalService;
import org.kocofarm.service.module.EmpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/approval/*")
@AllArgsConstructor
public class ApprovalController {
	private ApprovalService service;
	private EmpService eService;
	
	
	/*전체 기안서 리스트 가져오기*/
	/*	@GetMapping("/getDraftList")
	public String getDraftList(Model model){
		model.addAttribute("moduleNm", "approval");//leftbar띄우기
		model.addAttribute("draftList", service.getDraftList());
		return "module/approval/getDraftList";
	}*/
	
	/*페이징 처리한 리스트 가져오기 */
	@GetMapping("/getDraftList")
	public String getDraftList(Criteria cri , Model model){
		model.addAttribute("moduleNm", "approval");//leftbar띄우기
		model.addAttribute("draftList", service.getDraftList(cri));
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker",new PageDTO(cri, total));
		return "module/approval/getDraftList";
	}
	
	/* 전체 양식 리스트 가져오기 */
	@GetMapping("/getFormList")
	public String getFormList(Model model){
		model.addAttribute("moduleNm", "approval");//leftbar띄우기
		model.addAttribute("formList",service.getFormList()); 
		return "/module/approval/getFormList";
	}
	
	/* 특정 기안서 가져오기 */
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
	

	/* 기안서 양식 가져오기 */
	@GetMapping("/getForm")
	public String getForm(@RequestParam("formId") int formId, Model model,RedirectAttributes rttr){
		model.addAttribute("moduleNm", "approval");
		
		if(formId == 2){
			return  "module/approval/setExpenceDraft";
		}else if(formId == 4){
			return "module/approval/setVacationDraft";
		}else{
			return "module/approval/defaultDraft";
		}
	}
	
	/* 지출 명세서 입력하기 */
	@PostMapping("/setExpence")
	public String setDraft(ApprDraftVO draft,ApprExpenceVO expence,HttpServletRequest request){
			
			service.setDraft(draft);
			service.setExpence(expence);
			service.setExpenceCont(request);

		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 입력하기 */
	@PostMapping("/setVacation")
	public String setVacation(ApprDraftVO draft, ApprVacationVO vacation){
		service.setDraft(draft);
		service.setVacation(vacation);
		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 삭제하기 */
	@GetMapping("/delVacDraft")
	public String delVacDraft(@RequestParam("draftId") int draftId, RedirectAttributes rttr){

		log.info("삭제:"+draftId);
		service.delVacation(draftId);
		service.delDraft(draftId);
		
		return "redirect:/approval/getDraftList";
	}
	
	/* 지츨 명세서 삭제하기 */
	@GetMapping("/delExpDraft")
	public String delExpDraft(@RequestParam("draftId") int draftId, RedirectAttributes rttr){

		int expenceId = service.getExpence(draftId).getExpenceId();
		log.info("삭제:"+draftId);
		service.delExpenceCont(expenceId);
		service.delExpence(draftId);
		service.delDraft(draftId);
			
		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 수정 페이지로 이동 */
	@GetMapping("/getSetUpVacPage")
	public String getsetUpVacPage(@RequestParam("draftId") int draftId, Model model ) throws Exception{
		model.addAttribute("moduleNm", "approval"); //leftbar띄우기
		model.addAttribute("draft",service.getDraft(draftId));
		model.addAttribute("vacation",service.getVacation(draftId));

		return "module/approval/setUpVacationForm";
	}
	
	/*휴가 신청서 수정 */
	@PostMapping("/setUpVacation")
	public String setUpVacation(@RequestParam("draftId") int draftId, ApprDraftVO draft, ApprVacationVO vacation, Model model){
		draft.setDraftId(draftId);
		service.setUpVacation(vacation);
		service.setUpDraft(draft);

		
		log.info("Here");
		return "redirect:/approval/getDraftList";
	}
	
	/* 대체 근무자 emp 검색창 */
	@GetMapping("/searchReplaceEmp")
	public String searchRepalce(Model model){
		model.addAttribute("empList",eService.getEmpList());
		model.addAttribute("depList",eService.getDeptList());
		
		return "module/approval/getReplacementEmp";
	}
	
	/* 결재자 emp 검색 창 */
	@GetMapping("/searchApprovalEmp")
	public String searchApproval(Model model){
		model.addAttribute("empList",eService.getEmpList());
		model.addAttribute("depList",eService.getDeptList());
		
		return "module/approval/getApprovalEmp";
	}
	
	/*댓글 등록하기 */
	/*@PostMapping(value = "/setComment",
			consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ApprCommentVO comment){
		log.info("ApprCommentVO : " + comment);
		int setCount = service.setComment(comment);
		log.info(setCount);
		return setCount == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}*/	
	/* 댓글 작성하기*/
	@PostMapping("/setComment")
	@ResponseBody
	public String setComment(@RequestParam("dratId") int draftId, @RequestParam("empId") String empId, 
		@RequestParam("commentContents") ApprCommentVO comment){
		System.out.println("뿌잉");
		log.info("뿌잉");
		service.setComment(comment);
		 
		return "redirect:/approval/getVacationDraft";
	}
	
	/* 댓글 리스트 보기 */
	@GetMapping("/getCommentList")
	public String getCommentList(Model model){
		
		return "/module/approval/getDraftList";
	}
}
