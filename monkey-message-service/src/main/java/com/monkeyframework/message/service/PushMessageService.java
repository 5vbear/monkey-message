package com.monkeyframework.message.service;

import com.monkeyframework.exception.monkeyException;
import com.monkeyframework.message.model.PushMessage;

public interface PushMessageService {

	public long push(PushMessage message) throws monkeyException;	

}
