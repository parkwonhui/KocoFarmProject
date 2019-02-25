package org.kocofarm.service.module;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kocofarm.domain.schedule.ScheduleCalenderVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.domain.schedule.ScheduleCalenderListVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMemberMiniVO;
import org.kocofarm.domain.schedule.ScheduleCalenderMoveVO;
import org.kocofarm.domain.schedule.ScheduleCategoryVO;
import org.kocofarm.domain.schedule.ScheduleMemberVO;
import org.kocofarm.domain.schedule.ScheduleProjectSearchVO;
import org.kocofarm.domain.schedule.ScheduleCategoryMoveVO;
import org.kocofarm.domain.schedule.ScheduleProjectVO;
import org.kocofarm.domain.schedule.ScheduleTagVO;
import org.kocofarm.mapper.module.EmpMapper;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.sf.json.JSONArray;

@Log4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	
	private ScheduleMapper mapper;
	private EmpMapper empMapper;
	
	
	@Override
	public JSONArray getProjectJsonArray(ScheduleProjectSearchVO project, String empId){
		JSONArray jsonArr = new JSONArray();
		
		List<ScheduleProjectVO> projectList = mapper.getProjectList(project);
		List<ScheduleProjectVO> managerProjectList = mapper.getManagerProjectList(empId);
		
		// managerProjectList에서 projectList의 중복되는 값을 제거한 최종 list
		List<ScheduleProjectVO> addList = new ArrayList<ScheduleProjectVO>(); 		
		// projectList와 중복되는 프로젝트 제외
		for(ScheduleProjectVO managerListVO : managerProjectList){
			if(true == searchProject(projectList, managerListVO)){
				addList.add(managerListVO);
			}
		}
		
		
		projectList.addAll(addList);
				
		jsonArr = JSONArray.fromObject(projectList);
		
		return jsonArr;
	}

	public ScheduleProjectVO getSelectProject(int projectId){
		
		ScheduleProjectVO project = mapper.getSelectProject(projectId);
		return project;
	}


	@Override
	public List<ScheduleCalenderListVO> getProjectCalenderList(int projectId) {
		List<ScheduleCalenderListVO> list = mapper.getProjectCalenderList(projectId);
		
		// 작업자 정보 추가
		for(ScheduleCalenderListVO calender : list){
			int calenderId = calender.getCalenderId();
			List<ScheduleMemberVO> memberList = mapper.getMember(calenderId);
			List<EmpVO> empList = new ArrayList<EmpVO>();
			for(ScheduleMemberVO member : memberList){
				String memberEmp = member.getEmpId();
				EmpVO empVO = empMapper.getEmp(memberEmp);				
				empList.add(empVO);
			}
			calender.setMemberList(empList);
		}
		
		return list;
	}

	@Override
	public int setCalender(ScheduleCalenderVO calender) {
		
		int re = mapper.setCalender(calender);
		return re;
	}
	
	@Override
	public int setUpCalender(ScheduleCalenderVO calender){
		
		int re = mapper.setUpCalender(calender);
		return re;
	}
	
	@Transactional
	@Override
	public int delCalender(int calenderId){
		
		int re = mapper.delMemberWithCalender(calenderId);
		re = mapper.delCalender(calenderId);
		
		return re;
	}
	
	@Override
	public int setUpCalenderPos(List<ScheduleCalenderMoveVO> calenderMoveList){
		
		int re = 0;
		for(ScheduleCalenderMoveVO calenderMove : calenderMoveList){
			re = mapper.setUpCalenderPos(calenderMove);
			if(0 >= re){
				return -1;
			}
		}
		
		return re;
	}
	
	@Override
	public int setCategory(ScheduleCategoryVO category){
	
		int re = mapper.setCategory(category);
		return re;
	}
	
	@Override
	public int setUpCategory(ScheduleCategoryVO category){		
		
		int re = mapper.setUpCategory(category);
		return re;
	}
	
	@Override	
	public int setProject(ScheduleProjectVO project){
		
		int re = mapper.setProject(project);		
		return re;
	}

	@Transactional
	@Override
	public int setUpProject(ScheduleProjectVO project){
		
		int re = mapper.setUpProject(project);
		return re;
	}

	@Transactional
	@Override
	public int delCategory(ScheduleCategoryVO category){
		
		int re = mapper.delMemberWithCategory(category.getCategoryId());
		re = mapper.delCalenderWithCategory(category);
		re = mapper.delCategory(category.getCategoryId());
		
		return re;
	}
	
	@Transactional
	@Override
	public int setMoveCategory(ScheduleCategoryMoveVO category){
		
		int re = mapper.setMoveCategoryPosX(category);
		re = mapper.setOriCategoryPosX(category);
		
		return re;
	}
		
	@Transactional
	@Override
	public int delProject(int projectId){
		
		int re = mapper.delMemberWithProject(projectId);
		re = mapper.delCalenderWithProject(projectId);
		re = mapper.delCaltegoryWithProject(projectId);
		re = mapper.delProject(projectId);
		
		return re;
	}
	
	@Override
	public List<ScheduleMemberVO> getMember(int calenderId){
		return mapper.getMember(calenderId);
	}
	
	// 중복 체크
	public boolean searchProject(List<ScheduleProjectVO> list, ScheduleProjectVO project){
		for(ScheduleProjectVO vo : list){
			if(project.getProjectId() == vo.getProjectId()){
				return false;
			}
		}		
		
		return true;
	}

	
	// TAG 부분 정의
	@Override
	public int setTag(ScheduleTagVO tag) {
		int re = mapper.setTag(tag);
		return re;
	}

	@Override
	public int setUpTag(ScheduleTagVO tag) {
		int re = mapper.setUpTag(tag);
		return re;

	}

	@Override
	public int delTag(int tagId) {
		
		int re = mapper.delTag(tagId);
		return re;
	}

	@Override
	public List<ScheduleTagVO> getTagList(int calenderId) {
		
		List<ScheduleTagVO> re = mapper.getTagList(calenderId);
		
		return re;
	}
	
	@Override
	public List<ScheduleCalenderMemberMiniVO> getCalenderMember(int calenderId){
		List<ScheduleCalenderMemberMiniVO> list = mapper.getCalenderMember(calenderId);
		if(null == list){
			return null;
		}
		
		for(ScheduleCalenderMemberMiniVO vo : list){
			vo.setIsMember(1);
		}

		
		List<ScheduleCalenderMemberMiniVO> notMemberList = mapper.getCalenderNotMember(calenderId);
		if(null == notMemberList){
			return null;
		}
		
		list.addAll(notMemberList);
		
		return list;
	}

}
