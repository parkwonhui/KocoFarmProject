package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.emp.EmpVO;
import org.kocofarm.mapper.module.EmpMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmpServiceImpl implements EmpService{
	private EmpMapper empMapper;
	
	// 목록
	@Override
	public List<EmpVO> getEmpList() {
		
		return empMapper.getEmpList();
	}

	// 상세정보
	@Override
	public EmpVO getEmp(String empId) {
		
		return empMapper.getEmp(empId);
	}

	// 부서 목록
	@Override
	public List<DepartmentsVO> getDeptList() {
		
		return empMapper.getDeptList();
	}

}
