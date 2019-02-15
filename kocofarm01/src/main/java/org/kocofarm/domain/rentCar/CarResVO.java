package org.kocofarm.domain.rentCar;

public class CarResVO {
	
	private String resId;
	private String resWriter;
	private String resUser;
	private String purpose;
	private String stAddr;
	private String deAddr;
	private String regDt;
	private String upDt;
	private String stTime;
	private String enTime;
	private String stDate;
	private String enDate;	
	private String carId;
	
	public CarResVO(){ }

	public CarResVO(String resId, String resWriter, String resUser, String purpose, String stAddr, String deAddr,
			String regDt, String upDt, String stTime, String enTime, String stDate, String enDate, String carId) {
		super();
		this.resId = resId;
		this.resWriter = resWriter;
		this.resUser = resUser;
		this.purpose = purpose;
		this.stAddr = stAddr;
		this.deAddr = deAddr;
		this.regDt = regDt;
		this.upDt = upDt;
		this.stTime = stTime;
		this.enTime = enTime;
		this.stDate = stDate;
		this.enDate = enDate;
		this.carId = carId;
	}

	public String getResId() {
		return resId;
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResWriter() {
		return resWriter;
	}

	public void setResWriter(String resWriter) {
		this.resWriter = resWriter;
	}

	public String getResUser() {
		return resUser;
	}

	public void setResUser(String resUser) {
		this.resUser = resUser;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getStAddr() {
		return stAddr;
	}

	public void setStAddr(String stAddr) {
		this.stAddr = stAddr;
	}

	public String getDeAddr() {
		return deAddr;
	}

	public void setDeAddr(String deAddr) {
		this.deAddr = deAddr;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUpDt() {
		return upDt;
	}

	public void setUpDt(String upDt) {
		this.upDt = upDt;
	}

	public String getStTime() {
		return stTime;
	}

	public void setStTime(String stTime) {
		this.stTime = stTime;
	}

	public String getEnTime() {
		return enTime;
	}

	public void setEnTime(String enTime) {
		this.enTime = enTime;
	}

	public String getStDate() {
		return stDate;
	}

	public void setStDate(String stDate) {
		this.stDate = stDate;
	}

	public String getEnDate() {
		return enDate;
	}

	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}
	
	
	
	
}
