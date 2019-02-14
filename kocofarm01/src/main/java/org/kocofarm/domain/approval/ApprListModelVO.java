package org.kocofarm.domain.approval;

import java.util.List;

import lombok.Data;

@Data
public class ApprListModelVO {

	private List<ApprFormVO> list;
	private int requestPage;
	private int totalPageCount;
	private int startPage;
	private int endPage;
	private int totalCount;
	private int pageSize;
}
