package org.kocofarm.mapper.module;

import java.util.List;

import org.kocofarm.domain.emp.DepartmentsVO;
import org.kocofarm.domain.emp.EmpVO;

public interface EmpMapper {

	/* 한국말 */
	List<EmpVO> getEmpList();

	/* 깨진다 */
	EmpVO getEmp(String empId);

	/* 왜그럴까 */
	List<DepartmentsVO> getDeptList();

}
