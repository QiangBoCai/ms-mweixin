package com.mingsoft.weixin.constant.e;

import com.mingsoft.base.constant.e.BaseEnum;

/**
 * 
 * 类的描述  微信类型的枚举类
 * @author fuchen
 * @version 
 * 项目名：ms-mweixin
 * 创建日期：:2015年10月8日
 * 历史修订：2015年10月8日
 */
public enum WeixinTypeEnum implements BaseEnum {
	
		/**
		 * 服务号
		 */
		WEIXIN_SERVIECE(0,"服务号"), 
		/**
		 * 订阅号
		 */
		WEIXIN_Subscribe(1,"订阅号"), 
		/**
		 * 微信开发平台
		 */
		WEIXIN_OPEN(2,"微信开放平台");
		
		WeixinTypeEnum(int id,Object code) {
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
