/**
 * 
 */
package com.mingsoft.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.weixin.entity.MessageTemplateEntity;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author yangxy
 * Comments: 微信自定义消息模板
 * Create Date:2015-4-1
 * Modification history:
 */
public interface IMessageTemplateDao extends IBaseDao{
	/**
	 * 通过微信id和模块id查询微信短信实体
	 * @param weixinId  微信ID
	 * @param modelId  模块id
	 * @return MessageTemplateEntity实体
	 */
	MessageTemplateEntity getByWeixinIdAndModelId(@Param("weixinId")int weixinId,@Param("messageTemplateModelId")int modelId);

	/**
	 * 查询所有，返回集合
	 * @return  实体集合
	 */
	List<MessageTemplateEntity> queryAllMessages();
	
	/**
	 * 批量删除短信
	 * @param ids  需要删除的模板信息ID数组
	 */
	void deleteAll(@Param("ids")String[] ids);
}