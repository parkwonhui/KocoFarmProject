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
public class TestApprovalMapper {
		
	@Setter(onMethod_=@Autowired)
	private ApprovalMapper mapper;
	
	@Test
//	public void testGetList(){
//		mapper.listDraft().forEach(draft -> log.info(draft));
//	}
//	public void testListForm(){
//		mapper.listForm().forEach(form -> log.info(form));
//	}
//	public void testInsertDraft(){
//		ApprDraftVO draft = new ApprDraftVO();
//		draft.setDraftName("¿¹½Ã");
//		draft.setDraftTitle("¿¹½ÃÀÔ´Ï´Ù");
//		draft.setEmpId("EMP_001");
//		draft.setDraftYear(3);
//		draft.setFormId(4);
//		draft.setApproveState("°áÁ¦Áß");
//		
//		mapper.insertDraft(draft);
//		log.info(draft);
//	}
	public void testInsertVacation(){
		ApprVacationVO vacation = new ApprVacationVO();
		vacation.setDraftId(42);
		vacation.setFormId(4);
		vacation.setVacationType("¾Æ¸ô¶û½¯·©");
		vacation.setVacationDays(1);
		vacation.setVacationReason("¾Æ¸ô¶û");
		vacation.setReplacementId("EMP_001");
		
		mapper.insertVacation(vacation);
		log.info(vacation);
	}
}
