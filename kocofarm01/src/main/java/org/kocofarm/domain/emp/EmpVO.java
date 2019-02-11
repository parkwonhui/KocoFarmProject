package org.kocofarm.domain.emp;

import lombok.Data;

@Data
public class EmpVO {
	private String empId;
	private String korNm;
	private String engNm;
	private String email;
	private String cellPhone;
	private String hireDt;
	private String retirementDt;
	private String state;
	private String stateNm;
	private int annual;
	private String deptId;
	private String jobId;
	private String positionId;
	private String deptNm;
	private String jobNm;
	private String positionNm;
	private String managerId;
	private String managerNm;
	private int authority;
	private int salary;
	private String regDt;
	private String upDt;
	private String empImg;
	private String fileNm;
	private transient String pw;
	private String startDt;
	private String endDt;
	private String salt;

}
