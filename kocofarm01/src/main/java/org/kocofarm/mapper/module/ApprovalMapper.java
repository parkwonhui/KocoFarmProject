package org.kocofarm.mapper.module;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprEmpDraftDetailVO;
import org.kocofarm.domain.approval.ApprEmpDraftVO;
import org.kocofarm.domain.approval.ApprEmpVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.domain.emp.EmpVO;

public interface ApprovalMapper {
	/*--------------목록 불러오기--------------*/
	/* 전체 기안서 목록 불러오기 */
	public List<ApprDraftVO> getDraftList();
	/* 전체 양식 목록 불러오기 */
	public List<ApprFormVO> getFormList();
	
	
	/*--------------입력--------------*/
	/* 기안서 입력하기 */
	public void setDraft(ApprDraftVO draft);
	/* 휴가신청서 입력하기 */
	public void setVacation(ApprVacationVO vacation);
	/* 지출명세서 입력하기 */
	public void setExpence(ApprExpenceVO expence);
	/* 지출명세서 내역 입력 */
	public void setExpenceCont(ApprExpenceContVO expenceCont);
	/* 결재자 입력*/
	public void setApprEmp(ApprEmpVO apprEmp);
	
	
	/*--------------기타 정보 가져오기 --------------*/
	/* 최근 기안서 번호 가져오기 */
	public int getDraftNo();
	/* 결재자 정보 리스트 가져오기 */
	public List<EmpVO> getApprEmpList(int draftId);
	/* 결재할 기안서 갯수 가져오기 */
	public int getNumberOfDraft(String empId);
	/* 결재할 기안서 리스트 번호 가져오기 ----여기에 기안서 정보도 추가*/
	public List<ApprEmpDraftVO> getEmpDraftList(String empId);
	/* 결재자 정보  가져오기 */
	public ApprEmpDraftDetailVO getApprEmp(@Param("draftId") int draftId, @Param("empId") String empId);
	
	/*--------------기안서 가져오기--------------*/
	/* 특정 기안서 불러오기 */
	public ApprDraftVO getDraft(int draftId);
	/* 휴가 신청서 정보 불러오기 */
	public ApprVacationVO getVacation(int draftId);
	/* 지출 결의서 정보 불러오기 */
	public ApprExpenceVO getExpence(int draftId);
	/* 지출 결의서 내역 가져오기 */
	public List<ApprExpenceContVO> getExpenceCont(int expenceId);
	
	
	/*--------------수정--------------*/
	/* 기본 기안서 정보 수정 */
	public int setUpDraft(ApprDraftVO draft);
	/* 휴가 신청서 정보 수정 */
	public int setUpVacation(ApprVacationVO vacation);
	public int setUpVacHit(int draft);
	/* 지출 결의서 정보 수정*/
	public int setUpExpence(ApprExpenceVO expence);
	/* 지출 결의서 내역 수정*/
	public int setUpExpenceCont(ApprExpenceContVO expenceCont);
	/* 결재 상태 수정 (버튼에 따라 다름) */
	public int setUpApprOption(ApprEmpDraftDetailVO empDraft);

	/*--------------삭제--------------*/
	/* 기안서 삭제*/
	public int delDraft(int draftId);
	/* 휴가 신청서 삭제*/
	public int delVacation(int draftId);
	/* 지출명세서 삭제*/
	public int delExpence(int draftId);
	/* 지출명세서 내역 삭제*/
	public int delExpenceCont(int expenceId);
	
	/*--------------페이징--------------*/
	/* 페이징 처리*/
	public List<ApprDraftVO> getListWithPaging(Criteria cri);
	/*전체 기안서 개수 */
	public int getTotalCount(Criteria cri);
}
