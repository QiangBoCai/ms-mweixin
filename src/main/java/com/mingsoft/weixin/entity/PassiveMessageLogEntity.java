/**
 * 
 */
package com.mingsoft.weixin.entity;

import java.util.Date;

import com.mingsoft.base.entity.BaseEntity;

/**
 * 铭飞科技-微信
 * Copyright: Copyright (c) 2014 - 2015
 * @author 成卫雄   QQ:330216230
 * Comments:被动回复消息日志
 * Create Date:2014-10-12
 * Modification history:
 */
public class PassiveMessageLogEntity extends BaseEntity{
 
	/**
	 * 自增长ID
	 */
	private int passiveMessageLogId;
	
	/**
	 * 关联日志所属应用ID
	 */
	private int passiveMessageLogAppId;
	
	/**
	 * 关联微信自增长ID
	 */
	private int passiveMessageLogWeixinId;
	
	/**
	 * 关联用户Id
	 */
	private int passiveMessageLogPeopleId;
	
	/**
	 * 关联被动消息回复Id
	 */
	private int passiveMessageId;
	
	/**
	 * 该回复属于分类中的事件ID
	 */
	private int passiveMessageLogEventId;
	
	/**
	 * 日志生成时间
	 */
	private Date passiveMessageLogTime = new Date();
	
	/**
	 * 回复关键字,不支持正则
	 */
	private String passiveMessageLogKey;
	
	public int getPassiveMessageLogWeixinId() {
		return passiveMessageLogWeixinId;
	}

	public void setPassiveMessageLogWeixinId(int passiveMessageLogWeixinId) {
		this.passiveMessageLogWeixinId = passiveMessageLogWeixinId;
	}

	/**
	 * 获取passiveMessageLogKey
	 * @return  passiveMessageLogKey
	 */
	public String getPassiveMessageLogKey() {
		return passiveMessageLogKey;
	}

	/**
	 * 设置passiveMessageLogKey
	 * @param passiveMessageLogKey
	 */
	public void setPassiveMessageLogKey(String passiveMessageLogKey) {
		this.passiveMessageLogKey = passiveMessageLogKey;
	}

	/**
	 * 获取passiveMessageLogId
	 * @return  passiveMessageLogId
	 */
	public int getPassiveMessageLogId() {
		return passiveMessageLogId;
	}

	/**
	 * 设置passiveMessageLogId
	 * @param passiveMessageLogId
	 */
	public void setPassiveMessageLogId(int passiveMessageLogId) {
		this.passiveMessageLogId = passiveMessageLogId;
	}

	/**
	 * 获取passiveMessageLogAppId
	 * @return  passiveMessageLogAppId
	 */
	public int getPassiveMessageLogAppId() {
		return passiveMessageLogAppId;
	}

	/**
	 * 设置passiveMessageLogAppId
	 * @param passiveMessageLogAppId
	 */
	public void setPassiveMessageLogAppId(int passiveMessageLogAppId) {
		this.passiveMessageLogAppId = passiveMessageLogAppId;
	}

	/**
	 * 获取passiveMessageLogPeopleId
	 * @return  passiveMessageLogPeopleId
	 */
	public int getPassiveMessageLogPeopleId() {
		return passiveMessageLogPeopleId;
	}

	/**
	 * 设置passiveMessageLogPeopleId
	 * @param passiveMessageLogPeopleId
	 */
	public void setPassiveMessageLogPeopleId(int passiveMessageLogPeopleId) {
		this.passiveMessageLogPeopleId = passiveMessageLogPeopleId;
	}

	/**
	 * 获取passiveMessageId
	 * @return  passiveMessageId
	 */
	public int getPassiveMessageId() {
		return passiveMessageId;
	}

	/**
	 * 设置passiveMessageId
	 * @param passiveMessageId
	 */
	public void setPassiveMessageId(int passiveMessageId) {
		this.passiveMessageId = passiveMessageId;
	}

	/**
	 * 获取passiveMessageLogEventId
	 * @return  passiveMessageLogEventId
	 */
	public int getPassiveMessageLogEventId() {
		return passiveMessageLogEventId;
	}

	/**
	 * 设置passiveMessageLogEventId
	 * @param passiveMessageLogEventId
	 */
	public void setPassiveMessageLogEventId(int passiveMessageLogEventId) {
		this.passiveMessageLogEventId = passiveMessageLogEventId;
	}

	/**
	 * 获取passiveMessageLogTime
	 * @return  passiveMessageLogTime
	 */
	public Date getPassiveMessageLogTime() {
		return passiveMessageLogTime;
	}

	/**
	 * 设置passiveMessageLogTime
	 * @param passiveMessageLogTime
	 */
	public void setPassiveMessageLogTime(Date passiveMessageLogTime) {
		this.passiveMessageLogTime = passiveMessageLogTime;
	}

}
