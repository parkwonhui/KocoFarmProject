package org.kocofarm.domain.message;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageRoomListVO {
	int 	messageRoomId;
	String 	roomTitle;
	String 	lastMessageEmpId;
	String 	lastMessageEmpName;
	String 	lastMessage;
	String  empImg;
	Date 	lastMessageDate;
	String 	lastMessageDateToString;

}
