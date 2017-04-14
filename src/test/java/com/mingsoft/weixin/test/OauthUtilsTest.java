/**
The MIT License (MIT) * Copyright (c) 2017 铭飞科技

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */package com.mingsoft.weixin.test;


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
