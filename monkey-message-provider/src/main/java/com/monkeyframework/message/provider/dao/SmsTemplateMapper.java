package com.monkeyframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.monkeyframework.message.entity.SmsTemplate;

@Repository
public interface SmsTemplateMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SmsTemplate record);

    int insertSelective(SmsTemplate record);

    SmsTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsTemplate record);

    int updateByPrimaryKey(SmsTemplate record);
}