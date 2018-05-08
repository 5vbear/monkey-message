package com.monkeyframework.message.provider.service;

import com.monkeyframework.message.entity.MailLog;
import com.monkeyframework.exception.monkeyException;

public interface MailLogService {

	public void insert(MailLog mailLog) throws monkeyException;

	public void update(MailLog mailLog) throws monkeyException;

	public MailLog get(Long id);
		
	public void delete(Long id) throws monkeyException;
	
}
