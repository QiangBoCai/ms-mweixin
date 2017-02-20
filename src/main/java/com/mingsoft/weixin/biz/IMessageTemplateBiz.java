package com.mingsoft.weixin.biz;

import java.util.List;

import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.weixin.entity.MessageTemplateEntity;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author yangxy
 * Comments: 微信自定义消息模板
 * Create Date:2015-5-22
 * Modification history:
 */
public interface IMessageTemplateBiz extends IBaseBiz{
	
	/**
	 * 根据应用编号与模块编号获取自定义消息模板
	 * @param weixinId 微信编号
	 * @param modelId 模块编号
	 * @return 模板消息实体
	 */
	MessageTemplateEntity getByWeixinIdAndModelId(int weixinId,int modelId);
	
	/**
	 * 查询微信模板消息列表
	 * @return 实体列表
	 */
	List<MessageTemplateEntity> queryAllMessages();
	
	/**
	 * 多选删除
	 * @param ids 前端传来的勾选的checkbox勾选的id序列化
	 */
	void deleteAll(String[] ids);
}