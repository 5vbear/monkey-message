package com.monkeyframework.message.provider.plus;

import com.monkeyframework.message.provider.exception.MessageException;

public interface MailMessagePlus extends MessagePlus {
		
	public boolean doSend(String mailTo,String mailSubject, String mailBody,String ccMail,String bccMail,String isReadToMail) throws MessageException;

}
