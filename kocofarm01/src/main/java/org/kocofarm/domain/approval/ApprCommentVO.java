package org.kocofarm.domain.approval;

import lombok.Data;

@Data
public class ApprCommentVO {
				private int commentId;
				private int draftId;
				private String empId;
				private String commentContents;
				private String commentDt;
}
