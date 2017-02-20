package com.mingsoft.weixin.biz.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mingsoft.base.biz.impl.BaseBizImpl;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.weixin.biz.IMessageTemplateBiz;
import com.mingsoft.weixin.dao.IMessageTemplateDao;
import com.mingsoft.weixin.entity.MessageTemplateEntity;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author yangxy
 * Comments: 微信自定义消息模板
 * Create Date:2015-4-1
 * Modification history:
 */
@Service("messageTemplateBizImpl")
public class MessageTemplateBizImpl  extends BaseBizImpl implements IMessageTemplateBiz{
	/**
	 * 注入模板消息持久化层
	 */
	@Autowired
	private IMessageTemplateDao messageTemplateDao;
	



	@Override
	protected IBaseDao getDao() {
		// TODO Auto-generated method stub
		return messageTemplateDao;
	}
	
	/**
	 * 获取模板消息实体
	 * @param weixinId 微信ID
	 * @param modelId 模块ID
	 * @return 模板消息实体
	 */
	@Override
	public MessageTemplateEntity getByWeixinIdAndModelId(int weixinId, int modelId) {
		// TODO Auto-generated method stub
		return messageTemplateDao.getByWeixinIdAndModelId(weixinId, modelId);
	}


	@Override
	/**
	 * 查询模板消息列表
	 * @return 模板消息集合
	 */
	public List<MessageTemplateEntity> queryAllMessages() {
		// TODO Auto-generated method stub
		return messageTemplateDao.queryAllMessages();
	}


	/**
	 * 批量删除模板消息
	 * @param ids 模板消息id数组
	 */
	@Override
	public void deleteAll(String[] ids) {
		messageTemplateDao.deleteAll(ids);
	}
	
}