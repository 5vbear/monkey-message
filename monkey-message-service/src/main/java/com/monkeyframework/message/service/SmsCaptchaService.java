package com.monkeyframework.message.service;

import com.monkeyframework.exception.monkeyException;

public interface SmsCaptchaService {

	/**
	 * 验证码验证
	 */
	public void validateCaptcha(String group, String code, String mobile, String captcha) throws monkeyException;

	/**
	 * 发送验证码
	 */
	public String sendCaptcha(String group, String code, String mobile) throws monkeyException;

}
