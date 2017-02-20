/**
 * 
 */
package com.mingsoft.weixin.biz;

import java.util.List;


import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.PageUtil;
import com.mingsoft.weixin.entity.OauthEntity;

/**
 * 铭飞科技-微信
 * Copyright: Copyright (c) 2014 - 2015
 * @author 成卫雄   QQ:330216230
 * Comments:微信网页2.0授权业务层接口
 * Create Date:2014-10-7
 * Modification history:
 */
public interface IOauthBiz extends IBaseBiz{
	
	
	/**
	 * 根据微信ID查找列表
	 * @param weixinId 微信ID
	 * @param page 分页
	 * @return 授权集合
	 */
	public List<OauthEntity> queryList(int appId,int weixinId,PageUtil page);
	
	
	/**
	 * 批量删除
	 * @param ids 需要删除的授权id数组
	 */
	public void deleteByIds(int[] ids);
	
	/**
	 * 根据应用ID和微信ID查找总数
	 * @param appId 应用ID
	 * @param weixinId 微信ID
	 * @return 授权总数
	 */
	public int queryCount(int appId,int weixinId);
	
}
