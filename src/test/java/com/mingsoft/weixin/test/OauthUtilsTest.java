package com.mingsoft.weixin.test;


import org.junit.Test;

import com.mingsoft.base.test.BaseTest;
import com.mingsoft.weixin.util.OauthUtils;

/**
 * 
 * 网页授权2.0工具类测试
 * @author 成卫雄(qq:330216230)
 * @version 
 * 版本号：100-000-000<br/>
 * 创建日期：2015年8月28日 下午3:58:15<br/>
 * 历史修订：<br/>
 */
public class OauthUtilsTest extends BaseTest{

	/**
	 * 拼装授权地址测试
	 */
	@Test
	public void getCodeUrl(){
		String url = OauthUtils.getCodeUrl("wx287fd9ae5692d9b2","http://wx.i-weixiao.com",true,"1");
		LOG.debug(url);
	}
	
}
