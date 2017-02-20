package com.mingsoft.weixin.biz;
import java.util.List;


import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.util.PageUtil;
import com.mingsoft.weixin.entity.WeixinEntity;

/**
 * 
 * 微信公众帐号基础信息业务层接口
 * @author 付琛  QQ1658879747
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年12月4日
 * 历史修订：<br/>
 */
public interface IWeixinBiz extends IBaseBiz {
	
	/**
	 * 根据公众号原始id和微信ID获取的微信实体
	 * @param weixinOriginId 微信原始id
	 * @param weixinId 该微信号对应的微信ID
	 * @return 微信实体
	 */
	public WeixinEntity getWeixinEntityByWeixinOriginIdAndWeixinId(String weixinOriginId,int weixinId);
	
	
	/**
	 * 根据微信ID查询微信基础信息
	 * @param weixinId 自增长Id
	 * @return 微信基础信息
	 */
	public WeixinEntity getEntityById(int weixinId);
	
	/**
	 * 查询微信列表
	 * @param appId 应用编号
	 * @param page 分页条件
	 * @return 微信实体集合
	 */
	public List<WeixinEntity> queryAllByAppId(int appId,PageUtil page);

	/**
	 * 根据应用ID查询该应用下的微信数量
	 * @param appId 应用ID
	 * @return 微信数量
	 */
	public int getCountByAppId(int appId);
	
	/**
	 * 根据微信ID批量删除
	 * @param ids weixinID集合
	 */
	public void deleteByIds(int[] ids);

}
