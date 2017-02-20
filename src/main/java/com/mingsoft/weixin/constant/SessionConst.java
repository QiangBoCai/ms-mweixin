package com.mingsoft.weixin.constant;

/**
 * 微信系统中的session枚举类
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月30日 下午4:44:20<br/>
 * 历史修订：<br/>
 */
public enum SessionConst{
	
	WEIXIN_SESSION("weixin_session");
	
	/**
	 * 设置session常量
	 * @paran attr 常量
	 */
	SessionConst(String attr){
		this.attr = attr;
	}
	
	private String attr;
	
	/**
	 *  返回SessionConst常量的字符串表示
	 *  @return 字符串
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return attr;
	}
	
}
