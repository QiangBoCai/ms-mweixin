package com.mingsoft.weixin.biz;

import com.mingsoft.base.biz.IBaseBiz;

/**
 * 
 * 被动消息回复日志业务层接口
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月26日 上午9:37:51<br/>
 * 历史修订：<br/>
 */
public interface IPassiveMessageLogBiz extends IBaseBiz{

	/**
	 * 根据用户ID,事件ID,应用ID</br>
	 * 查询该用户在当前事件下响应消息的条数
	 * @param weixinId 微信Id
	 * @param peopleId 用户ID
	 * @param eventId 事件ID
	 * @param appId 应用ID
	 * @param key 回复关键字
	 * @return 日志条数
	 */
	public int getCountBySendMessage(int weixinId,int peopleId,int eventId,int appId,String key);
	
}
