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
public class MessageVO {

	int 	messageId;
	int 	messageRoomId;
	String  korNm;
	String  empImgSrc;
	String 	contents;
	Date 	dt;
	String  dateString;
	int 	fileNo;
	int 	fileSize;
	String 	fileName;
	String 	fileExtention;
	int 	type;
	String 	empId;	
}
