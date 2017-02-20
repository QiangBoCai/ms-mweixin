package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培 QQ:78750478
 * Comments:点击菜单后给用户推送内容的类型
 * Create Date:2015-1-19
 * Modification history:
 * * 1、文本</br> 2、图文</br> 3、语音</br> 4、外链</br>  5、应用商城</br> 6、关键词触发</br>
 */
public enum MenuStyleEnum implements BaseEnum {
	/**
	 *文本
	 */
	TEXT(1), 
	/**
	 * 图文
	 */
	PIC_ARTICLE(2), 
	/**
	 *语音
	 */
	VOICE(3), 
	/**
	 * 外链
	 */
	LINK(4),
	/**
	 * 应用商城
	 */
	APPLICATION_SHOP(5), 
	/**
	 * 关键词触发
	 */
	KEYWORD(6);
	
	MenuStyleEnum(Object code) {
		this.code = code;
	}

	private Object code;

	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return Integer.valueOf(code + "");
	}

}
