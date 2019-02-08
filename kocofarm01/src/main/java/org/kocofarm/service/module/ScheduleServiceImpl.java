package org.kocofarm.service.module;

import java.util.List;

import org.kocofarm.domain.schedule.ScheduleProject;
import org.kocofarm.mapper.module.ScheduleMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
	private ScheduleMapper mapper;
	
	@Override
	public List<ScheduleProject> getProjectList() {
		List<ScheduleProject> list = mapper.getProjectList();
		return list;
	}
}
