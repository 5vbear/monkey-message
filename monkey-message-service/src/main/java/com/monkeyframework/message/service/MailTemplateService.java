package com.monkeyframework.message.service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.model.page.Pagination;

import java.util.List;
import java.util.Map;

import com.monkeyframework.message.entity.MailTemplate;

public interface MailTemplateService {

	public void save(MailTemplate mailTemplate) throws monkeyException;

	public void update(MailTemplate mailTemplate) throws monkeyException;
	
	public void update(Long id, MailTemplate log) throws monkeyException;
	
	public void delete(Long id) throws monkeyException;

	public MailTemplate get(Long id);

	public MailTemplate getByGroupAndCode(String group, String code);
	
	public boolean isExistByThirdAuthId(Long thirdAuthId);
	
	public boolean isExistByGroupAndCode(String group, String code);
	
	public String buildContent(String group, String code, Map<String, String> data);
	
	public String buildContent(MailTemplate mailTemplate, Map<String, String> data);
	
	public Pagination findByPage(Pagination page, String keyword);

	public void deletes(List<Long> idList) throws monkeyException;

}
