package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 铭飞科技
 * Copyright: Copyright (c) 2014 - 2015
 * @author 王天培  QQ:78750478
 * Comments:微支付方式枚举类
 * Create Date:2015-1-20
 * Modification history:
 */
public enum WeixinPayEnum implements BaseEnum{
	JSAPI("JSAPI"),
	NATIVE("NATIVE"),
	APP("APP");
	WeixinPayEnum(Object code) {
		this.code = code;
	}
	
	private Object code ;
	@Override
	public int toInt() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String toString() {
		// TODO Auto-generated method stub
		return code.toString();
	}
}
