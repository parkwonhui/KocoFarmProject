package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.mapper.module.ApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

	private ApprovalMapper mapper;
	
	@Override
	public List<ApprDraftVO> getDraftList() {
		return mapper.getDraftList();
	}

	@Override
	public List<ApprFormVO> getFormList() {
		return mapper.getFormList();
	}

	@Override
	public int getDraftNo() {
		return mapper.getDraftNo();
	}

	@Override
	public ApprDraftVO getDraft(int draftId) {
		return mapper.getDraft(draftId);
	}

	@Override
	public void setDraft(ApprDraftVO draft) {
		mapper.setDraft(draft);
	}

	@Override
	public ApprExpenceVO getExpence(int draftId) {
		return mapper.getExpence(draftId);
	}

	@Override
	public List<ApprExpenceContVO> getExpenceCont(int expenceId) {
		return mapper.getExpenceCont(expenceId);
	}

	@Override
	public ApprVacationVO getVacation(int draftId) {
		return mapper.getVacation(draftId);
	}

	@Override
	public boolean setUpDraft(ApprDraftVO draft) {
		return mapper.setUpDraft(draft) == 1;
	}
	@Override
	public boolean setUpVacation(ApprVacationVO vacation){
		return mapper.setUpVacation(vacation)==1;
	}

	@Override
	public int setUpExpence(ApprExpenceVO expence) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setUpExpenceCont(ApprExpenceContVO expenceCont) {
		// TODO Auto-generated method stub
		return 0;
	}

}
