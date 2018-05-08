package com.monkeyframework.message.service;

import java.util.Map;

import com.monkeyframework.exception.monkeyException;

public interface SmsSendService {

	//通过模板发送
	public void send(String group, String code, String mobile, Map<String, String> data) throws monkeyException;
	
	//直接发送
	public void send(String group, String code, String mobile,  String content, Map<String, String> data) throws monkeyException;
	
}
