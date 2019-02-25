package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleTagVO;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService{
	ScheduleMapper mapper;

	// 태그 등록
	@Override
	public int setTag(ScheduleTagVO tagVO) {
		
		return mapper.setTag(tagVO);
	}

	// 태그 수정
	@Override
	public int setUpTag(ScheduleTagVO tagVO) {
		
		return mapper.setUpTag(tagVO);
	}

	// 태그 삭제
	@Override
	public int delTag(int tagId) {
		
		return mapper.delTag(tagId);
	}

	// 태그 목록
	@Override
	public List<ScheduleTagVO> getTagList(int calenderId) {
		
		return mapper.getTagList(calenderId);
	}

}
