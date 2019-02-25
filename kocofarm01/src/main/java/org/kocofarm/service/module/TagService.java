package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleTagVO;

public interface TagService {

	public int setTag(ScheduleTagVO tagVO);
	public int setUpTag(ScheduleTagVO tagVO);
	public int delTag(int tagId);
	public List<ScheduleTagVO> getTagList(int calenderId);
}
