package org.kocofarm.mapper.module;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kocofarm.domain.approval.ApprDraftVO;
import org.kocofarm.domain.approval.ApprExpenceContVO;
import org.kocofarm.domain.approval.ApprExpenceVO;
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
		draft.setDraftName("����");
		draft.setDraftTitle("�����Դϴ�");
		draft.setDraftYear(3);
		draft.setFormId(1);
		draft.setEmpId("EMP_004");
		draft.setApproveState("�Ƹ���");
		
		mapper.setDraft(draft);
		log.info(draft);
	}*/
	/*public void testSetInsertVacation(){
		ApprVacationVO vacation = new ApprVacationVO();
		vacation.setDraftId(30);
		vacation.setFormId(4);
		vacation.setVacationType("����");
		vacation.setVacationDays(1);
		vacation.setVacationReason("�Ƹ���������");
		vacation.setReplacementId("EMP_001");
		
		mapper.setInsertVacation(vacation);
		log.info(vacation);
	}*/
	
	/*public void testSetInsertExpence(){
		ApprExpenceVO expence = new ApprExpenceVO();
		expence.setDraftId(30);
		expence.setFormId(4);
		expence.setExpenceType("����");
		expence.setSumPrice("20000");
		expence.setContId(1);
		
		mapper.setInsertExpence(expence);
		log.info(expence);
	}*/
	
	/*public void testSetInsertExpenceCont(){
		ApprExpenceContVO expenceCont = new ApprExpenceContVO();
		expenceCont.setExpenceId(1);
		expenceCont.setContSeq(2);
		expenceCont.setCustomerName("kosta");
		expenceCont.setExpencePrice("2000");
		expenceCont.setCommissionOption("Option");
		expenceCont.setCommissionPrice("200");
		
		mapper.setInsertExpenceCont(expenceCont);
		log.info(expenceCont);
	}*/
	
	/*public void testUpdateDraft(){
		ApprDraftVO draft = new ApprDraftVO();
		draft.setDraftId(30);
		draft.setDraftTitle("업데이트");
		draft.setDraftYear(2);
		
		int count = mapper.updateDraft(draft);
		log.info("UPDATE COUNT : " + count);
	}*/
	
	/*public void testUpdateVacation(){
				ApprVacationVO vacation = new ApprVacationVO();
				vacation.setVacationType("연차");
				vacation.setVacationId(4);
				vacation.setVacationDays(7);
				vacation.setReplacementId("EMP_001");
				
				int count = mapper.updateVacation(vacation);
						log.info("UPDATE COUNT : " + count);
	}*/
	
	/*public void testDeleteDraft(){
		log.info("DELETE COUNT : " + mapper.deleteDraft(0));
	}*/
	/*public void testDeleteVacation(){
		log.info("DELETE COUNT : " + mapper.deleteVacation(30));
	}*/
	/*public void testDeleteExpence(){
		log.info("DELETE COUNT : " + mapper.delExpence(30));
	}*/
	public void testUpExpence(){
		ApprExpenceVO expence = new ApprExpenceVO();
		expence.setExpenceType("kosta");
		expence.setSumPrice("20000");
		
		mapper.setUpExpence(expence);
		log.info(expence);
	}
}
