package com.mingsoft.weixin.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 铭飞科技流量推广软件
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author killfen
 * @version 100-000-000
 * 版权所有
 * Comments:微信事件责任链处理类
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class WeixinEventChain extends IWeixinEventHandler {
	List<IWeixinEventHandler> weixinEventHandlers = new ArrayList<IWeixinEventHandler>();

	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		for (IWeixinEventHandler weixinEvent:weixinEventHandlers) {
			Map<String, Object> map = weixinEvent.execute(params);
			if (map!=null) {
				return map;
			}
		}  
		return null;
	}
	
	public List<IWeixinEventHandler> getWeixinEventHandlers() {
		return weixinEventHandlers;
	}

	public void setWeixinEventHandlers(List<IWeixinEventHandler> weixinEventHandlers) {
		this.weixinEventHandlers = weixinEventHandlers;
	}
	
}
