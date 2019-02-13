package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.approval.EmpCategoryVO;
import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.emp.EmpVO;

public interface EmpMapper {
	
	/* 목록 */
	List<EmpVO> getEmpList();
	
	/* 상세정보 */
	EmpVO getEmp(String empId);
	
	/* 부서 목록 */
	List<DepartmentsVO> getDeptList();
	

}
