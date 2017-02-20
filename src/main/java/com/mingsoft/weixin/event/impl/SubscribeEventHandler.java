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
 * Comments:用户关注</br>
 * 通过点击关注</br>
 * 扫描关注二维码关注</br>
 * 搜索帐号关注</br>
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class SubscribeEventHandler extends IWeixinEventHandler {

	public Map<String, Object> execute(Map<String, Object> params) {
		// TODO Auto-generated method stub
		init(params);
		if ( msgType != null && msgType.equals(EVENT) && event.equals(EVENT_SUBSCRIBE) && eventKey.indexOf("qrscene_")<0) {
			this.updatePeople();
			returnMap.put("content",this.sendPassiveMessage(null,EVENT_SUBSCRIBE));
			returnMap.put("type", true);
			return returnMap;
		}
		return null;
	}
	
}
