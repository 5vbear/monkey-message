package com.monkeyframework.message.provider.service;

import com.monkeyframework.message.entity.SmsLog;
import com.monkeyframework.exception.monkeyException;


public interface SmsLogService {

	public SmsLog get(Long id);

	/**
	 * 新添加
	 * 
	 * @param role
	 * @return
	 */
	public void save(SmsLog log) throws monkeyException;
	
	/**
	 * 修改
	 * 
	 * @param role
	 * @return
	 */
	public void update(SmsLog log) throws monkeyException;

	/**
	 * 删除短信记录
	 * 
	 * @param role
	 * @throws ServiceException
	 */
	public void delete(Long id) throws monkeyException;

}

