package com.mingsoft.weixin.entity;

import com.mingsoft.base.entity.BaseEntity;

 /**
 * 微信网页2.0授权表实体
 * @author 铭飞开发团队
 * @version 
 * 版本号：1.0.0<br/>
 * 创建日期：2017-5-24 22:47:11<br/>
 * 历史修订：<br/>
 */
public class WxOauthEntity extends BaseEntity {

	private static final long serialVersionUID = 1495637231532L;
	
	
	/**
	 * 
	 */
	private Integer oauthWeixinId; 
	/**
	 * 子增长ID
	 */
	private Integer oauthId; 
	/**
	 * 关联微信应用ID
	 */
	private Integer oauthAppId; 
	/**
	 * 授权成功重定向地址
	 */
	private String oauthSuccessUrl; 
	/**
	 * 授权失败者或错误重定向地址
	 */
	private String oauthErrorUrl; 
	/**
	 * 授权描述
	 */
	private String oauthDescription; 
	/**
	 * 授权类型。1.弹出授权界面 2.不弹出授权界面
	 */
	private Integer oauthType; 
	
	/**
	 * 设置
	 */
	public void setOauthWeixinId(Integer oauthWeixinId) {
		this.oauthWeixinId = oauthWeixinId;
	}

	/**
	 * 获取
	 */
	public Integer getOauthWeixinId() {
		return this.oauthWeixinId;
	}
	
	/**
	 * 设置子增长ID
	 */
	public void setOauthId(Integer oauthId) {
		this.oauthId = oauthId;
	}

	/**
	 * 获取子增长ID
	 */
	public Integer getOauthId() {
		return this.oauthId;
	}
	
	/**
	 * 设置关联微信应用ID
	 */
	public void setOauthAppId(Integer oauthAppId) {
		this.oauthAppId = oauthAppId;
	}

	/**
	 * 获取关联微信应用ID
	 */
	public Integer getOauthAppId() {
		return this.oauthAppId;
	}
	
	/**
	 * 设置授权成功重定向地址
	 */
	public void setOauthSuccessUrl(String oauthSuccessUrl) {
		this.oauthSuccessUrl = oauthSuccessUrl;
	}

	/**
	 * 获取授权成功重定向地址
	 */
	public String getOauthSuccessUrl() {
		return this.oauthSuccessUrl;
	}
	
	/**
	 * 设置授权失败者或错误重定向地址
	 */
	public void setOauthErrorUrl(String oauthErrorUrl) {
		this.oauthErrorUrl = oauthErrorUrl;
	}

	/**
	 * 获取授权失败者或错误重定向地址
	 */
	public String getOauthErrorUrl() {
		return this.oauthErrorUrl;
	}
	
	/**
	 * 设置授权描述
	 */
	public void setOauthDescription(String oauthDescription) {
		this.oauthDescription = oauthDescription;
	}

	/**
	 * 获取授权描述
	 */
	public String getOauthDescription() {
		return this.oauthDescription;
	}
	
	/**
	 * 设置授权类型。1.弹出授权界面 2.不弹出授权界面
	 */
	public void setOauthType(Integer oauthType) {
		this.oauthType = oauthType;
	}

	/**
	 * 获取授权类型。1.弹出授权界面 2.不弹出授权界面
	 */
	public Integer getOauthType() {
		return this.oauthType;
	}
	
}