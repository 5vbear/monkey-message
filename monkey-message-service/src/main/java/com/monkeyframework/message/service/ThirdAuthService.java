package com.monkeyframework.message.service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.model.page.Pagination;

import java.util.List;

import com.monkeyframework.message.entity.ThirdAuth;
import com.monkeyframework.message.model.ThirdAuthSo;

public interface ThirdAuthService {

	public ThirdAuth get(Long id);

	public Long save(ThirdAuth log) throws monkeyException;
	
	public Long update(ThirdAuth log) throws monkeyException;
	
	public Long update(Long id, ThirdAuth log) throws monkeyException;

	public Long delete(Long id) throws monkeyException;
	
	public List<ThirdAuth> findAll() throws monkeyException;
		
	//public void freshen();
	
	public Pagination findByPage(Pagination page, ThirdAuthSo so);
	
	public List<ThirdAuth> findListByType(Integer type);
		
}

