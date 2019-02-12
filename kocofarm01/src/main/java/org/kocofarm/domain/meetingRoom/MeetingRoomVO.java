package org.kocofarm.domain.meetingRoom;

import java.util.Date;

import lombok.Data;

//@Data
public class MeetingRoomVO {
	private int mId;
	private String mName;
	private int pNum;
	private Date regDt;
	private Date upDt;
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public Date getRegDt() {
		return regDt;
	}
	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}
	public Date getUpDt() {
		return upDt;
	}
	public void setUpDt(Date upDt) {
		this.upDt = upDt;
	}
	
	
}
