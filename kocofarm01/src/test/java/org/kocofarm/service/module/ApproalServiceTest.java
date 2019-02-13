package org.kocofarm.service.module;

import static org.junit.Assert.assertNotNull;

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
public class ApproalServiceTest {
	
	@Setter(onMethod_= {@Autowired})
	private ApprovalService service;
	/*@Test
	public void testExist(){
		log.info(service);
		assertNotNull(service);
	}*/
	

/*	@Test
	public void setDraft(){
		ApprDraftVO draft = new ApprDraftVO();
		draft.setDraftDt("2018-02-11");
		draft.setDraftName("기안");
		draft.setDraftTitle("기안서 제목");
		draft.setDraftYear(3);
		draft.setFormId(1);
		draft.setEmpId("EMP_004");
		draft.setApproveState("기안중");
		
		service.setDraft(draft);
		log.info("생성된 게시물 번호 :"+service.getDraftNo());
	}
	*/
	
/*	@Test
	public void testGetDraftList(){
		service.getDraftList().forEach(draft -> log.info(draft));
	}
	
	@Test
	public void testGetFormList(){
		service.getFormList().forEach(form ->log.info(form));
	}*/
	
	/*@Test
	public void testGetDraft(){
		service.getDraft(165);
	}*/
	/*@Test
	public void testSetUpDraft(){
		ApprDraftVO draft = service.getDraft(30);
		if(draft == null){
			return;
		}
		draft.setDraftTitle("수정");
		log.info("RESULT : " + service.setUpDraft(draft));
	}*/
	@Test
	public void testSetUpVacation(){
		ApprVacationVO vacation = service.getVacation(42);
		if(vacation == null){
			return;
		}
				vacation.setVacationType("휴가신청서");
				vacation.setVacationDays(2);
		vacation.setVacationReason("고냥");
		vacation.setReplacementId("EMP_001");
		log.info("RESULT : " + service.setUpVacation(vacation));
	}
}
