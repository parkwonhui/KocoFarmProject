package org.kocofarm.domain.meetingRoom;

import java.util.Date;

import lombok.Data;

@Data
public class MeetingRoomVO {
	private int mId;
	private String mName;
	private int pNum;
	private Date regDt;
	private Date upDt;
}
