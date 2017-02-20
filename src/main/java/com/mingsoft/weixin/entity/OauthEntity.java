/**
 * 
 */
package com.mingsoft.weixin.entity;

import com.mingsoft.base.entity.BaseEntity;

/**
 * 铭飞科技微信
 * Copyright: Copyright (c) 2014 - 2015
 * @author 成卫雄   QQ:330216230
 * Comments:网页授权2.0实体
 * Create Date:2014-10-7
 * Modification history:
 */
public class OauthEntity extends BaseEntity {

	/**
	 * 自增长ID
	 */
	private int oauthId;
	
	/**
	 * 微信基础信息Id
	 */
	private int oauthWeixinId;
	
	/**
	 * 关联微信应用ID
	 */
	private int oauthAppId;
	
	/**'
	 * 授权成功重定向地址
	 */
	private String oauthSuccessUrl;
	
	/**
	 * 授权失败或者错误重定向地址
	 */
	private String oauthErrorUrl;
	
	/**
	 * 授权类型
	 * 1.弹出授权界面可以获取到用户的详细信息
	 * 2.不弹出授权界面只能获取到用户的openId
	 */
	private int oauthType;
	
	/**
	 * 授权描述
	 */
	private String oauthDescription;

	public int getOauthWeixinId() {
		return oauthWeixinId;
	}

	public void setOauthWeixinId(int oauthWeixinId) {
		this.oauthWeixinId = oauthWeixinId;
	}

	/**
	 * 获取oauthId
	 * @return  oauthId
	 */
	public int getOauthId() {
		return oauthId;
	}

	/**
	 * 设置oauthId
	 * @param oauthId
	 */
	public void setOauthId(int oauthId) {
		this.oauthId = oauthId;
	}

	/**
	 * 获取oauthAppId
	 * @return  oauthAppId
	 */
	public int getOauthAppId() {
		return oauthAppId;
	}

	/**
	 * 设置oauthAppId
	 * @param oauthAppId
	 */
	public void setOauthAppId(int oauthAppId) {
		this.oauthAppId = oauthAppId;
	}

	/**
	 * 获取oauthSuccessUrl
	 * @return  oauthSuccessUrl
	 */
	public String getOauthSuccessUrl() {
		return oauthSuccessUrl;
	}

	/**
	 * 设置oauthSuccessUrl
	 * @param oauthSuccessUrl
	 */
	public void setOauthSuccessUrl(String oauthSuccessUrl) {
		this.oauthSuccessUrl = oauthSuccessUrl;
	}

	/**
	 * 获取oauthErrorUrl
	 * @return  oauthErrorUrl
	 */
	public String getOauthErrorUrl() {
		return oauthErrorUrl;
	}

	/**
	 * 设置oauthErrorUrl
	 * @param oauthErrorUrl
	 */
	public void setOauthErrorUrl(String oauthErrorUrl) {
		this.oauthErrorUrl = oauthErrorUrl;
	}

	/**
	 * 获取oauthType
	 * @return  oauthType
	 */
	public int getOauthType() {
		return oauthType;
	}

	/**
	 * 设置oauthType
	 * @param oauthType
	 */
	public void setOauthType(int oauthType) {
		this.oauthType = oauthType;
	}

	/**
	 * 获取oauthDescription
	 * @return  oauthDescription
	 */
	public String getOauthDescription() {
		return oauthDescription;
	}

	/**
	 * 设置oauthDescription
	 * @param oauthDescription
	 */
	public void setOauthDescription(String oauthDescription) {
		this.oauthDescription = oauthDescription;
	}
	
}
