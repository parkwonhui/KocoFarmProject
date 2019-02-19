package org.kocofarm.service.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprEmpDraftDetailVO;
import org.kocofarm.domain.approval.ApprEmpDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.emp.EmpVO;

public interface ApprovalService {
	/* 전체 기안서 목록 불러오기 */
	//public List<ApprDraftVO> getDraftList();
	/* 전체 양식 목록 불러오기 */
	public List<ApprFormVO> getFormList();
	/*최근 기안서 번호 가져오기 */
	public int getDraftNo();
	/* 특정 기안서 불러오기 */
	public ApprDraftVO getDraft(int draftId);
	/* 지 출 결의서 정보 가져오기 */
	public ApprExpenceVO getExpence(int draftId);
	/* 지출 결의서 내역 가져오기 */
	public List<ApprExpenceContVO> getExpenceCont(int expenceId);
	/* 휴가 신청서 정보 가져오기 */
	public ApprVacationVO getVacation(int draftId);
	
	/* 결재자 리스트 불러오기 */
	public List<EmpVO> getApprEmpList(int draftId);
	
	
	/*로그인 회원이 결재할 기안서 개수 가져오기 */
	public int getNumberOfDraft(String empId);
	/* 결재할 기안서 리스트 번호 가져오기 ----여기에 기안서 정보도 추가*/
	public List<ApprEmpDraftVO> getEmpDraftList(String empId);
	/* 결재자 정보  가져오기 */
	public ApprEmpDraftDetailVO getApprEmp(int draftId , String empId);
	
	
	/*페이징 처리한 draft 목록 가져오기 */
	public List<ApprDraftVO> getDraftList(Criteria cri);
	/*전체 기안서 개수 */
	public int getTotal(Criteria cri);
	
	
	/* 기안서 입력하기 */
	public void setDraft(ApprDraftVO draft);
	/* 휴가신청서 입력하기 */
	public void setVacation(ApprVacationVO vacation);
	/* 지출명세서 입력하기 */
	public void setExpence(ApprExpenceVO expence);
	/* 지출명세서 내역 입력 */
	public void setExpenceCont(HttpServletRequest request);
	/* 결재자 정보 입력*/
	public void setApprEmp(HttpServletRequest request);
	

	/* 기안서 삭제*/
	public boolean delDraft(int draftId);
	/* 휴가 신청서 삭제*/
	public void delVacation(int draftId);
	/* 지출명세서 삭제*/
	public void delExpence(int draftId);
	/* 지출명세서 내역 삭제*/
	public void delExpenceCont(int draftId);
	
	/* 기안서 수정 */
	public int setUpDraft(ApprDraftVO draft);
	/* 휴가 신청서 수정*/
	public int setUpVacation(ApprVacationVO vacation);
	public int setUpVacHit(int draft);
	/* 지출명세서 수정*/
	public int setUpExpence(ApprExpenceVO expence);
	/* 지출명세서 내역 수정*/
	public int setUpExpenceCont(ApprExpenceContVO expenceCont);
	/* 결재 상태 수정 (버튼에 따라 다름) */
	public int setUpApprOption(ApprEmpDraftDetailVO empDraft);
	
}
