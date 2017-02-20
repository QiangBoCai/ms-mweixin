package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 网页2.0授权登录类型
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年9月24日 下午5:28:32<br/>
 * 历史修订：<br/>
 */
public enum OauthTypeEnum implements BaseEnum{

	/**
	 * 此授权可获取到用户的详细信息
	 */
	SCOPE_USERINFO(1,"弹出授权界面"),
	
	/**
	 * 此授权只能获取到用的openId
	 */
	SCOPE_BASE(2,"不弹出授权界面");
	
	OauthTypeEnum(int id,Object code) {
		this.id = id;
		this.code = code;
	}
	
	private Object code;
	
	private int id;

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return id;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return code.toString();
	}
}
