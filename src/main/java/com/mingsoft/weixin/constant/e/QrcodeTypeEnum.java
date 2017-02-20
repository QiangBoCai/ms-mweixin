package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/** 
 * 微信二维码类型枚举类
 * @author  付琛  QQ:1658879747 
 * @version 1.0 
 * 创建时间：2015年11月13日 下午4:17:50  
 * 版本号：100-000-000<br/>
 * 历史修订<br/>
 */
public enum QrcodeTypeEnum implements BaseEnum {
	/**
	 * 永久
	 */
	PERMANENT(0,"永久"),
	
	/**
	 * 临时
	 */
	OCASSION(1,"临时");
	
	QrcodeTypeEnum(int id,Object code){
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
