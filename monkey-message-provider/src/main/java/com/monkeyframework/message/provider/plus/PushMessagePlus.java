package com.monkeyframework.message.provider.plus;

import com.monkeyframework.message.model.PushMessage;
import com.monkeyframework.message.provider.utils.bean.PushResponse;

public interface PushMessagePlus extends MessagePlus {
	
	public PushResponse doPush(PushMessage message);

}
