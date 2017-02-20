package com.mingsoft.weixin.event.impl;

import java.util.Map;

import com.mingsoft.weixin.entity.WeixinPeopleEntity;
import com.mingsoft.weixin.event.IWeixinEventHandler;

/**
 * 铭飞科技流量推广软件
 * Copyright: Copyright (c) 2013 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author killfen
 * @version 100-000-000
 * 版权所有
 * Comments:用户取消关注
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class UnSubscribeEventHandler extends IWeixinEventHandler {
	
	public Map<String, Object> execute(Map<String, Object> params) {
		init(params);
		if (null != msgType && msgType.equals(EVENT) && event.equals(EVENT_UNSUBSCRIBE)) {
			updatePeopleState();
			logger.debug("执行取消关注");
		} 
		return null;
	}

	/**
	 * 改变用户的关注状态
	 */
	private void updatePeopleState(){
		WeixinPeopleEntity weixinPeople = new WeixinPeopleEntity();
		weixinPeople.setWeixinPeopleOpenId(this.fromUserName);
		weixinPeople.setWeixinPeopleState(WeixinPeopleEntity.WEIXIN_PEOPLE_CANCEL_WATCH);
		this.weixinPeopleBiz.updatePeople(weixinPeople);
	}
}
