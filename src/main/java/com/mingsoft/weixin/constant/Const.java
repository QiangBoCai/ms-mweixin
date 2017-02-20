package com.mingsoft.weixin.constant;

import java.util.ResourceBundle;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培      QQ:78750478
 * Comments:微信模块
 * Create Date:2015-1-21
 * Modification history:
 */
public interface Const {
	public final static ResourceBundle RESOURCES = ResourceBundle.getBundle("com.mingsoft.weixin.resources.resources");
	/**
	 * 客服上传下载素材地址
	 */
	public static String CHAT_MEDIA_PATH = "/chat/upload/";

	/**
	 * 关注用户扫描二维码 生成二维码路径
	 */
	public final static String ORDER_PATH = "/qrcode/order/";
	
	/**
	 * 网页授权2.0微信APP重定向路径
	 */
	public final static String OAUTH_APP_URL = "/weixin/oauth";
	
	/**
	 * 网页授权2.0微信开放平台重定向路径
	 */
	public final static String OAUTH_OPEN_UEL = "/weixin/open/oauth";
}
