package com.monkeyframework.message.provider.dao;

import org.springframework.stereotype.Repository;

import com.monkeyframework.message.entity.PushTemplate;

@Repository
public interface PushTemplateMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(PushTemplate record);

    int insertSelective(PushTemplate record);

    PushTemplate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PushTemplate record);

    int updateByPrimaryKey(PushTemplate record);
}