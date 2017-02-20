/**
 * 
 */
package com.mingsoft.weixin.event.impl;

import java.util.Map;

import com.mingsoft.weixin.event.IWeixinEventHandler;

/**
 * 
 * 已关注用户扫描带参数的二维码
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月26日 下午7:16:20<br/>
 * 历史修订：<br/>
 */
public class QrcodeSubscribeEventHandler extends IWeixinEventHandler{

	@Override
	public Map<String, Object> execute(Map<String, Object> params) {
		// TODO Auto-generated method stub
		init(params);
		
		//判断事件类型
		if(msgType != null && msgType.equals(EVENT) && event.equals(SUBSCRIBED_SCAN) && ticket!=null){
			logger.debug("已关注用户,带参数二维码事件,二维码中的值:"+eventKey);
			returnMap.put("content",this.sendPassiveMessage(eventKey,SUBSCRIBED_SCAN));
			returnMap.put("type", true);
			return returnMap;			
		}
		return null;
	}
	
}
