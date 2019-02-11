package org.kocofarm.mapper.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ApprovalMapperTest {
	@Setter(onMethod_ = @Autowired)
	private ApprovalMapper mapper;
	
/*	@Test
	public void testGetDraftList(){
		mapper.getDraftList().forEach(draft -> log.info(draft));
	}
	
	@Test
	public void testGetFormList(){
		mapper.getFormList().forEach(form -> log.info(form));
	}
	*/
/*	@Test
	public void testDetailDraft(){
		mapper.getDraft(165);
	}*/
	@Test
	/*public void testSetDraft(){
		ApprDraftVO draft = new ApprDraftVO();
		draft.setDraftDt("2018-02-11");
		draft.setDraftName("¿¹½Ã");
		draft.setDraftTitle("¿¹½ÃÀÔ´Ï´Ù");
		draft.setDraftYear(3);
		draft.setFormId(1);
		draft.setEmpId("EMP_004");
		draft.setApproveState("¾Æ¸ô¶û");
		
		mapper.setDraft(draft);
		log.info(draft);
	}*/
	public void testSetInsertVacation(){
		ApprVacationVO vacation = new ApprVacationVO();
		vacation.setDraftId(30);
		vacation.setFormId(4);
		vacation.setVacationType("½¬±â");
		vacation.setVacationDays(1);
		vacation.setVacationReason("¾Æ¸ô¶û½¯²¨¾ß");
		vacation.setReplacementId("EMP_001");
		
		mapper.setInsertVacation(vacation);
		log.info(vacation);
	}
	
	
}
