package com.monkeyframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.monkeyframework.message.entity.MailLog;

@Repository
public interface MailLogMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(MailLog record);

    int insertSelective(MailLog record);

    MailLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MailLog record);

    int updateByPrimaryKey(MailLog record);
}