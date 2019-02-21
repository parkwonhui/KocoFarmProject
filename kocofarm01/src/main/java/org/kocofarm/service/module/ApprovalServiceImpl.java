package org.kocofarm.service.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.approval.ApprCommentVO;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprEmpDraftDetailVO;
import org.kocofarm.domain.approval.ApprEmpDraftVO;
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

/*	 湲곗븞�꽌 紐⑸줉 遺덈윭�삤湲� 
	@Override
	public List<ApprDraftVO> getDraftList() {
		return mapper.getDraftList();
	}
*/
	/* �럹�씠吏� 泥섎━�븳 湲곗븞�꽌 紐⑸줉 遺덈윭�삤湲� */
	@Override
	public List<ApprDraftVO> getDraftList(Criteria cri) {
		
		return mapper.getListWithPaging(cri);
	}
	/* �쟾泥� 湲곗븞�꽌 媛쒖닔 媛��졇�삤湲� */
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	
	/* �뼇�떇 紐⑸줉 遺덈윭�삤湲� */
	@Override
	public List<ApprFormVO> getFormList() {
		return mapper.getFormList();
	}

	/* 理쒓렐 draft_No 遺덈윭�삤湲� */
	@Override
	public int getDraftNo() {
		return mapper.getDraftNo();
	}

	/* 湲곗븞�꽌 遺덈윭�삤湲� */
	@Override
	public ApprDraftVO getDraft(int draftId) {
		return mapper.getDraft(draftId);
	}

	/* 吏�異� 紐낆꽭�꽌 遺덈윭�삤湲� */
	@Override
	public ApprExpenceVO getExpence(int draftId) {
		return mapper.getExpence(draftId);
	}

	/* 吏�異� 紐낆꽭�꽌 �궡�뿭 遺덈윭�삤湲� */
	@Override
	public List<ApprExpenceContVO> getExpenceCont(int expenceId) {
		return mapper.getExpenceCont(expenceId);
	}

	/* �쑕媛� �떊泥��꽌 遺덈윭�삤湲� */
	@Override
	public ApprVacationVO getVacation(int draftId) {
		return mapper.getVacation(draftId);
	}

	/* 寃곗옱�옄 由ъ뒪�듃 遺덈윭�삤湲� */
	@Override
	public List<EmpVO> getApprEmpList(int draftId) {
		return mapper.getApprEmpList(draftId);
	}
	
	@Override
	public ApprEmpDraftDetailVO getApprEmp(int draftId, String empId) {
		return mapper.getApprEmp(draftId , empId);
	}
	

	/*濡쒓렇�씤 �쉶�썝�씠 寃곗옱�븷 湲곗븞�꽌 媛쒖닔 媛��졇�삤湲� */
	@Override
	public int getNumberOfDraft(String empId) {
		return mapper.getNumberOfDraft(empId);
	}
	/* 寃곗옱�븷 湲곗븞�꽌 由ъ뒪�듃 踰덊샇 媛��졇�삤湲� */
	@Override
	public List<ApprEmpDraftVO> getEmpDraftList(String empId) {
		return mapper.getEmpDraftList(empId);
	}
	
	/* 湲곗븞�꽌 �엯�젰�븯湲� */
	@Override
	public void setDraft(ApprDraftVO draft) {
		mapper.setDraft(draft);
	}

	/* �쑕媛� �떊泥��꽌 �엯�젰�븯湲� */
	@Override
	public void setVacation(ApprVacationVO vacation) {
		
		int draftId = mapper.getDraftNo();
		vacation.setDraftId(draftId);
		mapper.setVacation(vacation);
	}

	/* 吏�異� 寃곗쓽�꽌 �엯�젰�븯湲� */
	@Override
	public void setExpence(ApprExpenceVO expence) {
		int draftId = mapper.getDraftNo();
		expence.setDraftId(draftId);
		mapper.setExpence(expence);
	}

	/* 吏�異� 寃곗쓽�꽌 �궡�뿭 �엯�젰�븯湲� */
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

	/* 寃곗옱�옄 �엯�젰�븯湲� */
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
	
	/* --------------------- �궘�젣 --------------------- */
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

	
	/* --------------------- �닔�젙 --------------------- */
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
	
	/* 寃곗옱 �긽�깭 �닔�젙 (踰꾪듉�뿉 �뵲�씪 �떎由�) */
	@Override
	public int setUpApprOption(ApprEmpDraftDetailVO empDraft) {
		return mapper.setUpApprOption(empDraft);
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
