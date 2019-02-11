package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;

public interface ApprovalMapper {

	/*��ü ��ȼ� ���*/
	public List<ApprDraftVO> getDraftList();
	/*��� ���*/
	public List<ApprFormVO> getFormList();
	/*�ֱ� ��ȼ� ��ȣ �ҷ�����*/
	public int getDraftNo();
	/* Ư�� ��ȼ� �ҷ����� */
	public ApprDraftVO getDraft(int draftId);
	/*�⺻ ��ȼ� ���� �Է��ϱ� */
	public void setDraft(ApprDraftVO draft);
	/*�ް� ���� �Է�*/
	public void setInsertVacation(ApprVacationVO vacation);
	
}
