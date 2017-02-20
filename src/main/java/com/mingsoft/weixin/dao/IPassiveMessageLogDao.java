package com.mingsoft.weixin.dao;
import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;

/**
 * 铭飞科技-微信
 * Copyright: Copyright (c) 2014 - 2015
 * @author 成卫雄  QQ:330216230
 * Comments:被动关注回复日志持久化层接口
 * Create Date:2014-10-12
 * Modification history:
 */
public interface IPassiveMessageLogDao extends IBaseDao{
	
	/**
	 * 根据自定义字段查询日志数量
	 * @param appId 应用编号
	 * @param weixinId 微信自增长ID
	 * @param passiveMessageLogPeopleId 用户ID
	 * @param passiveMessageLogKey 关键字
	 * @param passiveMessageLogEventId 事件类型
	 * @return
	 */
	public int getCountByCustom(@Param("appId")Integer appId,@Param("weixinId")Integer weixinId,@Param("passiveMessageLogPeopleId")Integer passiveMessageLogPeopleId,@Param("passiveMessageLogKey")String passiveMessageLogKey,@Param("passiveMessageLogEventId")Integer passiveMessageLogEventId);
	
}
