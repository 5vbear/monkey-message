package com.monkeyframework.message.service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.model.page.Pagination;

import java.util.List;
import java.util.Map;

import com.monkeyframework.message.entity.PushTemplate;

public interface PushTemplateService {

	public void save(PushTemplate pushTemplate) throws monkeyException;

	public void update(PushTemplate pushTemplate) throws monkeyException;
	
	public void update(Long id, PushTemplate log) throws monkeyException;
	
	public void delete(Long id) throws monkeyException;

	public PushTemplate get(Long id);

	public PushTemplate getByGroupAndCode(String group, String code);
	
	public boolean isExistByThirdAuthId(Long thirdAuthId);
	
	public boolean isExistByGroupAndCode(String group, String code);
	
	public String buildContent(String group, String code, Map<String, String> data);
	
	public String buildContent(PushTemplate pushTemplate, Map<String, String> data);
	
	public Pagination findByPage(Pagination page, String keyword);

	public void deletes(List<Long> idList) throws monkeyException;

}
