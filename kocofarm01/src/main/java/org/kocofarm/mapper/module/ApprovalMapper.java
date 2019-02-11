package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprFormVO;

public interface ApprovalMapper {

	/*?���? 기안?�� 목록 불러?���?*/
	public List<ApprDraftVO> getDraftList();
	/*?���? ?��?�� 목록 불러?���? */
	public List<ApprFormVO> getFormList();
	/*최근 기안?�� 번호 �??��?���?*/
	public int getDraftNo();
	/* ?��?�� 기안?�� 불러?���? */
	public ApprDraftVO getDraft(int draftId);
	/*기안?�� ?��?��?���? */
	public void setDraft(ApprDraftVO draft);
	
}
