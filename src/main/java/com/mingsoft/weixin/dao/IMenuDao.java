package com.mingsoft.weixin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.mingsoft.base.dao.IBaseDao;
import com.mingsoft.weixin.entity.MenuEntity;

/**
 * 
 * 微信底部菜单持久化层接口|继承:IBaseDao
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月26日 上午11:01:00<br/>
 * 历史修订：<br/>
 */
public interface IMenuDao extends IBaseDao{

	/**
	 * 查询菜单列表
	 * @param appId 应用ID
	 * @param weixinId 关联微信的唯一ID
	 * @param menuMenuId 父菜单ID
	 * @return 菜单列表
	 */
	public List<MenuEntity> queryList(@Param("appId")int appId,@Param("weixinId")int weixinId,@Param("menuMenuId")Integer menuMenuId);
	
	/**
	 * 根据素材ID对应的url查询菜单列表
	 * @param menuUrl 菜单链接地址
	 * @param appId 应用编号
	 * @param weixinId 微信ID
	 * @return 菜单列表
	 */
	public List<MenuEntity> queryListByMenuUrl(@Param("menuUrl")String menuUrl,@Param("appId")int appId,@Param("weixinId")int weixinId);

}
