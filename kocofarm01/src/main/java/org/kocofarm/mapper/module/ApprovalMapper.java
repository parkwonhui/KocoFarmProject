package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprFormVO;

public interface ApprovalMapper {

	/*전체 기안서 목록 불러오기*/
	public List<ApprDraftVO> getDraftList();
	/*전체 양식 목록 불러오기 */
	public List<ApprFormVO> getFormList();
	/*최근 기안서 번호 가져오기*/
	public int getDraftNo();
	/* 특정 기안서 불러오기 */
	public ApprDraftVO getDraft(int draftId);
	/*기안서 입력하기 */
	public void setDraft(ApprDraftVO draft);
	
}
