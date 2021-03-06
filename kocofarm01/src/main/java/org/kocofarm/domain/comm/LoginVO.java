package org.kocofarm.domain.comm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginVO {
	private String empId;
	private transient String pw;
	private transient String salt;
	private String korNm;
	private String engNm;
	private String email;
	private String cellPhone;
	private String hireDt;
	private String retirementDt;
	private String state;
	private int annual;
	private String deptId;
	private String jobId;
	private String positionId;
	private String managerId;
	private int authority;
	private int salary;
	private String regDt;
	private String upDt;
	private String emgImg;
	private int re;

}
