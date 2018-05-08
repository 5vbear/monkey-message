package com.monkeyframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.monkeyframework.message.entity.SmsLog;

@Repository
public interface SmsLogMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SmsLog record);

    int insertSelective(SmsLog record);

    SmsLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsLog record);

    int updateByPrimaryKey(SmsLog record);
}