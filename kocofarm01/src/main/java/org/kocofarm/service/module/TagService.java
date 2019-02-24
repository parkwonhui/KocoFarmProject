package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleTagVO;

public interface TagService {

	public int setTag(ScheduleTagVO tag);
	public int setUpTag(ScheduleTagVO tag);
	public int delTag(int tagId);
	public List<ScheduleTagVO> getTagList(int calenderId);
}
