package com.mingsoft.weixin.event.impl;

import java.util.Map;

import com.mingsoft.weixin.event.IWeixinEventHandler;
import com.mingsoft.weixin.util.XmlUtils;

/**
 * 铭飞科技流量推广软件
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author killfen
 * @version 100-000-000
 * 版权所有
 * Comments:接收图片处理
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class ReceiveImageEventHandler extends IWeixinEventHandler {
	
	public Map<String, Object> execute(Map<String, Object> params) {
		init(params);
		if (null != msgType && msgType.equals(MSGTYPE_IMAGE)) {
			logger.debug("执行image");
			//图片ID
			String mediaId = XmlUtils.getString(originalWeixinMsg, XML_MEDIAID);
			//图片地址
			String url = XmlUtils.getString(originalWeixinMsg, XML_MSG_PICURL);
			logger.debug("--接收到的图片ID:--"+mediaId);
			logger.debug("--接收到的图片地址:--"+url);
		} 
		return null;
	}

}
