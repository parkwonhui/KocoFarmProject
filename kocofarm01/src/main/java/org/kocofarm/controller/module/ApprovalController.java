package org.kocofarm.controller.module;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.kocofarm.domain.approval.ApprCommentVO;
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
	
	
	/*�쟾泥� 湲곗븞�꽌 由ъ뒪�듃 媛��졇�삤湲�*/
	/*	@GetMapping("/getDraftList")
	public String getDraftList(Model model){
		model.addAttribute("moduleNm", "approval");//leftbar�쓣�슦湲�
		model.addAttribute("draftList", service.getDraftList());
		return "module/approval/getDraftList";
	}*/
	
	/*�럹�씠吏� 泥섎━�븳 由ъ뒪�듃 媛��졇�삤湲� */
	@GetMapping("/getDraftList")
	public String getDraftList(Criteria cri , Model model){
		model.addAttribute("moduleNm", "approval");//leftbar�쓣�슦湲�
		model.addAttribute("draftList", service.getDraftList(cri));
		int total = service.getTotal(cri);
		model.addAttribute("pageMaker",new PageDTO(cri, total));
		return "module/approval/getDraftList";
	}
	
	/* �쟾泥� �뼇�떇 由ъ뒪�듃 媛��졇�삤湲� */
	@GetMapping("/getFormList")
	public String getFormList(Model model){
		
		
		model.addAttribute("moduleNm", "approval");//leftbar�쓣�슦湲�
		model.addAttribute("formList",service.getFormList()); 
		
		return "/module/approval/getFormList";
	}
	
	/* �듅�젙 湲곗븞�꽌 媛��졇�삤湲� */
	@GetMapping("/getDraft")
	public String getDraft(@RequestParam("draftId") int draftId, Model model){
		
		model.addAttribute("moduleNm", "approval"); //leftbar�쓣�슦湲�
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
	

	/* 湲곗븞�꽌 �뼇�떇 媛��졇�삤湲� */
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
	
	/* 吏�異� 紐낆꽭�꽌 �엯�젰�븯湲� */
	@PostMapping("/setExpence")
	public String setExpence(ApprDraftVO draft,ApprExpenceVO expence,HttpServletRequest request){
		
			service.setApprEmp(request);
			service.setDraft(draft);
			service.setExpence(expence);
			service.setExpenceCont(request);

		return "redirect:/approval/getDraftList";
	}
	
	/* �쑕媛� �떊泥��꽌 �엯�젰�븯湲� */
	@PostMapping("/setVacation")
	public String setVacation(ApprEmpVO apprEmp, ApprDraftVO draft, ApprVacationVO vacation,HttpServletRequest request){
	
		service.setApprEmp(request);
		service.setDraft(draft);
		service.setVacation(vacation);
		return "redirect:/approval/getDraftList";
	}
	
	/* �쑕媛� �떊泥��꽌 �궘�젣�븯湲� */
	@GetMapping("/delVacDraft")
	public String delVacDraft(@RequestParam("draftId") int draftId, RedirectAttributes rttr){

		log.info("�궘�젣:"+draftId);
		service.delVacation(draftId);
		service.delDraft(draftId);
		
		return "redirect:/approval/getDraftList";
	}
	
	/* 吏�痢� 紐낆꽭�꽌 �궘�젣�븯湲� */
	@GetMapping("/delExpDraft")
	public String delExpDraft(@RequestParam("draftId") int draftId, RedirectAttributes rttr){

		int expenceId = service.getExpence(draftId).getExpenceId();
		log.info("�궘�젣:"+draftId);
		service.delExpenceCont(expenceId);
		service.delExpence(draftId);
		service.delDraft(draftId);
			
		return "redirect:/approval/getDraftList";
	}
	
	/* �쑕媛� �떊泥��꽌 �닔�젙 �럹�씠吏�濡� �씠�룞 */
	@GetMapping("/getSetUpVacPage")
	public String getsetUpVacPage(@RequestParam("draftId") int draftId, Model model ) throws Exception{
		model.addAttribute("moduleNm", "approval"); //leftbar�쓣�슦湲�
		model.addAttribute("draft",service.getDraft(draftId));
		model.addAttribute("vacation",service.getVacation(draftId));

		return "module/approval/setUpVacationForm";
	}
	
	/*�쑕媛� �떊泥��꽌 �닔�젙 */
	@PostMapping("/setUpVacation")
	public String setUpVacation(@RequestParam("draftId") int draftId, ApprDraftVO draft, ApprVacationVO vacation, Model model){
		draft.setDraftId(draftId);
		service.setUpVacation(vacation);
		service.setUpDraft(draft);

		
		log.info("Here");
		return "redirect:/approval/getDraftList";
	}
	
	/* 寃곗옱 �긽�깭 �닔�젙 (踰꾪듉�뿉 �뵲�씪 �떎由�) */
	@GetMapping("/setUpApprState")
	public String setUpApprState(HttpSession session , @RequestParam("draftId") int draftId, @RequestParam("apprState") int apprState){
		ApprDraftVO draft = service.getDraft(draftId);
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		ApprEmpDraftDetailVO empDraft = service.getApprEmp(draftId, empId);
	
		//service.getApprEmp(draftId,empId);
		if(apprState == 0){
			draft.setApproveState("諛섎젮");
			empDraft.setApprOption("諛섎젮");
		}else if(apprState == 1){
			draft.setApproveState("寃곗옱以�");
			empDraft.setApprOption("寃곗옱");
		}
		
		service.setUpDraft(draft);
		service.setUpApprOption(empDraft);
		return "redirect:/approval/getEmpDraftList";
	}
	
	
	/* ��泥� 洹쇰Т�옄 emp 寃��깋李� */
	@GetMapping("/searchReplaceEmp")
	public String searchRepalce(Model model){
		model.addAttribute("empList",eService.getEmpList());
		model.addAttribute("depList",eService.getDeptList());
		
		return "module/approval/getReplacementEmp";
	}
	
	/* 寃곗옱�옄 emp 寃��깋 李� */
	@GetMapping("/searchApprovalEmp")
	public String searchApproval(Model model){
		model.addAttribute("empList",eService.getEmpList());
		model.addAttribute("depList",eService.getDeptList());
		
		return "module/approval/getApprovalEmp";
	}
	
	/*�뙎湲� �벑濡앺븯湲� */
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
	/* �뙎湲� �옉�꽦�븯湲�*/
	@PostMapping("/setComment")
	@ResponseBody
	public String setComment(@RequestParam("draftId") int draftId, @RequestParam("empId") String empId,
		ApprCommentVO comment){
		service.setComment(comment);
		return "redirect:/approval/getVacationDraft";
	}
	
	/* �뙎湲� 由ъ뒪�듃 蹂닿린 */
	@GetMapping("/getCommentList")
	public String getCommentList(@RequestParam("draftId") int draftId, Model model){
		
		return "/module/approval/getDraftList";
	}
		/* 濡쒓렇�씤 �썑 �궡媛� 寃곗옱�븷 湲곗븞�꽌 由ъ뒪�듃 遺덈윭�삤湲�*/
	@GetMapping("/getEmpDraftList")
	public String getEmpDraftList(HttpSession session, Model model){
		model.addAttribute("moduleNm", "approval");
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		
		model.addAttribute("perDraftList",service.getEmpDraftList(empId));
		return "module/approval/getEmpDraftList";
	}
	
}
