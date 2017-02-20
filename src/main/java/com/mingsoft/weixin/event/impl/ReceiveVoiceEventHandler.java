package com.mingsoft.weixin.event.impl;

import java.util.Map;

import com.mingsoft.weixin.event.IWeixinEventHandler;

/**
 * 铭飞科技流量推广软件
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author killfen
 * @version 100-000-000
 * 版权所有
 * Comments:接收音频处理
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class ReceiveVoiceEventHandler extends IWeixinEventHandler {
	
	public Map<String, Object> execute(Map<String, Object> params) {
		init(params);
		if (null != msgType && msgType.equals(MSGTYPE_VOICE)) {
			logger.debug("执行Voice");
		} 
		return null;
	}

}
