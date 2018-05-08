package com.monkeyframework.message.provider.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.monkeyframework.message.entity.SmsLog;
import com.monkeyframework.message.entity.SmsTemplate;
import com.monkeyframework.message.provider.exception.MessageException;
import com.monkeyframework.message.provider.plus.MessagePlus;
import com.monkeyframework.message.provider.plus.SmsMessagePlus;
import com.monkeyframework.message.provider.service.MessagePlusService;
import com.monkeyframework.message.provider.service.SmsLogService;
import com.monkeyframework.message.service.SmsSendService;
import com.monkeyframework.message.service.SmsTemplateService;
import com.monkeyframework.exception.monkeyException;

@Service("smsSendService")
public class SmsSendServiceImpl implements SmsSendService {

	private static final Log logger = LogFactory.getLog(SmsSendServiceImpl.class);
	
	@Resource
	private SmsLogService msmsLogService;
	
	@Resource
	private SmsTemplateService smsTemplateService;
	
	@Resource
	private MessagePlusService messagePlusService;

	@Async
	public void send(String group, String code, String mobile, String content, 
			Map<String, String> data) throws monkeyException {
		
		SmsTemplate template = smsTemplateService.getByGroupAndCode(group, code);
		SmsLog log = new SmsLog();
		//long id = idWorker.nextId();
		//log.setId(id);
		log.setTmpCode(code);
		log.setTmpGroup(group);
		String dataJson = "";
		if(null != data) {
			dataJson = JSON.toJSONString(data);
		}
		log.setData(dataJson);
		log.setMobile(mobile);
		log.setState((short)0);
		
		log.setCountFail(0);
		log.setCountOk(0);
		try {
			if(null != template && null == content) {
				content = smsTemplateService.buildContent(template, data);
				log.setContent(content);
			}
			msmsLogService.save(log);
		} catch (Exception e) {
			logger.error("存储SMS日志失败: " + e.getMessage());
		}
		
		// 发送的代码
		String thirdAuthId = template.getThirdAuthId();
		
		if (null != thirdAuthId) {
			int countOk = 0;
			int countFail = 0;
			String msgReturn = null;
			String msgId = null;

			List<MessagePlus> messagePlusList = messagePlusService.genrate(thirdAuthId);
			for (MessagePlus messagePlus : messagePlusList) {
				try {
					SmsMessagePlus plus = (SmsMessagePlus) messagePlus;
					msgId = plus.doSend(log.getMobile(), log.getContent());
					break;
				} catch (MessageException e) {
					countFail++;
					if (null != e.getCode())
						msgReturn = e.getCode();
					else
						msgReturn = e.getMessage();
				}
			}

			try {
				// 发送成功
				log.setMsgId(msgId);
				log.setCountOk(countOk);
				log.setCountFail(countFail);
				if (countOk > 0)
					log.setState((short) 2);
				else {
					log.setState((short) 1);
					log.setMsgReturn(msgReturn);
				}
				msmsLogService.update(log);
			} catch (Exception e) {
				logger.error("更新SMS日志失败: " + e.getMessage());
			}
		}
	}
	
	@Async
	public void send(String group, String code, String mobile, 
			Map<String, String> data) throws monkeyException {
		this.send(group, code, mobile, null, data);
	}

}
