package org.kocofarm.service.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
import org.kocofarm.domain.approval.ApprFormVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
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

/*	 기안서 목록 불러오기 
	@Override
	public List<ApprDraftVO> getDraftList() {
		return mapper.getDraftList();
	}
*/
	/* 페이징 처리한 기안서 목록 불러오기 */
	@Override
	public List<ApprDraftVO> getDraftList(Criteria cri) {
		
		return mapper.getListWithPaging(cri);
	}
	/* 전체 기안서 개수 가져오기 */
	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	
	/* 양식 목록 불러오기 */
	@Override
	public List<ApprFormVO> getFormList() {
		return mapper.getFormList();
	}

	/* 최근 draft_No 불러오기 */
	@Override
	public int getDraftNo() {
		return mapper.getDraftNo();
	}

	/* 기안서 불러오기 */
	@Override
	public ApprDraftVO getDraft(int draftId) {
		return mapper.getDraft(draftId);
	}

	/* 지출 명세서 불러오기 */
	@Override
	public ApprExpenceVO getExpence(int draftId) {
		return mapper.getExpence(draftId);
	}

	/* 지출 명세서 내역 불러오기 */
	@Override
	public List<ApprExpenceContVO> getExpenceCont(int expenceId) {
		return mapper.getExpenceCont(expenceId);
	}

	/* 휴가 신청서 불러오기 */
	@Override
	public ApprVacationVO getVacation(int draftId) {
		return mapper.getVacation(draftId);
	}

	/* 기안서 입력하기 */
	@Override
	public void setDraft(ApprDraftVO draft) {
		mapper.setDraft(draft);
	}

	/* 휴가 신청서 입력하기 */
	@Override
	public void setVacation(ApprVacationVO vacation) {
		int draftId = mapper.getDraftNo();
		vacation.setDraftId(draftId);
		mapper.setVacation(vacation);
	}

	/* 지출 결의서 입력하기 */
	@Override
	public void setExpence(ApprExpenceVO expence) {
		int draftId = mapper.getDraftNo();
		expence.setDraftId(draftId);
		mapper.setExpence(expence);
	}

	/* 지출 결의서 내역 입력하기 */
	@Override
	public void setExpenceCont(HttpServletRequest request) {
		ApprExpenceContVO expenceCont = new ApprExpenceContVO();
		String op = "yes";
		int sum = 0;
		int draftId = mapper.getDraftNo();
		int expenceId = mapper.getExpence(draftId).getExpenceId();
		
		String[] arr = request.getParameterValues("expence");
		for (int i = 0; i < arr.length; i += 4) {
			expenceCont.setExpenceId(expenceId);
			expenceCont.setContSeq(Integer.parseInt(arr[i]));
			expenceCont.setCustomerName(arr[i + 1]);
			expenceCont.setExpencePrice(arr[i + 2]);
			expenceCont.setCommissionPrice(arr[i + 3]);
			op = request.getParameter("commissionType" + (i / 4 + 1));
			expenceCont.setCommissionOption(op);
			sum += Integer.parseInt(arr[i + 2]);
			if (op.equals("yes")) {
				sum += Integer.parseInt(arr[i + 3]);
			}
			mapper.setExpenceCont(expenceCont);
		}
	}

	@Override
	public boolean delDraft(int draftId) {
		log.info("draft여기 탄다");
		return mapper.delDraft(draftId) == 1 ;
		
	}

	@Override
	public void delVacation(int draftId) {
		log.info(draftId+"vac여기도 탑니다.");
		mapper.delVacation(draftId);
	}

	@Override
	public void delExpence(int draftId) {
		log.info(draftId+"exp여기도 탑니다.2");
		mapper.delExpence(draftId);
	}

	@Override
	public void delExpenceCont(int expenceId) {
		log.info(expenceId+"expc여기도 탑니다3.");
		mapper.delExpenceCont(expenceId);
	}

	

	/*@Override
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
	}*/

}
