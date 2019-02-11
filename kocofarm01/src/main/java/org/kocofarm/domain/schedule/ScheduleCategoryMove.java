package org.kocofarm.domain.schedule;



import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleCategoryMove implements Serializable{

	private int projectId;
	private int oriCategoryId;
	private int oriCategoryX;
	private int moveCategoryId;
	private int moveCategoryX;
}
