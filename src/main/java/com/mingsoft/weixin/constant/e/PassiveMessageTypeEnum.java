package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author killfen
 * Comments:回复消息类型枚举
 * Create Date:2015-2-26
 * Modification history:
 * </p>
 */
public enum PassiveMessageTypeEnum implements BaseEnum{
	/**
	 * 回复类型</br>
	 * 最终回复</br>
	 * 每个关键字只能有一个最终回复类型</br>
	 */
	FINAL(1,"最终回复"),
	/**
	 * 回复类型</br>
	 * 拓展回复类型</br>
	 */
	ADD (2,"拓展回复"),
	/**
	 * 回复模版消息</br>
	 */
	MODEL(3,"回复模版");
	
	PassiveMessageTypeEnum(int id,Object code) {
		this.id = id;
		this.code = code;
	}

	private int id;
	private Object code;
	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return id;
	}
	public String toString() {
		return code.toString();
	}

}
