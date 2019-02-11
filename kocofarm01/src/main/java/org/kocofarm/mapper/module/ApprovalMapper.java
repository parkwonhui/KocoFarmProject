package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;

public interface ApprovalMapper {
	/* �⺻��ȼ� �Է� */
	public void insertDraft(ApprDraftVO draft);

	public void insertDraftSelectKey(ApprDraftVO draft);

	/* �ް� ���� �Է� */
	int insertVacation(ApprVacationVO vacation);

	/* ��� ��� */
	//// List<ApprovalForm> listForm(ApprovalFormSearch search);
	List<ApprFormVO> listForm();

	// ��ü��ȼ����
	// @Select("select * from DRAFT where DRAFT_ID > 0")
	// List<ApprDraftVO> listDraft();

	// /*��ü ��ȼ� ���*/
	// List<ApprovalDraft> listDraft();
	// /*�з��� ��ȼ� ���*/
	// List<ApprovalDraft> listStateDraft();
	// /*��ȼ� �� �Է� �� �̵�*/
	// ApprovalForm getDetailForm(int formId);
	// /*emp���� �ҷ�����*/
	// int getDraftId();
	// /*������Ǽ� ��ȣ �ҷ�����*/
	// int getExpenceId();
	// /*�⺻ ��ȼ� ���� �ҷ�����*/
	// ApprovalDraft getDraft(int draftId);
	// /*�ް� ��û�� �ҷ�����*/
	// ApprovalVacation getVacation(int draftId);
	// /*���� ���Ǽ� �ҷ����� */
	// ApprovalExpence getExpence(int draftId);
	// /*���� ���� ���� �ҷ�����*/
	// List<ApprovalExpenceCont> getExpenceCont(int expenceId);
	// /*�⺻ ��ȼ� ���� ����*/
	// int updateDraft(ApprovalDraft draft);
	// /*�ް� ��û�� ���� ����*/
	// int updateVacation(ApprovalVacation vacation);
	// /*�⺻ ��ȼ� ���� ����*/
	// int deleteDraft(int draftId);
	// /*�ް� ��û�� ����*/
	// int deleteVacation(int draftId);
	// /*���� ���Ǽ� ����*/
	// int deleteExpence(int draftId);
	// /*��� ���*/
	// int insertComment(DraftComment comment);
	// /*��� ���*/
	// List<DraftComment> listComment(int draftId);
	//
	// List<DraftComment> getDraftCommentList(Map<String, Object> map);
	//
	// int getCommentCount(Map<String, Object> map);

}
