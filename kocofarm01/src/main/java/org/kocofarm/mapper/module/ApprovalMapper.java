package org.kocofarm.mapper.module;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprVacationVO;

public interface ApprovalMapper {

	//��ȼ��Է�
	public void InsertDraft(ApprDraftVO draft);
	//�ް������Է�
	public void InsertVacation(ApprVacationVO vacation);
	//���⳻���Է�
	public void InsertExpence(ApprExpenceVO expence);
	//�������Է�
	public void InsertExpenceCont(ApprExpenceContVO expenceCont);
	//������
	
	

//	/*��� ���*/
////	List<ApprovalForm> listForm(ApprovalFormSearch search);
//	List<ApprovalForm> listForm();
//	/*��ü ��ȼ� ���*/ 
//	List<ApprovalDraft> listDraft();
//	/*�з��� ��ȼ� ���*/ 
//	List<ApprovalDraft> listStateDraft();
//	/*��ȼ� �� �Է� �� �̵�*/
//	ApprovalForm getDetailForm(int formId);
//	/*emp���� �ҷ�����*/
//	int getDraftId();
//	/*������Ǽ� ��ȣ �ҷ�����*/
//	int getExpenceId();
//	/*�⺻ ��ȼ� ���� �ҷ�����*/
//	ApprovalDraft getDraft(int draftId);
//	/*�ް� ��û�� �ҷ�����*/
//	ApprovalVacation getVacation(int draftId);
//	/*���� ���Ǽ� �ҷ����� */
//	ApprovalExpence getExpence(int draftId);
//	/*���� ���� ���� �ҷ�����*/
//	List<ApprovalExpenceCont> getExpenceCont(int expenceId);
//	/*�⺻ ��ȼ� ���� ����*/
//	int updateDraft(ApprovalDraft draft);
//	/*�ް� ��û�� ���� ����*/
//	int updateVacation(ApprovalVacation vacation);
//	/*�⺻ ��ȼ� ���� ����*/
//	int deleteDraft(int draftId);
//	/*�ް� ��û�� ����*/
//	int deleteVacation(int draftId);
//	/*���� ���Ǽ� ����*/
//	int deleteExpence(int draftId);
//	/*��� ���*/
//	int insertComment(DraftComment comment);
//	/*��� ���*/
//	List<DraftComment> listComment(int draftId);
//	
//	List<DraftComment> getDraftCommentList(Map<String, Object> map);
//	
//	int getCommentCount(Map<String, Object> map);

}
