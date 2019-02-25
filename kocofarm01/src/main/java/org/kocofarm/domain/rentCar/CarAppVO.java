package org.kocofarm.domain.rentCar;

public class CarAppVO {

	private String appId;
	private String resId;
	private String appSF;
	private String regDt;
	private String empId;
	
	public CarAppVO(){ }

	public CarAppVO(String appId, String resId, String appSF, String regDt, String empId) {
		super();
		this.appId = appId;
		this.resId = resId;
		this.appSF = appSF;
		this.regDt = regDt;
		this.empId = empId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getAppSF() {
		return appSF;
	}

	public void setAppSF(String appSF) {
		this.appSF = appSF;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "CarAppVO [appId=" + appId + ", resId=" + resId + ", appSF=" + appSF + ", regDt=" + regDt + ", empId="
				+ empId + "]";
	}
	
	
	
}
