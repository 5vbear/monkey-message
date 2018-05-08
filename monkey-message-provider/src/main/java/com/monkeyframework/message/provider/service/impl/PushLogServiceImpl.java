package com.monkeyframework.message.provider.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.monkeyframework.message.entity.PushLog;
import com.monkeyframework.message.provider.dao.PushLogMapper;
import com.monkeyframework.message.provider.service.PushLogService;
import com.monkeyframework.exception.monkeyException;

@Service("pushLogService")
public class PushLogServiceImpl implements PushLogService {	
	
	@Resource
	private PushLogMapper pushLogMapper;
	
	@Override
	public void insert(PushLog pushLog) throws monkeyException {
		pushLog.setCreateTime(new Timestamp(System.currentTimeMillis()));
		pushLog.setIsDelete(false);
		pushLogMapper.insert(pushLog);
	}

	@Override
	public void update(PushLog pushLog) throws monkeyException {
		pushLog.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		pushLogMapper.updateByPrimaryKey(pushLog);
	}
	
	@Override
	public void delete(Long id) throws monkeyException {
		PushLog pushLog = this.get(id);
		pushLog.setState((short)2);
		this.update(pushLog);
	}

	@Override
	public PushLog get(Long id) {
		return pushLogMapper.selectByPrimaryKey(id);
	}
	
}