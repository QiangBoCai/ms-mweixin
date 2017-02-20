package com.mingsoft.weixin.event.impl;
import java.util.ArrayList;
import java.util.List;
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
 * Comments:接收文本处理
 * Create Date:2014-3-11
 * Modification history:暂无
 */
public class ReceiveTextEventHandler extends IWeixinEventHandler {
	
	public Map<String, Object> execute(Map<String, Object> params) {
		init(params);
		if (null != msgType && msgType.equals(MSGTYPE_TEXT)) {
			String content = XmlUtils.getString(originalWeixinMsg, XML_MSG_CONTENT);
			logger.debug("--执行"+MSGTYPE_TEXT+"事件--接受到的文本信息:"+content);
			returnMap.put("content", getContent(content));
			returnMap.put("type", true);
			return returnMap;
		} 
		return null;
	}
	
	/**
	 * 获取返回消息，如果存在关键字就直接返回关键字绑定消息
	 * @param content　原始内容
	 * @return　
	 */
	private  List<String> getContent(String content) {
		List list = this.sendPassiveMessage(content,MSGTYPE_TEXT);
		if(list!=null) { 
			return list;
		} else {
			list = new ArrayList();
			list.add(XmlUtils.buildDuoKeFu(this.fromUserName,this.toUserName));
		}
			
		return list;
	}
}
