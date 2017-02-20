package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 史爱华
 * Comments:消息回复类型
 * Create Date:2015-1-19
 * Modification history:
 * *0、单图文 </br>  1、多图文</br> 2、文本</br> 3、图片</br>
 */
public enum NewsTypeEnum implements BaseEnum {
	/**
	 *单图文
	 */
	SINGLE_NEWS(0,"单图文"), 
	/**
	 * 多图文
	 */
	NEWS(1,"多图文"), 
	/**
	 *文本
	 */
	TEXT(2,"文本"), 
	/**
	 * 图片
	 */
	IMAGE(3,"图片");
	NewsTypeEnum(int id,Object code) {
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