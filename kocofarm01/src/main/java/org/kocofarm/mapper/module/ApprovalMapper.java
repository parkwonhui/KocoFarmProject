package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;

public interface ApprovalMapper {

	/*전체 기안서 출력*/
	public List<ApprDraftVO> getDraftList();
	/*양식 출력*/
	public List<ApprFormVO> getFormList();
	/*최근 기안서 번호 불러오기*/
	public int getDraftNo();
	/* 특정 기안서 불러오기 */
	public ApprDraftVO getDraft(int draftId);
	/*기본 기안서 정보 입력하기 */
	public void setDraft(ApprDraftVO draft);
	/*휴가 정보 입력*/
	public void setInsertVacation(ApprVacationVO vacation);
	
}
