package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;

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
	
	
	/*--------------기타 정보 가져오기 --------------*/
	/* 최근 기안서 번호 가져오기 */
	public int getDraftNo();
	
	
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
	/* 지출 결의서 정보 수정*/
	public int setUpExpence(ApprExpenceVO expence);
	/* 지출 결의서 내역 수정*/
	public int setUpExpenceCont(ApprExpenceContVO expenceCont);
	
	
	
	/*--------------삭제--------------*/
	/* 기안서 삭제*/
	public int delDraft(int draftId);
	/* 휴가 신청서 삭제*/
	public int delVacation(int draftId);
	/* 지출명세서 삭제*/
	public int delExpence(int draftId);
	/* 지출명세서 내역 삭제*/
	public int delExpenceCont(int expenceId);
	
}
