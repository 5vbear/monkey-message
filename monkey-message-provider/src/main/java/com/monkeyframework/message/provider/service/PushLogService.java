package com.monkeyframework.message.provider.service;

import com.monkeyframework.message.entity.PushLog;
import com.monkeyframework.exception.monkeyException;

public interface PushLogService {

	public void insert(PushLog pushLog) throws monkeyException;

	public void update(PushLog pushLog) throws monkeyException;

	public PushLog get(Long id);
		
	public void delete(Long id) throws monkeyException;
	
}
