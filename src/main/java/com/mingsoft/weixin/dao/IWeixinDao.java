/**
 * 
 */
package com.mingsoft.weixin.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;
import com.mingsoft.weixin.entity.WeixinEntity;

/**
 * 
 * Copyright: Copyright (c) 2014 - 2015
 * Company:景德镇铭飞科技有限公司
 * @author 石超
 * @version 300-001-001
 * 版权所有 铭飞科技
 * Comments: 微信公众帐号基础信息持久化层接口
 * Create Date:2013-12-23
 * Modification history:
 */
public interface IWeixinDao extends IBaseDao {

	/**
	 * 查询微信基础信息
	 * @param appId 应用编号
	 * @param weixinId 微信ID
	 * @param weixinOriginId 微信原始ID
	 * @return 查询到的微信实体
	 */
	public WeixinEntity getEntity(@Param("appId")Integer appId,@Param("weixinId")int weixinId,@Param("weixinOriginId") String weixinOriginId);
	
	/**
	 * 查询微信实体带分页
	 * @param appId 应用编号
	 * @param page 分页条件
	 * @return 微信实体集合
	 */
	public List<WeixinEntity> queryList(@Param("appId")int appId,@Param("page")PageUtil page);
	
	/**
	 * 根据应用ID查询该应用下的微信数量
	 * @param appId 应用ID
	 * @return 微信数量
	 */
	public int getCountByAppId(@Param("appId")int appId);
	
	/**
	 * 根据微信ID集合批量删除微信
	 * @param ids 微信ID集合
	 */
	public void deleteByIds(@Param("ids")int[] ids);

}
