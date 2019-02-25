package org.kocofarm.service.module;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprVacationVO;
import org.kocofarm.domain.comm.Criteria;
import org.kocofarm.mapper.module.ApprovalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j*/
/*public class ApproalServiceTest {
	
	@Setter(onMethod_= {@Autowired})
	private ApprovalService service;
	@Test
	public void testExist(){
		log.info(service);
		assertNotNull(service);
	}
	

	@Test
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
	
	@Test
	public void testGetDraftList(){
		service.getDraftList(new Criteria(1,5)).forEach(draft -> log.info(draft));
	}
	
	
	@Test
	public void testGetFormList(){
		service.getFormList().forEach(form ->log.info(form));
	}
	@Test
	public void testGetDraft(){
		service.getDraft(165);
	
	}
	
	@Test
	public void setDraft(){
		ApprDraftVO draft = new ApprDraftVO();
		ApprVacationVO vacation = new ApprVacationVO();
		draft.setDraftDt("2018-02-11");
		draft.setDraftName("기안");
		draft.setDraftTitle("기안서 제목");
		draft.setDraftYear(3);
		draft.setFormId(1);
		draft.setEmpId("EMP_004");
		draft.setApproveState("기안중");
		service.setDraft(draft);

		int draftId = service.getDraftNo();
		vacation.setDraftId(draftId);
		vacation.setFormId(1);
		vacation.setReplacementId("EMP_003");
		vacation.setVacationDays(3);
		vacation.setVacationEndDt("2018-02-11");
		vacation.setVacationStartDt("2018-02-08");
		vacation.setVacationReason("쉬고싶어요");
		vacation.setVacationType("연차");
		
		service.setVacation(vacation);
		log.info("생성된 게시물 번호 :"+service.getDraftNo());
	}
	
	public void testDelete(){
		log.info("remove result  : "+service.delDraft(166));
	}
}*/
