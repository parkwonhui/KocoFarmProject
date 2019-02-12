package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
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
	/*���� ���� �Է�*/
	public void setInsertExpence(ApprExpenceVO expence);
	/*���� ��� �Է�*/
	public void setInsertExpenceCont(ApprExpenceContVO expenceCont);
	/*분류된 기안서 출력*/ 
	List<ApprDraftVO> getListStateDraft();
	/*기안서 상세 입력 폼 이동*/
	ApprFormVO getDetailForm(int formId);
	/*emp정보 불러오기*/
	public void getDraftId();
	/*지출결의서 번호 불러오기*/
	public void getExpenceId();
	/*휴가 신청서 불러오기*/
	ApprVacationVO getVacation(int draftId);
	/*지출 결의서 불러오기 */
	ApprExpenceVO getExpence(int draftId);
	/*지출 내용 정보 불러오기*/
	List<ApprExpenceContVO> getExpenceCont(int expenceId);
	/*기본 기안서 정보 수정*/
	public int updateDraft(ApprDraftVO draft);
	/*휴가 신청서 정보 수정*/
	public int updateVacation(ApprVacationVO vacation);
	/*기본 기안서 정보 삭제*/
	public int deleteDraft(int draftId);
	/*휴가 신청서 삭제*/
	public int deleteVacation(int draftId);
	/*지출 결의서 삭제*/
	public int deleteExpence(int draftId);
}
