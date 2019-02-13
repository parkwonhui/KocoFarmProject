package org.kocofarm.domain.meetingRoom;

import java.io.Serializable;

public class ReservationVO implements Serializable {

	private int rvId;
	private String mTitle;
	private String startDt;
	private String endDt;
	private String dept;
	private String rvUser;
	private String rvWriter;
	private String regDt;
	private String upDt;
	private int mId;
	private String rvUserNm;
	private String rvWriterNm;
	private String deptNm;
	public int getRvId() {
		return rvId;
	}
	public void setRvId(int rvId) {
		this.rvId = rvId;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getRvUser() {
		return rvUser;
	}
	public void setRvUser(String rvUser) {
		this.rvUser = rvUser;
	}
	public String getRvWriter() {
		return rvWriter;
	}
	public void setRvWriter(String rvWriter) {
		this.rvWriter = rvWriter;
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
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getRvUserNm() {
		return rvUserNm;
	}
	public void setRvUserNm(String rvUserNm) {
		this.rvUserNm = rvUserNm;
	}
	public String getRvWriterNm() {
		return rvWriterNm;
	}
	public void setRvWriterNm(String rvWriterNm) {
		this.rvWriterNm = rvWriterNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	
}
