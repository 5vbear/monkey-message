package com.monkeyframework.message.service;

import java.util.Map;

import com.monkeyframework.exception.monkeyException;

public interface MailSendService {

	//通过模板发送
	public void send(String group, String code, String mailTo, Map<String, String> data) throws monkeyException;
	
	public void send(String group, String code, String mailTo, String ccMail, String bccMail, 
			String isReadToMail, Map<String, String> data) throws monkeyException;
	
	public void send(String group, String code, String mailSubject, String mailBody, 
			String mailTo, String ccMail, String bccMail, String isReadToMail, 
			Map<String, String> data) throws monkeyException;
	
}
