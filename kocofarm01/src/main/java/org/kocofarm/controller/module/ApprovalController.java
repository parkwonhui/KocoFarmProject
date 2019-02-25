package org.kocofarm.controller.module;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
	public String getDraftList( HttpSession session , Criteria cri , Model model){

		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		model.addAttribute("loginEmp",login);
		String empId = login.getEmpId();
		EmpVO emp = eService.getEmp(empId);
		model.addAttribute("Emp",emp);
		
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
	public String getDraft(@RequestParam("draftId") int draftId, Model model , HttpSession session){
		model.addAttribute("moduleNm", "approval"); //leftbar띄우기
		
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		model.addAttribute("loginEmp",eService.getEmp(empId));
		model.addAttribute("draft",service.getDraft(draftId));
		
		model.addAttribute("apprEmp",service.getApprEmp(draftId, empId));
		model.addAttribute("empVO",eService.getEmp(service.getDraft(draftId).getEmpId())); //기안자 정보
		model.addAttribute("apprEmpList",service.getApprEmpInfoList(draftId));
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
		
			service.setDraft(draft);
			service.setExpence(expence);
			service.setExpenceCont(request);
			service.setApprEmp(request);

		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 입력하기 */
	@PostMapping("/setVacation")
	public String setVacation(ApprEmpVO apprEmp, ApprDraftVO draft, ApprVacationVO vacation,HttpServletRequest request){
	
		service.setDraft(draft);
		service.setVacation(vacation);
		service.setApprEmp(request);
		return "redirect:/approval/getDraftList";
	}
	
	/* 휴가 신청서 삭제하기 */
	@GetMapping("/delVacDraft")
	public String delVacDraft(@RequestParam("draftId") int draftId, RedirectAttributes rttr){

		log.info("삭제:"+draftId);
		service.delApprEmp(draftId);
		service.delVacation(draftId);
		service.delDraft(draftId);
		
		return "redirect:/approval/getDraftList";
	}
	
	/* 지츨 명세서 삭제하기 */
	@GetMapping("/delExpDraft")
	public String delExpDraft(@RequestParam("draftId") int draftId, RedirectAttributes rttr){

		int expenceId = service.getExpence(draftId).getExpenceId();
		log.info("삭제:"+draftId);
		service.delApprEmp(draftId);
		service.delExpenceCont(expenceId);
		service.delExpence(draftId);
		service.delDraft(draftId);
			
		return "redirect:/approval/getDraftList";
	}
	
	
	
	/* 휴가 신청서 수정 페이지로 이동 */
	@GetMapping("/getSetUpVacPage")
	public String getsetUpVacPage(@RequestParam("draftId") int draftId, Model model ) throws Exception{
		model.addAttribute("moduleNm", "approval"); //leftbar띄우기
		ApprDraftVO draft = service.getDraft(draftId);
		ApprVacationVO vacation = service.getVacation(draftId);
		model.addAttribute("emp", eService.getEmp(draft.getEmpId()));
		model.addAttribute("replacement",eService.getEmp(vacation.getReplacementId()));
		model.addAttribute("draft",service.getDraft(draftId));
		model.addAttribute("vacation",service.getVacation(draftId));
		String apprStringId = "";
		String apprStringNm = "";
		List<ApprEmpDraftDetailVO> list = service.getApprEmpList(draftId);
		for(int i =0; i< list.size(); i++){
			apprStringId += list.get(i).getEmpId();
			EmpVO emp = eService.getEmp(list.get(i).getEmpId());
			apprStringNm += emp.getKorNm();
			if(i < list.size()-1){
				apprStringId +=",";
				apprStringNm +=",";
			}
		}
		model.addAttribute("apprId", apprStringId);
		model.addAttribute("apprNm", apprStringNm);
	
		return "module/approval/setUpVacationForm";
	}
	
	
	/*휴가 신청서 수정 */
	@PostMapping("/setUpVacation")
	public String setUpVacation(HttpServletRequest request, @RequestParam("draftId") int draftId, ApprDraftVO draft, ApprVacationVO vacation, Model model){
		/* 결재자 수정 */
		service.delApprEmp(draftId);
		service.setApprEmp(request);
		/* 휴가 신청서 관련 정보 수정 */
		service.setUpVacation(vacation);
		/* 기본 기안서 수정  */
		draft.setApproveState("기안중");
		service.setUpDraft(draft);

		log.info("Here");
		return "redirect:/approval/getDraftList";
	}
	
	/* 결재 상태 수정 -- 저장버튼 (버튼에 따라 다름) */
	@GetMapping("/setUpApprState")
	public String setUpApprState(HttpSession session ,HttpServletRequest request, @RequestParam("draftId") int draftId, @RequestParam("apprState") int apprState){
		ApprDraftVO draft = service.getDraft(draftId);
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		ApprEmpDraftDetailVO empDraft = service.getApprEmp(draftId, empId);
		
		if(apprState == 0){
			draft.setApproveState("반려");
			empDraft.setApprOption("반려");
			empDraft.setDraftSign("return");
		}else if(apprState == 1){
			String draftSign = request.getParameter("tmpSignImage");
			draft.setApproveState("결재중");
			empDraft.setApprOption("결재");
			empDraft.setDraftSign(draftSign);
		}
		
		service.setUpDraft(draft);
		service.setUpApprOption(empDraft);
		
		int count = 0;
		int refuse = 0;
		List<ApprEmpDraftDetailVO> apprEmpList = service.getApprEmpList(draftId);

		for(int i = 0; i < apprEmpList.size(); i++){
			
			if(apprEmpList.get(i).getDraftSign() == null){
				count = 0;
			}else if(apprEmpList.get(i).getDraftSign().equals("return")){
				refuse = 1;
			}else{
				count = 1;
			}
		}
		
		if(count == 1 && refuse == 0){
			draft.setApproveState("결재완료");
			service.setUpDraft(draft);
		}
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
	
@PostMapping("/setComment")
	@ResponseBody
	public String setComment(@RequestParam("draftId") int draftId, @RequestParam("empId") String empId,
		ApprCommentVO comment){
		service.setComment(comment);
		return "redirect:/approval/getDraft?draftId=${ApprDraftVO.draftId }";
	}
	
	/* 댓글 리스트 가져오기  */
	@GetMapping("/getCommentList")
	public String getCommentList(@RequestParam("draftId") int draftId,@RequestParam("empId") String empId,
			Model model){
		List<ApprCommentVO> list = service.getCommentList(draftId);
		model.addAttribute("list", list);
		return "/module/approval/getCommentList";
	}
	
	/* 댓글 삭제 */
	@PostMapping("/delComment")
	public String delComment(@RequestParam("draftId") int draftId,@RequestParam("commentId") int commentId, ApprCommentVO comment){
	service.delComment(commentId);
	return "/module/approval/getVacationDraft";
	}
	/* 로그인 후 내가 결재할 기안서 리스트 불러오기*/
	@GetMapping("/getEmpDraftList")
	public String getEmpDraftList(HttpSession session, Model model){
		model.addAttribute("moduleNm", "approval");
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
	
		model.addAttribute("perDraftList",service.getEmpDraftList(empId));
		return "module/approval/getEmpDraftList";
	}
	
	/* sign 등록 페이지로 이동 */
	@GetMapping("/setEmpSign")
	public String setEmpSign(HttpSession session, Model model){
		model.addAttribute("moduleNm", "approval");
		LoginVO login = (LoginVO) session.getAttribute("loginVO");
		String empId = login.getEmpId();
		model.addAttribute("emp", eService.getEmp(empId));
		return "module/approval/setEmpSign";
	}
	
	/* sign imgage 저장 */
	@PostMapping("/uploadSign")
	public String uploadAjaxPost(HttpSession session,MultipartFile[] uploadFile){
		
		String uploadFolder = "C:\\Users\\KOSTA\\git\\KocoFarmProject\\kocofarm01\\src\\main\\webapp\\resources\\upload\\temp";
		
		for(MultipartFile multipartFile : uploadFile){
			LoginVO login = (LoginVO) session.getAttribute("loginVO");
			String empId = login.getEmpId();
			String empSign = multipartFile.getOriginalFilename();
			
			empSign = empSign.substring(empSign.lastIndexOf("\\") + 1);
			
			File saveFile = new File(uploadFolder, empSign);
			try {
				multipartFile.transferTo(saveFile);
				service.setUpSign(empId, empSign);
				
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
		return "module/approval/setEmpSign";
	}
	

	@GetMapping("/test")
	public String test(){
	
		return "module/approval/index";
	}
/*	
	@GetMapping("/save")
	public String saveget(){
	
		return "module/approval/save";
	}*/
	
	@PostMapping("/save")
	public String savepost( Model model){

		return "module/approval/save";
	}
	
}
