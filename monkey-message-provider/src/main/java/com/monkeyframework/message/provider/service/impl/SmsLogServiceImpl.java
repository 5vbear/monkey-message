package com.monkeyframework.message.provider.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.message.entity.SmsLog;
import com.monkeyframework.message.provider.dao.SmsLogMapper;
import com.monkeyframework.message.provider.service.SmsLogService;

/**
 * @author xusm
 * 
 */
@Service("smsLogService")
public class SmsLogServiceImpl implements SmsLogService {

	@Resource
	private SmsLogMapper smsLogMapper;


	public SmsLog get(Long id) {
		return smsLogMapper.selectByPrimaryKey(id);
	}

	/**
	 * 新添加
	 * 
	 * @param role
	 * @return
	 */
	public void save(SmsLog log) throws monkeyException {
		log.setCreateTime(new Date());
		log.setIsDelete(false);
		smsLogMapper.insert(log);
	}
	
	/**
	 * 修改
	 * 
	 * @param role
	 * @return
	 */
	public void update(SmsLog log) throws monkeyException {
		log.setUpdateTime(new Date());
		smsLogMapper.updateByPrimaryKey(log);
	}

	/**
	 * 删除短信记录
	 * 
	 * @param role
	 * @throws monkeyException
	 */
	public void delete(Long id) throws monkeyException {
		SmsLog log = get(id);
		log.setState((short)3);
		smsLogMapper.updateByPrimaryKey(log);
	}
	
}
