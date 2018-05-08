package com.monkeyframework.message.provider.service;

import java.util.List;

import com.monkeyframework.message.provider.plus.MessagePlus;

public interface MessagePlusService {

	public MessagePlus genrate(Long id);
	
	public List<MessagePlus> genrate(String ids);
	
}

