package org.kocofarm.service.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.approval.ApprCommentVO;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprEmpDraftDetailVO;
import org.kocofarm.domain.approval.ApprEmpDraftVO;
import org.kocofarm.domain.approval.ApprEmpPerDraftVO;
import org.kocofarm.domain.approval.ApprEmpVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.comm.LoginVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.mapper.module.ApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

	private ApprovalMapper mapper;

/*	 기안서 목록 불러오기 
	@Override
	public List<ApprDraftVO> getDraftList() {
		return mapper.getDraftList();
	}
*/
	/* 페이징 처리한 기안서 목록 불러오기 */
	@Override
	public List<ApprDraftVO> getDraftList(Criteria cri) {
		
		return mapper.getListWithPaging(cri);
	}
	/* 전체 기안서 개수 가져오기 */
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	
	/* 양식 목록 불러오기 */
	@Override
	public List<ApprFormVO> getFormList() {
		return mapper.getFormList();
	}

	/* 최근 draft_No 불러오기 */
	@Override
	public int getDraftNo() {
		return mapper.getDraftNo();
	}

	/* 기안서 불러오기 */
	@Override
	public ApprDraftVO getDraft(int draftId) {
		return mapper.getDraft(draftId);
	}

	/* 지출 명세서 불러오기 */
	@Override
	public ApprExpenceVO getExpence(int draftId) {
		return mapper.getExpence(draftId);
	}

	/* 지출 명세서 내역 불러오기 */
	@Override
	public List<ApprExpenceContVO> getExpenceCont(int expenceId) {
		return mapper.getExpenceCont(expenceId);
	}

	/* 휴가 신청서 불러오기 */
	@Override
	public ApprVacationVO getVacation(int draftId) {
		return mapper.getVacation(draftId);
	}

	/* 결재자 정보 리스트 불러오기 */
	@Override
	public List<ApprEmpPerDraftVO> getApprEmpInfoList(int draftId) {
		return mapper.getApprEmpInfoList(draftId);
	}
	
	/* 결재자 리스트 불러오기 */
	@Override
	public List<ApprEmpDraftDetailVO> getApprEmpList(int draftId) {
		return mapper.getApprEmpList(draftId);
	}
	
	@Override
	public ApprEmpDraftDetailVO getApprEmp(int draftId, String empId) {
		return mapper.getApprEmp(draftId , empId);
	}
	

	/*로그인 회원이 결재할 기안서 개수 가져오기 */
	@Override
	public int getNumberOfDraft(String empId) {
		return mapper.getNumberOfDraft(empId);
	}
	/* 결재할 기안서 리스트 번호 가져오기 */
	@Override
	public List<ApprEmpDraftVO> getEmpDraftList(String empId) {
		return mapper.getEmpDraftList(empId);
	}
	
	/* 기안서 입력하기 */
	@Override
	public void setDraft(ApprDraftVO draft) {
		mapper.setDraft(draft);
	}

	/* 휴가 신청서 입력하기 */
	@Override
	public void setVacation(ApprVacationVO vacation) {
		
		int draftId = mapper.getDraftNo();
		vacation.setDraftId(draftId);
		mapper.setVacation(vacation);
	}

	/* 지출 결의서 입력하기 */
	@Override
	public void setExpence(ApprExpenceVO expence) {
		int draftId = mapper.getDraftNo();
		expence.setDraftId(draftId);
		mapper.setExpence(expence);
	}

	/* 지출 결의서 내역 입력하기 */
	@Override
	public void setExpenceCont(HttpServletRequest request) {
		ApprExpenceContVO expenceCont = new ApprExpenceContVO();
		String op = "yes";
		int sum = 0;
		int draftId = mapper.getDraftNo();
		int expenceId = mapper.getExpence(draftId).getExpenceId();
		
		String[] arr = request.getParameterValues("expence");
		for (int i = 0; i < arr.length; i += 4) {
			expenceCont.setExpenceId(expenceId);
			expenceCont.setContSeq(Integer.parseInt(arr[i]));
			expenceCont.setCustomerName(arr[i + 1]);
			expenceCont.setExpencePrice(arr[i + 2]);
			expenceCont.setCommissionPrice(arr[i + 3]);
			op = request.getParameter("commissionType" + (i / 4 + 1));
			expenceCont.setCommissionOption(op);
			sum += Integer.parseInt(arr[i + 2]);
			if (op.equals("yes")) {
				sum += Integer.parseInt(arr[i + 3]);
			}
			mapper.setExpenceCont(expenceCont);
		}
	}

	/* 결재자 입력하기 */
	@Override
	public void setApprEmp(HttpServletRequest request) {
		ApprEmpVO apprEmp = new ApprEmpVO();
		String empIdList = request.getParameter("empIdList");
		String[] empId =empIdList.split(",");
		int draftId = mapper.getDraftNo();
		for(int i = 0; i<empId.length; i++){
			apprEmp.setDraftId(draftId);
			apprEmp.setEmpId(empId[i]);
			mapper.setApprEmp(apprEmp);
		}
		
	}
	
	/* --------------------- 삭제 --------------------- */
	@Override
	public boolean delDraft(int draftId) {
		return mapper.delDraft(draftId) == 1 ;
		
	}

	@Override
	public void delVacation(int draftId) {
		mapper.delVacation(draftId);
	}

	@Override
	public void delExpence(int draftId) {
		mapper.delExpence(draftId);
	}

	@Override
	public void delExpenceCont(int expenceId) {
		mapper.delExpenceCont(expenceId);
	}

	
	/* --------------------- 수정 --------------------- */
	@Override
	public int setUpDraft(ApprDraftVO draft) {
		return mapper.setUpDraft(draft);
	}
	@Override
	public int setUpVacation(ApprVacationVO vacation){
		return mapper.setUpVacation(vacation);
	}

	
	@Override
	public int setUpExpence(ApprExpenceVO expence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setUpExpenceCont(ApprExpenceContVO expenceCont) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/* 결재 상태 수정 (버튼에 따라 다름) */
	@Override
	public int setUpApprOption(ApprEmpDraftDetailVO empDraft) {
		return mapper.setUpApprOption(empDraft);
	}
	
	/* emp에 sign 추가 수정 */
	@Override
	public int setUpSign(String empId,String empSign) {
		return mapper.setUpSign(empId,empSign);
	}
	


	@Override
	public int setComment(ApprCommentVO comment){
		return mapper.setComment(comment);
	}
	
	@Override
	public List<ApprCommentVO> getCommentList(int draftId){
		return mapper.getCommentList(draftId);
	}
	
	@Override
	public int delComment(int commentId){
		return mapper.delComment(commentId);
	}
}
