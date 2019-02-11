package org.kocofarm.domain.meetingRoom;

import lombok.Data;

@Data
public class ReservationVO {

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
}
