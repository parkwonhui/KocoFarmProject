package org.kocofarm.domain.schedule;



import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScheduleCategoryMove implements Serializable{

	private int projectId;
	private int oriCategoryId;
	private int oriCategoryX;
	private int moveCategoryId;
	private int moveCategoryX;
}
