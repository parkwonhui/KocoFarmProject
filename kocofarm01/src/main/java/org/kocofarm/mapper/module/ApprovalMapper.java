package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;

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
	/*휴가입력하기*/
	public void setInsertVacation(ApprVacationVO vacation);
	/*지출 입력하기*/
	public void setInsertExpence(ApprExpenceVO expence);
	/**/
	public void setInsertExpenceCont(ApprExpenceContVO expenceCont);
	/*분류된 기안서 출력*/ 
	List<ApprDraftVO> getListStateDraft();
	/*기안서 상세 입력 폼 이동*/
	ApprFormVO getDetailForm(int formId);
	/*emp정보 불러오기*/
	public void getDraftId();
	/*지출결의서 번호 불러오기*/
	public void getExpenceId();
	/*휴가 신청서 정보 가져오기*/
	public ApprVacationVO getVacation(int draftId);
	/*지출 결의서 정보 가져오기*/
	public ApprExpenceVO getExpence(int draftId);
	/*지출 결의서 내역 가져오기*/
	public List<ApprExpenceContVO> getExpenceCont(int expenceId);
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
