package org.kocofarm.controller.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprEmpDraftDetailVO;
import org.kocofarm.domain.approval.ApprEmpDraftVO;
import org.kocofarm.domain.approval.ApprEmpVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.comm.PageDTO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.service.module.ApprovalService;
import org.kocofarm.service.module.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		model.addAttribute("empVO",eService.getEmp(service.getDraft(draftId).getEmpId()));
		model.addAttribute("apprEmp",service.getApprEmpList(draftId));
		int formId = service.getDraft(draftId).getFormId();
		
		if(formId == 2){
			model.addAttribute("expence",service.getExpence(draftId));
			int expenceId = service.getExpence(draftId).getExpenceId();
			model.addAttribute("expenceCont",service.getExpenceCont(expenceId));
			return  "/module/approval/getExpenceDraft";
		}else if(formId == 4){
			model.addAttribute("vacation", service.getVacation(draftId));
			model.addAttribute("replaceEmp", eService.getEmp(service.getVacation(draftId).getReplacementId()));
			return "module/approval/getVacationDraft";
		}else{
			return "module/approval/defaultDraft";
		}
	}
	

	/* 기안서 양식 가져오기 */
	@GetMapping("/getForm")
	public String getForm(HttpSession session, @RequestParam("formId") int formId, Model model,RedirectAttributes rttr){
		model.addAttribute("moduleNm", "approval");
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		model.addAttribute("loginEmp",eService.getEmp(empId));
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
	public String setExpence(ApprDraftVO draft,ApprExpenceVO expence,HttpServletRequest request){
		
			service.setApprEmp(request);
			service.setDraft(draft);
			service.setExpence(expence);
			service.setExpenceCont(request);

		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 입력하기 */
	@PostMapping("/setVacation")
	public String setVacation(ApprEmpVO apprEmp, ApprDraftVO draft, ApprVacationVO vacation,HttpServletRequest request){
	
		service.setApprEmp(request);
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
	
	/* 결재 상태 수정 (버튼에 따라 다름) */
	@GetMapping("/setUpApprState")
	public String setUpApprState(HttpSession session , @RequestParam("draftId") int draftId, @RequestParam("apprState") int apprState){
		ApprDraftVO draft = service.getDraft(draftId);
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		ApprEmpDraftDetailVO empDraft = service.getApprEmp(draftId, empId);
	
		//service.getApprEmp(draftId,empId);
		if(apprState == 0){
			draft.setApproveState("반려");
			empDraft.setApprOption("반려");
		}else if(apprState == 1){
			draft.setApproveState("결재중");
			empDraft.setApprOption("결재");
		}
		
		service.setUpDraft(draft);
		service.setUpApprOption(empDraft);
		return "redirect:/approval/getEmpDraftList";
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
	
	/*@GetMapping("/test")
	public String tset(){
		return "module/approval/test";
	}*/
	
	/* 로그인 후 내가 결재할 기안서 리스트 불러오기*/
	@GetMapping("/getEmpDraftList")
	public String getEmpDraftList(HttpSession session, Model model){
		model.addAttribute("moduleNm", "approval");
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		
		model.addAttribute("perDraftList",service.getEmpDraftList(empId));
		return "module/approval/getEmpDraftList";
	}

	
}
