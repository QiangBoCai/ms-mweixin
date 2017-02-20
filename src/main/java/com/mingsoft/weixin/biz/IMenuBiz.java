package com.mingsoft.weixin.biz;

import java.util.List;
import com.mingsoft.base.biz.IBaseBiz;
import com.mingsoft.weixin.entity.MenuEntity;

/**
 * 
 * 微信底部菜单业务层接口|继承：IBaseBiz
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月26日 上午11:00:28<br/>
 * 历史修订：<br/>
 */
public interface IMenuBiz extends IBaseBiz{

	/**
	 * 根据微信Id查询菜单集合
	 * @param appId 应用编号
	 * @param weixinId 微信ID
	 * @return　菜单集合
	 */
	public List<MenuEntity> queryListById(int appId,int weixinId);
	
	/**
	 * 根据父菜单ID查询菜单列表
	 * @param appId 应用编号
	 * @param weixinId 微信编号
	 * @param menuMenuId 父菜单ID
	 * @return 菜单集合
	 */
	public List<MenuEntity> queryByParentId(int appId,int weixinId,Integer menuMenuId);
	
	
	/**
	 * 根据素材ID对应的url是否在存在
	 * @param menuUrl 素材ID(对应的menuUrl值相等)
	 * @param appId 应用编号
	 * @param weixinId 微信ID
	 * @return 菜单列表
	 */
	public List<MenuEntity> queryListByMenuUrl(String menuUrl,int appId,int weixinId);
	
	/**
	 * 更新菜单的外连接
	 * @param menu 菜单实体
	 * @param sourceUrl 外连接地址
	 */
	public void updateOrCreateLink(MenuEntity menu,String sourceUrl);
	
	/**
	 * 更新or保存文本菜单
	 * @param menu ：菜单实体
	 * @param replyText：要显示的文本内容
	 * @param appId：应用编号
	 */
	public void  updateOrCreateText(MenuEntity menu,String replyText,int appId);
	
	/**
	 * 更新或创建图文信息菜单
	 * @param menu：菜单实体
	 * @param picId：图片ID
	 * @param appId：应用编号
	 */
	public void updateOrCreatePic(MenuEntity menu,String picId,int appId);
	
	/**
	 * 更新或创建关键字菜单
	 * @param menu ：菜单实体
	 * @param passiveMessageId ：被动回复关联id
	 * @param keyword 关键字
	 */
	public void updateOrCreateKeyword(MenuEntity menu,String passiveMessageId,String keyword);
	
	/**
	 * 查询所有菜单，并按照父子的格式排列好</br>
	 * @param appId 系统应用ID
	 * @param weixinId 关联系统中微信的唯一ID
	 * @return 菜单列表
	 */
	public List<MenuEntity> queryMenu(int appId,int weixinId);
	
	/**
	 * 查询指定菜单的子菜单列表
	 * @param appId 应用id
	 * @param weixinId 关联系统中微信的唯一ID
	 * @param menuMenuId 菜单父id
	 * @return 菜单列表
	 */
	public List<MenuEntity> queryByMenumenuId(int appId,int weixinId,Integer menuMenuId);
	
}
