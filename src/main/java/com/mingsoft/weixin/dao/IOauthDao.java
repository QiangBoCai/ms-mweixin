package com.mingsoft.weixin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.util.PageUtil;
import com.mingsoft.weixin.entity.OauthEntity;

/**
 * 
 * 微信网页2.0授权持久化接口
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月19日 下午2:50:39<br/>
 * 历史修订：<br/>
 */
public interface IOauthDao extends IBaseDao{
	
	
	/**
	 * 根据微信ID查找授权列表
	 * @param appId 应用编号
	 * @param weixinId 微信ID
	 * @param page 分页参数
	 * @return 授权列表
	 */
	public List<OauthEntity> queryList(@Param("appId")int appId,@Param("weixinId")int weixinId,@Param("page")PageUtil page);

	
	/**
	 * 根据应用ID和微信ID查找列表总数
	 * @param appId 应用ID
	 * @param weixinID 微信ID
	 * @return 授权总数
	 */
	public int queryCount(@Param("appId")int appId,@Param("weixinId")int weixinId);
	
	/**
	 * 批量删除
	 * @param ids 需要删除的授权ID数组
	 */
	public void deleteByIds(@Param("ids") int[] ids);
}
