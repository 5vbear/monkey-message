package com.monkeyframework.message.provider.plus;

import com.monkeyframework.message.provider.exception.MessageException;

public interface SmsMessagePlus extends MessagePlus{
	
	public String doSend(String mobile, String content) throws MessageException;
	
}
