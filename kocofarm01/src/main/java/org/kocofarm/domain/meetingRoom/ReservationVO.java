package org.kocofarm.domain.meetingRoom;

import java.util.Date;

import lombok.Data;

@Data
public class ReservationVO {

	private int rvId;
	private String mTitle;
	private Date startDt;
	private Date endDt;
	private String dept;
	private String rvUser;
	private String rvWriter;
	private int mId;
	private Date regDt;
	private Date upDt;
}
