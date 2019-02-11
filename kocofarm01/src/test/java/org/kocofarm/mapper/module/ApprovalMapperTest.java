package org.kocofarm.mapper.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.approval.ApprDraftVO;
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
	public void testSetDraft(){
		ApprDraftVO draft = new ApprDraftVO();
		draft.setDraftDt("2018-02-11");
		draft.setDraftName("기안");
		draft.setDraftTitle("기안서 제목");
		draft.setDraftYear(3);
		draft.setFormId(1);
		draft.setEmpId("EMP_004");
		draft.setApproveState("기안중");
		
		mapper.setDraft(draft);
		log.info(draft);
	}
}
