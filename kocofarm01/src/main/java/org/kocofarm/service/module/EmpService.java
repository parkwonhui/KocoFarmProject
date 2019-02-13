package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.emp.EmpVO;

public interface EmpService {
	// 목록
	public List<EmpVO> getEmpList();
	
	// 상세정보
	public EmpVO getEmp(String empId);
	
	// 부서 목록
	public List<DepartmentsVO> getDeptList();
	
}
