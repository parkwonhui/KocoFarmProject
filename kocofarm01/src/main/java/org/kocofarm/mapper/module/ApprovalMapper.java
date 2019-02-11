package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprFormVO;

public interface ApprovalMapper {

	/*? „ì²? ê¸°ì•ˆ?„œ ëª©ë¡ ë¶ˆëŸ¬?˜¤ê¸?*/
	public List<ApprDraftVO> getDraftList();
	/*? „ì²? ?–‘?‹ ëª©ë¡ ë¶ˆëŸ¬?˜¤ê¸? */
	public List<ApprFormVO> getFormList();
	/*ìµœê·¼ ê¸°ì•ˆ?„œ ë²ˆí˜¸ ê°?? ¸?˜¤ê¸?*/
	public int getDraftNo();
	/* ?Š¹? • ê¸°ì•ˆ?„œ ë¶ˆëŸ¬?˜¤ê¸? */
	public ApprDraftVO getDraft(int draftId);
	/*ê¸°ì•ˆ?„œ ?…? ¥?•˜ê¸? */
	public void setDraft(ApprDraftVO draft);
	
}
