package com.mingsoft.weixin.event.impl;

import java.util.Map;

import com.mingsoft.weixin.event.IWeixinEventHandler;

/**
 * 
 * 铭飞科技流量推广软件
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author killfen
 * @version 100-000-000
 * 版权所有
 * Comments:接收用户地理位置，与LocationEventHandler不一样，这是需要手动发送
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class ReceiveLocationEventHandler extends IWeixinEventHandler {
	
	public Map<String, Object> execute(Map<String, Object> params) {
		init(params);
		if (null != msgType && msgType.equals(MSGTYPE_LOCATION)) {
			logger.debug("接收location");
		} 
		return null;
	}

}
